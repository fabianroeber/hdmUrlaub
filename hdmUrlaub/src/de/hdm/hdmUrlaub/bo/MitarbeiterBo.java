package de.hdm.hdmUrlaub.bo;

import java.util.List;

/**
 * Diese Klasse enth&auml;lt alle relevaten Daten zu einem Mitarbeiter der
 * Hochschule.
 * 
 * @author Fabian
 *
 */
public class MitarbeiterBo extends HdmUrlaubBusinessObject {

	private static final long serialVersionUID = 2190497750177912427L;

	private String vorname;

	private String nachname;

	private String passwort;

	private String email;

	private List<UrlaubsantragBo> urlaubsantraege;

	public MitarbeiterBo() {
		super();
	}

	public MitarbeiterBo(int id, String vorname, String nachname) {
		super();
		this.setId(id);
		this.vorname = vorname;
		this.nachname = nachname;

	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public List<UrlaubsantragBo> getUrlaubsantraege() {
		return urlaubsantraege;
	}

	public void setUrlaubsantraege(List<UrlaubsantragBo> urlaubsantraege) {
		this.urlaubsantraege = urlaubsantraege;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
