package hdmUrlaub.ldap;

import java.security.GeneralSecurityException;

import org.junit.Test;

import com.unboundid.ldap.sdk.LDAPException;

import de.hdm.hdmUrlaub.ldap.LdapAuthentificator;

/**
 * Test f�r den LDAP test
 * @author Fabian
 *
 */
public class LDAPtest {

	@Test
	public void test() throws LDAPException, GeneralSecurityException {
		LdapAuthentificator ldapAuthentificator = new LdapAuthentificator();

		try {
			String test = ldapAuthentificator.authenticate("fr031", "beispiel");
			System.out.println(test);
			
		} catch (LDAPException | GeneralSecurityException e) {
			System.out.println("Der Benutzer konnte nicht autorisiert werdens");
		}

	}
}
