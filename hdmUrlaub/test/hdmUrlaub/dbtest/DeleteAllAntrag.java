package hdmUrlaub.dbtest;

import java.util.List;

import org.junit.Test;

import de.hdm.hdmUrlaub.db.DataAccess;
import de.hdm.hdmUrlaub.db.dbmodel.Urlaubsantrag;

public class DeleteAllAntrag {

	@Test
	public void test() {
	
	DataAccess dataAccess = new DataAccess();
	
	List<Urlaubsantrag> list = dataAccess.getAllUrlaubsantrags(9);
	
	for (Urlaubsantrag urlaubsantrag : list) {
		dataAccess.deleteUrlaubsantrag(urlaubsantrag);
	}
 		
	}

}
