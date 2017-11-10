package Domini;
import java.util.Scanner;

public class Combinacio {
	private int [] combinacio;
	private int tamany;

	// CONSTRUCTOR
	public Combinacio(int tamany) {
		combinacio = new int [tamany];
		this.tamany = tamany;
	}
	
	// GETTERS AND SETTERS
	
	// Retorna una combinacio
	public int[] getCombinacio() {
		return combinacio;
	}

	// Modifica el contingut d'una combinacio
	public void setCombinacio(int [] combinacio){
		this.combinacio = combinacio;
	}
	
	//  OTHERS
	
	// retorna el color de la posicio x
	// PRE: 0 >=x >= tamany-1
	public int get_elementx(int x){
		return combinacio[x];
	}
	
	// Donada una posicio x i un element y canvia l'element de la posicio x per y
	public void set_elementx(int x, int y){
		if (combinacio[x] == y) {
			combinacio[x] = 0;
		}
		else combinacio[x] = y;
	}
	
	// donat un nombre de blancs i negres els posa correctament
	// a la combinacio de la solucio
	public void omplir(int blancs, int negres){
		int i = 0;
		while(i < tamany){
			if (blancs >0){
				combinacio[i] = 2;
				--blancs;
			}
			else if (negres > 0){
				combinacio[i] = 1;
				--negres;
			}
			else combinacio[i] = 0;
			++i;
		}
	}
	
	// Llegeix una combinacio introduida per teclat
	
	public void llegir_comb(){
		Scanner S = new Scanner(System.in);
		System.out.println("Introdueix els valors:");
		for(int i = 0; i < tamany; ++i){
			combinacio[i] = S.nextInt();
		}
	}
	
	// Escriu la combinacio per pantalla
	public void escriu_combinacio(){
		
		for (int i = 0; i < combinacio.length; ++i){
			 System.out.print (combinacio[i]);	
		}
		System.out.println();
	}
	
	public int comprovar_colors(int color){
		for (int i = 0; i < tamany; ++i){
			System.out.println("aix�"+combinacio[i]);
			if (combinacio[i]<0 || combinacio[i] >= color)return -1;
		}
		return 0;
	}
	
}