package de.hdm.hdmUrlaub.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import de.hdm.hdmUrlaub.bo.UrlaubsantragBo;
import de.hdm.hdmUrlaub.db.mapper.UrlaubsantragMapper;
import de.hdm.hdmUrlaub.enums.Status;

/**
 * Dieses Bean verwaltet die Best&auml;tigung oder Ablehnung von
 * Urlaubsantr&auml;gen.
 * 
 * @author Fabian
 *
 */
@ManagedBean(name = "confirmBean")
@RequestScoped
public class ConfirmBean {

	private UrlaubsantragMapper urlaubsantragMapper;

	private UrlaubsantragBo urlaubsantrag;

	private String begruendung;

	@ManagedProperty(value = "#{dataAccesBean}")
	private DataAccessBean dataAccessBean;

	/**
	 * Diese Managedproperty enth&auml;lt den Key, der f&uuml;r die Aktivierung
	 * eines {@link UrlaubsantragBo} generiert wurde.
	 */
	@ManagedProperty(value = "#{param.key}")
	private String key;

	public ConfirmBean() {
		urlaubsantragMapper = new UrlaubsantragMapper();
	}

	@PostConstruct
	public void init() {
		getUrlaubsantragByKey(key);
	}

	public void getUrlaubsantragByKey(String key) {
		urlaubsantrag = urlaubsantragMapper.getBo(dataAccessBean
				.getDataAccess().getUrlaubsantragByKey(key));
	}

	public void saveAntrag(boolean genehmigt) {
		if (genehmigt) {
			urlaubsantrag.setStatus(Status.GENEHMIGT);
		} else {
			urlaubsantrag.setStatus(Status.ABGELEHNT);
			urlaubsantrag.setBegruendung(begruendung);
		}
		try {
			dataAccessBean.getDataAccess().saveUrlaubsantrag(
					urlaubsantragMapper.getDbObject(urlaubsantrag));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public UrlaubsantragBo getUrlaubsantrag() {
		return urlaubsantrag;
	}

	public void setUrlaubsantrag(UrlaubsantragBo urlaubsantrag) {
		this.urlaubsantrag = urlaubsantrag;
	}

	public DataAccessBean getDataAccessBean() {
		return dataAccessBean;
	}

	public void setDataAccessBean(DataAccessBean dataAccessBean) {
		this.dataAccessBean = dataAccessBean;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getBegruendung() {
		return begruendung;
	}

	public void setBegruendung(String begruendung) {
		this.begruendung = begruendung;
	}

}
