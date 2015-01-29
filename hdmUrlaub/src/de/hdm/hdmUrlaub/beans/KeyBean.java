package de.hdm.hdmUrlaub.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import de.hdm.hdmUrlaub.bo.UrlaubsantragBo;

/**
 * Diese Bean gibt den akutellen Parameter der url an die Confirm Bean weiter.
 * Dies muss hierhin ausgelagert werden, da der Parameter immer nur über den
 * Zeitraum des Requests überlebt.
 * 
 * @author Fabian
 *
 */
@ManagedBean(name = "keyBean")
@RequestScoped
public class KeyBean {

	/**
	 * Diese Managedproperty enth&auml;lt den Key, der f&uuml;r die Aktivierung
	 * eines {@link UrlaubsantragBo} generiert wurde.
	 */
	@ManagedProperty(value = "#{param.key}")
	private String key;

	@ManagedProperty(value = "#{confirmBean}")
	private ConfirmBean confirmBean;

	@PostConstruct
	public void init() {
		confirmBean.setKey(key);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public ConfirmBean getConfirmBean() {
		return confirmBean;
	}

	public void setConfirmBean(ConfirmBean confirmBean) {
		this.confirmBean = confirmBean;
	}

}
