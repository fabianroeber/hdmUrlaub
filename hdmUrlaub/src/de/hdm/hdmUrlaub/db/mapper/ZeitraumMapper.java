package de.hdm.hdmUrlaub.db.mapper;

import java.util.ArrayList;
import java.util.List;

import de.hdm.hdmUrlaub.bo.ZeitraumBo;
import de.hdm.hdmUrlaub.db.dbmodel.Zeitraum;

/**
 * Wandelt ein Hibernate Object vom {@link Zeitraum} in ein Business Object vom
 * Typ {@link ZeitraumBo} um.
 * 
 * @author Fabian
 *
 */
public class ZeitraumMapper implements DbMapper<ZeitraumBo, Zeitraum> {

	public ZeitraumMapper() {

	}

	@Override
	public ZeitraumBo getBo(Zeitraum dbobject) {
		return new ZeitraumBo(dbobject.getId(), dbobject.getBeginn(),
				dbobject.getEnde());
	}

	@Override
	public List<ZeitraumBo> getBoList(List<Zeitraum> dbObjectListe) {
		List<ZeitraumBo> zeitraums = new ArrayList<ZeitraumBo>();
		for (Zeitraum zeitraum : dbObjectListe) {
			zeitraums.add(getBo(zeitraum));
		}
		return null;
	}

	@Override
	public Zeitraum getDbObject(ZeitraumBo bo) {
		Zeitraum zeitraum = new Zeitraum(bo.getBeginn(), bo.getEnde());
		zeitraum.setId(bo.getId());
		return zeitraum;
	}
}
