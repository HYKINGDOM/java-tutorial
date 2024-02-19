package com.java.patterns.ChainofResponsibility;

/**
 * 用『责任链模式』来优化 参数多重校验，非常优雅！
 *
 * 之前在做需求，写一个方法，先在前面做验证， if不满足A条件则return， if不满足B条件则return... 一共写了5个验证，等验证通过以后才执行下面的逻辑， 过了一阵产品提了需求，跟这个方法类似，
 * 我又把这个方法copy了一份，只不过验证条件稍微有点不一样，变成6个验证了。
 */
public class Test {

    public static void main(String[] args) {
        Handler.Builder builder = new Handler.Builder();
        //链式编程，谁在前谁在后看的清清楚楚
        builder.addHandler(new ValidateHandler()).addHandler(new LoginHandler()).addHandler(new AuthHandler())
            .addHandler(new BusinessLogicHandler());
        User user = new User();
        user.setUserName("woniu");
        user.setPassWord("666");
        builder.build().doHandler(user);
    }

}
