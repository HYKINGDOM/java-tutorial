package com.java.util.javautil.patterns.commandpattern.notrecommended;


import com.java.util.javautil.patterns.commandpattern.recommend.device.Light;
import com.java.util.javautil.patterns.commandpattern.recommend.device.Stereo;

/**
 * 按钮的具体执行方法
 * @author yihur
 */
public class TraditionControl implements Control {

    Light light;

    Stereo stereo;

    public TraditionControl(Light light, Stereo stereo) {
        this.light = light;
        this.stereo = stereo;
    }

    @Override
    public void onButton(int slot) {
        switch (slot) {
            case 0:
                light.On();
                break;
            case 1:
                stereo.On();
                break;
            case 2:
                int vol = stereo.GetVol();
                if (vol < 11) {
                    stereo.SetVol(++vol);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void offButton(int slot) {
        switch (slot) {
            case 0:
                light.Off();
                break;
            case 1:
                stereo.Off();
                break;
            case 2:
                int vol = stereo.GetVol();
                if (vol > 0) {
                    stereo.SetVol(--vol);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void undoButton() {

    }


}
