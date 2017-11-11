package Domini;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Algoritme {
	private Set<Combinacio> poblacio;
	private boolean primer;
	private ArrayList<Integer> colorNull;
	
	public Algoritme(Tauler t) {
		poblacio = new HashSet<Combinacio>();
		primer = true;
		colorNull = new ArrayList<Integer>();
	}
	
	// Emplena el atribut poblacio amb combinacions aleatories
	public void emplenarPoblacio(Tauler t) {
		Random r = new Random();
		for (int i = 0; i < 150; ++i) {
			Combinacio c = new Combinacio(t.getLine_size());
			for (int j = 0; j < t.getLine_size(); ++j){
				c.set_elementx(j, r.nextInt(t.getColors()));
			}
			poblacio.add(c);
		}
	}
	
	// Emplena el vector ColorNull de colors que sabem que no apareixen a la combinacio a endevinar
	public void emplenarColorNull(Combinacio c, Tauler t) {
		for (int i = 0; i < t.getLine_size(); ++i) {
			if (!colorNull.contains(c.get_elementx(i))){
				colorNull.add(c.get_elementx(i));
			}
		}
	}
	
	// Elimina part de la poblacio que tingui els mateixos elements que Combinacio c
	public void eliminarPoblacio(Tauler t) {
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
		    	it.remove();
		    }
		}
	}
	
	public void fitness(Tauler t) {
		
	}
	
	public void crossoverPoblacio(Tauler t) {
		
	}
	
	
	public void escriureSet() {
		Iterator<Combinacio> it = poblacio.iterator();
		while (it.hasNext()) {
			it.next().escriu_combinacio();
		}
	}
	
	
	public Combinacio algoritmeGenetic(Tauler t) {
		
		// Retorna el primer element del conjunt poblacio
		if (primer) {
			primer = false;
			emplenarPoblacio(t);
			return poblacio.iterator().next();
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
				eliminarPoblacio(t);
			}
			else {
				crossoverPoblacio(t);
			}
			return poblacio.iterator().next();
		}
	}
}

