package com.businessDataStorage.process;

import java.sql.Connection;
import java.sql.SQLException;

public class InsertStorePortal extends abstractPortal implements iPortal{

	@Override
	public void run(Connection conn,DatabaseAttr databaseAttr){

		String sourceTable=databaseAttr.getSourceTable();
		String destDatabase=databaseAttr.getDestDatabase();
		String storeTable=databaseAttr.getStoreTable();
		String platformString=databaseAttr.getPlatformString();
		String startDate=databaseAttr.getStartDate();
		String endDate=databaseAttr.getEndDate();
        try {
          //2、插入新的店铺数据，需要对新的店铺数据进行Distinct操作
            String insertStoreSql="insert into "+destDatabase+"."+storeTable+"(storeActualID,storeName,"
            	+"shopkeeper,companyName,storeLocation,loc_province,loc_city,storeLocationCode,storeURL,platform,extractTime,analyzeTime)"
 			+"select a.storeActualID,a.storeName,a.shopkeeper,a.companyName,a.storeLocation,a.std_storeLocationProvince,a.std_storeLocationCity,"
 			+"a.storelocationcode,a.std_storeURL,a.website,a.extractTime,a.analyzeTime from  " +sourceTable+ "  as a left outer JOIN "+destDatabase+"."+storeTable+" as b on a.storeActualID=b.storeActualID  and a.website=b.platform "
 			+"where "+platformString+" a.errorInfo='' and b.storeactualid is null  and (a.analyzeTime BETWEEN '"+startDate+" 0:00:00' and '"+endDate+" 23:59:59') GROUP BY a.storeActualID,a.website";
            
            int insertStoreNum=executeSql(conn,insertStoreSql);
            if(insertStoreNum==-1)
            	System.out.println("插入店铺命令执行失败");
            else
            	System.out.println("成功插入"+insertStoreNum+"个店铺");
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           
        }
		
	}

}
