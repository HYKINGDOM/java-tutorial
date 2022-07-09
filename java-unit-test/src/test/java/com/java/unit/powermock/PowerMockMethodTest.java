package com.java.util.javautil.scs.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.doCallRealMethod;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.support.membermodification.MemberMatcher.method;

@RunWith(PowerMockRunner.class)
public class PowerMockMethodTest {

    @Test(expected = RuntimeException.class)
    public void when_gambling_is_true_then_always_explode() throws Exception {
        TestMethod spy = PowerMockito.spy(new TestMethod());
        when(spy, method(TestMethod.class, "doTheGamble", String.class, int.class))
                .withArguments(anyString(), anyInt())
                .thenReturn(true);
    }

    @Test
    public void test_when_return_void() throws Exception {
//        TestMethod testMethod = new TestMethod();
//        Class<?> clazz = testMethod.getClass();
//        Field field = clazz.getDeclaredField("stringList");
//        field.setAccessible(true);
//        List<String> string = (List<String>) field.get(testMethod);
//        Object doReturnVoid = field.get("doReturnVoid");
        TestMethod testMethod1 = PowerMockito.spy(new TestMethod());
        List<String> stringList = new ArrayList<>();
        PowerMockito.when(testMethod1, "doReturnVoid", anyString(), anyInt());
        PowerMockito.when(testMethod1, "stringList").thenReturn(stringList);

//        TestMethod testMethod = PowerMockito.spy(new TestMethod());
//
//        PowerMockito
//                .verifyPrivate(testMethod, Mockito.times(1))
//                .invoke("doReturnVoid");


    }

    @Test
    public void whenAddCalledRealMethodCalled() {
        TestMethod myList = mock(TestMethod.class);
        doCallRealMethod().when(myList).add(any(Integer.class), any(String.class));
        myList.add(1, "real");

        verify(myList, times(1)).add(1, "real");
    }

    @Test
    public void saveUserwithMock() {
//        User user = Mockito.mock(User.class);
        User user = new User();
        UserDao userDao = Mockito.mock(UserDao.class);
        Mockito.doNothing().when(userDao).saveUser(user);
        UserService userSerivce = new UserService(userDao);
        userSerivce.saveUser(user);
        Mockito.verify(userDao, Mockito.times(1)).saveUser(user);
    }


    @Test
    public void saveUserwithPowerMock() {
        //        User user = new User();
        User user = PowerMockito.mock(User.class);
        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.doNothing().when(userDao).saveUser(user);
        UserService userService = new UserService(userDao);
        userService.saveUser(user);

    }
}
