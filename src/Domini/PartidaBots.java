package Domini;

import Dades.Ranking;

/** <h1>Classe que gestiona una partida completa de MasterMind per a IAvsIA.</h1>
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

public class PartidaBots extends Partida {

    private PCMaker pcMaker1, pcMaker2;
    private PCBreaker pcBreaker1, pcBreaker2;

    /** Constructora de la classe.
     * @param nom Nom del jugador inicial.
     * @param conf Configuracio de la partida.
     * @param tamLinia Numero de posicions per linia.
     * @param numColors Numero de colors disponibles.
     */
    public PartidaBots(String nom, int conf, int tamLinia, int numColors){
        super(nom, conf, tamLinia, numColors);

        //Fem aixo per a tenir el nom que volem i no modificar la funcio original en Partida

        //Som partida de bots, a utilitzar pels controladors
        cpuVScpu = true;

        //Creem jugadors BOT
        pcBreaker1 = new PCBreaker("CPU_Inteligent", 0);
        pcMaker1 = new PCMaker("CPU_Estúpida", 0);
    }

    /** Funcio que permet que els jugadors facin les seves corresponents accions durant un torn. Aquesta versió
     * permet introduir una combinació (utilitzat per a la versió GUI).
     * @param comb Combinació entrant, recollida de la GUI.
     * @return Retorna 0 si el torn no ha produit cap event. 1 si el torn ha produit un final de fase.
     * 2 si el torn ha produit final de partida. 3 si no es permeten mes torns. */
    @Override
    public int fesTorn(Combinacio comb){
        int event = 0;

        if(fase == 2) event = 3;
        else{
            if(conf == 0){//HBreaker i PCMaker
                System.out.println("Inserta la teva proposta de combinacio:");
                tauler.set_ultima_linia(pcBreaker1.fesJugada(tauler));
                //Mirem si la jugada es guanyadora o hem acabat les posicions del tauler
                if(tauler.tauler_ple() || tauler.encert()) {
                    if(tauler.encert()) System.out.println("HAS ENCERTAT LA COMBINACIO!");
                    if(fase == 0) event = 1;
                    else if(fase == 1) event = 2;
                    seguentFaseGUI();
                }
            }else{//PCBreaker i HMaker
                tauler.set_ultima_linia(pcBreaker2.fesJugada(tauler));
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
     * Comportament diferenciat depenent de la fase actual. Versió utilitzada per al programa amb GUI.*/
    @Override
    protected void seguentFaseGUI(){
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
                pcBreaker1.setPuntuacio(oldTauler.puntuacio());
                pcMaker2 = new PCMaker(pcBreaker1.getNom(),pcBreaker1.getPuntuacio());
                pcBreaker2 = new PCBreaker(pcMaker1.getNom(), pcMaker1.getPuntuacio());
                //Volem que pcBreaker 2 sigui tonto
                pcBreaker2.setInteligencia(false);
                System.out.println("Inserta la combinació inicial:");
                tauler.setInitial_line(pcMaker2.triaCombinacio(tauler.getLine_size(), tauler.getColors()));//Posem nova comb inicial
                conf = 1;
            }
            else{//PCBreaker i HMaker//NO UTILITZADA DE MOMENT
                pcbreaker.setPuntuacio(oldTauler.puntuacio());
                pcmaker = new PCMaker(pcbreaker.getNom(), pcbreaker.getPuntuacio());
                hbreaker = new HBreaker(hmaker.getNom(),hmaker.getPuntuacio());
                tauler.setInitial_line(pcMaker2.triaCombinacio(tauler.getLine_size(), tauler.getColors()));
                conf = 0;
            }
            ++fase;
        }
        //Finalitzar partida i actualitzar ranking
        else if(fase == 1){
            if(conf == 0) pcBreaker1.setPuntuacio(tauler.puntuacio());
            else if(conf == 1) pcBreaker2.setPuntuacio(tauler.puntuacio());
            System.out.println("PUNTUACIO FINAL");
            System.out.println("---------------");
            System.out.println(pcBreaker1.getNom() + ": " + pcBreaker1.getPuntuacio());
            System.out.println(pcBreaker2.getNom() + ": " + pcBreaker2.getPuntuacio() + "\n");
            System.out.println("Afegint jugador humà al ranking...\n");
            System.out.println("--- JOC FINALITZAT ---\n");
            //ranking.insertaJugador(hbreaker.toString());
            ++fase;
        }
    }

    /** Funció que retorna el nom del jugador màquina, en aquest cas la màquina inteligent.
     * @return Nom del jugador màquina amb IA complexe.*/
    @Override
    public String getNomHuma(){
        return pcBreaker1.getNom();
    }

    /** Funció que retorna el nom del jugador màquina, en aquest cas la màquina tonta.
     * @return Nom del jugador màquina amb IA simple.*/
    @Override
    public String getNomCPU(){
        return pcBreaker2.getNom();
    }

    /** Funcio que retorna la puntuacio que ha aconseguit el jugador màquina inteligent.
     * @return Puntuacio del jugador humà inteligent.*/
    @Override
    public int getPuntuacioHuma(){
        return pcBreaker1.getPuntuacio();
    }

    /** Funcio que retorna la puntuacio que ha aconseguit el jugador màquina simple.
     * @return Puntuacio del jugador màquina simple.*/
    @Override
    public int getPuntuacioCPU(){
        return pcBreaker2.getPuntuacio();
    }

}
