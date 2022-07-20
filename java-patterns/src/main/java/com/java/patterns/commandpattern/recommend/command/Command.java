package com.java.patterns.commandpattern.recommend.command;

/**
 * @author yihur
 */
public interface Command {

	/**
	 * 执行命令
	 */
    void execute();

	/**
	 * 控制
	 */
	void undo();
}
