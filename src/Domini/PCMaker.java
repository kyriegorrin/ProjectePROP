package Domini;

import java.util.Random;

/**<h1>Classe que representa un CodeMaker controlat per la màquina.</h1>
 * <p>PCMaker és una subclasse de Jugador que permet desenvolupar les funcionalitats necessàries d'un jugador controlat per la màquina
 * en el rol de CodeMaker. La seva única funció rellevant és inserir la combinació inicial a trobar de la partida,
 * ja que la computació de les pistes de cada linia del tauler ja està automatitzada.</p>
 *
 * @author Ricard Zarco Badia
 */
public class PCMaker extends Jugador {

    /**
     * Constructora per defecte. Utilitza la constructora de la superclasse (Jugador).
     */
    public PCMaker() {
    }

    /** Constructora que crea un objecte PCMaker amb dos paràmetres.
     * @param nom És un String que identifica a un jugador.
     * @param puntuacio És un nombre enter &gt;= 0;
     */
    public PCMaker(String nom, int puntuacio) {
        super(nom, puntuacio);
    }

    /**
     * Retorna la combinació inicial del tauler (la solució).
     * La combinació és totalment aleatoria dins dels paràmetres proporcionats.
     * @param tamany Enter &gt; 0.
     * @param colors Enter &gt; 0.
     * @return Es retorna la combinacio inicial del tauler.
     */
    public Combinacio triaCombinacio(int tamany, int colors){
        Combinacio combinacio = new Combinacio(tamany);

        for(int i = 0; i < tamany; ++i){
            combinacio.set_elementx(i, new Random().nextInt(colors));
        }
        return combinacio;
    }
}
