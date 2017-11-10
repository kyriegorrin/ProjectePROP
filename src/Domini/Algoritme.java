package Domini;
import java.util.Random;

public class Algoritme {
	private Combinacio primer;
	private Combinacio segon;
	private Combinacio resultat;
	
	public Algoritme(Tauler t) {
		primer = new Combinacio(t.getLine_size());
		segon = new Combinacio(t.getLine_size());
		resultat = new Combinacio(t.getLine_size());
	}
	
	public Combinacio evolucio(Tauler t) {
		obtenir_millors(t);
		Random r = new Random();
		for (int i = 0; i <= t.getLine_size(); ++i) {
			if (r.nextInt(10) % 2 == 0) {
				resultat.set_elementx(i, primer.get_elementx(i));
			}
			else {
				resultat.set_elementx(i, segon.get_elementx(i));
			}
		}
		resultat.set_elementx(r.nextInt(t.getLine_size()), r.nextInt(t.getColors()));
		return resultat;
	}
	
	public void obtenir_millors(Tauler t) {
		// La taula te 1 combinacio
		if (t.getUltima() == t.getLine_number() - 2) {
			primer = t.getlinia(t.getUltima());
		}
		// La taula te 2 o mes combinacions
		else {
			int max, linia_max, linia_max2;
			max = linia_max = linia_max2 = 0;
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
					linia_max2 = linia_max;
					linia_max = i;
				}
			}
			primer = t.getlinia(linia_max);
			segon = t.getlinia(linia_max2);
		}
	}
}
