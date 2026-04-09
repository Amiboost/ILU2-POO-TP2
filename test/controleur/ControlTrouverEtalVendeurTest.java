package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlTrouverEtalVendeurTest {
	private Village village;
	private Chef abraracourcix;
	private Gaulois bonemine;
	
	@BeforeEach
	private void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);		
		bonemine = new Gaulois("Bonemine", 5);
		village.ajouterHabitant(bonemine);
	}

	@Test
	void testControlTrouverEtalVendeur() {
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		assertNotNull(controlTrouverEtalVendeur, "Constructeur ne renvoie pas null");
	}

	@Test
	void testTrouverEtalVendeur() {
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		assertNull(controlTrouverEtalVendeur.trouverEtalVendeur("Existe pas"));
		assertNull(controlTrouverEtalVendeur.trouverEtalVendeur(abraracourcix.getNom()));
		village.installerVendeur(abraracourcix, "pommes", 7);
		assertNotNull(controlTrouverEtalVendeur.trouverEtalVendeur(abraracourcix.getNom()));
		assertNull(controlTrouverEtalVendeur.trouverEtalVendeur(bonemine.getNom()));
		village.installerVendeur(bonemine, "fleurs", 3);
		assertNotNull(controlTrouverEtalVendeur.trouverEtalVendeur(bonemine.getNom()));
	}
}
