package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.EtalNonOccupeException;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;

public class ScenarioCasDegrade {

	public static void main(String[] args) throws EtalNonOccupeException, VillageSansChefException {
		Gaulois obelix = new Gaulois("Obélix", 25);
		Etal etal = new Etal();
		etal.libererEtal();

		try {
			
	        Gaulois acheteur = null; 
	        etal.acheterProduit(10, acheteur);
	    } catch (NullPointerException e) {
	        System.err.println("Erreur : " + e.getMessage());
	    }
		
		try {
			
			Gaulois acheteur = obelix;
			etal.acheterProduit(0, acheteur); // Exemple d'appel avec quantité négative
	        System.out.println("Achat réussi !");
	    } catch (IllegalArgumentException e) {
	        System.err.println("Erreur : " + e.getMessage());
	    }
		
		try {
		    
		    Gaulois acheteur = obelix; 
		    etal.acheterProduit(10, acheteur);
		    System.out.println("Achat réussi !");
		} catch (IllegalStateException e) {
		    System.err.println("Erreur : " + e.getMessage());
		}
		Village village = new Village("le village des irréductibles", 10, 5);
		village.afficherVillageois();
		System.out.println(village.afficherVillageois());
		System.out.println("Fin du test");

	}

}
