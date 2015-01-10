package de.hdm.hdmUrlaub.db.mapper;

import java.util.ArrayList;
import java.util.List;

import de.hdm.hdmUrlaub.bo.FachvorgesetzterBo;
import de.hdm.hdmUrlaub.db.dbmodel.Fachvorgesetzter;

public class FachvorgesetzterMapper implements
		DbMapper<FachvorgesetzterBo, Fachvorgesetzter> {

	public FachvorgesetzterMapper() {
	}

	@Override
	public FachvorgesetzterBo getBo(Fachvorgesetzter dbobject) {
		FachvorgesetzterBo bo = new FachvorgesetzterBo(dbobject.getId(),
				dbobject.getNachname(), dbobject.getVorname(),
				dbobject.getEmail());
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
		//not necessary
		return null;
	}
}
