package com.businessDataStorage.process;

public class DatabaseAttr {
	
	private String connectUrl;
	private String userName;
	private String password;
	private String sourceTable;
	
	private String destDatabase;
	private String storeTable;
	private String productTable;
	private String productMonitorTable;
	
	private String platformString;
	private String startDate;
	private String endDate;
	private String connectionString;
	public String getConnectUrl() {
		return connectUrl;
	}
	public void setConnectUrl(String connectUrl) {
		this.connectUrl = connectUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSourceTable() {
		return sourceTable;
	}
	public void setSourceTable(String sourceTable) {
		this.sourceTable = sourceTable;
	}
	public String getDestDatabase() {
		return destDatabase;
	}
	public void setDestDatabase(String destDatabase) {
		this.destDatabase = destDatabase;
	}
	public String getStoreTable() {
		return storeTable;
	}
	public void setStoreTable(String storeTable) {
		this.storeTable = storeTable;
	}
	public String getProductTable() {
		return productTable;
	}
	public void setProductTable(String productTable) {
		this.productTable = productTable;
	}
	public String getProductMonitorTable() {
		return productMonitorTable;
	}
	public void setProductMonitorTable(String productMonitorTable) {
		this.productMonitorTable = productMonitorTable;
	}
	public String getPlatformString() {
		return platformString;
	}
	public void setPlatformString(String platformString) {
		this.platformString = platformString;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getConnectionString() {
		return connectionString;
	}
	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

}
