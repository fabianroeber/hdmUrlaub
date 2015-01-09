package de.hdm.hdmUrlaub.db.mapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.hdm.hdmUrlaub.bo.MitarbeiterBo;
import de.hdm.hdmUrlaub.bo.UrlaubsantragBo;
import de.hdm.hdmUrlaub.db.dbmodel.Mitarbeiter;
import de.hdm.hdmUrlaub.db.dbmodel.Urlaubsantrag;

/**
 * Wandelt ein Hibernate Object vom {@link Mitarbeiter} in ein Business Object
 * vom Typ {@link MitarbeiterBo} um.
 * 
 * @author Fabian
 *
 */
public class MitarbeiterMapper implements DbMapper<MitarbeiterBo, Mitarbeiter> {

	private UrlaubsantragMapper urlaubsantragmapper;

	public MitarbeiterMapper() {
		urlaubsantragmapper = new UrlaubsantragMapper();
	}

	@Override
	public MitarbeiterBo getBo(Mitarbeiter dbobject) {

		MitarbeiterBo mitarbeiterBo = new MitarbeiterBo(dbobject.getId(),
				dbobject.getVorname(), dbobject.getNachname());
		ArrayList<UrlaubsantragBo> antraege = new ArrayList<UrlaubsantragBo>();
		for (UrlaubsantragBo urlaubsantragBo : urlaubsantragmapper
				.getBoList(new ArrayList<Urlaubsantrag>(dbobject
						.getUrlaubsantrags()))) {
			antraege.add(urlaubsantragBo);
		}
		mitarbeiterBo.setUrlaubsantraege(antraege);
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

		Set<Urlaubsantrag> antraege = new HashSet<Urlaubsantrag>();

		for (UrlaubsantragBo urlaubsantragBo : bo.getUrlaubsantraege()) {
			antraege.add(urlaubsantragmapper.getDbObject(urlaubsantragBo));
		}
		Mitarbeiter mitarbeiter = new Mitarbeiter(bo.getVorname(),
				bo.getNachname(), bo.getEmail(), bo.getPasswort(), antraege);
		mitarbeiter.setId(bo.getId());
		return mitarbeiter;
	}
}
