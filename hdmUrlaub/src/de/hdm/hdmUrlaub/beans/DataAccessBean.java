package de.hdm.hdmUrlaub.beans;

import javax.faces.bean.ManagedBean;

import de.hdm.hdmUrlaub.db.DataAccess;

@ManagedBean(name = "dataAccesBean")
public class DataAccessBean {

	public DataAccess dataAccess;

	public DataAccessBean() {
		dataAccess = new DataAccess();
	}

}
