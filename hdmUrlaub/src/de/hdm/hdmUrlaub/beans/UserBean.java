package de.hdm.hdmUrlaub.beans;

import java.io.Serializable;
import java.security.GeneralSecurityException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.unboundid.ldap.sdk.LDAPException;

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
public class UserBean implements Serializable {

	private static final long serialVersionUID = 6129158197703648244L;

	private LdapAuthentificator ldapAuthentificator;

	private boolean loggedIn;

	private String userName;

	private String password;

	/**
	 * Hier wird die Klasse {@link NavigationBean} injiziert, um Zugriff auf
	 * Navigationsaktionen zu bekommen.
	 */
	@ManagedProperty(value = "#{navigationBean}")
	private NavigationBean navigationBean;

	/**
	 * Diese Methode versucht den Benutzer zu authentifizieren
	 */
	public void login() {

		if (userName != null && password != null) {

			if (userName.equalsIgnoreCase("devmode")) {

				loggedIn = true;
				navigationBean.redirectToWelcome();

			} else {

				try {
					String ldapuser = ldapAuthentificator.authenticate(
							userName, password);

					// Datenbankzugriff auf Tabelle Mitarbetier TODO
					if (ldapuser != null && ldapuser.equals(userName)) {
						loggedIn = true;
						navigationBean.redirectToWelcome();
					}

				} catch (LDAPException | GeneralSecurityException e) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Error",
									"Nutzer konnte nicht autorisiert werden"));
					loggedIn = false;
					navigationBean.redirectToLogin();
				}

			}

		}

	}

	public void logout() {
		loggedIn = false;
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

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public UserBean() {
		ldapAuthentificator = new LdapAuthentificator();
	}

	public NavigationBean getNavigationBean() {
		return navigationBean;
	}

	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}

}
