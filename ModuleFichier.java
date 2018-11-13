import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.util.Scanner;

/**
 * Module utilitaire qui permet de rÃ©cupÃ©rer et de sauvegarder les donnÃ©es des
 * maÃ®tres, des engagements et des assignations.
 *
 * (voir Ã©noncÃ© tp1A18INF111).
 *
 * @author Pierre BÃ©lisle
 * @revisor MÃ©lanie Lord
 * @version Automne 2018
 */
public class ModuleFichier {
	
	 public static final int SAUVE = 0;
	 public static final int OPEN = 1;
	 
	 /**
	  * Demander l'utilisateur pour le fichier de l'election et remplir
	  * les collections de l'election avec l'information dans ce fichier.
	  * 
	  * @param election
	  * 
	  * @author Daniel Colalillo
	  * @author Jason Pang
	  * @since 10/30/2018
	  * @version 1.0.1
	  */
	 public static void getElection(Election election){
		 
		  /*
		   * Strategie: 
		   * Demander l'utilisateur pour le fichier le l'election en utilisant
		   * la methode obtenirFic(). Lire chaque ligne du fichier et remplir
		   * les collection d'objet du Election en utilisant les methode
		   * ajouterDepute, ajouterNomParti et ajouterCirconsciption.
		   */
		 
		 // Demander l'utilisateur pour le fichier
		 File fic = obtenirFic("Veuillez choisir un fichier d'election", "txt", OPEN);
		 
		 try
		 {
			 Scanner fichier = new Scanner(fic);
			 
			 // Verifier s'il y a une prochaine ligne.
			 while(fichier.hasNextLine())
			 {
				 // Obtenir la prochaine ligne.
				 String stringTmp = fichier.nextLine();
				 //separer l'information apres chaque tab
				 String[] tabTmp = stringTmp.split("\t");
				 
				 // Obtenir le nom complet du Depute.
				 String nomDepute = tabTmp[3] + tabTmp[4];
				 
				 // Obtenir le numero du Circonscription.
				 int numero = Integer.parseInt(tabTmp[0]);
				 
				 /*
				  * Ajouter les information du fichier a les collections 
				  * du l'election.
				  */
				 int noParti = election.ajouterNomParti(tabTmp[2]);
				 
				 int noCirc = election.ajouterCirconscription(tabTmp[1], numero);
				 
				 election.ajouterDepute(noCirc, nomDepute, noParti);
			 }
			 
			 fichier.close();
			 
		 } catch(FileNotFoundException e){
			 e.printStackTrace();
		 }
		 
		 
	 }
	 
	/**
	    * Méthode utilitaire privée qui permet d'obtenir un fichier sélectionné par
	    * l'utilisateur. L'extension ne doit pas contenir le "."
	    *
	    * @param description Apparaît dans "type de fichier" pour guider
	    * l'utilisateur.
	    *
	    * @param extension Les 3 lettres en suffixe au point d'un nom de fichier.
	    *
	    * @param type : OUVRE ou SAUVE Sert à avoir le bon bouton dans le
	    * JFileChooser, selon le type on a "ouvrir" ou "enregistrer".
	    *
	    * @return null si le nom n'est pas valide ou si annulé.
	    */
	   private static File obtenirFic(String description, String extension, 
			   int type) {

	      /*
	       * Stratégie : On utilise le JFileChooser de javax.swing selon 
	       * le type (SAUVE ou OPEN) reçue.
	       * 
	       * FileNameExtensionFilter permet de filtrer les extensions.
	       */
	      
	      //Création du sélectionneur de fichier (répertoire courant).
	      JFileChooser fc = new JFileChooser(".");

	      File fic = null;
	      int reponse;

	      
	      
	      //On filtre seulement les fichiers avec l'extension fournie
	      FileNameExtensionFilter filter
	              = new FileNameExtensionFilter(extension, extension);

	      fc.setDialogTitle(description);
	      fc.addChoosableFileFilter(filter);
	      fc.setFileFilter(filter);

	      //On obtient le nom du fichier à ouvrir en lecture ou en écriture?
	      if (type == OPEN) {
	         reponse = fc.showOpenDialog(null);
	      } else {
	         reponse = fc.showSaveDialog(null);
	      }

	      //On obtient le fichier seulement si le fichier a été choisi
	      if (reponse == JFileChooser.APPROVE_OPTION) {
	         fic = fc.getSelectedFile();
	      }

	      return fic;
	   }
	   
	   /**
	    * Le SP principal pour la sauvegarde du fichier binaire
	    *
	    * @param election
	    */
	   public static void sauverFichierBinaire(Election election) {

	      /*
	       * Stratégie : On utilise  un FileOutputStream qui permet d'écrire
	       * les données de l'election d'un coup.
	       */
	      
	      File fic
	              = obtenirFic("Nom du fichier binaire + l'extension (.bin)",
	                      "bin",
	                      SAUVE);

	      if (fic != null) {
	         ObjectOutputStream tampon = null;

	         try {
	            //Crée le fichier et ouverture du tampon d'écriture     
	            FileOutputStream tamponFic = new FileOutputStream(fic);
	            tampon = new ObjectOutputStream(tamponFic);

	            //  Écriture et fermeture.
	            tampon.writeObject(election);
	            tampon.close();

	         } catch (FileNotFoundException e1) {

	            e1.printStackTrace();

	         // Une erreur de lecture, on détruit le fichier si on a eu
	         // le temps de le créer.
	         } catch (IOException e) {

	            // On obtient le chemin du fichier pour le détruire.
	            Path path
	                    = FileSystems.getDefault().getPath(fic.getName());

	            // Destruction du fichier ouvert (ou créé) s'il y a un problème.
	            try {
	               tampon.close();
	               Files.delete(path);

	            } catch (IOException e1) {
	               e1.printStackTrace();
	            }

	            e.printStackTrace();
	         }
	      }
	   }
	   
	   /**
	    * Le SP principal pour la recuperation du fichier binaire
	    *
	    * @return election
	    */
	   public static Election getElectionBinaire()
	   {
		   
		   /*
		    * Stratégie : On utilise  un FileInputStream qui permet de lire
		    * les données de l'election d'un coup.
		    */
		   
		   Election election = null;
		   
		   File fic
		              = obtenirFic("Nom du fichier binaire + l'extension (.bin)",
		                      "bin",
		                      OPEN);

		   if (fic != null) {
		      ObjectInputStream tampon = null;

		      try {
		         //Crée le fichier et ouverture du tampon d'écriture     
		         FileInputStream tamponFic = new FileInputStream(fic);
		         tampon = new ObjectInputStream(tamponFic);

		         //  Lire et fermeture.
		         election = (Election)tampon.readObject();
		         tampon.close();

		      } catch (ClassNotFoundException e1) {

		         e1.printStackTrace();

		      // Une erreur de lecture, on détruit le fichier si on a eu
		      // le temps de le créer.
		      } catch (IOException e) {

		         // On obtient le chemin du fichier pour le détruire.
		         Path path
		                 = FileSystems.getDefault().getPath(fic.getName());

		         // Destruction du fichier ouvert (ou créé) s'il y a un problème.
		         try {
		            tampon.close();
		            Files.delete(path);

		         } catch (IOException e1) {
		            e1.printStackTrace();
		         }

		            e.printStackTrace();
		      }
		   }
		   return election;
	   }
   
}
