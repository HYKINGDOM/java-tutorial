package com.java.util.javautil.scs.powermock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestMethod {

    private List<String> stringList = new ArrayList<>();

    public void meaningfulPublicApi() {
        if (doTheGamble("Whatever", 1 << 3)) {
            throw new RuntimeException("boom");
        }
    }

    private boolean doTheGamble(String whatever, int binary) {
        Random random = new Random(System.nanoTime());
        boolean gamble = random.nextBoolean();
        System.err.println("\n>>> GAMBLE CALLED <<<\n");
        return gamble;
    }

    private void doReturnVoid(String whatever, int binary) {
        Random random = new Random(System.nanoTime());
        boolean gamble = random.nextBoolean();
        String whateverStr = whatever + "str";
        stringList.add(whateverStr);
        System.err.println("\n>>> GAMBLE" + gamble + " CALLED <<<\n");
    }

    public void add(int index, String element) {
        // no-op
    }

}
