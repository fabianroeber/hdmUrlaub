package de.hdm.hdmUrlaub.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.hdm.hdmUrlaub.bo.UrlaubsantragBo;
import de.hdm.hdmUrlaub.db.mapper.UrlaubsantragMapper;

/**
 * Dieses Bean verwaltet die Übersicht über alle Urlaube eines Mitarbeiters.
 * (urlaubuebersicht.xhtml)
 * 
 * @author Fabian
 *
 */
@ManagedBean(name = "urlaubsUebersichtBean")
@SessionScoped
public class UrlaubUebersichtBean implements Serializable {

	private static final long serialVersionUID = -7042363919058309194L;

	private Date date;

	private List<UrlaubsantragBo> urlaubsantrags;

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
	 * Hier wird die Klasse UserBean injiziert, im Zugriff auf den aktuell
	 * angemeldeten Mitarbeiter zu bekommen.
	 */
	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

	public UrlaubUebersichtBean() {
		urlaubsantragMapper = new UrlaubsantragMapper();
	}

	@PostConstruct
	public void init() {
		getAllUrlaubsantrags();
	}

	/**
	 * Diese Methode holt alle {@link UrlaubsantragBo} aus der Datenbank.
	 */
	public void getAllUrlaubsantrags() {
		if (userBean.getMitarbeiter() != null) {
			urlaubsantrags = urlaubsantragMapper.getBoList(dataAccessBean
					.getDataAccess().getAllUrlaubsantrags(
							userBean.getMitarbeiter().getId()));
		}

	}

	public String getDates() {
		// mock implementation.
		return "{'2015':{" + "'1':{" + "'1':true}," + "'4':{" + "'1':true,"
				+ "'10':true," + "'15':true }," + "'5':{" + "'17':true}" + "}"
				+ "}";
	}

	public List<UrlaubsantragBo> getUrlaubsantrags() {
		return urlaubsantrags;
	}

	public void setUrlaubsantrags(List<UrlaubsantragBo> urlaubsantrags) {
		this.urlaubsantrags = urlaubsantrags;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
