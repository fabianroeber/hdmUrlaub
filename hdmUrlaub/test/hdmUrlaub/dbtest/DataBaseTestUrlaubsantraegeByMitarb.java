package hdmUrlaub.dbtest;

import java.util.List;

import org.junit.Test;

import de.hdm.hdmUrlaub.bo.UrlaubsantragBo;
import de.hdm.hdmUrlaub.db.DataAccess;
import de.hdm.hdmUrlaub.db.dbmodel.Urlaubsantrag;
import de.hdm.hdmUrlaub.db.mapper.UrlaubsantragMapper;

public class DataBaseTestUrlaubsantraegeByMitarb {

	@Test
	public void test() {
		DataAccess dataAccess = new DataAccess();
		UrlaubsantragMapper uam = new UrlaubsantragMapper();

		List<UrlaubsantragBo> liste = uam.getBoList(dataAccess.getAllUrlaubsantrags(9));

		for (UrlaubsantragBo urlaubsantrag : liste) {
			System.out.print(urlaubsantrag.getVertretung());
			System.out.print(urlaubsantrag.getStatus());
		}
	}

}
