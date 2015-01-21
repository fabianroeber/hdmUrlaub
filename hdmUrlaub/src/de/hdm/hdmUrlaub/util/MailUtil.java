package de.hdm.hdmUrlaub.util;

import de.hdm.hdmUrlaub.bo.FachvorgesetzterBo;
import de.hdm.hdmUrlaub.bo.UrlaubsantragBo;
import de.hdm.hdmUrlaub.db.dbmodel.Urlaubsantrag;

/**
 * Diese Klasse versendet Mails zur Genehmigung eines {@link Urlaubsantrag}s
 * durch einen {@link FachvorgesetzterBo}
 * 
 * @author Fabian
 *
 */
public class MailUtil {

	/**
	 * Schickt eine Mail an den Fachvorgesetzten und den Mitarbeiter zur
	 * Genehmigung.
	 * 
	 * @param urlaubsantragBo
	 */
	public static void sendRequestMail(UrlaubsantragBo urlaubsantragBo) {

	}

	/**
	 * Schickt eine Mail an Fachvorgesetzten und Mitarbeiter zur Information,
	 * dass der Antrag gel&ouml;scht wurde.
	 * 
	 * @param urlaubsantragBo
	 */
	public static void sendCancellationMail(UrlaubsantragBo urlaubsantragBo) {

	}

	/**
	 * Schickt eine Mail an den Mitarbeiter, dass sein Urlaubsantrag abgelehnt
	 * wurde.
	 */
	public static void sendDeclinedMail() {

	}

	/**
	 * Schickt eine Mail an den Mitarbeiter, dass sein Urlaubsantrag best&auml;tigt
	 * wurde.
	 */
	public static void sendConfirmedMail() {

	}

}
