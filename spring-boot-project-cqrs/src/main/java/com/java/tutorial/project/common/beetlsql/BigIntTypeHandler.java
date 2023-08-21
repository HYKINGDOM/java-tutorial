package com.java.tutorial.project.common.beetlsql;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;

import org.beetl.sql.core.mapping.type.JavaSqlTypeHandler;
import org.beetl.sql.core.mapping.type.ReadTypeParameter;
import org.beetl.sql.core.mapping.type.WriteTypeParameter;

/**
 *
 * 将Float映射为BigInteger
 *
 */
public final class BigIntTypeHandler extends JavaSqlTypeHandler {

	@Override
	public Object getValue(ReadTypeParameter typePara) throws SQLException {
		BigDecimal id = typePara.getRs().getBigDecimal(typePara.getIndex());
		return id.toBigIntegerExact();
	}

	@Override
	public void setParameter(WriteTypeParameter writeTypeParameter, Object obj) throws SQLException {
		BigInteger bigInteger = (BigInteger) obj;
		BigDecimal id = new BigDecimal(bigInteger);
		writeTypeParameter.getPs().setBigDecimal(writeTypeParameter.getIndex(), id);
	}

}
