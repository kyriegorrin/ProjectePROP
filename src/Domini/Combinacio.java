/**
 * Clase Combinacio
 */
package Domini;
import java.util.Scanner;

public class Combinacio {
	
	/**
	 * ATRIBUTS DE LA CLASE
	 */
	private int [] combinacio; /// Vector d'enters que identificar un color
	private int tamany; /** Tamany conte el size del vector combinacio */
	private int puntuacio;

	/// CONSTRUCTORS 
	
/** \brief Constructora que genera un objecte combinacio.
 * 
 * @param tamany es un enter >0.
 * @return Un objecte combinacio
 */
	public Combinacio(int tamany) {
		
		combinacio = new int [tamany];
		this.tamany = tamany;
		puntuacio = 0;
	}

	public Combinacio(Combinacio comb){
		this.tamany = comb.tamany;
		this.puntuacio = comb.puntuacio;
		this.combinacio = new int[comb.tamany];
		for(int i = 0; i < comb.tamany; ++i){
			this.combinacio[i] = comb.combinacio[i];
		}
	}
	
	///SETTERS & GETTERS
	
	
	
	/** @brief Getter que retonra el vector d'enters de combinacio.
	 * @return retorna un vector d'enters amb els colors
	 */
	
	
	public int[] getCombinacio() {
		return combinacio;
	}
	/**brief Setter de l'atribut coombinacio.
	 * @combinacio Es un vector d'enters inicialitzat de gradnaria @tamany.
	 */
	 
	public void setCombinacio(int [] combinacio){
		this.combinacio = combinacio;
	}
	
	///ALTRES
	
	

	/**@brief Getter de l'element que hi ha a una posicio concreta.
	 * @x 0 >=x >= tamany-1.
	 * @return Retorna l'element de la posicio x de l'atribut combinacio
	 */
	
	public int get_elementx(int x){
		return combinacio[x];
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int get_puntuacio(){
		return puntuacio;
	}
	
	/**@brief Donada una posicio x i un element y posa y a la posicio x	 
	 * de l'atribut combinacio
	 * @x 0 >=x >= tamany-1
	 * @y Es un enter inicialitzat
	 */ 
	public void set_elementx(int x, int y){
		combinacio[x] = y;
	}
	
	/**@brief Donat un nombre de blancs i negres emplena l'atribut combinacio.
	 * @blancs  0 >=blancs>= tamany-1.
	 * @negres 0 >=negres >= tamany-1.
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
	
	/**@brief Llegeix una combinacio introduida per teclat i 
	 * els posa com a parametres de la instacia combinacio.
	 */
	
	public void llegir_comb(){
		Scanner S = new Scanner(System.in);
		System.out.println("INTRODUEIX ELS VALORS -> FORMAT: <int>espai<int>espai<int>....");
		for(int i = 0; i < tamany; ++i){
			combinacio[i] = S.nextInt();
		}
	}
	
	/**
	 * @brief Escriu una combinacio per pantalla.
	 */
	public void escriu_combinacio(){
		for (int i = 0; i < combinacio.length; ++i){
			 System.out.print (combinacio[i]);	
		}
		System.out.println();
	}
	
	/** @brief Donat un nombre de colors comprova que es respecti
	 * que tots els elements de combinacio siguin de un enter inferior
	 * a colors i superior a 0.
	 * @colors Es un enter >0
	 * @return Retorna -1 si no es rspecta la franja de colors, en cas contrari retorna 0
	 */
	
	public int comprovar_colors(int color){
		
		for (int i = 0; i < tamany; ++i){
			if (combinacio[i]<0 || combinacio[i] >= color)return -1;
		}
		return 0;
	}
	
}
