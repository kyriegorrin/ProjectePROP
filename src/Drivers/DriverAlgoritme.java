package Drivers;

import Domini.Algoritme;
import Domini.Combinacio;
import Domini.Tauler;

import java.util.Scanner;

public class DriverAlgoritme {

	public static void main(String [] args){
		Tauler t = new Tauler(8, 4, 8);
		Combinacio c = new Combinacio(t.getLine_size());
		c.llegir_comb();
		t.setInitial_line(c);
		Algoritme a = new Algoritme();
		int cont = 0;
		
		while (t.getUltima() >= 0) {
			System.out.println("iteracio:"+cont);
			c = a.algoritmeGenetic(t);
			c.escriu_combinacio();
			System.out.println("TAULER");
			t.set_ultima_linia(c);
			t.escriu_tauler();
			System.out.println("----------------");
			t.escriu_solucio();
			++cont;
			System.out.println();
		}
	}
}
