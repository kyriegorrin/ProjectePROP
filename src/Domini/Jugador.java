package Domini;

/**<h1>Classe pare de tots els tipus de jugador.</h1>
 * <p>Determina els atributs intrínsecs d'un jugador, els mètodes necessaris per a gestionar-los i
 * proporciona mètodes virtuals a implementar per les seves subclasses.</p>
 *
 * @author Ricard Zarco Badia
 */
public class Jugador {

    private String nom; /// id del jugador
    private int puntuacio; ///puntuacio del jugador

    /**
     * Constructora que crea un Jugador buit i amb puntuació 0.
     */
    public Jugador(){
        this.nom = null;
        this.puntuacio = 0;
    }

    /**
     * Constructora que crea un jugador amb els dos paràmetres donats.
     * @param nom És un String que identifica un jugador.
     * @param puntuacio És un nombre enter &gt;= 0.
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
     * Setter del nom del jugador.
     * @param nom String que conté el nom a assignar al jugador.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
     * Getter de la puntuació d'un jugador.
     * @return Es retorna l'atribut puntuació.
     */
    public int getPuntuacio() {
        return puntuacio;
    }

    /**
     * Setter de la puntuació d'un Jugador.
     * @param puntuacio És un enter &gt; 0.
     */
    public void setPuntuacio(int puntuacio) {
        this.puntuacio = puntuacio;
    }

    /**
     * Funció virtual, diferent per a cada subclasse. Retorna una combinació proposada pel jugador.
     * @param tauler Un tauler inicialitzat.
     * @return Retorna la combinació proposada, en aquest cas és null ja que és un mètode virtual a implementar per les subclasses.
     */
    public Combinacio fesJugada(Tauler tauler){
        //El return no sera utilitzat, aixo es un metode virtual per als CodeBreakers
        return null;
    }

    /**
     * Converteix el nom i la puntuació en un string.
     * @return Un string contenint el nom i la puntuació del jugador.
     */
    public String toString(){
        return nom + " " + puntuacio;
    }
}
