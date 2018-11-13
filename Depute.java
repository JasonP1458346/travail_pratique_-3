

import java.io.Serializable;


public class Depute implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	private String nomDuDepute;
	private int numeroDeCase;
	private int numeroDuParti;
	
	/**
	 * Initialization des variables privé du classe Depute avec des valeurs de 
	 * défaut.
	 * 
	 * @author Jason Pang
	 * @since 10/23/2018
	 * @version 1.0.0
	 */
	public Depute()
	{
		this.nomDuDepute = "";
		this.numeroDeCase = 0;
		this.numeroDuParti = 0;
	}
	
	/**
	 * Initialization des variables privé du classe Depute avec des valeurs
	 * donné par l'utilisateur.
	 * 
	 * @param nomDuDepute
	 * 				nom et prénom du Député
	 * @param numeroDeCase
	 * 				les numéros de case où se trouvent la circonscription
	 * @param numeroDuParti
	 * 				le numéro du nom de parti
	 * 
	 * @author Jason Pang
	 * @since 10/23/2018
	 * @version 1.0.0
	 */
	public Depute(String nomDuDepute, int numeroDeCase, int numeroDuParti)
	{
		this.nomDuDepute = nomDuDepute;
		this.numeroDeCase = numeroDeCase;
		this.numeroDuParti = numeroDuParti;
	}
	
	/**
	 * Copier les valeurs d'un objet de type Depute.
	 *  
	 * @param depute
	 * 
	 * @author Jason Pang
	 * @since 10/23/2018
	 * @version 1.0.0
	 */
	public Depute(Depute depute)
	{
		this.nomDuDepute = depute.nomDuDepute;
		this.numeroDeCase = depute.numeroDeCase;
		this.numeroDuParti = depute.numeroDuParti;
	}
	
	/**
	 * Definir le nom du depute.
	 * 
	 * @param nomDuDepute
	 * 
	 * @author Jason Pang
	 * @since 10/23/2018
	 * @version 1.0.0
	 */
	public void setDepute(String nomDuDepute)
	{
		this.nomDuDepute = nomDuDepute;
	}
	
	/**
	 * Definir le numero du case.
	 * 
	 * @param numeroDuCase
	 * 
	 * @author Jason Pang
	 * @since 10/23/2018
	 * @version 1.0.0
	 */
	public void setNumCase(int numeroDuCase)
	{
		this.numeroDeCase = numeroDuCase;
	}
	
	/**
	 * Definir le numbero du Parti.
	 * 
	 * @param numeroDuParti
	 * 
	 * @author Jason Pang
	 * @since 10/23/2018
	 * @version 1.0.0
	 */
	public void setNumParti(int numeroDuParti)
	{
		this.numeroDuParti = numeroDuParti;
	}
	
	/**
	 * obtenir le nom du Depute.
	 * 
	 * @return nomDuDepute
	 * 
	 * @author Jason Pang
	 * @since 10/23/2018
	 * @version 1.0.0
	 */
	public String getDepute()
	{
		return this.nomDuDepute;
	}
	
	/**
	 * Obternir le numero de case.
	 * 
	 * @return numeroDeCase
	 * 
	 * @author Jason Pang
	 * @since 10/23/2018
	 * @version 1.0.0
	 */
	public int getNumCase()
	{
		return this.numeroDeCase;
	}
	
	/**
	 * Obtenir le numero du Parti.
	 * 
	 * @return numeroDuParti
	 * 
	 * @author Jason Pang
	 * @since 10/23/2018
	 * @version 1.0.0
	 */
	public int getNumParti()
	{
		return this.numeroDuParti;
	}
	
	/**
	 * Cloner cet objet dans un autre objet de type Depute.
	 * 
	 * @param depute
	 * 
	 * @author Jason Pang
	 * @since 10/23/2018
	 * @version 1.0.0
	 */
	public void cloneDepute(Depute depute)
	{
		depute.nomDuDepute = this.nomDuDepute;
		depute.numeroDeCase = this.numeroDeCase;
		depute.numeroDuParti = this.numeroDuParti;
	}
	/**
	 * Convertir les variables d'objet depute en chaine de
	 * caractere et retourne un chaine de character avec les
	 * varaibles.
	 * 
	 * @return string
	 * 
	 * @author Jason Pang
	 * @since 10/23/2018
	 * @version 1.0.0
	 */
	public String toString()
	{
		return this.nomDuDepute+ " " 
				+this.numeroDeCase+ " " 
				+this.numeroDuParti;
	}
	
	/**
	 * Compare un autre objet de type Depute avec ce depute et 
	 * verifier s'ils sont egal.
	 * 
	 * @param depute
	 * @return vrai ou faux
	 * 
	 * @author Jason Pang
	 * @since 10/23/2018
	 * @version 1.0.0
	 */
	public boolean equals(Depute depute)
	{
		return this.nomDuDepute.equals(depute.nomDuDepute) && 
				this.numeroDeCase == depute.numeroDeCase && 
				this.numeroDuParti == depute.numeroDuParti;
	}
	
}
