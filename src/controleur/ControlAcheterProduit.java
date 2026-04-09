package controleur;

import personnages.Gaulois;
import villagegaulois.Etal;
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
		Gaulois[] vendeurs = village.rechercherVendeursProduit(produit);
		String[] result = new String[0];
		if (vendeurs != null) {
			result = new String[vendeurs.length];
			for (int i = 0; i < vendeurs.length; i++) {
				result[i] = vendeurs[i].getNom();
			}
		}
		return result;
	}
	
	public boolean estHabitant(String acheteur) {
		return controlVerifierIdentite.verifierIdentite(acheteur);
	}
	
	public int acheterProduit(String nomVendeur, int quantiteVoulue) {
		int result = 0;
		Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
		if (etal != null)
			result = etal.acheterProduit(quantiteVoulue);
		return result;
	}
}
