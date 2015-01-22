package hdmUrlaub.dbtest;

import org.junit.Test;

import de.hdm.hdmUrlaub.db.DataAccess;
import de.hdm.hdmUrlaub.db.dbmodel.Urlaubsantrag;

public class DataBaseTestUrlaubsantragByKey {

	@Test
	public void test() {
		DataAccess dataAccess = new DataAccess();
		Urlaubsantrag urlaubsantrag = dataAccess
				.getUrlaubsantragByKey("ebc76ed9-64dc-4392-aa5d-ecdf68419c89");
		System.out.println(urlaubsantrag.getActivationKey());
	}
}
