package de.hdm.hdmUrlaub.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.hdm.hdmUrlaub.bo.UrlaubsantragBo;
import de.hdm.hdmUrlaub.db.DataAccess;
import de.hdm.hdmUrlaub.db.mapper.UrlaubsantragMapper;

/**
 * Dieses Bean verwaltet die Übersicht über alle Urlaube eines Mitarbeiters.
 * 
 * @author Fabian
 *
 */
@ManagedBean(name = "urlaubsUebersichtBean")
@SessionScoped
public class UrlaubUebersichtBean implements Serializable {

	private static final long serialVersionUID = -7042363919058309194L;

	private List<UrlaubsantragBo> urlaubsantrags;

	private UrlaubsantragMapper urlaubsantragMapper;
	private DataAccess dataAccess;

	public UrlaubUebersichtBean() {
		dataAccess = new DataAccess();
		urlaubsantragMapper = new UrlaubsantragMapper();
	}

	public void getAllUrlaubsantrags() {
		urlaubsantrags = urlaubsantragMapper.getBoList(dataAccess
				.getAllUrlaubsantrags());
	}

	public List<UrlaubsantragBo> getUrlaubsantrags() {
		return urlaubsantrags;
	}

	public void setUrlaubsantrags(List<UrlaubsantragBo> urlaubsantrags) {
		this.urlaubsantrags = urlaubsantrags;
	}

}
