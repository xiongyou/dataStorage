package com.businessDataStorage.process;

import java.sql.Connection;
import java.sql.SQLException;

public class InsertProductMonitorPortal extends abstractPortal implements iPortal {

	@Override
	public void run(Connection conn, DatabaseAttr databaseAttr) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String sourceTable=databaseAttr.getSourceTable();
				String destDatabase=databaseAttr.getDestDatabase();
				String productMonitorTable=databaseAttr.getProductMonitorTable();
				String platformString=databaseAttr.getPlatformString();
				String startDate=databaseAttr.getStartDate();
				String endDate=databaseAttr.getEndDate();

		        try {
				//4、插入交易信息（此部分源数据表中包含到内部统一使用的URL的编号 ，来作为ProductInnerID）
		        	String insertProductMonitor="insert into "+destDatabase+"."+productMonitorTable+" (productInnerId,"
		            		+"productActualID,weight,std_weight,productPrice,productPromPrice,std_price,monthSaleCount,commentCount,"
		            		+"platform,extractTime,analyzeTime,year,monthOfYear,dayOfMonth) select a.productInnerID,a.productActualID,"
		            		+"a.weight,a.std_stdWeightValue,a.productPrice,a.productPromPrice,a.std_stdPrice,a.monthSaleCount,a.commentCount,"
		            		+"a.website,a.extractTime,a.analyzeTime,a.year,a.monthOfYear,a.dayOfMonth from " +sourceTable+ " as a" 
		            		+" where "+platformString+" errorinfo=''  and a.relativeInnerid is null"
		            		+" and (a.analyzeTime BETWEEN '"+startDate+" 0:00:00' and '"+endDate+" 23:59:59')";
		            
		            System.out.println(insertProductMonitor);
		            int insertProductMonNum=executeSql(conn,insertProductMonitor);
		            if(insertProductMonNum==-1)
		            	System.out.println("插入交易信息命令执行失败");
		            else
		            	System.out.println("成功插入"+insertProductMonNum+"条交易信息");
		        } catch (SQLException e) {
		            System.out.println("MySQL操作错误");
		            e.printStackTrace();
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		           
		        }

	}

}
