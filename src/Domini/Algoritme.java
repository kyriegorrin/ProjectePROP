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
	
	public Algoritme() {
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
	
	public Combinacio calcularFitness(Tauler t) {
		Iterator<Combinacio> it = poblacio.iterator();
		Combinacio c = new Combinacio(t.getLine_size());
		Combinacio c_max = new Combinacio(poblacio.iterator().next());
		int max = 0;
		while (it.hasNext()) {
			c = it.next();
			int puntuacio = 0;
			for (int i = t.getUltima(); i < t.getLine_number(); ++i) {
				for (int j = 0; j < t.getLine_size(); ++j) {
					for (int k = 0; k < t.getLine_size(); ++k) {
						if ((t.getlinia(i)).get_elementx(j) == c.get_elementx(k)) ++puntuacio;
					}
				}
			}
			if (puntuacio > max) {
				max = puntuacio;
				c_max = c;
			}
		}
		Combinacio resultat = new Combinacio(c_max);
		return resultat;
	}
	
	public void crossoverPoblacio(Tauler t) {
		Iterator<Combinacio> it = poblacio.iterator();
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
				eliminarPoblacio(t);
			}
			else {
				crossoverPoblacio(t);
			}
			Combinacio c = calcularFitness(t);
			Combinacio resultat = new Combinacio(c);
			return resultat;
		}
	}
}

