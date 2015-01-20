package de.hdm.hdmUrlaub.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.hdm.hdmUrlaub.db.DataAccess;

@ManagedBean(name = "dataAccesBean")
@SessionScoped
public class DataAccessBean {

	private DataAccess dataAccess;

	public DataAccessBean() {
		dataAccess = new DataAccess();
	}

	public DataAccess getDataAccess() {
		return dataAccess;
	}

	public void setDataAccess(DataAccess dataAccess) {
		this.dataAccess = dataAccess;
	}
}
