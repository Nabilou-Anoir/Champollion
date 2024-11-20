package champollion;

import org.junit.jupiter.api.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;

	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");
	}


	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
				"Un nouvel enseignant doit avoir 0 heures prévues");
	}

	@Test
	public void testAjouteHeures() {
		// 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		assertEquals(10, untel.heuresPrevuesPourUE(uml),
				"L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

		// 20h TD pour UML
		untel.ajouteEnseignement(uml, 0, 20, 0);

		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
				"L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");

	}
	@Test
	void testSousService() {
		assertTrue(untel.enSousService());
		untel.ajouteEnseignement(uml, 100, 92, 0);
	}
	@Test
	public void testVolNeg() {
		try {
			untel.ajouteEnseignement(uml, -2, 2, 2);
			fail();
		} catch (IllegalArgumentException e) {

		}
	}
	@Test
	void testServiceUe() {
		assertNull(untel.getServicePrevuFromUE(uml));
		untel.ajouteEnseignement(uml, 20, 15, 10);
		assertEquals(untel.getServicePrevus().iterator().next(), untel.getServicePrevuFromUE(uml));
		assertNull(untel.getServicePrevuFromUE(java));
	}

	@Test
	void testAjoutInter() {
		try {
			untel.ajouteIntervention(new Intervention(new Date(), 2, null, TypeIntervention.TP, untel, uml));
			fail();
		} catch (IllegalArgumentException a) {

		}

	}
	@Test
	void testAjoutInt(){
		untel.ajouteEnseignement(uml, 20, 20, 200);
		Intervention inter =  new Intervention(new Date(), 2, null, TypeIntervention.TP, untel, uml);
		untel.ajouteIntervention(inter);
		assertEquals(inter, untel.getIntervensions().iterator().next(), "L'intervention n'a pas été correctement ajoutée.");
	}
}