package de.hdm.hdmUrlaub.beans;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

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
@ViewScoped
public class ConfirmBean {

	private UrlaubsantragMapper urlaubsantragMapper;

	private UrlaubsantragBo urlaubsantrag;

	private String begruendung;

	@ManagedProperty(value = "#{dataAccesBean}")
	private DataAccessBean dataAccessBean;

	private String key;

	public ConfirmBean() {
		urlaubsantragMapper = new UrlaubsantragMapper();
	}

	@PostConstruct
	public void init() {
	
	}

	public void getAntrag() {
		getUrlaubsantragByKey(key);
	}

	public void getUrlaubsantragByKey(String key) {
		try {
			urlaubsantrag = urlaubsantragMapper.getBo(dataAccessBean
					.getDataAccess().getUrlaubsantragByKey(key));
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		
	}

	public void saveAntrag(boolean genehmigt) {

		FacesContext context = FacesContext.getCurrentInstance();

		if (genehmigt) {
			urlaubsantrag.setStatus(Status.GENEHMIGT);
		} else {
			urlaubsantrag.setStatus(Status.ABGELEHNT);
			urlaubsantrag.setBegruendung(begruendung);
		}
		try {
			dataAccessBean.getDataAccess().saveUrlaubsantrag(
					urlaubsantragMapper.getDbObject(urlaubsantrag));
			context.addMessage(null, new FacesMessage(
					"Antrag erfolgreich gespeichert"));
		} catch (PersistenceException e) {
			context.addMessage(null, new FacesMessage("Fehler beim Speichern"));
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
