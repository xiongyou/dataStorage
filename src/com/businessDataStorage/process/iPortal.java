package com.businessDataStorage.process;

import java.sql.Connection;

public interface iPortal {
	
	void run(Connection conn,DatabaseAttr databaseAttr);
}
