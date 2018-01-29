package com.businessDataStorage.process;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class abstractPortal implements iPortal {

	@Override
	public void run(Connection conn, DatabaseAttr databaseAttr) {
		// TODO Auto-generated method stub

	}
	public static int executeSql(Connection conn,String sql) throws SQLException{
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
        Statement stmt = conn.createStatement();        
        int result = stmt.executeUpdate(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
        return result;
	}

}
