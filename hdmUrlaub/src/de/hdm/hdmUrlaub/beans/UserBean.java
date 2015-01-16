package de.hdm.hdmUrlaub.beans;

import java.security.GeneralSecurityException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.unboundid.ldap.sdk.LDAPException;

import de.hdm.hdmUrlaub.bo.MitarbeiterBo;
import de.hdm.hdmUrlaub.ldap.LdapAuthentificator;

/**
 * Diese Klassen verwaltet das An- und Abmelden von Benutzern und stellt
 * Informationen &uuml;ber den aktuell angemeldeten Benutzer beresit.
 * 
 * @author Fabian
 *
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {

	@ManagedProperty(value = "#{mitarbeiterBo}")
	private MitarbeiterBo mitarbeiterBo;

	LdapAuthentificator ldapAuthentificator;

	public UserBean() {
		ldapAuthentificator = new LdapAuthentificator();
	}

	private String userName;

	private String password;

	public String login() {

		if (userName != null && password != null) {

			if (userName.equalsIgnoreCase("devmode")) {

				mitarbeiterBo = new MitarbeiterBo(1, true, "Fabian", "Röber",
						"fabianroeber@gmail.com");

				return "/content_mobile.xhtml";

			} else {

				try {
					String username = ldapAuthentificator.authenticate(
							userName, password);

					// Datenbankzugriff auf Tabelle Mitarbetier TODO

				} catch (LDAPException | GeneralSecurityException e) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Error",
									"Nutzer konnte nicht autorisiert werden"));
					return "";

				}

			}

		}
		return "/content_mobile.xhtml";

	}

	public String logout() {
		mitarbeiterBo.setLoggedIn(false);
		return "/logout.xhtml";
	}

	public MitarbeiterBo getMitarbeiterBo() {
		return mitarbeiterBo;
	}

	public void setMitarbeiterBo(MitarbeiterBo mitarbeiterBo) {
		this.mitarbeiterBo = mitarbeiterBo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
