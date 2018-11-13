import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.LinkedList;
import java.util.Arrays;
import java.io.IOException;
import java.io.Serializable;

public class Election implements Serializable{
	
	public int annee;
	
	/**
	 * constructeur utilisé pour initialiser une classe en fonction des 
	 * entrées de l'utilisateur
	 * 
	 * @param int annee
	 * 
	 * @author Daniel Colalillo
	 * @since 10/27/2018
	 * @version 1.0.0
	 */
    public Election(int annee){
    	this.annee = annee;
    }
    /*
     * Collection d'objet
     */
	List<Circonscription> circonscriptionCollection = new ArrayList<Circonscription>();
	
	List<String> nomsCirconscriptionCollection = new ArrayList<String>();
	
	Vector<String> nomsPartiCollection = new Vector<String>();

	List<Depute> deputeCollection = new LinkedList<Depute>();
	
	List<String> nomsDeputeCollection = new ArrayList<String>();
	
	
	int[][] index;

	/**
	 * initialise un index
	 * 
	 * @author Daniel Colalillo
	 * @author Jason Pang
	 * @since 10/30/2018
	 * @version 1.0.1
	 */
	public void genererIndex(){
	
		/*
		 * Strategie:
		 * Generer une index 2D avec les tailles des collections
		 * nomsCirconscriptionCollection et nomsPartiCollection.
		 * Initializer les valeurs de l'index a -1(VIDE).
		 * Utiliser le method peuplerIndex() pour remplir les cases
		 * de l'index.
		 */
		index = new int[nomsCirconscriptionCollection.size()][nomsPartiCollection.size()];
		//Remplir tous les cases avec -1.
		for(int[] row: index)
			Arrays.fill(row, Constantes.VIDE);
		
		peuplerIndex(index);
		
	}
	
	/**
	 * Remplir l'index avec les numero des cases des depute associe avec les
	 * partis et circonscriptions.
	 * 
	 * @param index
	 * 
	 * @author Jason Pang
	 * @since 11/1/2018
	 * @version 1.0.1
	 */
	public void peuplerIndex(int[][] index)
	{
		/*
		 * Strategie:
		 * Utiliser un for loop pour remplir l'index avec tout les Deputes.
		 */
		
		// Peupler l'index avec tout les deputes dans la collection des deputes.
		for (int i = 0; i < deputeCollection.size(); i++)
		{
			// Remplir l'index avec le numero de case du Depute.
			String nomDepute = deputeCollection.get(i).getDepute();
			index [deputeCollection.get(i).getNumCase()]
					[deputeCollection.get(i).getNumParti()]
					= nomsDeputeCollection.indexOf(nomDepute);
		}
	}
	
	/**
	 * l’ajoutez à la collection de nomsCirconscriptionCollection. Vous 
	 * retenez la position où il a été ajouté que vous retournez.
	 * 
	 * @param le String nom et le int numéro de l’élu.
	 * 
	 * @return position où il a été ajouté avec int index.
	 * 
	 * @author Daniel Colalillo
	 * @since 10/27/2018
	 * @version 1.0.0
	 */
	public int ajouterCirconscription(String nom, int numero){
		
		/*
		 * strategie: 
		 * vérifie si le nom existe en utilisant la méthode .indexOf () puis 
		 * s'il existe, retourne la position avec un index. toutefois, si 
		 * elle n’existe pas, utilisez la méthode .add pour remplir la 
		 * circonscription.
		 * 
		 */
		
		//cherche le nom entrée et l'insere dans un index
		int index = nomsCirconscriptionCollection.indexOf(nom);
		
		//si le nom ne se trouve pas dans la liste, ajoutez-y
		if(index == -1){
			
			circonscriptionCollection.add(new Circonscription(numero, nom, Constantes.VIDE));  //ajoute dans collection d'objets
			
			nomsCirconscriptionCollection.add(new String(nom));  //ajoute dans collection de noms
			
			//définir l'index de la nouvelle conscription comme dernière place du tableau
			index = circonscriptionCollection.size() - 1;
		}
		
		return index;
		
	}
		
	/**
	 * l’ajoutez à la collection de nomsPartiCollection et retournez la 
	 * position où il a été ajouté.
	 * 
	 * @param le String nom du parti.
	 * 
	 * @return position où il a été ajouté ou trouvé avec int index.
	 * 
	 * @author Daniel Colalillo
	 * @since 10/27/2018
	 * @version 1.0.0
	 */
	public int ajouterNomParti(String nomParti){
		
		/*
		 * strategie: 
		 * vérifie si le nom du parti existe en utilisant la méthode 
		 * .indexOf() puis s'il existe, retourne la position avec un index. 
		 * toutefois, si elle n’existe pas, utilisez la méthode .add pour 
		 * remplir la Parti collection.
		 * 
		 */
	
		//cherche le nom entrée et l'insere dans un index
		int index = nomsPartiCollection.indexOf(nomParti);
		
		//si le nom ne se trouve pas dans la liste, ajoutez-y
		if(index == -1){
			
			nomsPartiCollection.add(new String(nomParti));
		
			//définir l'index de la nouvelle parti comme dernière place du tableau
			index = nomsPartiCollection.size() - 1;
		}
		
		return index;
	}
	
	/**
	 * l’ajoutez à la collection de debuteCollection et retournez la 
	 * position où il a été ajouté.
	 * 
	 * @param le nom du député, le numéro de circonscription et le numéro du parti
	 * 
	 * @return position où il a été ajouté ou trouvé avec int index.
	 * 
	 * @author Daniel Colalillo
	 * @since 10/27/2018
	 * @version 1.0.0
	 */
	
	public void ajouterDepute(int circonscription, String nomDepute, int noParti){
		
		/*
		 * strategie: 
		 * vérifie si le Depute existe en utilisant la méthode 
		 * .indexOf() puis s'il existe, retourne la position avec un index. 
		 * toutefois, si elle n’existe pas, utilisez la méthode .add pour 
		 * remplir la debute collection.
		 * 
		 */
		
		//cherche le nom entrée et l'insere dans un index
		int index = deputeCollection.indexOf(nomDepute);
		
		//si le nom ne se trouve pas dans la liste, ajoutez-y
		if(index == -1){
			
			deputeCollection.add(new Depute(nomDepute, circonscription, noParti));
		
			nomsDeputeCollection.add(nomDepute);
			
			//définir l'index de la nouvelle debute comme dernière place du tableau
			index = deputeCollection.size() - 1;
		}

	}
	
	/**
	 * Obtient et retourne les noms des consciptions en forme de String array.
	 * 
	 * @return
	 * 
	 * @author Jason Pang
	 * @since 10/30/2018
	 * @version 1.0.0
	 */
	public String[] obtenirNomsCirconscription()
	{
		/*
		 * Strategie:
		 * Creer un nouveau tableau de type String de la taille de 
		 * nomsCirconscriptionCollection. Convertir la liste en tableau
		 * avec la methode toArray().
		 */
		
		// Creer un tableau pour les noms des circonscriptions
		String[] tabNomsCirconsciption = 
				new String[nomsCirconscriptionCollection.size()];
		// Convertir la liste en tableau 
		tabNomsCirconsciption = 
				nomsCirconscriptionCollection.toArray(tabNomsCirconsciption);
		
		return tabNomsCirconsciption;
	}
	
	/**
	 * Obtient et retourne les noms des partis en forme de String aray.
	 * 
	 * @return tabNomsParti
	 * 
	 * @author Jason Pang
	 * @since 10/30/2018
	 * @version 1.0.0
	 */
	public String[] obtenirNomsParti()
	{
		/*
		 * Strategie:
		 * Creer un nouveau tableau de type String de la taille de 
		 * nomsPartiCollection. Convertir la liste en tableau
		 * avec la methode toArray().
		 */
		
		// Creer un tableau pour les noms des partis
		String[] tabNomsParti = new String[nomsPartiCollection.size()];
		// Convertir la liste en tableau 
		tabNomsParti = nomsPartiCollection.toArray(tabNomsParti);
		
		return tabNomsParti;	
	}
	
	/**
	 * Obtient la liste et retourne les noms des deputes en forme de String array.
	 * 
	 * @return tabNomsDepute
	 * 
	 * @author Jason Pang
	 * @since 10/30/2018
	 * @version 1.0.0
	 */
	public String[] obtenirNomsDepute()
	{
		/*
		 * Strategie:
		 * Creer un nouveau tableau de type String de la taille de 
		 * nomsDeputeCollection. Convertir la liste en tableau
		 * avec la methode toArray().
		 */
		
		// Creer un tableau pour les noms des deputes
		String[] tabNomsDepute = new String[nomsDeputeCollection.size()];
		// Convertir la liste en tableau 
		tabNomsDepute = nomsDeputeCollection.toArray(tabNomsDepute);
			
		return tabNomsDepute;		
	}
}



