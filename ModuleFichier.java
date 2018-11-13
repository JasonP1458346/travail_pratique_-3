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
 * Module utilitaire qui permet de récupérer et de sauvegarder les données des
 * maîtres, des engagements et des assignations.
 *
 * (voir énoncé tp1A18INF111).
 *
 * @author Pierre Bélisle
 * @revisor Mélanie Lord
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
	    * M�thode utilitaire priv�e qui permet d'obtenir un fichier s�lectionn� par
	    * l'utilisateur. L'extension ne doit pas contenir le "."
	    *
	    * @param description Appara�t dans "type de fichier" pour guider
	    * l'utilisateur.
	    *
	    * @param extension Les 3 lettres en suffixe au point d'un nom de fichier.
	    *
	    * @param type : OUVRE ou SAUVE Sert � avoir le bon bouton dans le
	    * JFileChooser, selon le type on a "ouvrir" ou "enregistrer".
	    *
	    * @return null si le nom n'est pas valide ou si annul�.
	    */
	   private static File obtenirFic(String description, String extension, 
			   int type) {

	      /*
	       * Strat�gie : On utilise le JFileChooser de javax.swing selon 
	       * le type (SAUVE ou OPEN) re�ue.
	       * 
	       * FileNameExtensionFilter permet de filtrer les extensions.
	       */
	      
	      //Cr�ation du s�lectionneur de fichier (r�pertoire courant).
	      JFileChooser fc = new JFileChooser(".");

	      File fic = null;
	      int reponse;

	      
	      
	      //On filtre seulement les fichiers avec l'extension fournie
	      FileNameExtensionFilter filter
	              = new FileNameExtensionFilter(extension, extension);

	      fc.setDialogTitle(description);
	      fc.addChoosableFileFilter(filter);
	      fc.setFileFilter(filter);

	      //On obtient le nom du fichier � ouvrir en lecture ou en �criture?
	      if (type == OPEN) {
	         reponse = fc.showOpenDialog(null);
	      } else {
	         reponse = fc.showSaveDialog(null);
	      }

	      //On obtient le fichier seulement si le fichier a �t� choisi
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
	       * Strat�gie : On utilise  un FileOutputStream qui permet d'�crire
	       * les donn�es de l'election d'un coup.
	       */
	      
	      File fic
	              = obtenirFic("Nom du fichier binaire + l'extension (.bin)",
	                      "bin",
	                      SAUVE);

	      if (fic != null) {
	         ObjectOutputStream tampon = null;

	         try {
	            //Cr�e le fichier et ouverture du tampon d'�criture     
	            FileOutputStream tamponFic = new FileOutputStream(fic);
	            tampon = new ObjectOutputStream(tamponFic);

	            //  �criture et fermeture.
	            tampon.writeObject(election);
	            tampon.close();

	         } catch (FileNotFoundException e1) {

	            e1.printStackTrace();

	         // Une erreur de lecture, on d�truit le fichier si on a eu
	         // le temps de le cr�er.
	         } catch (IOException e) {

	            // On obtient le chemin du fichier pour le d�truire.
	            Path path
	                    = FileSystems.getDefault().getPath(fic.getName());

	            // Destruction du fichier ouvert (ou cr��) s'il y a un probl�me.
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
		    * Strat�gie : On utilise  un FileInputStream qui permet de lire
		    * les donn�es de l'election d'un coup.
		    */
		   
		   Election election = null;
		   
		   File fic
		              = obtenirFic("Nom du fichier binaire + l'extension (.bin)",
		                      "bin",
		                      OPEN);

		   if (fic != null) {
		      ObjectInputStream tampon = null;

		      try {
		         //Cr�e le fichier et ouverture du tampon d'�criture     
		         FileInputStream tamponFic = new FileInputStream(fic);
		         tampon = new ObjectInputStream(tamponFic);

		         //  Lire et fermeture.
		         election = (Election)tampon.readObject();
		         tampon.close();

		      } catch (ClassNotFoundException e1) {

		         e1.printStackTrace();

		      // Une erreur de lecture, on d�truit le fichier si on a eu
		      // le temps de le cr�er.
		      } catch (IOException e) {

		         // On obtient le chemin du fichier pour le d�truire.
		         Path path
		                 = FileSystems.getDefault().getPath(fic.getName());

		         // Destruction du fichier ouvert (ou cr��) s'il y a un probl�me.
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
