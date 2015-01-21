package hdmUrlaub.dbtest;

import java.util.List;

import org.junit.Test;

import de.hdm.hdmUrlaub.db.DataAccess;
import de.hdm.hdmUrlaub.db.dbmodel.Urlaubsantrag;

public class DataBaseTestUrlaubsantraegeByMitarb {

	@Test
	public void test() {
		DataAccess dataAccess = new DataAccess();

		List<Urlaubsantrag> liste = dataAccess.getAllUrlaubsantrags(9);

		for (Urlaubsantrag urlaubsantrag : liste) {
			System.out.print(urlaubsantrag.getVertretung());
		}
	}

}
