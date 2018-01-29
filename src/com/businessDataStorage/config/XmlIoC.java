package com.businessDataStorage.config;

import java.sql.Connection;
import java.sql.DriverManager;

import com.businessDataStorage.process.DatabaseAttr;
import com.businessDataStorage.process.iPortal;

public class XmlIoC extends StdConfig {

	public XmlIoC(String filePath) {
		super(filePath);
		// TODO Auto-generated constructor stub
	}

	public static Object getBean(XmlIoC xmlIoC) {
		try {
			String className = xmlIoC.getXpathText("config/className/text()");
			System.out.println(className);
			Object obj = Class.forName(className).newInstance();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String args[]) {
		try {
			XmlIoC xmlIoC = new XmlIoC("config.xml");

			iPortal portal = (iPortal) XmlIoC.getBean(xmlIoC);
			DatabaseAttr databaseAttr = new DatabaseAttr();
			databaseAttr.setConnectUrl(xmlIoC.getXpathText("config/source/connectUrl/text()"));
			databaseAttr.setUserName(xmlIoC.getXpathText("config/source/userName/text()"));
			databaseAttr.setPassword(xmlIoC.getXpathText("config/source/password/text()"));
			databaseAttr.setSourceTable(xmlIoC.getXpathText("config/source/sourceTable/text()"));
			databaseAttr.setDestDatabase(xmlIoC.getXpathText("config/destination/database/text()"));
			databaseAttr.setStoreTable(xmlIoC.getXpathText("config/destination/storeTable/text()"));
			databaseAttr.setProductTable(xmlIoC.getXpathText("config/destination/productTable/text()"));
			databaseAttr.setProductMonitorTable(xmlIoC.getXpathText("config/destination/productMonitorTable/text()"));

			String platformList = xmlIoC.getXpathText("config/platformList/text()");
			String[] platformArray = platformList.split(",");
			String platformString = "";

			if (platformArray.length > 0 && platformList!="") {
				platformString += "(";
				for (int i = 0; i < platformArray.length; i++) {
					if (i == platformArray.length - 1)
						platformString = platformString + " website='" + platformArray[i] + "' ";
					else
						platformString = platformString + " website='" + platformArray[i] + "' or ";
				}
				platformString += ")  and";
			}
			databaseAttr.setPlatformString(platformString);
			databaseAttr.setStartDate(xmlIoC.getXpathText("config/startDate/text()"));
			databaseAttr.setEndDate(xmlIoC.getXpathText("config/endDate/text()"));
			databaseAttr.setConnectionString(databaseAttr.getConnectUrl() + "&user=" + databaseAttr.getUserName()
					+ "&password=" + databaseAttr.getPassword());
			// 1、建立数据库连接
			// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
			// 避免中文乱码要指定useUnicode和characterEncoding
			// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
			// 下面语句之前就要先创建javademo数据库user=root&password=root
			Connection conn = null;
			try {
				// 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
				// 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
				Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
				// or:
				// com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
				// or：
				// new com.mysql.jdbc.Driver();

				System.out.println("成功加载MySQL驱动程序");
				// 一个Connection代表一个数据库连接
				conn = DriverManager.getConnection(databaseAttr.getConnectionString());
				portal.run(conn, databaseAttr);
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
