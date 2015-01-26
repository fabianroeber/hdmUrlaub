package de.hdm.hdmUrlaub.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;

import de.hdm.hdmUrlaub.bo.UrlaubsantragBo;
import de.hdm.hdmUrlaub.bo.ZeitraumBo;
import de.hdm.hdmUrlaub.db.DataAccess;
import de.hdm.hdmUrlaub.db.dbmodel.Urlaubsantrag;
import de.hdm.hdmUrlaub.db.mapper.UrlaubsantragMapper;
import de.hdm.hdmUrlaub.util.MailUtil;

/**
 * Dieses Bean verwaltet die �bersicht �ber alle Urlaube eines Mitarbeiters.
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

	private List<UrlaubsantragBo> urlaubsantraege;

	private UrlaubsantragMapper urlaubsantragMapper;

	private String[] dates;

	public void setDates(String[] dates) {
		this.dates = dates;
	}

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
		getAllUrlaubsantraege();
	}

	/**
	 * Diese Methode holt alle {@link UrlaubsantragBo} aus der Datenbank.
	 */
	public void getAllUrlaubsantraege() {
		if (userBean.getMitarbeiter() != null) {
			urlaubsantraege = urlaubsantragMapper.getBoList(dataAccessBean
					.getDataAccess().getAllUrlaubsantrags(
							userBean.getMitarbeiter().getId()));
		}

	}

	public void deleteUrlaubsantrag(UrlaubsantragBo urlaubsantrag) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			dataAccessBean.getDataAccess().deleteUrlaubsantrag(
					urlaubsantragMapper.getDbObject(urlaubsantrag));
			MailUtil.sendCancellationMail(urlaubsantrag);
		} catch (PersistenceException e) {
			context.addMessage(null, new FacesMessage("Fehlgeschlagen",
					"Löschvorgang fehlgeschlagen! \n " + e.getMessage()));
		}

		context.addMessage(
				null,
				new FacesMessage(
						"Erfolgreich",
						"Der Urlaubsantrag wurde erfolgreich gelöscht. Ihr Fachvorgesetzter wurde über die Löschung informiert."));

	}
	
	public String editUrlaubsantrag(UrlaubsantragBo urlaubsantrag) {
		
		return navigationBean.toSecondPage();

	}

	public String[] getDates() {
		// HIER SCHMIEDER DATEN AUS DEN ANTRÄGEN LADEN // "5-16-2015";
		List<String> result = new ArrayList<String>();
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		SimpleDateFormat formatter = new SimpleDateFormat("M-d-yyyy");

		for (UrlaubsantragBo ua : urlaubsantraege) {

			for (ZeitraumBo zr : ua.getZeitraums()) {
				start.setTime(zr.getBeginn());
				end.setTime(zr.getEnde());
				end.add(Calendar.DAY_OF_YEAR, 1); // Add 1 day to endDate to
													// make sure endDate is
													// included into the final
													// list
				while (start.before(end)) {

					result.add(formatter.format(start.getTime()));
					start.add(Calendar.DAY_OF_YEAR, 1);
				}

			}

		}
		String[] resultAr = new String[result.size()];
		resultAr = result.toArray(resultAr);
		return resultAr;
	}

	public List<UrlaubsantragBo> getUrlaubsantrags() {
		return urlaubsantraege;
	}

	public void setUrlaubsantrags(List<UrlaubsantragBo> urlaubsantraege) {
		this.urlaubsantraege = urlaubsantraege;
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
