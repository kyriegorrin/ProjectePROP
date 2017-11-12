package Domini;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Algoritme {
	private Set<Combinacio> poblacio;
	private Set<Combinacio> poblacioFitness;
	private boolean primer;
	private ArrayList<Integer> colorNull;
	
	public Algoritme() {
		poblacio = new HashSet<Combinacio>();
		poblacioFitness = new HashSet<Combinacio>();
		primer = true;
		colorNull = new ArrayList<Integer>();
	}
	
	/** @brief Emplena el Set poblacio amb combinacions aleatories
	 */
	public void emplenarPoblacio(Tauler t) {
		Random r = new Random();
		for (int i = 0; i < 1000; ++i) {
			Combinacio c = new Combinacio(t.getLine_size());
			for (int j = 0; j < t.getLine_size(); ++j){
				c.set_elementx(j, r.nextInt(t.getColors()));
			}
			poblacio.add(c);
		}
	}
	
	/** @brief Emplena l'ArrayList colorNull amb tots aquells colors que
	 * han estat confirmats que no apareixen a la combinacio que s'ha d'endevinar.
	 */
	public void emplenarColorNull(Combinacio c, Tauler t) {
		for (int i = 0; i < t.getLine_size(); ++i) {
			if (!colorNull.contains(c.get_elementx(i))){
				colorNull.add(c.get_elementx(i));
			}
		}
	}
	
	/** @brief Elimina de poblacio totes aquelles combinacions que nomes tenen colors
	 * que pertanyen al ArrayList colorNull.
	 */
	public void eliminarColorPoblacio(Tauler t) {
		Iterator<Combinacio> it = poblacio.iterator();
		Combinacio caux = new Combinacio(t.getLine_size());
		while (it.hasNext()) {
			caux = it.next();
		    int noApte = 0;
		    int i = 0;
		    while (noApte < t.getLine_size() && i < t.getLine_size()) {
		    	int j = 0;
		    	while (noApte < t.getLine_size() && j < colorNull.size()) {
		    		if (caux.get_elementx(i) == colorNull.get(j)) ++noApte;
		    		++j;
		    	}
		    	++i;
		    }
		    if (noApte >= 4) {
		    	Combinacio cremove = new Combinacio(caux);
		    	poblacio.remove(cremove);
		    }
		}
	}
	
	/** @brief Emplena el Set poblacioFitness amb totes les combinacions que
	 * assoleixen la formula de Fitness.
	 */
	public void calcularFitness(Tauler t) {
		Iterator<Combinacio> it = poblacio.iterator();
		Combinacio c = new Combinacio(t.getLine_size());
		Combinacio fitness = new Combinacio(t.getLine_size());
		
		int fmax = 0;
		int liniaFitness = t.getUltima();
		for (int i = t.getUltima(); i < t.getLine_number(); ++i) {
			int blancs = 0;
			int negres = 0;
			for (int j = 0; j != t.getLine_size(); ++j) {
				if (t.get_solucio_linia(i).get_elementx(j) == 1) ++blancs;
				else if (t.get_solucio_linia(i).get_elementx(j) == 2) ++negres;
			}
			if (((2*negres) + blancs) > fmax) {
				fmax = (2*negres) + blancs;
				fitness = t.getlinia(i);
				liniaFitness = i;
			}
		}
		
		while (it.hasNext()) {
			c = it.next();
			Tauler t2 = new Tauler (t.getLine_number(), t.getLine_size(), t.getColors());
			t2.setInitial_line(fitness);
			t2.set_ultima_linia(c);
			if (t.get_solucio_linia(liniaFitness).comparar(t2.get_solucio_linia(t2.getUltima() + 1))){
				poblacioFitness.add(c);	
			}
		}
	}
	
	/** @brief Afegeix al Set poblacio els crossovers que s'ha fet
	 * sobre el Set poblacioFitness
	 */
	public void crossoverPoblacioFitness(Tauler t) {
		Iterator<Combinacio> it = poblacioFitness.iterator();
		Random r = new Random();
		while (it.hasNext()) {
			Combinacio aux = new Combinacio(t.getLine_size());
			aux = it.next();
			if (it.hasNext()) {
				Combinacio aux2 = new Combinacio(t.getLine_size());
				aux2 = it.next();
				int n = r.nextInt(t.getLine_size());
				if (n%2 == 0) {
					aux.set_elementx(n, aux2.get_elementx(n));
					if (r.nextInt(100) > 3) aux.set_elementx(n, r.nextInt(t.getColors()));
					poblacio.add(aux);
				}
				else {
					aux2.set_elementx(n, aux.get_elementx(n));
					if (r.nextInt(100) > 3) aux2.set_elementx(n, r.nextInt(t.getColors()));
					poblacio.add(aux2);
				}
			}
		}
	}
	
	
	public Combinacio algoritmeGenetic(Tauler t) {
		// Retorna el primer element del conjunt poblacio
		if (primer) {
			primer = false;
			emplenarPoblacio(t);
			Combinacio c;
			c = new Combinacio(poblacio.iterator().next());
			return c;
		}
		
		// S'executa l'algoritme Genetic
		else {
			Combinacio pista = new Combinacio(t.getLine_size());
			Combinacio aux = new Combinacio(t.getLine_size());
			pista = t.get_solucio_linia(t.getUltima() + 1);
			aux = t.getlinia(t.getUltima() + 1);
			
			// comprovar si pista te algun valor
			int cont = 0;
			for (int i = 0; i != t.getLine_size(); ++i) {
				if (pista.get_elementx(i) != 0) ++cont;
			}
			// si no en te elimina de poblacio aquells que tenen els mateixos colors
			if (cont == 0) {
				emplenarColorNull(aux, t);
				eliminarColorPoblacio(t);
			}
			calcularFitness(t);
			poblacio.clear();
			crossoverPoblacioFitness(t);
			Combinacio resultat = new Combinacio(poblacio.iterator().next());
			return resultat;
		}
	}
}

