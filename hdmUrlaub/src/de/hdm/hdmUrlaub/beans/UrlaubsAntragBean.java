package de.hdm.hdmUrlaub.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;

import de.hdm.hdmUrlaub.bo.FachvorgesetzterBo;
import de.hdm.hdmUrlaub.bo.UrlaubsantragBo;
import de.hdm.hdmUrlaub.bo.ZeitraumBo;
import de.hdm.hdmUrlaub.db.mapper.FachvorgesetzterMapper;
import de.hdm.hdmUrlaub.db.mapper.UrlaubsantragMapper;
import de.hdm.hdmUrlaub.enums.Status;
import de.hdm.hdmUrlaub.util.MailUtil;

/**
 * Diese Bean verwaltet die Erstellung eines {@link UrlaubsantragBo}.
 * (urlaubsantrag-xhtml)
 * 
 * @author Fabian
 * 
 */
@ManagedBean(name = "urlaubsantragBean")
@ViewScoped
public class UrlaubsAntragBean implements Serializable {

	private static final long serialVersionUID = 8393254310160065177L;

	private Date beginn;
	private Date ende;
	private int anzahltage = 0;
	private FachvorgesetzterBo fachvorgesetzterBo;

	private List<ZeitraumBo> zeitraums;
	private UrlaubsantragBo urlaubsantrag;

	private FachvorgesetzterMapper fachvorgesetzterMapper;
	private UrlaubsantragMapper urlaubsantragMapper;

	/**
	 * Hier wird die Klasse {@link DataAccessBean} injiziert, die den
	 * Datenbankzugriff bereitstellt.
	 */
	@ManagedProperty(value = "#{dataAccesBean}")
	private DataAccessBean dataAccessBean;

	/**
	 * Hier wird die Klasse {@link NavigationBean} injiziert, um Zugriff auf
	 * Navigationsaktionen zu bekommen.
	 */
	@ManagedProperty(value = "#{navigationBean}")
	private NavigationBean navigationBean;

	/**
	 * Hier wird die Klasse UserBean injiziert, um Informationen &uuml;ber den
	 * aktuell angemeldeten User zu bekommen.
	 */
	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

	public UrlaubsAntragBean() {
		zeitraums = new ArrayList<ZeitraumBo>();
		urlaubsantrag = new UrlaubsantragBo();
		fachvorgesetzterMapper = new FachvorgesetzterMapper();
		urlaubsantragMapper = new UrlaubsantragMapper();
	}

	/**
	 * F&uuml;gt einen Zeitraum zu einem Urlaubsantrag hinzu.
	 */
	public void addZeitraum() {
		ZeitraumBo zeitraumBo = new ZeitraumBo();
		zeitraumBo.setBeginn(beginn);
		zeitraumBo.setEnde(ende);
		zeitraums.add(zeitraumBo);
		anzahltage = anzahltage + zeitraumBo.getAnzahltage();
		beginn = null;
		ende = null;
	}

	/**
	 * Entfernt einen Zeitraum aus dem Urlaubsantrag.
	 * 
	 * @param index
	 */
	public void removeZeitraum(int index) {
		ZeitraumBo zeitraum = zeitraums.get(index);
		anzahltage = anzahltage - zeitraum.getAnzahltage();
		zeitraums.remove(index);
	}

	/**
	 * L&auml;dt alle verf&uuml;gbaren Fachvorgesetzten
	 * 
	 * 
	 * @return List {@link FachvorgesetzterBo}
	 */
	public List<FachvorgesetzterBo> getAllFachVorgesetzter() {
		return fachvorgesetzterMapper.getBoList(dataAccessBean.getDataAccess()
				.getAllFachvorgesetzter());
	}

	/**
	 * Methode zum Speichern eines Urlaubsantrags {@link UrlaubsantragBo}
	 */
	public String saveUrlaubsantrag() {
		urlaubsantrag.setZeitraums(zeitraums);
		urlaubsantrag.setStatus(Status.OFFEN);
		urlaubsantrag.setMitarbeiter(userBean.getMitarbeiter());
		urlaubsantrag.setFachvorgesetzter(fachvorgesetzterBo);
		urlaubsantrag.setKey(UUID.randomUUID().toString());

		try {
			dataAccessBean.getDataAccess().saveUrlaubsantrag(
					urlaubsantragMapper.getDbObject(urlaubsantrag));
			MailUtil.sendRequestMail(urlaubsantrag);
		} catch (PersistenceException e) {
			// JETZT SCHMIEDER
		}

		urlaubsantrag = new UrlaubsantragBo();
		return navigationBean.toSecondPage();
	}

	public UrlaubsantragBo getUrlaubsantrag() {
		return urlaubsantrag;
	}

	public void setUrlaubsantrag(UrlaubsantragBo urlaubsantrag) {
		this.urlaubsantrag = urlaubsantrag;
	}

	public List<ZeitraumBo> getZeitraums() {
		return zeitraums;
	}

	public void setZeitraums(List<ZeitraumBo> zeitraums) {
		this.zeitraums = zeitraums;
	}

	public Date getBeginn() {
		return beginn;
	}

	public void setBeginn(Date beginn) {
		this.beginn = beginn;
	}

	public Date getEnde() {
		return ende;
	}

	public void setEnde(Date ende) {
		this.ende = ende;
	}

	public int getAnzahltage() {
		return anzahltage;
	}

	public void setAnzahltage(int anzahltage) {
		this.anzahltage = anzahltage;
	}

	public FachvorgesetzterBo getFachvorgesetzterBo() {
		return fachvorgesetzterBo;
	}

	public void setFachvorgesetzterBo(FachvorgesetzterBo fachvorgesetzterBo) {
		this.fachvorgesetzterBo = fachvorgesetzterBo;
	}

	public DataAccessBean getDataAccessBean() {
		return dataAccessBean;
	}

	public void setDataAccessBean(DataAccessBean dataAccessBean) {
		this.dataAccessBean = dataAccessBean;
	}

	public NavigationBean getNavigationBean() {
		return navigationBean;
	}

	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
