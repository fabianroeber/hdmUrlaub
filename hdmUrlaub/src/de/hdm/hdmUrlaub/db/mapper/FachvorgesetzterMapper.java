package de.hdm.hdmUrlaub.db.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.hdm.hdmUrlaub.bo.FachvorgesetzterBo;
import de.hdm.hdmUrlaub.bo.UrlaubsantragBo;
import de.hdm.hdmUrlaub.db.dbmodel.Fachvorgesetzter;
import de.hdm.hdmUrlaub.db.dbmodel.Urlaubsantrag;

public class FachvorgesetzterMapper implements
		DbMapper<FachvorgesetzterBo, Fachvorgesetzter> {

	private UrlaubsantragMapper urlaubsantragmapper;

	public FachvorgesetzterMapper() {
		urlaubsantragmapper = new UrlaubsantragMapper();
	}

	@Override
	public FachvorgesetzterBo getBo(Fachvorgesetzter dbobject) {
		List<UrlaubsantragBo> urlaubsantragBos = new ArrayList<UrlaubsantragBo>();
		for (Urlaubsantrag urlaubsantrag : dbobject.getUrlaubsantrags()) {
			urlaubsantragBos.add(urlaubsantragmapper.getBo(urlaubsantrag));
		}
		FachvorgesetzterBo bo = new FachvorgesetzterBo(dbobject.getId(),
				dbobject.getNachname(), dbobject.getVorname(),
				dbobject.getEmail(), urlaubsantragBos);
		return bo;
	}

	@Override
	public List<FachvorgesetzterBo> getBoList(
			List<Fachvorgesetzter> dbObjectListe) {
		List<FachvorgesetzterBo> fachvorgesetzterBos = new ArrayList<FachvorgesetzterBo>();
		for (Fachvorgesetzter fachvorgesetzter : dbObjectListe) {
			fachvorgesetzterBos.add(getBo(fachvorgesetzter));
		}
		return fachvorgesetzterBos;
	}

	@Override
	public Fachvorgesetzter getDbObject(FachvorgesetzterBo bo) {
		Set<Urlaubsantrag> antraege = new HashSet<Urlaubsantrag>();
		for (UrlaubsantragBo urlaubsantragBo : bo.getUrlaubsantragBos()) {
			antraege.add(urlaubsantragmapper.getDbObject(urlaubsantragBo));
		}
		Fachvorgesetzter fachvorgesetzter = new Fachvorgesetzter(bo.getId(),
				bo.getNachname(), bo.getVorname(), bo.getEmail(), antraege);
		return fachvorgesetzter;
	}
}
