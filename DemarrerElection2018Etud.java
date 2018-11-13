

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.util.ArrayList;
import java.util.List;

/*
 * Programme pour suivre les �lections du Qu�bec en 2018
 * 
 * @author Pierre B�lisle
 * @version Copyright A2018
 * 
 * @revision Daniel Colalillo
 */
public class DemarrerElection2018Etud {

	public static void main(String[] args) {
		
		String[] tabMenuPremierFois = {"Ouvrir fichier texte", 
				                       "Ouvrir fichier binaire"};
		
		String[] tabMenuOptions = 
			{"Voir tous les d�put�s d�une circonscription",
				"Voir tous les d�put�s d�un parti",
				"Voir le parti et la circonscription d�un d�put�",
				"Quitter"};
		
		
		preparerPourMac();
		
		/*
		 * Les donn�es sont charg�es en m�moire.
		 */
		Election election = new Election(Constantes.ANNEE_ELECTION);
		
		String str = (String) JOptionPane.showInputDialog(null, 
				                    "S�lectionnez le type de fichier", 
				                    "Type de fichier (texte ou binaire?)", 
				                    0, null, tabMenuPremierFois, 0);
		
		/*
		 * 
		 * � ex�cuter la premi�re fois.
		 * 
		 */				
		if(str.equals(tabMenuPremierFois[0])) {
			
			ModuleFichier.getElection(election);
			election.genererIndex();
			ModuleFichier.sauverFichierBinaire(election);
			//election.genererIndex();
		}
		/* A �x�cuter seulement apr�s que le fichier texte a �t�  ouvert
		 * et sauvegarder en binaire.
		 */
		else {
			
			election = ModuleFichier.getElectionBinaire();
		}		
		
		String options = null;
		
		//laisser l'utilisateur choisir une option et la sauvegarder
		options = (String) JOptionPane.showInputDialog(null, 
                "S�lectionnez le parametre que vous voulez voir", 
                "Type de parametre (d�put�s d�une circonscription,"
                + " d�put�s d�un parti , "
                + "parti et la circonscription d�un d�put�, ou quitter?)", 
                0, null, tabMenuOptions, 0);
		
		//Voir tous les d�put�s d�une circonscription
		if(options.equals(tabMenuOptions[0])) {
			
			nomCirconscription(election);
			
		}
		
		//Voir tous les d�put�s d�un parti
		else if(options.equals(tabMenuOptions[1])){
			
			nomParti(election);
			
		}
		
		else if(options.equals(tabMenuOptions[2])){
			
			nomsDepute(election);
			
		}
		
		else if(options.equals(tabMenuOptions[3])){
			
			quitter(election);
			
		}				
	}
	/**
	 * affiche le nom et le parti des membres de la conscription choisie.
	 * 
	 * @param collection election de type Election
	 * 
	 * @author Daniel Colalillo
	 * @since 11/6/2018
	 * @version 1.0.0
	 */
	public static void nomCirconscription(Election election){
		
		/*
		 * strategie: 
		 * demander � l'utilisateur une conscription, 
		 * v�rifier le nombre de d�put�s dans la conscription 
		 * et afficher les d�put�s associ�s et leurs partis � 
		 * l'aide d'une boucle pour remplir un tableau.
		 * 
		 */
		
		
		String options = null;
		
		int optionChoisi = 0;
		
		List<String> nomDeputeParti = new ArrayList<String>();
		
		nomDeputeParti.add("nom Depute \t numero de parti\n");
		
		//demander � l'utilisateur une conscription et stocker la r�ponse dans 
		//des options
		options = JOptionPane.showInputDialog(null, 
				"choisi un circonscription", "circonscription", 
				0, null, election.obtenirNomsCirconscription(), 0).toString();
		
		//convertir le nombre en son �quivalent de cha�ne
		optionChoisi = election.nomsCirconscriptionCollection.indexOf(options);
		
		for( int i = 0; i < election.nomsPartiCollection.size(); i++)
		{
			//si l'index est vide ajouter pas une nouvelle cha�ne
			if(election.index[optionChoisi][i] != Constantes.VIDE){
				
				//ajouter la partie index�e avec le nom du d�put� en 
				//utilisant la m�thode de l'index pour obtenir la position
				//des d�put�s
				nomDeputeParti.add(new String(election.deputeCollection.get
						(election.index[optionChoisi][i]).getDepute()) + 
						election.nomsPartiCollection.get(i));
			}
			
		}
		
		//afficher la liste des d�put�s et de leurs partis en fonction de 
		//la conscription s�lectionn�e
		JOptionPane.showMessageDialog(null, nomDeputeParti.toArray(), 
				"parti et depute", 0);
		
	}
	
	/**
	 * Afficher les noms de parti. Afficher les noms des deputes qui appartient
	 * au parti choisi part l'utilisateur.
	 * 
	 * @param election
	 * 
	 * @author Jason Pang
	 * @since 11/6/2018
	 * @version 1.0.1
	 */
	public static void nomParti(Election election)
	{
		/*
		 * Strategie:
		 * Utiliser les methodes de JOptionPane pour afficher la liste des
		 * partis et affichier les noms des deputes. Utiliser indexOf() pour
		 * savoir la position du parti dans la liste de parti et obtenir les 
		 * noms des depute.
		 */
		
		String options = null;
		
		List<String> nomsDepute = new ArrayList<String>();
		
		//Demander l'utilisateur de choisir un parti
		options = JOptionPane.showInputDialog(null, 
                "choisi un Parti", "Parti", 0, null, 
                election.obtenirNomsParti(), 0).toString();
		
		//Obtenir la numero du parti. 
		int numParti = election.nomsPartiCollection.indexOf(options);
		
		/*
		 * Remplir la liste avec les noms des depute associer avec le parti
		 */
		for (int i = 0; i < election.circonscriptionCollection.size(); i++)
		{
			//Verifier si l'index east vide.
			if(election.index[i][numParti] != Constantes.VIDE)
			{
				nomsDepute.add(new String(election.nomsDeputeCollection.get
						(election.index[i][numParti])));
			}
		}
		
		//Affichier les deputes du Parti
		JOptionPane.showMessageDialog(null, nomsDepute.toArray(), 
				"Les Deputes de " + options, 0);
	}
	
	/**
	 * Afficher les noms des Deputes. Afficher la circonscription et la parti
	 * des deputes qui appartient au depute choisi part l'utilisateur.
	 * 
	 * @param election
	 * 
	 * @author Jason Pang
	 * @since 11/6/2018
	 * @version 1.0.1
	 */
	public static void nomsDepute(Election election)
	{
		/*
		 * Strategie:
		 * Utiliser les methodes de JOptionPane pour afficher la liste des
		 * deputes et affichier la circonscription et la parti du deputes.
		 * Utiliser indexOf() pour savoir la position du depute dans la liste
		 * de depute et obtenir leur circonscription et leur parti. 
		 */
		
		String options = null;
		
		//Demander l'utlisateur de choisir un Depute
		options = JOptionPane.showInputDialog(null, 
                "choisi un Depute", "Depute", 
                0, null, election.obtenirNomsDepute(), 0).toString();
		
		//Obtenir la numero de Depute.
		int depute = election.nomsDeputeCollection.indexOf(options);
		
		//Obtenir la circonscription du depute.
		int noCirc = election.deputeCollection.get(depute).getNumCase();
		//Obtenir la parti du depute.
		int noParti = election.deputeCollection.get(depute).getNumParti();
		
		String information = 
				"Circonscription: \n" +
				election.nomsCirconscriptionCollection.get(noCirc) + 
				"\nParti: \n" + 
				election.nomsPartiCollection.get(noParti);
		
		//Afficher la circonscription et parti du Depute.
		JOptionPane.showMessageDialog(null, information, options + ": \n", 0);
	}
	
	/**
	 * quitte le programme et affiche un message notifiant l'utilisateur
	 * 
	 * @param collection election de type Election
	 * 
	 * @author Daniel Colalillo
	 * @since 11/6/2018
	 * @version 1.0.0
	 */
	public static void quitter(Election election){
		
		/*
		 * strategie: 
		 * affiche un message en utilisant joptionpane puis quitte le 
		 * programme en utilisant system.exit
		 * 
		 */
		
		
		JOptionPane.showMessageDialog(null, "quitter programme", "quitter", 0);
		
		//quitte le programme
		System.exit(0);
			
	}
	
	/*
	 * N�cessaire � JOptionPane sur un Mac
	 */
	public static void preparerPourMac() {

		try {
			
	         UIManager.setLookAndFeel(
	        		 UIManager.getCrossPlatformLookAndFeelClassName());
	         
	      } catch (Exception e) {  
	         e.printStackTrace();
	      }
	}
}
