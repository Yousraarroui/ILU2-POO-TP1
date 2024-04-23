package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public class Marche{
		private Etal[] etals;
		
		public Marche(int nombreEtals) {
			etals = new Etal[nombreEtals];
			for (int i = 0; i < nombreEtals; i++) {
				etals[i] = new Etal();
			}
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (indiceEtal >= 0 && indiceEtal< etals.length) {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			}
		}
		
		public int trouverEtalLibre() {
			//conçue pour trouver le premier étal libre (non occupé) dans le marché
			for (int i = 0; i < etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1; // aucun etal n'est disponible
		}
		
		public Etal[] trouverEtals(String produit) {
			// permet de rechercher tous les étals qui contiennent un certain produit donné.
		    int count = 0;//détermine combien d'étals remplissent les critères spécifiés.
		    for (int i = 0; i < etals.length; i++) {
		        if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
		            count++;
		        }
		    }
		    
		    Etal[] result = new Etal[count];//creation d'un tableau de taille count
		    int resultIndex = 0;
		    
		    for (int i = 0; i < etals.length; i++) {
		        if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
		            result[resultIndex] = etals[i];
		            resultIndex++;
		        }
		    }
		    
		    return result;// retourne un tableau contenant tous les étals où l’on vend un produit.
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			//recherche un étal occupé par un vendeur spécifique 
		    for (int i = 0; i < etals.length; i++) {
		        if (etals[i].isEtalOccupe() && etals[i].getVendeur() == gaulois) {
		            return etals[i];
		        }
		    }
		    return null;
		}

		
		public String afficherMarche() {
			//donne une vue d'ensemble de l'état actuel du marché.
		    StringBuilder chaine = new StringBuilder();
		    int nbEtalVide = 0;
		    
		    for (int i = 0; i < etals.length; i++) {
		        if (etals[i].isEtalOccupe()) {
		            chaine.append(etals[i].afficherEtal() + "\n");
		        } else {
		            nbEtalVide++;
		        }
		    }
		    
		    if (nbEtalVide > 0) {
		        chaine.append("Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n");
		    }
		    
		    return chaine.toString();
		}
		
		
	}
}