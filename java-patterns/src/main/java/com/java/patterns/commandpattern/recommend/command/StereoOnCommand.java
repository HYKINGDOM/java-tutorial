package com.java.patterns.commandpattern.recommend.command;

import com.java.patterns.commandpattern.recommend.device.Light;
import com.java.patterns.commandpattern.recommend.device.Stereo;

/**
 * 音响打开
 *
 * @author yihur
 */
public class StereoOnCommand implements Command {
    private Stereo setreo;

    public StereoOnCommand(Stereo setreo) {
        this.setreo = setreo;
    }

    @Override
    public void execute() {
        setreo.On();
        setreo.SetCd();

    }

    @Override
    public void undo() {
        setreo.Off();
    }

}
