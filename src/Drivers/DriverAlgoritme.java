package Drivers;

import Domini.Algoritme;
import Domini.Tauler;
import Domini.Combinacio;
import java.util.Scanner;

public class DriverAlgoritme {

	public static void main(String [] args){
		Scanner S = new Scanner(System.in);
		int files, columnes, colors;
		System.out.print("Introdueix el nombre de files:");
		files = S.nextInt();
		System.out.print("Introdueix el nombre de columnes:");
		columnes = S.nextInt();
		System.out.print("Introdueix el nombre de colors:");
		colors = S.nextInt();
		Tauler t = new Tauler(files, columnes, colors);
		System.out.println("Ara introdueix la combinacio a endevinar!");
		Combinacio c = new Combinacio(t.getLine_size());
		c.llegir_comb();
		t.setInitial_line(c);
		Algoritme a = new Algoritme();
		int cont = 0;
		boolean encertat = false;

		System.out.println("Escolleix si vols Minimax o Random (0 o 1 respectivament):");
		if (0 == S.nextInt()) {

			while (t.getUltima() >= 0 && !encertat) {
				Combinacio c2 = new Combinacio(t.getLine_size());
				System.out.println("iteracio:" + cont);
				c2 = a.algoritmeMinimax(t);
				System.out.print("linia afegida: ");
				c2.escriu_combinacio();
				System.out.println();
				t.set_ultima_linia(c2);
				if (t.get_comb_ini().comparar(c2)) {
					System.out.println("HAS ENCERTAT");
					encertat = true;
				} else {
					for (int i = 0; i != t.getLine_number(); ++i) {
						for (int j = 0; j != t.getLine_size(); ++j) {
							System.out.print(t.getlinia(i).get_elementx(j));
						}

						System.out.print("///");
						t.get_solucio_linia(i).escriu_combinacio();
						System.out.println();
					}
					++cont;
					System.out.println();
					System.out.println("----------------");
					System.out.println("----------------");
					System.out.println();
				}
			}
		}

		else {
			while (t.getUltima() >= 0 && !encertat) {
				Combinacio c2 = new Combinacio(t.getLine_size());
				System.out.println("iteracio:" + cont);
				c2 = a.algoritmeRandom(t);
				System.out.print("linia afegida: ");
				c2.escriu_combinacio();
				System.out.println();
				t.set_ultima_linia(c2);
				if (t.get_comb_ini().comparar(c2)) {
					System.out.println("HAS ENCERTAT");
					encertat = true;
				} else {
					for (int i = 0; i != t.getLine_number(); ++i) {
						for (int j = 0; j != t.getLine_size(); ++j) {
							System.out.print(t.getlinia(i).get_elementx(j));
						}

						System.out.print("///");
						t.get_solucio_linia(i).escriu_combinacio();
						System.out.println();
					}
					++cont;
					System.out.println();
					System.out.println("----------------");
					System.out.println("----------------");
					System.out.println();
				}
			}
		}
	}
}
