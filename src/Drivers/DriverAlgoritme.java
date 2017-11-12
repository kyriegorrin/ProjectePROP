package Drivers;

import Domini.Algoritme;
import Domini.Combinacio;
import Domini.Tauler;

public class DriverAlgoritme {

	public static void main(String [] args){
		Tauler t = new Tauler(8, 4, 6);
		Combinacio c = new Combinacio(t.getLine_size());
		c.llegir_comb();
		t.setInitial_line(c);
		Algoritme a = new Algoritme();
		int cont = 0;
		
		while (t.getUltima() >= 0) while (t.getUltima() >= 0) {
			Combinacio c2 = new Combinacio(t.getLine_size());
			System.out.println("iteracio:"+cont);
			c2 = a.algoritmeGenetic(t);
			System.out.print("linia afegida: ");
			c2.escriu_combinacio();
			t.set_ultima_linia(c2);
			if (t.get_comb_ini().comparar(c2)) System.out.println("HAS ENCERTAT");
			else {
				for (int i = 0; i != t.getLine_number(); ++i) {
					for (int j = 0; j != t.getLine_size(); ++j) {
						System.out.print(t.getlinia(i).get_elementx(j));
					}
					
					System.out.print("///");
					t.get_solucio_linia(i).escriu_combinacio();
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
