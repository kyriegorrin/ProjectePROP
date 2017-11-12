package Domini;

/**
 * 
 * CLASE pcbREAKER
 *
 */
public class PCBreaker extends Jugador{
    private Algoritme algoritme; 

    /**
     * Constructora que crea un PCBreaker buit.
     */
    public PCBreaker() {
    }

    /**
     * Constructora que crea un PCBreaker inicialitzat.
     * @param nom Es un string inicialitzat.
     * @param puntuacio Es un enter >= 0.
     */
    public PCBreaker(String nom, int puntuacio) {
        super(nom, puntuacio);
        algoritme = new Algoritme();
    }

    /**
     * Es sobrescriu la funcio del pare de fer jugada.
     */
    @Override
    public Combinacio fesJugada(Tauler tauler){
        Combinacio combinacio = algoritme.algoritmeGenetic(tauler);
        return combinacio;
    }
}
