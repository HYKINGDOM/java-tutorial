package com.java.util.javautil.patterns.commandpattern.notrecommended;

/**
 * 控制接口
 * @author yihur
 */
public interface Control {

	/**
	 * 打开按钮
	 * @param slot
	 */
	void onButton(int slot);

	/**
	 * 关闭按钮
	 * @param slot
	 */
	void offButton(int slot);

	/**
	 * 取消执行
	 */
	void undoButton();
}
