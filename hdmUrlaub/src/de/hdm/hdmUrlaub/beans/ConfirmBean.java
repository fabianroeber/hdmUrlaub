package de.hdm.hdmUrlaub.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import de.hdm.hdmUrlaub.bo.UrlaubsantragBo;
import de.hdm.hdmUrlaub.db.mapper.UrlaubsantragMapper;

/**
 * Dieses Bean verwaltet die Best&auml;tigung oder Ablehnung von
 * Urlaubsantr&auml;gen.
 * 
 * @author Fabian
 *
 */
@ManagedBean(name = "confirmBean")
@ViewScoped
public class ConfirmBean {

	private UrlaubsantragMapper urlaubsantragMapper;

	private UrlaubsantragBo urlaubsantrag;

	/**
	 * Diese Managedproperty enth&auml;lt den Key, der f&uuml;r die Aktivierung
	 * eines {@link UrlaubsantragBo} generiert wurde.
	 */
	@ManagedProperty(value = "#{param.key}")
	private Long key;

	public ConfirmBean() {
		urlaubsantragMapper = new UrlaubsantragMapper();
	}

	@PostConstruct
	public void init() {

	}

	public void getUrlaubsantragByKey(String key) {

	}

	public UrlaubsantragBo getUrlaubsantrag() {
		return urlaubsantrag;
	}

	public void setUrlaubsantrag(UrlaubsantragBo urlaubsantrag) {
		this.urlaubsantrag = urlaubsantrag;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

}
