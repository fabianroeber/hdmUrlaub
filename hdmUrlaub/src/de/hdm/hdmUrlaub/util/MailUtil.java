package de.hdm.hdmUrlaub.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import de.hdm.hdmUrlaub.bo.FachvorgesetzterBo;
import de.hdm.hdmUrlaub.bo.UrlaubsantragBo;
import de.hdm.hdmUrlaub.bo.ZeitraumBo;
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
	 * 
	 */
	public static void sendRequestMail(UrlaubsantragBo urlaubsantragBo) {
		String host = "mx.freenet.de";
		final String username = "markusschmieder@freenet.de";
		final String password = "hdmurlaub";

		String to = urlaubsantragBo.getFachvorgesetzter().getEmail();
		String from = "markusschmieder@freenet.de";

		String zeitraueme = "";
		for (ZeitraumBo zeitraumBo : urlaubsantragBo.getZeitraums()) {
			zeitraueme = zeitraueme
					+ " \n "
					+ "- "
					+ (zeitraumBo.getBeginn().compareTo(zeitraumBo.getEnde()) == 0 ? " am "
							+ zeitraumBo.getBeginnAsString()
							: " von " + zeitraumBo.getBeginnAsString()
									+ " bis " + zeitraumBo.getEndeAsString());
		}

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", host);



		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(from);
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("HdM Urlaub: Urlaubsantrag");

			message.setText("Hallo, \n \n"
					+ urlaubsantragBo.getMitarbeiter().getVorname()
					+ " "
					+ urlaubsantragBo.getMitarbeiter().getNachname()
					+ " ("
					+ urlaubsantragBo.getMitarbeiter().getEmail()
					+ ") beantragt "
					+ urlaubsantragBo.getAnzahltage()
					+ " Tage Urlaub "
					+ (urlaubsantragBo.getZeitraums().size() > 1 ? "in den Zeiträumen: \n "
							: "im Zeitraum: \n")
					+ zeitraueme
					+ "\n \n"
					+ (urlaubsantragBo.getVertretung() != "" ? "Vertretung: "
							+ urlaubsantragBo.getVertretung() : "")
					+ "\n \n"
					+ "Um den Urlaubsantrag zu genehmigen oder abzulehnen folgende Seite aufrufen: http://localhost:8080/hdmUrlaub/confirmpage.xhtml?key"
					+ urlaubsantragBo.getKey());

			Transport.send(message);

			System.out.println("Sent message successfully.");
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	private javax.mail.internet.InternetAddress InternetAddress(String string) {
		// TODO Auto-generated method stub
		return null;
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
	 * Schickt eine Mail an den Mitarbeiter, dass sein Urlaubsantrag
	 * best&auml;tigt wurde.
	 */
	public static void sendConfirmedMail() {

	}

}
