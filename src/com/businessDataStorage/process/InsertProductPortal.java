package com.businessDataStorage.process;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InsertProductPortal extends abstractPortal implements iPortal {

	@Override
	public void run(Connection conn, DatabaseAttr databaseAttr) {
		// TODO Auto-generated method stub
		String sourceTable=databaseAttr.getSourceTable();
		String destDatabase=databaseAttr.getDestDatabase();
		String storeTable=databaseAttr.getStoreTable();
		String productTable=databaseAttr.getProductTable();
		String platformString=databaseAttr.getPlatformString();
		String startDate=databaseAttr.getStartDate();
		String endDate=databaseAttr.getEndDate();
		        
        try {
            
          //3、插入新的产品
            String insertProduct="INSERT into "+destDatabase+"."+productTable+"(productInnerId,productActualID,"
            		+"productURL,productName,productDescription,weight,std_weight,origin,province,loc_province,"
            		+"loc_city,productLocationCode,category,specialtyCategory,brand,factoryName,factoryAddress,"
            		+"series,specification,deliveryStartAddress,std_deliveryProvince,std_deliveryCity,keyword,"
            		+"singleKeyword,storeActualID,platform,extractTime,analyzeTime,isValid) select a.productInnerID,"
            		+"a.productActualID,a.productURL,a.productName,a.productDescription,a.weight,"
            		+"a.std_stdWeightValue,a.origin,a.province,a.std_province,a.std_city,a.productLocationCode,"
            		+"a.category,a.specialtyCategory,a.brand,a.factoryName,a.factoryAddress,a.series,"
            		+"a.specification,a.deliveryStartArea,a.std_deliveryProvince,a.std_deliveryCity,a.keyword,"
            		+"a.keyword,a.storeActualID,a.website,a.extractTime,a.analyzeTime,a.isValid from " +sourceTable+ " as a left  JOIN "
            		+destDatabase+"."+productTable+" as b on a.productinnerid=b.productinnerid"
            		+" where "+platformString+" errorinfo='' and a.productActualId <>'' and "
            		+" b.productInnerId is null  and (a.analyzeTime BETWEEN '"+startDate+" 0:00:00' and '"+endDate+" 23:59:59') and relativeInnerId is null";
           // System.out.println(insertProduct);
            int insertProductNum=executeSql(conn,insertProduct);
            if(insertProductNum==-1)
            	System.out.println("插入商品命令执行失败");
            else
            	System.out.println("成功插入"+insertProductNum+"个商品");
            	
			//3.1 更新店铺storeinnerid
            String updateProduct="update "+destDatabase+"."+productTable+"  as a, "
            		+destDatabase+"."+storeTable+" as b set a.storeinnerid=b.storeInnerId "
            		+"where a.storeinnerid is null and a.storeActualID=b.storeactualid and a.platform=b.platform ";
            
            int updateProductNum=executeSql(conn,updateProduct);
            if(updateProductNum==-1)
            	System.out.println("更新商品文店铺id命令执行失败");
            else
            	System.out.println("成功更新"+updateProductNum+"个商品的店铺id");
		
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
	}
	}
}
