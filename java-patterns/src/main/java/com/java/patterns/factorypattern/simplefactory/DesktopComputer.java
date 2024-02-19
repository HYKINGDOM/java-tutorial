package com.java.patterns.factorypattern.simplefactory;

/**
 * @author yihur
 */
public class DesktopComputer extends BaseComputer {

    private String ram;
    private String nvidia;
    private String hdd;
    private String cpu;

    public DesktopComputer(String ram, String nvidia, String hdd, String cpu) {
        this.ram = ram;
        this.nvidia = nvidia;
        this.hdd = hdd;
        this.cpu = cpu;
    }

    @Override
    public String getRAM() {
        return this.ram;
    }

    @Override
    public String getNvidia() {
        return this.nvidia;
    }

    @Override
    public String getHDD() {
        return this.hdd;
    }

    @Override
    public String getCPU() {
        return this.cpu;
    }
}
