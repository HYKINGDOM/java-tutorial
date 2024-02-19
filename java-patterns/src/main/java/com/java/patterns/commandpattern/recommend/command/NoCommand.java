package com.java.patterns.commandpattern.recommend.command;

/**
 * 没有命令
 *
 * @author yihur
 */
public class NoCommand implements Command {

    @Override
    public void execute() {
    }

    @Override
    public void undo() {
    }

}
