package Domini;

import Dades.Ranking;

public class PartidaBots extends Partida {

    private PCMaker pcMaker1, pcMaker2;
    private PCBreaker pcBreaker1, pcBreaker2;

    /** Constructora de la classe*/
    public PartidaBots(String nom, int conf, int tamLinia, int numColors){
        super(nom, conf, tamLinia, numColors);

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
                //Eliminada seleccio de combinacio inicial, ara es tria desde GUI
                conf = 1;
            }
            else{//PCBreaker i HMaker//NO UTILITZADA DE MOMENT
                pcbreaker.setPuntuacio(oldTauler.puntuacio());
                pcmaker = new PCMaker(pcbreaker.getNom(), pcbreaker.getPuntuacio());
                hbreaker = new HBreaker(hmaker.getNom(),hmaker.getPuntuacio());
                //Eliminada seleccio de combinacio inicial, ara es tria desde GUI
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
}
