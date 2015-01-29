package de.hdm.hdmUrlaub.enums;

import de.hdm.hdmUrlaub.bo.UrlaubsantragBo;

/**
 * Status-Enum, dass alle möglichen Status eines {@link UrlaubsantragBo} beschreibt.
 * 
 * @author Fabian
 *
 */
public enum Status {

	OFFEN("Genehmigung ausstehend"), GENEHMIGT("Genehmigt"), ABGELEHNT(
			"Abgelehnt");

	private String text;

	private Status(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
