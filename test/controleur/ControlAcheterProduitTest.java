package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAcheterProduitTest {
	private Village village;
	private Chef abraracourcix;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;
	private Gaulois bonemine;
	
	@BeforeEach
	private void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		bonemine = new Gaulois("Bonemine", 5);
		village.ajouterHabitant(bonemine);
	}

	@Test
	void testControlAcheterProduit() {
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		assertNotNull(controlAcheterProduit, "Constructeur ne renvoie pas null");
	}

	@Test
	void testTrouverVendeurs() {
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		assertEquals(0, controlAcheterProduit.trouverVendeurs("rien").length);
		village.installerVendeur(abraracourcix, "pommes", 15);
		assertEquals(0, controlAcheterProduit.trouverVendeurs("rien").length);
		assertEquals(1, controlAcheterProduit.trouverVendeurs("pommes").length);
		village.installerVendeur(bonemine, "pommes", 7);
		assertEquals(2, controlAcheterProduit.trouverVendeurs("pommes").length);
		Gaulois asterix = new Gaulois("Asterix", 8);
		village.ajouterHabitant(asterix);
		village.installerVendeur(asterix, "pain", 3);
		assertEquals(2, controlAcheterProduit.trouverVendeurs("pommes").length);
		assertEquals(1, controlAcheterProduit.trouverVendeurs("pain").length);
	}

	@Test
	void testEstHabitant() {
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		assertTrue(controlAcheterProduit.estHabitant(abraracourcix.getNom()));
		assertFalse(controlAcheterProduit.estHabitant("Existe pas"));
		assertTrue(controlAcheterProduit.estHabitant(bonemine.getNom()));
	}

	@Test
	void testAcheterProduit() {
		ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		assertEquals(0, controlAcheterProduit.acheterProduit("Existe pas", 1));
		village.installerVendeur(abraracourcix, "pommes", 5);
		assertEquals(0, controlAcheterProduit.acheterProduit(abraracourcix.getNom(), 0));
		assertEquals(1, controlAcheterProduit.acheterProduit(abraracourcix.getNom(), 1));
		assertEquals(4, controlAcheterProduit.acheterProduit(abraracourcix.getNom(), 7));
		assertEquals(0, controlAcheterProduit.acheterProduit(abraracourcix.getNom(), 1));
		village.partirVendeur(abraracourcix);
		assertEquals(0, controlAcheterProduit.acheterProduit(abraracourcix.getNom(), 1));
	}

}
