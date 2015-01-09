package hdmUrlaub.dbtest;

import org.junit.Test;

import de.hdm.hdmUrlaub.db.DataAcces;
import de.hdm.hdmUrlaub.db.dbmodel.Urlaubsantrag;

public class DataBaseTestUrlaubsantragLoeschen {

	@Test
	public void test() {
		
		DataAcces dataAcces = new DataAcces();
		Urlaubsantrag urlaubsantrag = dataAcces.getAllUrlaubsantrags().get(0);
		
		dataAcces.deleteUrlaubsantrag(urlaubsantrag);
	}
}
