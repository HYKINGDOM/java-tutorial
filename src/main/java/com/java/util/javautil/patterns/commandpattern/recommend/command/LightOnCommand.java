package com.java.util.javautil.patterns.commandpattern.recommend.command;


import com.design.pattern.project.commandpattern.recommend.device.Light;

/**
 * 灯管打开命令
 *
 * @author yihur
 */
public class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;

    }

    @Override
    public void execute() {
        light.On();
    }

    @Override
    public void undo() {
        light.Off();
    }

}
