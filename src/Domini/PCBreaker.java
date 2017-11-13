package Domini;

/**<h1>Classe que representa un CodeBreaker controlat per la màquina.</h1>
 *
 * <p>PCBreaker és una subclasse de Jugador que permet desenvolupar les funcionalitats necessàries d'un jugador controlat per la màquina
 * en el rol de CodeBreaker. La seva única funció rellevant és permetre a la màquina fer una jugada que consisteix en computar
 * una combinació per a intentar encertar la combinació guanyadora de la partida.</p>
 *
 * @author Ricard Zarco Badia
 */
public class PCBreaker extends Jugador{
    private Algoritme algoritme; 

    /** Constructora per defecte. Utilitza la constructora de la superclasse (Jugador).
     */
    public PCBreaker() {
    }

    /** Constructora que crea un objecte PCBreaker amb dos paràmetres.
     * @param nom És un String que identifica a un jugador.
     * @param puntuacio És un nombre enter &gt;= 0;
     */
    public PCBreaker(String nom, int puntuacio) {
        super(nom, puntuacio);
        algoritme = new Algoritme();
    }

    /** Mètode utilitzat per a permetre al PCBreaker computar i proposar una combinació en base a l'estat del tauler.
     * @param tauler El tauler on es desenvolupa la partida.
     * @return Retorna una combinació computada per PCBreaker.
     */
    @Override
    public Combinacio fesJugada(Tauler tauler){
        Combinacio combinacio = algoritme.algoritmeGenetic(tauler);
        return combinacio;
    }
}
