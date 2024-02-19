package com.java.tutorial.junit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * @Suite的作用是将一个类标记为JUnit平台上的测试套件。
 * @SelectClasses指定在JUnit平台上运行测试套件时要选择的类。
 */
@Suite
@SelectClasses(value = {JUnitTest.class, JUnitAssertTest.class})
public class RunSuite {
}
