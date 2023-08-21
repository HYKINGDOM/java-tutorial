package com.java.tutorial.project.common.beetlsql;

import org.beetl.sql.core.SQLManagerExtend;
import org.beetl.sql.core.extend.ParaExtend;

public final class CustomizeSQLManagerExtend extends SQLManagerExtend {

    protected ParaExtend customizeParaExtend = new CustomizeParaExtend();

    @Override
    public ParaExtend getParaExtend() {
        return this.customizeParaExtend;
    }

}
