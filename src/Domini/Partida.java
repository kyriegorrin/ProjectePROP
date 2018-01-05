package Domini;

import Dades.Ranking;

/** <h1>Classe que gestiona una partida completa de MasterMind.</h1>
 *
 *  <p>Aquesta classe conté tots els mètodes necessaris per a gestionar i assegurar que es pugui desenvolupar una partida
 *  completa de MasterMind. Una partida completa consta de dues fases, on es juga amb un rol diferent en cada una. Quan aquestes
 *  dues classes es completen, es dona la partida per acabada. També existeixen mètodes complementaris per a funcionalitats adicionals,
 *  com mirar el rànking, per exemple.</p>
 *
 *  El nombre de linies per defecte és 15.
 *
 *  @author Ricard Zarco Badia
 */
public class Partida {
    //------------------------------ATRIBUTS------------------------------------//
    //Jugadors
    private HBreaker hbreaker;
    private HMaker hmaker;
    private PCBreaker pcbreaker;
    private PCMaker pcmaker;

    //Taulers
    private Tauler tauler;
    private Tauler oldTauler;
    //TODO: modifica el valor a 15 per a l'entrega final.
    public static final int NUM_LINIES = 15;

    //Capa de dades
    private Ranking ranking;

    //Variables d'estat de la partida
    private int conf;
    //Si es 0, juguen HBreaker i PCMaker primer
    //Si es 1, juguen HMaker i PCBreaker primer

    private int fase;
    //Si es 0, es la primera fase de la partida.
    //Si es 1, es la segona fase (el tauler s'ha reiniciat i s'han intercanviat rols)
    //Si es 2, la partida s'ha acabat

    //---------------------------------METODES----------------------------------//

    /**Constructora de la classe.
     * @param nom string que conté el nom amb el que volem identificar al jugador humà.
    * @param conf determina quin jugador ocupa quin rol inicialment. Ha de ser 0 (CodeBreaker humà) o 1 (CodeBreaker màquina).
    * @param tamLinia indica quantes posicions te cada linia. Ha de ser &gt; 0.
    * @param numColors indica quants colors utilitzem. Ha de ser &gt; 0*/
    public Partida(String nom, int conf, int tamLinia, int numColors){
        this.conf = conf;
        this.fase = 0;
        tauler = new Tauler(NUM_LINIES, tamLinia, numColors);
        ranking = new Ranking();
        ranking.inicialitza();

        if(this.conf == 0){
            hbreaker = new HBreaker(nom, 0);
            pcmaker = new PCMaker("CPU", 0);
            tauler.setInitial_line(pcmaker.triaCombinacio(tamLinia,numColors));
        }else{
            hmaker = new HMaker(nom, 0);
            pcbreaker = new PCBreaker("CPU", 0);
            tauler.setInitial_line(hmaker.triaCombinacio(tamLinia,numColors));
        }
    }

    /** Funcio que permet que els jugadors facin les seves corresponents accions durant un torn.
     * @return Retorna 0 si el torn no ha produit cap event. 1 si el torn ha produit un final de fase.
     * 2 si el torn ha produit final de partida. 3 si no es permeten mes torns.
     */
    public int fesTorn(){
        int event = 0;

        if(fase == 2) event = 3;
        else{
            if(conf == 0){//HBreaker i PCMaker
                System.out.println("Inserta la teva proposta de combinacio:");
                tauler.set_ultima_linia(hbreaker.fesJugada(tauler));
                //Mirem si la jugada es guanyadora o hem acabat les posicions del tauler
                if(tauler.tauler_ple() || tauler.encert()) {
                    if(tauler.encert()) System.out.println("HAS ENCERTAT LA COMBINACIO!");
                    if(fase == 0) event = 1;
                    else if(fase == 1) event = 2;
                    seguentFase();
                }
            }else{//PCBreaker i HMaker
                tauler.set_ultima_linia(pcbreaker.fesJugada(tauler));
                //Mirem si la jugada es guanyadora o hem acabat les posicions del tauler
                if (tauler.tauler_ple() || tauler.encert()) {
                    if(tauler.encert()) System.out.println("HAS ENCERTAT LA COMBINACIO!");
                    if (fase == 0) event = 1;
                    else if (fase == 1) event = 2;
                    seguentFase();
                }
            }
        }
        return event;
    }

    /** Funcio que permet que els jugadors facin les seves corresponents accions durant un torn. Aquesta versió
     * permet introduir una combinació (utilitzat per a la versió GUI).
     * @param comb Combinació entrant, recollida de la GUI.
     * @return Retorna 0 si el torn no ha produit cap event. 1 si el torn ha produit un final de fase.
     * 2 si el torn ha produit final de partida. 3 si no es permeten mes torns.
     */
    public int fesTorn(Combinacio comb){
        int event = 0;

        if(fase == 2) event = 3;
        else{
            if(conf == 0){//HBreaker i PCMaker
                System.out.println("Inserta la teva proposta de combinacio:");
                tauler.set_ultima_linia(comb);
                //Mirem si la jugada es guanyadora o hem acabat les posicions del tauler
                if(tauler.tauler_ple() || tauler.encert()) {
                    if(tauler.encert()) System.out.println("HAS ENCERTAT LA COMBINACIO!");
                    if(fase == 0) event = 1;
                    else if(fase == 1) event = 2;
                    seguentFaseGUI();
                }
            }else{//PCBreaker i HMaker
                tauler.set_ultima_linia(comb);
                //Mirem si la jugada es guanyadora o hem acabat les posicions del tauler
                if (tauler.tauler_ple() || tauler.encert()) {
                    if(tauler.encert()) System.out.println("HAS ENCERTAT LA COMBINACIO!");
                    if (fase == 0) event = 1;
                    else if (fase == 1) event = 2;
                    seguentFaseGUI();
                }
            }
        }
        return event;
    }

    /** Metode intern per a avançar de fase i fer les operacions que aixo requereixi.
     * Comportament diferenciat depenent de la fase actual
     */
    private void seguentFase(){
        //Canvi de rols i nou tauler
        if(fase == 0){
            //Reiniciem tauler
            oldTauler = tauler;
            oldTauler.escriu_tot(); //Aquesta la posem aquí perque si no mai sabrem l'estat final del tauler un cop canviem de rol
            tauler = new Tauler(oldTauler.getLine_number(),oldTauler.getLine_size(), oldTauler.getColors());
            System.out.println("\n--FASE ACABADA--");
            System.out.println("Canviant rols i iniciant segona partida...");

            //Intercanvi de rols
            if(conf == 0){//HBreaker i PCMaker
                hbreaker.setPuntuacio(oldTauler.puntuacio());
                hmaker = new HMaker(hbreaker.getNom(),hbreaker.getPuntuacio());
                pcbreaker = new PCBreaker(pcmaker.getNom(), pcmaker.getPuntuacio());
                System.out.println("Inserta la combinació inicial:");
                tauler.setInitial_line(hmaker.triaCombinacio(tauler.getLine_size(), tauler.getColors()));
                conf = 1;
            }
            else{//PCBreaker i HMaker
                pcbreaker.setPuntuacio(oldTauler.puntuacio());
                pcmaker = new PCMaker(pcbreaker.getNom(), pcbreaker.getPuntuacio());
                hbreaker = new HBreaker(hmaker.getNom(),hmaker.getPuntuacio());
                tauler.setInitial_line(pcmaker.triaCombinacio(tauler.getLine_size(), tauler.getColors()));
                conf = 0;
            }
            ++fase;
        }
        //Finalitzar partida i actualitzar ranking
        else if(fase == 1){
            if(conf == 0) hbreaker.setPuntuacio(tauler.puntuacio());
            else if(conf == 1) pcbreaker.setPuntuacio(tauler.puntuacio());
            System.out.println("PUNTUACIO FINAL");
            System.out.println("---------------");
            System.out.println(hbreaker.getNom() + ": " + hbreaker.getPuntuacio());
            System.out.println(pcbreaker.getNom() + ": " + pcbreaker.getPuntuacio() + "\n");
            System.out.println("Afegint jugador humà al ranking...\n");
            System.out.println("--- JOC FINALITZAT ---\n");
            ranking.insertaJugador(hbreaker.toString());
            ++fase;
        }
    }

    /** Metode intern per a avançar de fase i fer les operacions que aixo requereixi.
     * Comportament diferenciat depenent de la fase actual. Versió utilitzada per al programa amb GUI.
     */
    private void seguentFaseGUI(){
        //Canvi de rols i nou tauler
        if(fase == 0){
            //Reiniciem tauler
            oldTauler = tauler;
            oldTauler.escriu_tot(); //Aquesta la posem aquí perque si no mai sabrem l'estat final del tauler un cop canviem de rol
            tauler = new Tauler(oldTauler.getLine_number(),oldTauler.getLine_size(), oldTauler.getColors());
            System.out.println("\n--FASE ACABADA--");
            System.out.println("Canviant rols i iniciant segona partida...");

            //Intercanvi de rols
            if(conf == 0){//HBreaker i PCMaker
                hbreaker.setPuntuacio(oldTauler.puntuacio());
                hmaker = new HMaker(hbreaker.getNom(),hbreaker.getPuntuacio());
                pcbreaker = new PCBreaker(pcmaker.getNom(), pcmaker.getPuntuacio());
                System.out.println("Inserta la combinació inicial:");
                tauler.setInitial_line(hmaker.triaCombinacio(tauler.getLine_size(), tauler.getColors()));
                conf = 1;
            }
            else{//PCBreaker i HMaker
                pcbreaker.setPuntuacio(oldTauler.puntuacio());
                pcmaker = new PCMaker(pcbreaker.getNom(), pcbreaker.getPuntuacio());
                hbreaker = new HBreaker(hmaker.getNom(),hmaker.getPuntuacio());
                tauler.setInitial_line(pcmaker.triaCombinacio(tauler.getLine_size(), tauler.getColors()));
                conf = 0;
            }
            ++fase;
        }
        //Finalitzar partida i actualitzar ranking
        else if(fase == 1){
            if(conf == 0) hbreaker.setPuntuacio(tauler.puntuacio());
            else if(conf == 1) pcbreaker.setPuntuacio(tauler.puntuacio());
            System.out.println("PUNTUACIO FINAL");
            System.out.println("---------------");
            System.out.println(hbreaker.getNom() + ": " + hbreaker.getPuntuacio());
            System.out.println(pcbreaker.getNom() + ": " + pcbreaker.getPuntuacio() + "\n");
            System.out.println("Afegint jugador humà al ranking...\n");
            System.out.println("--- JOC FINALITZAT ---\n");
            ranking.insertaJugador(hbreaker.toString());
            ++fase;
        }
    }

    /** Funcio que retorna la combinació de pistes que conté la fila indicada.
     *  @param fila Fila de la que volem la combinació de pistes.
     * @return Retorna la combinació de pistes de la fila desitjada.
     */
    public Combinacio getPista(int fila){
        return tauler.get_solucio_linia_natural(fila);
    }

    /** Mètode que mostra l'estat del ranking per pantalla.
     */
    public void mostraRanking(){
        String stringRanking = ranking.toString();
        if(stringRanking.equals("")) System.out.println("No hi ha ningu al ranking");
        else System.out.println(stringRanking);
    }

    /** Mètode que mostra l'estat del tauler per pantalla.
     */
    public void mostraTauler(){
        tauler.escriu_tot();
    }
}
