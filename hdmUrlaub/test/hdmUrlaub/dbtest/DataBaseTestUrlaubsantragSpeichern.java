package hdmUrlaub.dbtest;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import de.hdm.hdmUrlaub.db.DataAccess;
import de.hdm.hdmUrlaub.db.dbmodel.Fachvorgesetzter;
import de.hdm.hdmUrlaub.db.dbmodel.Mitarbeiter;
import de.hdm.hdmUrlaub.db.dbmodel.Urlaubsantrag;
import de.hdm.hdmUrlaub.db.dbmodel.Zeitraum;

public class DataBaseTestUrlaubsantragSpeichern {

	@Test
	public void test() {
		DataAccess dataAcces = new DataAccess();
		Fachvorgesetzter fachvorgesetzter = dataAcces.getAllFachvorgesetzter()
				.get(1);
		Mitarbeiter mitarbeiter = dataAcces.getAllMitarbeiter().get(0);

		Set<Zeitraum> zeitraums = new HashSet<Zeitraum>();
		zeitraums.add(new Zeitraum(new Date(), new Date()));

		Urlaubsantrag antrag = new Urlaubsantrag();
		antrag.setAnzahltage(1);
		antrag.setZeitraums(zeitraums);
		antrag.setMitarbeiter(mitarbeiter);
		antrag.setStatus('o');
		antrag.setVertretung("Hans Wurst");

		antrag.setFachvorgesetzter(fachvorgesetzter);

		dataAcces.saveUrlaubsantrag(antrag);
		dataAcces.closeEntityManagerAndFactory();
	}
}
