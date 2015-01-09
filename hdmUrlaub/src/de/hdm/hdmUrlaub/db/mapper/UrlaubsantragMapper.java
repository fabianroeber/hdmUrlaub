package de.hdm.hdmUrlaub.db.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.hdm.hdmUrlaub.bo.UrlaubsantragBo;
import de.hdm.hdmUrlaub.bo.ZeitraumBo;
import de.hdm.hdmUrlaub.db.dbmodel.Urlaubsantrag;
import de.hdm.hdmUrlaub.db.dbmodel.Zeitraum;
import de.hdm.hdmUrlaub.enums.Status;

/**
 * Wandelt ein Hibernate Object vom {@link Urlaubsantrag} in ein Business Object
 * vom Typ {@link UrlaubsantragBo} um.
 * 
 * @author Fabian
 *
 */
public class UrlaubsantragMapper implements
		DbMapper<UrlaubsantragBo, Urlaubsantrag> {

	private MitarbeiterMapper mitarbeitermapper;
	private ZeitraumMapper zeitraumMapper;
	private FachvorgesetzterMapper fachvorgesetzterMapper;

	public UrlaubsantragMapper() {
		mitarbeitermapper = new MitarbeiterMapper();
		zeitraumMapper = new ZeitraumMapper();
		fachvorgesetzterMapper = new FachvorgesetzterMapper();
	}

	@Override
	public UrlaubsantragBo getBo(Urlaubsantrag dbobject) {

		UrlaubsantragBo urlaubsantragBo = new UrlaubsantragBo(dbobject.getId(),
				dbobject.getVertretung(), fachvorgesetzterMapper.getBo(dbobject
						.getFachvorgesetzter()), dbobject.getAnzahltage(),
				mitarbeitermapper.getBo(dbobject.getMitarbeiter()));

		switch (dbobject.getStatus()) {
		case 'g':
			urlaubsantragBo.setStatus(Status.GENEHMIGT);
			break;
		case 'o':
			urlaubsantragBo.setStatus(Status.OFFEN);
			break;
		case 'a':
			urlaubsantragBo.setStatus(Status.ABGELEHNT);
			break;
		default:
			break;
		}

		return urlaubsantragBo;
	}

	@Override
	public List<UrlaubsantragBo> getBoList(List<Urlaubsantrag> dbObjectListe) {
		ArrayList<UrlaubsantragBo> antraege = new ArrayList<UrlaubsantragBo>();
		for (Urlaubsantrag urlaubsantrag : dbObjectListe) {
			antraege.add(getBo(urlaubsantrag));
		}
		return antraege;
	}

	@Override
	public Urlaubsantrag getDbObject(UrlaubsantragBo bo) {
		char status;
		switch (bo.getStatus()) {
		case GENEHMIGT:
			status = 'g';
			break;
		case ABGELEHNT:
			status = 'a';
		case OFFEN:
			status = 'o';
		default:
			status = 'o';
		}
		Set<Zeitraum> zeitraums = new HashSet<Zeitraum>();
		for (ZeitraumBo zeitraumbo : bo.getZeitraums()) {
			zeitraums.add(zeitraumMapper.getDbObject(zeitraumbo));
		}

		return new Urlaubsantrag(bo.getId(), mitarbeitermapper.getDbObject(bo
				.getMitarbeiter()), fachvorgesetzterMapper.getDbObject(bo
				.getFachvorgesetzter()), status, bo.getAnzahltage(),
				bo.getVertretung(), zeitraums);
	}
}
