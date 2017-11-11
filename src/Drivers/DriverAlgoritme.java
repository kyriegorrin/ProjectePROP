package Drivers;

import Domini.Algoritme;
import Domini.Combinacio;
import Domini.Tauler;

public class DriverAlgoritme {

	public static void main(String [] args){
		Tauler t = new Tauler(8, 4, 8);
		Combinacio c = new Combinacio(t.getLine_size());
		c.llegir_comb();
		t.setInitial_line(c);
		Algoritme a = new Algoritme();
		int cont = 0;
		
		while (t.getUltima() >= 0) {
			Combinacio c2 = new Combinacio(t.getLine_size());
			System.out.println("iteracio:"+cont);
			c2 = a.algoritmeGenetic(t);
			c2.escriu_combinacio();
			System.out.println("TAULER");
			t.set_ultima_linia(c2);
			t.escriu_tauler();
			System.out.println("----------------");
			System.out.println("SOLUCIO");
			t.escriu_solucio();
			++cont;
			System.out.println();
			System.out.println("----------------");
			System.out.println("----------------");
			System.out.println();
		}
	}
}
