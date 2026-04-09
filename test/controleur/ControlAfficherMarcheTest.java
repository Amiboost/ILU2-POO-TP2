package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAfficherMarcheTest {
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
	void testControlAfficherMarche() {
		ControlAfficherMarche controlAfficherMarche = new ControlAfficherMarche(village);
		assertNotNull(controlAfficherMarche, "Constructeur ne renvoie pas null");
	}

	@Test
	void testDonnerInfosMarche() {
		ControlAfficherMarche controlAfficherMarche = new ControlAfficherMarche(village);
		assertNotNull(controlAfficherMarche.donnerInfosMarche());
		village.installerVendeur(abraracourcix, "pommes", 10);
		assertNotNull(controlAfficherMarche.donnerInfosMarche());
		village.installerVendeur(bonemine, "poires", 5);
		assertNotNull(controlAfficherMarche.donnerInfosMarche());
		village.partirVendeur(bonemine);
		assertNotNull(controlAfficherMarche.donnerInfosMarche());
	}

}
