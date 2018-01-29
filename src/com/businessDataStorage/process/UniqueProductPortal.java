package com.businessDataStorage.process;

import java.sql.Connection;
import java.sql.SQLException;

public class UniqueProductPortal extends abstractPortal implements iPortal {

	@Override
	public void run(Connection conn, DatabaseAttr databaseAttr) {
		// TODO Auto-generated method stub
		String sourceTable = databaseAttr.getSourceTable();
		String destDatabase = databaseAttr.getDestDatabase();
		String productTable = databaseAttr.getProductTable();

		try {
			//先和已有的商品进行对比
			String updateSql = "update " + destDatabase + "." + productTable + " as a ," + sourceTable + " as b "
					+ "set  b.relativeinnerid=a.productinnerid"
					+ " where  b.productActualID<>'' and a.productactualid=b.productactualid and a.platform=b.website and a.productinnerid<>b.productinnerid";
			int updateProductNum = executeSql(conn, updateSql);
			if (updateProductNum == -1)
				System.out.println("与已有产品表关联更新relativeinnerid命令执行失败");
			else
				System.out.println("与已有产品表关联成功更新" + updateProductNum + "个relativeinnerid");
			
			//然后对还没有入库的商品进行内部的相关标记
			String updateSql1 = "update " + sourceTable + " as a, "
					+ "(select productActualID,website,min(productInnerId) as minInnerId from " + sourceTable
					+ " where productActualID<>'' and productActualID is not null  and relativeinnerid is null"
					+ " GROUP BY productActualID,website having count(*)>1) as b"
					+ " set a.relativeInnerid=b.minInnerId "
					+ "where  a.productactualid=b.productActualid and a.website=b.website "
					+ "and relativeinnerid is null and a.productInnerId<>minInnerId";

			int updateProductNum1 = executeSql(conn, updateSql1);
			if (updateProductNum1 == -1)
				System.out.println("以最小InnerID更新relativeinnerid命令执行失败");
			else
				System.out.println("以最小InnerID更新成功更新" + updateProductNum1 + "个relativeinnerid");
		} catch (SQLException e) {
			System.out.println("MySQL操作错误");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
