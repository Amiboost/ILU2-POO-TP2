package controleur;

import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}

	public String[] trouverVendeurs(String produit) {
		return controlTrouverEtalVendeur.trouverVendeurs(produit);
	}
	
	public boolean estHabitant(String acheteur) {
		return controlVerifierIdentite.verifierIdentite(acheteur);
	}
	
	public int acheterProduit(String nomVendeur, int quantiteVoulue) {
		return controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur).acheterProduit(quantiteVoulue);
	}
}
