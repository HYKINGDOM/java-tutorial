package com.java.patterns.factorypattern.simplefactory;

/**
 * @author yihur
 */
public class TestFactory {


    public static void main(String[] args) {
        BaseComputer desktop = ComputerFactory.getBaseComputer("DesktopComputer", "16 GB", "gtx2060", "1 TB", "4.4 GHz");
        BaseComputer server = ComputerFactory.getBaseComputer("ServerComputer", "32 GB", "5700Tx", "1 TB", "4.9 GHz");
        System.out.println("Factory DesktopComputer Config::" + desktop);
        System.out.println("Factory ServerComputer Config::" + server);
    }
}
