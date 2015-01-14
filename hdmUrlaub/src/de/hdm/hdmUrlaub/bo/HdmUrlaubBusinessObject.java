package de.hdm.hdmUrlaub.bo;

import java.io.Serializable;

/**
 * Basisklasse f&uuml;r alle Business Objects.
 * 
 * @author Fabian
 *
 */
public class HdmUrlaubBusinessObject implements Serializable {

	private static final long serialVersionUID = 6947890086626039880L;
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return (id != null) ? (this.getClass().hashCode() + id.hashCode())
				: super.hashCode();
	}

}
