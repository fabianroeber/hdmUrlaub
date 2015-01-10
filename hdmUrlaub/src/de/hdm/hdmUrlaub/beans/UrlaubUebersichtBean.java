package de.hdm.hdmUrlaub.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Dieses Bean verwaltet die Übersicht über alle Urlaube eines Mitarbeiters.
 * @author Fabian
 *
 */
@ManagedBean(name = "urlaubsUebersichtBean")
@SessionScoped
public class UrlaubUebersichtBean implements Serializable {

	private static final long serialVersionUID = -7042363919058309194L;

}
