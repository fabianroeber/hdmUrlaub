package de.hdm.hdmUrlaub.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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

	private Date date;

	private List<UrlaubsantragBo> urlaubsantraege;

	private String dates;

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

	@PostConstruct
	public void init() {
		getAllUrlaubsantraege();
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
		urlaubsantrag.setAnzahltage(anzahltage);

		FacesContext context = FacesContext.getCurrentInstance();

		try {
			dataAccessBean.getDataAccess().saveUrlaubsantrag(
					urlaubsantragMapper.getDbObject(urlaubsantrag));
			MailUtil.sendRequestMail(urlaubsantrag);
		} catch (PersistenceException e) {
			context.addMessage(null, new FacesMessage("Fehlgeschlagen",
					"Vorgang fehlgeschlagen! \n " + e.getMessage()));
		}

		context.addMessage(
				null,
				new FacesMessage(
						"Erfolgreich",
						"Der Urlaubsantrag wurde gespeichert und an den Fachvorgesetzten zur Genehmigung gesendet!"));
		anzahltage = 0;
		beginn = null;
		ende = null;
		urlaubsantrag = new UrlaubsantragBo();
		zeitraums = new ArrayList<ZeitraumBo>();
		getAllUrlaubsantraege();
		return navigationBean.toSecondPage();
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
		loadDates();

	}

	public void deleteUrlaubsantrag(UrlaubsantragBo urlaubsantrag) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			dataAccessBean.getDataAccess().deleteUrlaubsantrag(
					urlaubsantragMapper.getDbObject(urlaubsantrag));
			MailUtil.sendCancellationMail(urlaubsantrag);
			context.addMessage(
					null,
					new FacesMessage(
							"Erfolgreich",
							"Der Urlaubsantrag wurde erfolgreich gelöscht. Ihr Fachvorgesetzter wurde über die Löschung informiert."));
		} catch (PersistenceException e) {
			context.addMessage(null, new FacesMessage("Fehlgeschlagen",
					"Löschvorgang fehlgeschlagen! \n " + e.getMessage()));
		}
		getAllUrlaubsantraege();

	}

	private void loadDates() {
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

					result.add("'" + formatter.format(start.getTime()) + "'");
					start.add(Calendar.DAY_OF_YEAR, 1);
				}

			}

		}
		String[] resultAr = new String[result.size()];
		resultAr = result.toArray(resultAr);

		dates = Arrays.toString(resultAr);

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<UrlaubsantragBo> getUrlaubsantraege() {
		return urlaubsantraege;
	}

	public void setUrlaubsantraege(List<UrlaubsantragBo> urlaubsantraege) {
		this.urlaubsantraege = urlaubsantraege;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

}
