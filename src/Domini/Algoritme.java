package Domini;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/** <h1> CLASSE ALGORITME </h1>
 *
 * <p>La classe Algoritme s'encarrega de resoldre una partida de Mastermind utilitzant l'algoritme
 * "Five-guess algorithm" de Donald Knuth.</p>
 * 
 * @author Miquel Bars Barba
 */

public class Algoritme {
	private ArrayList<Combinacio> poblacio;
	private boolean first;
	
	/** Constructora que crea un objecte Algoritme.
	 */
	public Algoritme() {
		poblacio = new ArrayList<Combinacio>();
		first = true;
	}
	
	/**
	 * S'emplena el ArrayList poblacio amb totes les combinacions possibles
	 * @param t Es tractat del tauler del joc
	 */
	public void emplenarPoblacio(Tauler t) {
		double base = t.getColors();
		double exponent = t.getLine_size();
		double tamany = Math.pow(base, exponent);
		for (int i = 0; i < tamany; ++i) {
			int[] v = new int[t.getLine_size()];
			boolean incrementar = true;
			boolean primer = true;
			int j = t.getLine_size() - 1;
			while (incrementar) {
				if (j > 0) { 
					if (primer) {
						v[j] = i % t.getColors();
						v[j-1] = i / t.getColors();
						primer = false;
					}
					else {
						v[j-1] = v[j] / t.getColors();
						v[j] = v[j] % t.getColors();
					}
				}
				else incrementar = false;
				--j;
			}
			Combinacio c = new Combinacio(t.getLine_size());
			c.setCombinacio(v);
			poblacio.add(c);
		}
	}
	
	/**
	 * El ArrayList poblacio es substituit per un nou ArrayList en el qual
	 * s'hi introdueix aquelles combinacions les quals la Combinacio "anterior" 
	 * tindria la mateixa "pista".
	 * @param t Es tractat del tauler del joc
	 */
	public void minimax(Tauler t) {
		Combinacio pista = new Combinacio(t.get_solucio_linia(t.getUltima() + 1));
		Combinacio anterior = new Combinacio(t.getlinia(t.getUltima() + 1));
		int blancs = 0;
		int negres = 0;
		for (int i = 0; i < t.getLine_size(); ++i) {
			if (pista.get_elementx(i) == 2) ++blancs;
			else if (pista.get_elementx(i) == 1) ++negres;
		}
		Iterator<Combinacio> it = poblacio.iterator();
		ArrayList<Combinacio> auxpoblacio = new ArrayList<Combinacio>();
		while(it.hasNext()){
			Combinacio c = new Combinacio(it.next());
			Tauler t2 = new Tauler(t.getLine_number(), t.getLine_size(), t.getColors());
			t2.setInitial_line(c);
			t2.set_ultima_linia(anterior);
			Combinacio pista2 = new Combinacio(t2.get_solucio_linia(t2.getUltima() + 1));
			int blancs2 = 0;
			int negres2 = 0;
			for (int i = 0; i < t2.getLine_size(); ++i) {
				if (pista2.get_elementx(i) == 2) ++blancs2;
				else if (pista2.get_elementx(i) == 1) ++negres2;
			}
			if (negres == negres2 && blancs == blancs2) {
				auxpoblacio.add(c);
			}
		}
		poblacio = auxpoblacio;
	}

	/**
	 * Quan entra per primer cop crida la funcio "emplenarPoblacio" i despres s'executa
	 * "minimax" fins que s'acaba. Retorna una Combinacio amb el primer element de la poblacio.
	 * 
	 * @param t Es tractat del tauler del joc
	 * @return Retorna una combinació computada a partir de l'algoritme minimax.
	 */
	public Combinacio algoritmeMinimax(Tauler t) {
		Combinacio resultat = new Combinacio(t.getLine_size());
		if (first) {
			emplenarPoblacio(t);
			resultat = poblacio.get(0);
			first = false;
		}
		else{
			minimax(t);
			resultat = poblacio.get(0);
		}
		return resultat;
	}
	/**
	 * Retorna una Combinacio aleatoria.
	 *
	 * @param t Es tractat del tauler del joc
	 * @return Retorna una combinació computada a partir de l'algoritme Random.
	 */
	public Combinacio algoritmeRandom(Tauler t){
		Combinacio resultat = new Combinacio(t.getLine_size());
		Random rn = new Random();
		for (int i = 0; i != t.getLine_size(); ++i){
			resultat.set_elementx(i, rn.nextInt(t.getColors()));
		}
		return resultat;
	}
}