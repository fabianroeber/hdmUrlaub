package de.hdm.hdmUrlaub.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import de.hdm.hdmUrlaub.bo.FachvorgesetzterBo;
import de.hdm.hdmUrlaub.bo.MitarbeiterBo;
import de.hdm.hdmUrlaub.bo.UrlaubsantragBo;
import de.hdm.hdmUrlaub.bo.ZeitraumBo;
import de.hdm.hdmUrlaub.db.DataAccess;
import de.hdm.hdmUrlaub.db.mapper.FachvorgesetzterMapper;
import de.hdm.hdmUrlaub.db.mapper.UrlaubsantragMapper;
import de.hdm.hdmUrlaub.enums.Status;

/**
 * Diese Bean verwaltet die Erstellung eines {@link UrlaubsantragBo}.
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
	private MitarbeiterBo loggedInMitarbeiter;
	private String fachv;

	private List<ZeitraumBo> zeitraums;
	private UrlaubsantragBo urlaubsantrag;

	private FachvorgesetzterMapper fachvorgesetzterMapper;
	private UrlaubsantragMapper urlaubsantragMapper;

	private DataAccess dataAccess;

	public UrlaubsAntragBean() {

		// Mitarbeiter zu Testzwecken
		loggedInMitarbeiter = new MitarbeiterBo(1, "Fabian", "Röber");

		zeitraums = new ArrayList<ZeitraumBo>();
		urlaubsantrag = new UrlaubsantragBo();
		fachvorgesetzterMapper = new FachvorgesetzterMapper();
		urlaubsantragMapper = new UrlaubsantragMapper();
		dataAccess = new DataAccess();
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
		return fachvorgesetzterMapper.getBoList(dataAccess
				.getAllFachvorgesetzter());
	}

	/**
	 * Methode zum Speichern eines Urlaubsantrags {@link UrlaubsantragBo}
	 */
	public String saveUrlaubsantrag() {
		urlaubsantrag.setZeitraums(zeitraums);
		urlaubsantrag.setMitarbeiter(loggedInMitarbeiter);
		urlaubsantrag.setStatus(Status.OFFEN);
		// Hier Mail verschicken!
		dataAccess.saveUrlaubsantrag(urlaubsantragMapper
				.getDbObject(urlaubsantrag));
		return "pm:third?transition=slide";
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

	public String getFachv() {
		return fachv;
	}

	public void setFachv(String fachv) {
		this.fachv = fachv;
	}

}
