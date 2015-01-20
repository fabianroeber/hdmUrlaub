package hdmUrlaub.dbtest;

import org.junit.Test;

import de.hdm.hdmUrlaub.bo.MitarbeiterBo;
import de.hdm.hdmUrlaub.db.DataAccess;
import de.hdm.hdmUrlaub.db.mapper.MitarbeiterMapper;

public class DataBaseTestSingleMitarbeiter {

	@Test
	public void test() {

		DataAccess dataAccess = new DataAccess();
		MitarbeiterMapper mitarbeiterMapper = new MitarbeiterMapper();

		MitarbeiterBo mitarbeiter = mitarbeiterMapper.getBo(dataAccess
				.getMitarbeiterByUserName("devmode"));

		System.out.print(mitarbeiter.getEmail());

	}

}
