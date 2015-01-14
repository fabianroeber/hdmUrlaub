package de.hdm.hdmUrlaub.db.mapper;

import java.util.ArrayList;
import java.util.List;

import de.hdm.hdmUrlaub.bo.MitarbeiterBo;
import de.hdm.hdmUrlaub.db.dbmodel.Mitarbeiter;

/**
 * Wandelt ein Hibernate Object vom {@link Mitarbeiter} in ein Business Object
 * vom Typ {@link MitarbeiterBo} um.
 * 
 * @author Fabian
 *
 */
public class MitarbeiterMapper implements DbMapper<MitarbeiterBo, Mitarbeiter> {

	public MitarbeiterMapper() {
	}

	@Override
	public MitarbeiterBo getBo(Mitarbeiter dbobject) {

		MitarbeiterBo mitarbeiterBo = new MitarbeiterBo(dbobject.getId(),
				dbobject.getVorname(), dbobject.getNachname());

		mitarbeiterBo.setPasswort(dbobject.getPasswort());
		mitarbeiterBo.setEmail(dbobject.getEmail());
		return mitarbeiterBo;
	}

	@Override
	public List<MitarbeiterBo> getBoList(List<Mitarbeiter> dbObjectListe) {
		ArrayList<MitarbeiterBo> mitarbeiterBoListe = new ArrayList<MitarbeiterBo>();

		for (Mitarbeiter mitarbeiter : dbObjectListe) {
			mitarbeiterBoListe.add(getBo(mitarbeiter));
		}
		return mitarbeiterBoListe;
	}

	@Override
	public Mitarbeiter getDbObject(MitarbeiterBo bo) {

		Mitarbeiter mitarbeiter = new Mitarbeiter(bo.getVorname(),
				bo.getNachname(), bo.getEmail(), bo.getPasswort());
		mitarbeiter.setId(bo.getId());
		return mitarbeiter;
	}
}
