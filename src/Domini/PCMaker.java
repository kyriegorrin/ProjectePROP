package Domini;

/**
 * CLASE PC MAKER
 */
import java.util.Random;

public class PCMaker extends Jugador {
	
	/**
	 * Constructora que crea un PCMaker vuit.
	 */

    public PCMaker() {
    }

    /**
     * Constructora que crea un PCMaker pasant-li els parametres al pare.
     * @param nom String Inicializat.
     * @param puntuacio Enter inicialitzat >= 0.
     */
    public PCMaker(String nom, int puntuacio) {
        super(nom, puntuacio);
    }

    /**
     * Retorna la combinació inicial del tauler (la solucio). En aquest cas es random dintre del rang de colors.
     * @param tamany Enter > 0.
     * @param colors Enter > 0.
     * @returnEs retorna la combinacio inicial del tauler.
     */
    public Combinacio triaCombinacio(int tamany, int colors){
        Combinacio combinacio = new Combinacio(tamany);

        for(int i = 0; i < tamany; ++i){
            combinacio.set_elementx(i, new Random().nextInt(colors));
        }
        return combinacio;
    }
}
