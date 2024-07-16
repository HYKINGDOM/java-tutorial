package com.java.tutorial.project.config;

import com.java.tutorial.project.domain.MyProcess;
import org.apache.commons.pool2.DestroyMode;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;

public class MyProcessFactory implements PooledObjectFactory<MyProcess> {

    @Override
    public void activateObject(PooledObject<MyProcess> p) throws Exception {

    }

    @Override
    public void destroyObject(PooledObject<MyProcess> p) throws Exception {
        final MyProcess process = p.getObject();
        if(null != process){
            process.setName("test");
        }
    }

    @Override
    public PooledObject<MyProcess> makeObject() throws Exception {
        return null;
    }

    @Override
    public void passivateObject(PooledObject<MyProcess> p) throws Exception {

    }

    @Override
    public boolean validateObject(PooledObject<MyProcess> p) {
        return false;
    }
}
