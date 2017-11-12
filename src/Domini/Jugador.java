package Domini;
import Domini.Tauler;

/**
 * 
 * CLASE JUGADOR
 *
 */

public class Jugador {

    private String nom; /// id del jugador
    private int puntuacio; ///puntuaio del jugador

    /**
     * Constructora que crea un Jugador buit.
     */
    public Jugador(){
        this.nom = null;
        this.puntuacio = 0;
    }

    /**
     * Constructora que crea un jugador amb els dos parametres.
     * @param nom Es un String que identifica un jugador.
     * @param puntuacio Es un Enter >=00.
     */
    public Jugador(String nom, int puntuacio) {
        this.nom = nom;
        this.puntuacio = puntuacio;
    }

    /**
     * Getter del nom del jugador.
     * @return Es retorna l'atribut nom.
     */
    public String getNom() {
        return nom;
    }

    /**
     *Setter del nom del jugador.
     * 
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
     * Getter de la puntuacio d'unjugador.
     * @return Es retorna l'atribut puntuacio.
     */
    public int getPuntuacio() {
        return puntuacio;
    }
    /**
     * Setter de la puntuacio d'un Jugador.
     * @param puntuacio Es unenter > 0.
     */
    public void setPuntuacio(int puntuacio) {
        this.puntuacio = puntuacio;
    }


    /**
     * Funcio virtual, diferent per a cada subclasse, retorna una combinació en forma d'enter.
     * @param tauler de la partida.
     * @return En aquest cas es retorna null.
     */
    public Combinacio fesJugada(Tauler tauler){
        //El return no sera utilitzat, aixo es un metode virtual per als CodeBreakers
        return null;
    }
    /**
     * Converteix el nom i la puntiacio en un string.
     */
    public String toString(){
        return nom + " " + puntuacio;
    }
}
