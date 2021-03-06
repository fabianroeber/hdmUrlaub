package de.hdm.hdmUrlaub.bo;

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

	private String username;

	public MitarbeiterBo() {
		super();
	}

	public MitarbeiterBo(Integer id, String vorname, String nachname) {
		super();
		this.setId(id);
		this.vorname = vorname;
		this.nachname = nachname;

	}

	public MitarbeiterBo(Integer id, String vorname, String nachname,
			String email) {
		super();
		this.setId(1);
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
	}

	public MitarbeiterBo(String username) {
		super();
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
