package Domini;

/**<h1>Classe que representa un CodeMaker humà.</h1>
 *
 * <p>HMaker és una subclasse de Jugador que permet desenvolupar les funcionalitats necessàries d'un jugador humà
 * en el rol de CodeMaker. La seva única funció rellevant és permetre a l'usuari inserir la combinació inicial a trobar de la
 * partida, ja que la computació de les pistes de cada linia del tauler ja està automatitzada.</p>
 *
 * @author Ricard Zarco Badia
 */

public class HMaker extends Jugador{

    /** Constructora per defecte. Utilitza la constructora de la superclasse (Jugador).
     */
    public HMaker() {
        super();
    }

    /** Constructora que crea un objecte HMaker amb dos paràmetres.
     * @param nom És un String que identifica a un jugador.
     * @param puntuacio És un nombre enter &gt;= 0;
     */
    public HMaker(String nom, int puntuacio) {
        super(nom, puntuacio);
    }

    /** Mètode utilitzat per a permetre insertar una combinació inicial donada per l'usuari (HMaker).
     *  Si la combinació inserida no respecta els paràmetres de color, la torna a demanar.
     * @param tamany Enter &gt; 0 que determina el nombre de posicions de la combinació a inserir per l'usuari.
     * @param colors Enter &gt; 0 que determina el nombre de colors possibles a utilitzar en la combinació a inserir per l'usuari.
     * @return Retorna la combinació inserida per l'usuari. Si la combinació inserida és superior en longitud a la requerida,
     *          retorna una combinació composta per les primeres "tamany" posicions inserides.
     */
    public Combinacio triaCombinacio(int tamany, int colors){
        int correcte = -1;
        Combinacio combinacio = new Combinacio(tamany);

        while(correcte < 0){
            System.out.println("Rang de colors: 0.."+ (colors-1));
            combinacio = new Combinacio(tamany);
            combinacio.llegir_comb();
            correcte = combinacio.comprovar_colors(colors);

            if (correcte < 0){
                System.out.println("La combinacio no respecta els parametres! Torna a ficar-la. \n");
            }
        }
        return combinacio;
    }
}
