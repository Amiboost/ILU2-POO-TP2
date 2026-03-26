package frontiere;

import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		if (!controlAcheterProduit.estHabitant(nomAcheteur)) {
			System.out.println("Je suis désolée " + nomAcheteur + " mais il faut être un habitant de notre village pour commercer ici.");
		} else {
			String produit = Clavier.entrerChaine("Quel produit voulez-vous acheter ?\n");
			String[] vendeurs = controlAcheterProduit.trouverVendeurs(produit);
			if (vendeurs.length == 0) {
				System.out.println("Désolé, personne ne vend ce produit au marché.");
			} else {
				System.out.println("Chez quel commerçant voulez-vous acheter des " + produit + " ?");
				StringBuilder question = new StringBuilder();
				for (int i=0; i < vendeurs.length; i++) {
					question.append((i+1) + " - " + vendeurs[i] + "\n");
				}
				int iVendeur = Clavier.entrerEntier(question.toString()) - 1;
				System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + vendeurs[iVendeur]);
				
				
				int quantiteVoulue = Clavier.entrerEntier("Combien de " + produit + " voulez-vous acheter ?\n");
				int quantiteAchetee = controlAcheterProduit.acheterProduit(vendeurs[iVendeur], quantiteVoulue);
				if (quantiteAchetee == 0) {
					System.out.println(nomAcheteur + " veut acheter " + quantiteVoulue + " " + produit + ", malheureusement il n'y en a plus !");
				} else if (quantiteAchetee == quantiteVoulue) {
					System.out.println(nomAcheteur + " achète " + quantiteVoulue + " " + produit + " à " + vendeurs[iVendeur]);
				} else {
					System.out.println(nomAcheteur + " veut acheter " + quantiteVoulue + " " + produit + ", malheureusement " + vendeurs[iVendeur] +
							" n'en a plus que " + quantiteAchetee + ". " + nomAcheteur + " achète tout le stock de " + vendeurs[iVendeur] + ".");
				}
			}
		}
	}
}
