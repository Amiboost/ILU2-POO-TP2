package controleur;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ControlTrouverEtalVendeur {
	private Village village;

	public ControlTrouverEtalVendeur(Village village) {
		this.village = village;
	}

	public Etal trouverEtalVendeur(String nomVendeur) {
		Etal etal = null;
		Gaulois gaulois = village.trouverHabitant(nomVendeur);
		if (gaulois != null)
			etal = village.rechercherEtal(gaulois);
		return etal;
	}
	
	public String[] trouverVendeurs(String produit) {
		Gaulois[] vendeurs = village.rechercherVendeursProduit(produit);
		String[] result = new String[vendeurs.length];
		for (int i = 0; i < vendeurs.length; i++) {
			result[i] = vendeurs[i].getNom();
		}
		return result;
	}
}
