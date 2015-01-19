package de.hdm.hdmUrlaub.enums;

public enum Status {

	OFFEN("Genehmigung ausstehend"), GENEHMIGT("Genehmigt"), ABGELEHNT("Abgelehnt");

	private String text;

	private Status(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
