package de.hdm.hdmUrlaub.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import de.hdm.hdmUrlaub.bo.FachvorgesetzterBo;
import de.hdm.hdmUrlaub.bo.UrlaubsantragBo;
import de.hdm.hdmUrlaub.bo.ZeitraumBo;
import de.hdm.hdmUrlaub.db.DataAccess;
import de.hdm.hdmUrlaub.db.mapper.FachvorgesetzterMapper;

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

	private List<ZeitraumBo> zeitraums;
	private UrlaubsantragBo urlaubsantrag;

	private FachvorgesetzterMapper fachvorgesetzterMapper;

	private DataAccess dataAccess;

	public UrlaubsAntragBean() {
		zeitraums = new ArrayList<ZeitraumBo>();
		urlaubsantrag = new UrlaubsantragBo();
		fachvorgesetzterMapper = new FachvorgesetzterMapper();
		dataAccess = new DataAccess();
	}

	public void addZeitraum() {
		ZeitraumBo zeitraumBo = new ZeitraumBo();
		zeitraumBo.setBeginn(beginn);
		zeitraumBo.setEnde(ende);
		zeitraums.add(zeitraumBo);
		beginn = null;
		ende = null;
	}

	public List<FachvorgesetzterBo> getAllFachVorgesetzter() {
		return fachvorgesetzterMapper.getBoList(dataAccess
				.getAllFachvorgesetzter());
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

}
