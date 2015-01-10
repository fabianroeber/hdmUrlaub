package de.hdm.hdmUrlaub.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import de.hdm.hdmUrlaub.bo.FachvorgesetzterBo;
import de.hdm.hdmUrlaub.db.DataAccess;
import de.hdm.hdmUrlaub.db.dbmodel.Urlaubsantrag;
import de.hdm.hdmUrlaub.db.mapper.FachvorgesetzterMapper;

@ManagedBean(name = "urlaubsantragBean")
@ViewScoped
public class UrlaubsAntragBean implements Serializable {

	private static final long serialVersionUID = 8393254310160065177L;

	private Urlaubsantrag urlaubsantrag;

	private FachvorgesetzterBo selectedFachvorgesetzter;

	private FachvorgesetzterMapper fachvorgesetzterMapper;

	private DataAccess dataAccess;

	public UrlaubsAntragBean() {
		fachvorgesetzterMapper = new FachvorgesetzterMapper();
		dataAccess = new DataAccess();
	}

	public List<FachvorgesetzterBo> getAllFachVorgesetzter() {
		return fachvorgesetzterMapper.getBoList(dataAccess
				.getAllFachvorgesetzter());
	}

	public Urlaubsantrag getUrlaubsantrag() {
		return urlaubsantrag;
	}

	public void setUrlaubsantrag(Urlaubsantrag urlaubsantrag) {
		this.urlaubsantrag = urlaubsantrag;
	}

	public FachvorgesetzterBo getSelectedFachvorgesetzter() {
		return selectedFachvorgesetzter;
	}

	public void setSelectedFachvorgesetzter(
			FachvorgesetzterBo selectedFachvorgesetzter) {
		this.selectedFachvorgesetzter = selectedFachvorgesetzter;
	}

}
