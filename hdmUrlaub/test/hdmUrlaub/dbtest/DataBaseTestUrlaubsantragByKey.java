package hdmUrlaub.dbtest;

import org.junit.Test;

import de.hdm.hdmUrlaub.db.DataAccess;
import de.hdm.hdmUrlaub.db.dbmodel.Urlaubsantrag;

public class DataBaseTestUrlaubsantragByKey {

	@Test
	public void test() {
		DataAccess dataAccess = new DataAccess();
		Urlaubsantrag urlaubsantrag = dataAccess
				.getUrlaubsantragByKey("76a4bcab-a52d-4597-bd1c-822d30938782");
		System.out.println(urlaubsantrag.getActivationKey());
	}
}
