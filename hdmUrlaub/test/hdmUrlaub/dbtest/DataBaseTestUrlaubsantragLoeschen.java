package hdmUrlaub.dbtest;

import org.junit.Test;

import de.hdm.hdmUrlaub.db.DataAccess;
import de.hdm.hdmUrlaub.db.dbmodel.Urlaubsantrag;

public class DataBaseTestUrlaubsantragLoeschen {

	@Test
	public void test() {
		
		DataAccess dataAcces = new DataAccess();
		Urlaubsantrag urlaubsantrag = dataAcces.getAllUrlaubsantrags().get(0);
		
		dataAcces.deleteUrlaubsantrag(urlaubsantrag);
	}
}
