package hdmUrlaub.dbtest;

import java.util.List;

import org.junit.Test;

import de.hdm.hdmUrlaub.db.DataAcces;
import de.hdm.hdmUrlaub.db.dbmodel.Urlaubsantrag;

public class DataBaseTestMitarbeiter {

	@Test
	public void test() {
		DataAcces dataAcces = new DataAcces();

		List<Urlaubsantrag> liste = dataAcces.getAllUrlaubsantrags();

		for (Urlaubsantrag urlaubsantrag : liste) {
			System.out.println(urlaubsantrag.getId());
			System.out.println(urlaubsantrag.getMitarbeiter().getNachname());
		}

	}

}
