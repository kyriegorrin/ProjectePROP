package Domini;
import java.util.Random;

public class Algoritme {
	private Combinacio primer;
	private Combinacio segon;
	
	public Algoritme(Tauler t) {
		primer = new Combinacio(t.getLine_size());
		segon = new Combinacio(t.getLine_size());
	}
	
	public Combinacio evolucio(Tauler t) {
		obtenir_millors(t);
		
		int max = 0;
		int linia_max = 0;
		for (int i = 0; i <= t.getUltima(); ++i) {
			int aux = 0;
			int cont = 0;
			for (int j = 0; j <= t.getLine_size(); ++j) {
				if (t.get_solucio_linia(i).get_elementx(j) == 2) {
					aux += 2;
					++cont;
				}
				else if (t.get_solucio_linia(i).get_elementx(j) == 1) {
					++aux;
					++cont;
				}
			}
			if ((aux+cont) > max) {
				max = aux + cont;
				linia_max = i;
			}
		}
		
		Combinacio resultat = t.getlinia(linia_max);
		Random r = new Random();
		resultat.set_elementx(r.nextInt(t.getLine_size()), r.nextInt(6));
		return resultat;
	}
	
	public void obtenir_millors(Tauler t) {
		// La taula te 1 combinacio
		if (t.getUltima() == 1) {

		}
		// La taula te 2 o mes combinacions
		else{
		
		}
	}
}
