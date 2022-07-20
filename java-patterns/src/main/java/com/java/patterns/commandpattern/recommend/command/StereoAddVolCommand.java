package com.java.patterns.commandpattern.recommend.command;


import com.java.util.javautil.patterns.commandpattern.recommend.device.Stereo;

/**
 * 音响音量增加
 * @author yihur
 */
public class StereoAddVolCommand implements Command {
    private Stereo setreo;

    public StereoAddVolCommand(Stereo setreo) {
        this.setreo = setreo;
    }

    @Override
    public void execute() {
        int vol = setreo.GetVol();
        if (vol < 11) {
            setreo.SetVol(++vol);
        }

    }

    @Override
    public void undo() {
        int vol = setreo.GetVol();
        if (vol > 0) {
            setreo.SetVol(--vol);
        }

    }

}
