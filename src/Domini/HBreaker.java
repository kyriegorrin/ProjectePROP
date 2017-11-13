package Domini;

/**<h1>Classe que representa un CodeBreaker humà.</h1>
 *
 * <p>HBreaker és una subclasse de Jugador que permet desenvolupar les funcionalitats necessàries d'un jugador humà
 * en el rol de CodeBreaker. La seva única funció rellevant és permetre a l'usuari fer una jugada que consisteix en proposar
 * una combinació per a intentar encertar la combinació guanyadora de la partida.</p>
 *
 * @author Ricard Zarco Badia
 */

public class HBreaker extends Jugador {

    /** Constructora per defecte. Utilitza la constructora de la superclasse (Jugador).
     */
    public HBreaker() {
        super();
    }

    /** Constructora que crea un objecte HBreaker amb dos paràmetres.
     * @param nom És un String que identifica a un jugador.
     * @param puntuacio És un nombre enter &gt;= 0;
     */
    public HBreaker(String nom, int puntuacio){
        super(nom, puntuacio);
    }

    /** Mètode utilitzat per a permetre insertar una jugada (combinació) donada per l'usuari (HBreaker).
     *  Si la combinació inserida no respecta els paràmetres de color, la torna a demanar.
     * @param tauler El tauler on es desenvolupa la partida.
     * @return Retorna la combinació inserida per l'usuari. Si la combinació inserida és superior en longitud a la requerida,
     *          retorna una combinació composta per les primeres "tamany" posicions inserides.
     */
    @Override
    public Combinacio fesJugada(Tauler tauler){
        int correcte = -1;
        Combinacio combinacio = new Combinacio(tauler.getLine_size());

        while(correcte < 0){
            combinacio.llegir_comb();
            correcte = combinacio.comprovar_colors(tauler.getColors());

            if (correcte < 0){
                System.out.println("La combinacio no respecta els parametres de color! Torna a ficar-la. \n");
            }
        }
        return combinacio;
    }
}
