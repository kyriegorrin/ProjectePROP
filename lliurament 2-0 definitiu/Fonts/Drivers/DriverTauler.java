package Drivers;

import java.util.Scanner;
import Domini.Tauler;
import Domini.Combinacio;

public class DriverTauler {

	public static void main(String[] args){
		
		System.out.print("escriu el numero de linies:");
		int files = 0;
		Scanner S = new Scanner(System.in);
		files = S.nextInt();
		System.out.print("escriu el numero d'elements:");
		int numero_elements = 0;
		numero_elements = S.nextInt();
		System.out.print("escriu el numero de colors:");
		int colors = 0;
		colors = S.nextInt();
		Tauler t = new Tauler(files, numero_elements, colors);
		String comanda;
		comanda = S.nextLine();
		
		
		while(!comanda.equals("exit")){
			switch(comanda){
				case "help":
					System.out.println("Llista de comandes:");
					System.out.println("linies_tauler-> Per obtenir el nombre de linies del tauler.");
					System.out.println("elements_linia->Per obtenir el nombre de d'elements de la linia.");
					System.out.println("colors->Per visualitzar el nombre de colors.");
					System.out.println("escriure_endivinar->Per escriure la linia per endevinar.");
					System.out.println("endivinar->Per obtenir la linia per endevinar.");
					System.out.println("afegir_linia->Per posar els elements a la ultima linia.");
					System.out.println("llegir_combinaciox->Per lleguir una combinacio i modificar l'element x per y.");
					System.out.println("elementsx->Per obtenir els elements de una linia.");
					System.out.println("tauler->per visualitzar el tauler.");
					System.out.println("solucio->Per visualitzar la solucio.");
					System.out.println("puntuacio->Per obtenir la puntuacio");
					System.out.println("tauler_solucio->Per escriure tauler i solucio");
					System.out.println("exit-> Per sortir");
				case "linies_tauler":
				
					 System.out.println("files: "+t.getLine_number());
					 break;
			
				case "elements_linia":
					System.out.println("elements: "+t.getLine_size());
					break;
		
				case "colors":
					System.out.println("colors:"+t.getColors());
					break;
					
				case "escriure_endivinar":
					Combinacio comb = new Combinacio(t.getLine_size());
					comb.llegir_comb();
					t.setInitial_line(comb);
					break;
					
				case "endivinar":
					
					(t.get_comb_ini()).escriu_combinacio();
					System.out.println();
					break;
					
				case "afegir_linia":
					
					Combinacio combi = new Combinacio(t.getLine_size());
					combi.llegir_comb();
					t.set_ultima_linia(combi);
					break;
					
				case "llegir_combinaciox":
					Combinacio c = new Combinacio(t.getLine_size());
					c.llegir_comb();
					System.out.print("Combinacio inicial())");
					c.escriu_combinacio();
					System.out.println();
					int x = 0;
					int y = 0;
					System.out.print("Escriu element que vols midficar");
					x = S.nextInt();
					System.out.print("Escriu el valor");
					y = S.nextInt();
					c.set_elementx(x, y);
					c.escriu_combinacio();
					System.out.println();
					
					break;
				case "elementsx":
					System.out.println("Introdueix numero de linia:");
					int l = S.nextInt();
					if (l < 0 || l >= t.getLine_number()) System.out.println("El numero que has introduit no es valid");
					else {
						t.getlinia(l).escriu_combinacio();
						System.out.println();
					}
					break;
				case "tauler":
					t.escriu_tauler();
					break;
				case "solucio":
					t.escriu_solucio();
					break;
				case "puntuacio":
					System.out.println("WINER WINER CHICKEN DINER."+t.puntuacio());
					break;
				case "tauler_solucio":
					t.escriu_tot();
					break;			
			}
			comanda = S.nextLine();
			
		}
		
		S.close();
	}
}
