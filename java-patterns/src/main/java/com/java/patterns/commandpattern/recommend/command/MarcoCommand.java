package com.java.patterns.commandpattern.recommend.command;

/**
 * @author yihur
 */
public class MarcoCommand implements Command {

    private Command[] commands;

    public MarcoCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (int i = 0, len = commands.length; i < len; i++) {
            commands[i].execute();
        }
    }

    @Override
    public void undo() {
        for (int i = commands.length - 1; i >= 0; i--) {
            commands[i].undo();
        }
    }

}
