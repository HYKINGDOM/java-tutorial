package com.java.util.javautil.patterns.commandpattern.recommend.command;


import com.design.pattern.project.commandpattern.recommend.device.Stereo;

/**
 * 音响关闭
 *
 * @author yihur
 */
public class StereoOffCommand implements Command {
    private Stereo setreo;

    public StereoOffCommand(Stereo setreo) {
        this.setreo = setreo;
    }

    @Override
    public void execute() {
        setreo.Off();
    }

    @Override
    public void undo() {
        setreo.On();
        setreo.SetCd();
    }

}

