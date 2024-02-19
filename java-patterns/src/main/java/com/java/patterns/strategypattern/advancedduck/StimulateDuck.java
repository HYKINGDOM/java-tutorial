package com.java.patterns.strategypattern.advancedduck;

import com.java.patterns.strategypattern.advancedduck.ducks.PlasticBaseDuck;
import com.java.patterns.strategypattern.advancedduck.ducks.BaseDuck;
import com.java.patterns.strategypattern.advancedduck.ducks.GreenHeadBaseDuck;
import com.java.patterns.strategypattern.advancedduck.ducks.RedHeadBaseDuck;
import com.java.patterns.strategypattern.advancedduck.ducks.StoneBaseDuck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 基于策略模式的实践
 *
 * @author Administrator
 */
public class StimulateDuck {

    private static final Map<String, Function<BaseDuck, BaseDuck>> FUNCTION_HASH_MAP = new HashMap<>();

    static {
        FUNCTION_HASH_MAP.put("GreenHeadBaseDuck", e -> new GreenHeadBaseDuck());
        FUNCTION_HASH_MAP.put("RedHeadBaseDuck", e -> new RedHeadBaseDuck());
        FUNCTION_HASH_MAP.put("StoneBaseDuck", e -> new StoneBaseDuck());
        FUNCTION_HASH_MAP.put("PlasticBaseDuck", e -> new PlasticBaseDuck());
    }

    public static void main(String[] args) {
        BaseDuck mGreenHeadBaseDuck = new GreenHeadBaseDuck();
        BaseDuck mRedHeadBaseDuck = new RedHeadBaseDuck();
        BaseDuck stoneBaseDuck = new StoneBaseDuck();
        BaseDuck plasticBaseDuck = new PlasticBaseDuck();

        List<BaseDuck> baseDucks = new ArrayList<>();
        baseDucks.add(plasticBaseDuck);
        baseDucks.add(stoneBaseDuck);
        baseDucks.add(mRedHeadBaseDuck);
        baseDucks.add(mGreenHeadBaseDuck);

        for (BaseDuck baseDuck : baseDucks) {
            BaseDuck redHeadBaseDuck = FUNCTION_HASH_MAP.get(baseDuck.getDuckName()).apply(baseDuck);
            redHeadBaseDuck.display();
            redHeadBaseDuck.fly();
            redHeadBaseDuck.quack();
            redHeadBaseDuck.swim();
            System.out.println("=========================");
        }

        //
        //
        //
        //        mGreenHeadBaseDuck.display();
        //        mGreenHeadBaseDuck.fly();
        //        mGreenHeadBaseDuck.quack();
        //        mGreenHeadBaseDuck.swim();
        //        System.out.println("=========================");
        //
        //        mRedHeadBaseDuck.display();
        //        mRedHeadBaseDuck.fly();
        //        mRedHeadBaseDuck.quack();
        //        mRedHeadBaseDuck.swim();
        //        System.out.println("======修改红头鸭属性======");
        //        mRedHeadBaseDuck.display();
        //        mRedHeadBaseDuck.setFlyBehavior(new NoFlyBehavior());
        //        mRedHeadBaseDuck.fly();
        //        mRedHeadBaseDuck.setQuackBehavior(new NoQuackBehavior());
        //        mRedHeadBaseDuck.quack();
        //        mRedHeadBaseDuck.setmSwimBehavior(new NoSwimBehavior());
        //
        //        System.out.println("=========================");
        //        stoneBaseDuck.display();
        //        stoneBaseDuck.fly();
        //        stoneBaseDuck.quack();
        //        stoneBaseDuck.swim();
        //
        //        System.out.println("=========================");
        //        plasticBaseDuck.display();
        //        plasticBaseDuck.fly();
        //        plasticBaseDuck.quack();
        //        plasticBaseDuck.swim();

    }

}
