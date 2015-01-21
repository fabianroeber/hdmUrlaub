package de.hdm.hdmUrlaub.bo;

import java.util.List;

import de.hdm.hdmUrlaub.enums.Status;

/**
 * Diese Klasse enth&auml;lt alle relevaten Daten zu einem Urlaubsantrag.
 * 
 * @author Fabian
 *
 */
public class UrlaubsantragBo extends HdmUrlaubBusinessObject {

	private static final long serialVersionUID = -7712834612413841876L;

	private String vertretung;

	private FachvorgesetzterBo fachvorgesetzter;

	private int anzahltage;

	private Status status;

	private MitarbeiterBo mitarbeiter;

	private String key;

	private String begruendung;

	private List<ZeitraumBo> zeitraums;

	public UrlaubsantragBo() {
		super();
	}

	public UrlaubsantragBo(Integer id, String vertretung,
			FachvorgesetzterBo fachvorgesetzter, int anzahltage,
			MitarbeiterBo mitarbeiter) {
		super();
		this.setId(id);
		this.vertretung = vertretung;
		this.fachvorgesetzter = fachvorgesetzter;
		this.anzahltage = anzahltage;
		this.mitarbeiter = mitarbeiter;
	}

	public String getVertretung() {
		return vertretung;
	}

	public void setVertretung(String vertretung) {
		this.vertretung = vertretung;
	}

	public FachvorgesetzterBo getFachvorgesetzter() {
		return fachvorgesetzter;
	}

	public void setFachvorgesetzter(FachvorgesetzterBo fachvorgesetzter) {
		this.fachvorgesetzter = fachvorgesetzter;
	}

	public int getAnzahltage() {
		return anzahltage;
	}

	public void setAnzahltage(int anzahltage) {
		this.anzahltage = anzahltage;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public MitarbeiterBo getMitarbeiter() {
		return mitarbeiter;
	}

	public void setMitarbeiter(MitarbeiterBo mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}

	public List<ZeitraumBo> getZeitraums() {
		return zeitraums;
	}

	public void setZeitraums(List<ZeitraumBo> zeitraums) {
		this.zeitraums = zeitraums;
	}

	public String getBegruendung() {
		return begruendung;
	}

	public void setBegruendung(String begruendung) {
		this.begruendung = begruendung;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
