package com.java.tutorial.project.validate.handle;

import com.java.tutorial.project.validate.BaseHandlerChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 * 可以参考此方法自定义handle
 * @author HY
 */
@Slf4j
@Service
public class MyHandlerChain extends BaseHandlerChain<MyHandler, String, String> {

    @Autowired
    public MyHandlerChain(List<MyHandler> myHandlers) {
        super(myHandlers);
    }
}
