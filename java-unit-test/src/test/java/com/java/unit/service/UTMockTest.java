package com.java.unit.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UTMockTest {

    @Mock
    private UTMock utMock;

    private List<Integer> integerList;

    @Before
    public void init_ut_mock(){
//        utMock = new UTMock();

        integerList = new ArrayList<>();
        integerList.add(1001);
    }

    @Test
    public void test_case_1(){
//        String s = utMock.convertToString(integerList);
//        assertEquals("100111112222",s);
    }

    @Test
    public void test_case_2(){
        doNothing().when(utMock).sumNumber(anyList());

        utMock.sumNumber(integerList);

        verify(utMock, times(1)).sumNumber(integerList);
    }

}