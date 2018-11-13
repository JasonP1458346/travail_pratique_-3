
import java.io.Serializable;

public class Circonscription implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numero;
    private String nomCirc;
    private int numeroCase;
        
    /**
     * constructeur utilisé pour initialiser une classe en fonction des 
     * entrées de l'utilisateur
     * 
     * @param un numéro et un nom de circonscription, puis le numéro 
     * 		  de case du député élu correspondant à sa position dans la 
     * 		  collection des députés (de la classe Election).  
     * 
     * 
     * @author Daniel Colalillo
     * @since 10/24/2018
     * @version 1.0.0
     */
    public Circonscription(int num, String  nCirc, int numCase) {
        numero = num;
        nomCirc = nCirc;
        numeroCase = numCase;
    }
        
    /**
     * mutatuer pour numero
     * 
     * @param nouvVal
     * @return void
     * 
     * @author Daniel Colalillo
     * @since 10/24/2018
     * @version 1.0.0
     */
    public void setNumero(int nouvVal){
    	
         numero = nouvVal;
    }
    
    /**
     * mutatuer pour nomCirc
     * 
     * @param nouvVal
     * @return void
     * 
     * @author Daniel Colalillo
     * @since 10/24/2018
     * @version 1.0.0
     */
    public void setNomCirconscription(String nouvVal){
    	
        nomCirc = nouvVal;
    }
        
    /**
     * mutatuer pour numeroCase
     * 
     * @param nouvVal
     * @return void
     * 
     * @author Daniel Colalillo
     * @since 10/24/2018
     * @version 1.0.0
     */
    public void setNumeroCase(int nouvVal){
    	
        numeroCase = nouvVal;
    }
    
    /**
     * accesseurs pour numero
     * 
     * @param void
     * @return numero
     * 
     * @author Daniel Colalillo
     * @since 10/24/2018
     * @version 1.0.0
     */
    public int getNumero(){
    	
       return numero;
    }
        
    /**
     * accesseurs pour nomCirc
     * 
     * @param void
     * @return nomCirc
     * 
     * @author Daniel Colalillo
     * @since 10/24/2018
     * @version 1.0.0
     */
    public String getNomCirconscription(){
    	
        return nomCirc;
     }
    
    /**
     * accesseurs pour numeroCase
     * 
     * @param void
     * @return numeroCase
     * 
     * @author Daniel Colalillo
     * @since 10/24/2018
     * @version 1.0.0
     */
    public int getNumeroCase(){
    	
        return numeroCase;
     }

}

