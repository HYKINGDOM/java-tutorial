package com.java.patterns.factorypattern.simplefactory;

/**
 * @author yihur
 */
public class ComputerFactory {

    public static BaseComputer getBaseComputer(String type, String ram, String nvidia, String hdd, String cpu) {

        switch (type) {
            case "DesktopComputer":
                return new DesktopComputer(ram, nvidia, hdd, cpu);
            case "ServerComputer":
                return new ServerComputer(ram, nvidia, hdd, cpu);
            default:
                break;
        }

        return null;
    }
}
