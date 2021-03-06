package de.hdm.hdmUrlaub.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Dieses Bean stellt Navigationsaktionen zwischen Views und Seiten bereit.
 * 
 * @author Fabian
 *
 */
@ManagedBean(name = "navigationBean")
@SessionScoped
public class NavigationBean implements Serializable {

	private static final long serialVersionUID = 1520318172495977648L;

	/**
	 * Redirect to login page.
	 * 
	 * @return Login page name.
	 */
	public String redirectToLogin() {
		return "/login.xhtml?faces-redirect=true";
	}

	/**
	 * Go to login page.
	 * 
	 * @return Login page name.
	 */
	public String toLogin() {
		return "/login.xhtml";
	}

	/**
	 * Redirect to welcome page.
	 * 
	 * @return Welcome page name.
	 */
	public String redirectToWelcome() {
		return "/secured/content_mobile.xhtml?faces-redirect=true";
	}

	/**
	 * Go to welcome page.
	 * 
	 * @return Welcome page name.
	 */
	public String toWelcome() {
		return "/secured/content_mobile.xhtml";
	}

	/**
	 * Go to logout page.
	 * 
	 * @return
	 */
	public String redirectToLogout() {
		return "/logout.xhtml?faces-redirect=true";
	}

	/**
	 * Go to third Page.
	 * 
	 * @return
	 */
	public String toThirdPage() {
		return "pm:third?transition=slide";
	}

	/**
	 * Go to Second Page.
	 * 
	 * @return
	 */
	public String toSecondPage() {
		return "pm:second?transition=slide";
	}

}
