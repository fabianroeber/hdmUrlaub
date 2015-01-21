package mail;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import de.hdm.hdmUrlaub.bo.FachvorgesetzterBo;
import de.hdm.hdmUrlaub.bo.MitarbeiterBo;
import de.hdm.hdmUrlaub.bo.UrlaubsantragBo;
import de.hdm.hdmUrlaub.bo.ZeitraumBo;
import de.hdm.hdmUrlaub.util.MailUtil;


/**
 * Test, der eine Mail versendet
 * 
 * @author Markus
 *
 */
public class MailTest {

	UrlaubsantragBo urlaubsantragBo = new UrlaubsantragBo(10001, "Marc Hauck",
			new FachvorgesetzterBo(10006, "Engstler", "Martin",
					"markusschmieder@gmx.de"), 5, new MitarbeiterBo(100005,
					"Markus", "Schmieder", "ms332@hdm-stuttgart.de"));
	
	
	
	List<ZeitraumBo> list = null;
	
	
	@Test
	public void test() {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2015);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date date1 = cal.getTime();
		
		cal.set(Calendar.YEAR, 2015);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 5);
		Date date2 = cal.getTime();
		
		ZeitraumBo zeitraum = new ZeitraumBo(1, date1, date2);
		
		//Scheißdreck
		list.add(zeitraum);
		urlaubsantragBo.setZeitraums(list);
		
		MailUtil mailUtil = new MailUtil();
		MailUtil.sendRequestMail(urlaubsantragBo);
	}

}
