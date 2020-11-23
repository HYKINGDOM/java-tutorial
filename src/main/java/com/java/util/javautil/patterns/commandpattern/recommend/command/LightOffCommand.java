package com.java.util.javautil.patterns.commandpattern.recommend.command;


import com.design.pattern.project.commandpattern.recommend.device.Light;

/**
 * 灯光关闭命令
 * @author yihur
 */
public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.Off();
    }

    @Override
    public void undo() {
        light.On();
    }

}
