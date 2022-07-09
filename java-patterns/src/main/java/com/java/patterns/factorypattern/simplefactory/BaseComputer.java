package com.java.util.javautil.patterns.factorypattern.simplefactory;

/**
 * @author yihur
 */
public abstract class BaseComputer {

    public abstract String getRAM();

    public abstract String getNvidia();

    public abstract String getHDD();

    public abstract String getCPU();

    @Override
    public String toString() {
        return "RAM= " + this.getRAM() + "NVIDIA=" + this.getNvidia() + ", HDD=" + this.getHDD() + ", CPU=" + this.getCPU();
    }
}
