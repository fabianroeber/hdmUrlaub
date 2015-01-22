package de.hdm.hdmUrlaub.db;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import de.hdm.hdmUrlaub.db.dbmodel.Fachvorgesetzter;
import de.hdm.hdmUrlaub.db.dbmodel.Mitarbeiter;
import de.hdm.hdmUrlaub.db.dbmodel.Urlaubsantrag;
import de.hdm.hdmUrlaub.db.dbmodel.Zeitraum;

/**
 * Klasse f&uuml;r alle Datenbankzugriffe.
 * 
 * @author Fabian
 *
 */
public class DataAccess implements Serializable {

	private static final long serialVersionUID = -4014010120323805478L;

	private EntityManagerFactory entityManagerFactory = null;

	private EntityManager entityManager = null;

	/**
	 * Deffinierte Persistence Unit in der persistence.xml (config file f&uuml;r
	 * Hibernate-JPA)
	 */
	private static final String PERSISTENCEUNIT = "hdmUrlaub";

	public DataAccess() {
		getEntityManager();
	}

	/**
	 * Methode zum Abrufen aller {@link Urlaubsantrag} eines Mitarbeiters.
	 * 
	 * * @return {@link List} <{@link Urlaubsantrag}>
	 */
	public List<Urlaubsantrag> getAllUrlaubsantrags(Integer mitarbeiterId)
			throws PersistenceException {

		List<Urlaubsantrag> urlaubsantrags = entityManager
				.createQuery(
						"Select urlaubsantrag FROM Urlaubsantrag urlaubsantrag WHERE urlaubsantrag.mitarbeiter = "
								+ mitarbeiterId, Urlaubsantrag.class)
				.getResultList();

		return urlaubsantrags;
	}

	/**
	 * Methode zum Abrufen aller {@link Mitarbeiter}.
	 * 
	 * @return {@link List}<{@link Mitarbeiter}>
	 */
	public List<Mitarbeiter> getAllMitarbeiter() throws PersistenceException {

		List<Mitarbeiter> mitarbeiters = entityManager.createQuery(
				"Select mitarbeiter FROM Mitarbeiter mitarbeiter",
				Mitarbeiter.class).getResultList();

		return mitarbeiters;
	}

	public Mitarbeiter getMitarbeiterByUserName(String username)
			throws PersistenceException {

		Mitarbeiter mitarbeiter = entityManager
				.createQuery(
						"SELECT mitarbeiter FROM Mitarbeiter mitarbeiter where mitarbeiter.username = '"
								+ username + "'", Mitarbeiter.class)
				.getSingleResult();
		return mitarbeiter;
	}

	public Urlaubsantrag getUrlaubsantragByKey(String key)
			throws PersistenceException {

		Urlaubsantrag urlaubsantrag = entityManager
				.createQuery(
						"SELECT urlaubsantrag FROM Urlaubsantrag urlaubsantrag where urlaubsantrag.activationKey = '"
								+ key + "'", Urlaubsantrag.class)
				.getSingleResult();
		return urlaubsantrag;
	}

	/**
	 * Methode zum Abrufen aller Fachvorgesetzten.
	 * 
	 * @return {@link List}<{@link Fachvorgesetzter}>
	 */
	public List<Fachvorgesetzter> getAllFachvorgesetzter()
			throws PersistenceException {
		List<Fachvorgesetzter> fachvorgesetzters = entityManager
				.createQuery(
						"Select fachvorgesetzter FROM Fachvorgesetzter fachvorgesetzter",
						Fachvorgesetzter.class).getResultList();

		return fachvorgesetzters;
	}

	/**
	 * Methode zum Speichern eines {@link Urlaubsantrag}
	 * 
	 * @param urlaubsantrag
	 */
	public void saveUrlaubsantrag(Urlaubsantrag urlaubsantrag)
			throws PersistenceException {
		entityManager.getTransaction().begin();
		Urlaubsantrag urlaubsantragToSave;
		if (urlaubsantrag.getId() != null) {
			urlaubsantragToSave = entityManager.find(Urlaubsantrag.class,
					urlaubsantrag.getId());
		} else {
			urlaubsantragToSave = new Urlaubsantrag();
		}
		urlaubsantragToSave.setAnzahltage(urlaubsantrag.getAnzahltage());
		urlaubsantragToSave.setFachvorgesetzter(urlaubsantrag
				.getFachvorgesetzter());
		urlaubsantragToSave.setStatus(urlaubsantrag.getStatus());
		urlaubsantragToSave.setMitarbeiter(urlaubsantrag.getMitarbeiter());
		urlaubsantragToSave.setVertretung(urlaubsantrag.getVertretung());
		urlaubsantragToSave.setFachvorgesetzter(urlaubsantrag
				.getFachvorgesetzter());
		urlaubsantragToSave.setActivationKey(urlaubsantrag.getActivationKey());
		urlaubsantragToSave.setBegruendung(urlaubsantrag.getBegruendung());

		entityManager.persist(urlaubsantragToSave);

		Set<Zeitraum> zeitraums = urlaubsantrag.getZeitraums();

		for (Zeitraum zeitraum : zeitraums) {
			Zeitraum zeitraumToSave;
			if (zeitraum.getId() != null) {
				zeitraumToSave = entityManager.find(Zeitraum.class,
						zeitraum.getId());
			} else {
				zeitraumToSave = new Zeitraum();
			}
			zeitraumToSave.setBeginn(zeitraum.getBeginn());
			zeitraumToSave.setEnde(zeitraum.getEnde());
			zeitraumToSave.setUrlaubsantrag(urlaubsantragToSave);
			entityManager.persist(zeitraumToSave);
		}
		entityManager.getTransaction().commit();
	}

	/**
	 * Methode zum Speichern eines Mitarbeiters.
	 * 
	 * @param mitarbeiter
	 */
	public void saveMitarbeiter(Mitarbeiter mitarbeiter)
			throws PersistenceException {
		entityManager.getTransaction().begin();
		Mitarbeiter mitarbeiterToSave;
		if (mitarbeiter.getId() != null) {
			mitarbeiterToSave = entityManager.find(Mitarbeiter.class,
					mitarbeiter.getId());
		} else {
			mitarbeiterToSave = new Mitarbeiter();
		}
		mitarbeiterToSave.setNachname(mitarbeiter.getNachname());
		mitarbeiterToSave.setVorname(mitarbeiter.getVorname());
		mitarbeiterToSave.setEmail(mitarbeiter.getEmail());
		mitarbeiterToSave.setUsername(mitarbeiter.getUsername());

		entityManager.persist(mitarbeiter);
		entityManager.getTransaction().commit();
	}

	/**
	 * Methode zum L&ouml;schen eines {@link Urlaubsantrag};
	 * 
	 * @param urlaubsantrag
	 */
	public void deleteUrlaubsantrag(Urlaubsantrag urlaubsantrag)
			throws PersistenceException {
		entityManager.getTransaction().begin();
		entityManager.remove(urlaubsantrag);
		entityManager.getTransaction().commit();
	}

	/**
	 * Erstellt eine neue {@link EntityManagerFactory} und einen neuen
	 * {@link EntityManager}.
	 * 
	 * @return
	 */
	private void getEntityManager() {
		entityManagerFactory = Persistence
				.createEntityManagerFactory(PERSISTENCEUNIT);
		entityManager = entityManagerFactory.createEntityManager();
	}

	/**
	 * Schlie�t den {@link EntityManager} und die {@link EntityManagerFactory};
	 */
	public void closeEntityManagerAndFactory() {
		entityManager.close();
		entityManagerFactory.close();
	}

}
