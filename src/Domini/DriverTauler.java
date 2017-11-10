package tauler;

import java.util.Scanner;

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
		Tauler t = new Tauler(files, numero_elements,colors);
		System.out.println("Apreta 0 per sortir.");
		System.out.println("Apreta 1 per obtenir el nombre de linies del tauler.");
		System.out.println("Apreta 2 per obtenir el nombre de d'elements de la linia.");
		System.out.println("Apreta 3 per obtenir els elements de una linia.");
		System.out.println("Apreta 4 per posar els elements a la ultima linia.");
		System.out.println("Apreta 5 per visualitzar el tauler.");
		System.out.println("Apreta 6 per escriure la linia per endevinar.");
		System.out.println("Apreta 7 per obtenir la linia per endevinar.");
		System.out.println("Apreta 8 per visualitzar la solucio.");
		System.out.println("Apreta 9 per visualitzar el nombre de colors.");
		int n = 0;
		n = S.nextInt();
		while(n != 0){
			switch(n){
				case 0:
				{
					break;
				}
				case 1:
				{
					 System.out.println("files: "+t.getLine_number());
					 break;
				}
				case 2:
				{
					System.out.println("elements: "+t.getLine_size());
					break;
				}
				case 3:
				{
					System.out.print("Introdueix numero de linia:");
					int l = S.nextInt();
					if (l < 0 || l >= t.getLine_number()) System.out.println("El numero que has introduit no es valid");
					else {
						Combinacio c = t.getlinia(l);
						c.escriu_combinacio();
					}
					break;
				}
				case 4:
				{
					Combinacio comb = new Combinacio(t.getLine_size());
					comb.llegir_comb();
					t.set_ultima_linia(comb);
					
					break;
				}
				case 5:
				{
					t.escriu_tauler();
					break;
				}
				case 6:
				{
					Combinacio comb = new Combinacio(t.getLine_size());
					comb.llegir_comb();
					t.setInitial_line(comb);
					break;
				}
				case 7:{
					(t.get_comb_ini()).escriu_combinacio();
					break;
				}
				case 8:
				{
					t.escriu_solucio();
					break;
				}
				case 9:
				{
					System.out.print("colors:"+t.getColors());
				}
			}
			n = S.nextInt();
		}
		S.close();
	}
}