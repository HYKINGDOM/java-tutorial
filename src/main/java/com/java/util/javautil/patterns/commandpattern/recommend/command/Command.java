package com.java.util.javautil.patterns.commandpattern.recommend.command;

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
