package de.hdm.hdmUrlaub.bo;

import java.util.Date;

/**
 * Diese Klasse enth&auml;lt einen Zeitraum eines Urlaubsantrags. Ein
 * Urlaubsantrag kann mehrere davon haben.
 * 
 * @author Fabian
 *
 */
public class ZeitraumBo extends HdmUrlaubBusinessObject {

	private static final long serialVersionUID = 3440962018375324263L;

	private Date beginn;

	private Date ende;

	private UrlaubsantragBo urlaubsantrag;

	public ZeitraumBo() {
		super();
	}

	public ZeitraumBo(Integer id, Date beginn, Date ende,
			UrlaubsantragBo urlaubsantrag) {
		super();
		this.setId(id);
		this.beginn = beginn;
		this.ende = ende;
		this.urlaubsantrag = urlaubsantrag;
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

	public UrlaubsantragBo getUrlaubsantrag() {
		return urlaubsantrag;
	}

	public void setUrlaubsantrag(UrlaubsantragBo urlaubsantrag) {
		this.urlaubsantrag = urlaubsantrag;
	}

}
