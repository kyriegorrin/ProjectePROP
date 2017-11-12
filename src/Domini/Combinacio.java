/**
 * Clase Combinacio
 */
package Domini;
import java.util.Scanner;

/**
 * CLASE COMBINACIO
 *
 *
 */

public class Combinacio {
	
	 ///ATRIBUTS DE LA CLASE
	private int [] combinacio; /// Vector d'enters que identificar un color
	private int tamany; /// Tamany conte el size del vector combinacio 
	private int puntuacio;

	/// CONSTRUCTORS 
	
/** Constructora que genera un objecte combinacio.
 * 
 * @param tamany es un enter >0.
 * @return Es retorna un objecta combiancio inicialitzat.
 */
	public Combinacio(int tamany) {
		
		combinacio = new int [tamany];
		this.tamany = tamany;
		puntuacio = 0;
	}
	
	///SETTERS & GETTERS
	
	
	
	/** Getter dels colors de la combinacio.
	 * @return retorna Es retorna l'atribut combinacio.
	 */
	
	
	public int[] getCombinacio() {
		return combinacio;
	}
	/**Setter de l'atribut coombinacio.
	 * @param combinacio Es un vector d'enters inicialitzat de gradnaria @tamany.
	 */
	 
	public void setCombinacio(int [] combinacio){
		this.combinacio = combinacio;
	}
	
	///ALTRES
	
	

	/**Getter del color que hi ha a una posicio concreta.
	 * @parameters x Es un enter 0 >=x >= tamany-1.
	 * @return Retorna l'element de la posicio x de l'atribut combinacio.
	 */
	
	public int get_elementx(int x){
		return combinacio[x];
	}
	/**
	 * Getter que retorna la puntuacio de la combinacio.
	 * @return la puntuacio qua acumula aquesta combinacio.
	 */
	
	
	public int get_puntuacio(){
		return puntuacio;
	}
	
	/**Posa un element a una posicio concreta al vector de colors de la combinacio. 
	 * @param x Es un enter 0 >=x >= tamany-1.
	 * @param y Es un enter inicialitzat.
	 */ 
	public void set_elementx(int x, int y){
		combinacio[x] = y;
	}
	
	/**Donat un nombre de blancs i negres emplena l'atribut combinacio.
	 * @param blancs  Es un enter 0 >=blancs>= tamany-1.
	 * @param negres Es un enter 0 >=negres >= tamany-1.
	 */
	public void omplir(int blancs, int negres){
		int i = 0;
		while(i < tamany){
			if (blancs >0){
				combinacio[i] = 2;
				--blancs;
				puntuacio += 200;
			}
			else if (negres > 0){
				combinacio[i] = 1;
				--negres;
				puntuacio += 100;
			}
			else combinacio[i] = 0;
			++i;
		}
	}
	
	/** Llegeix per pantalla la combinacio.
	 * 
	 */
	
	public void llegir_comb(){
		Scanner S = new Scanner(System.in);
		System.out.println("INTRODUEIX ELS VALORS -> FORMAT: <int>espai<int>espai<int>....");
		for(int i = 0; i < tamany; ++i){
			combinacio[i] = S.nextInt();
		}
	}
	
	/**
	 *  Escriu una combinacio per pantalla.
	 */
	public void escriu_combinacio(){
		for (int i = 0; i < combinacio.length; ++i){
			 System.out.print (combinacio[i]);	
		}
	}
	
	/**  Donat un nombre de colors comprova que es respecti que 
	 * tots els elements de combinacio siguin de un enter inferior
	 * a colors i superior a 0.
	 * @params color Es un enter >0
	 * @return Retorna -1 si no es rspecta la franja de colors, en cas contrari retorna 0
	 */
	
	public int comprovar_colors(int color){
		
		for (int i = 0; i < tamany; ++i){
			if (combinacio[i]<0 || combinacio[i] >= color)return -1;
		}
		return 0;
	}
	/**
	 * Donada una combinacio c la compara amb l'atribut combiancio.
	 * @param c Es una combinacio incialitzada.
	 * @return Si són iguals retorna true, si són diferents retorna false.
	 */
	public boolean comparar(Combinacio c){
		for(int i = 0; i < tamany; ++i){
			if(c.get_elementx(i) != combinacio[i])return false;
		}
		return true;
		
		
	}
	
}
