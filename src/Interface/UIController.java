package Interface;

import javax.swing.*;
import Domini.*;
import Dades.*;

public class UIController {
    //Dades auxiliars per al funcionament de la partida
    private Ranking ranking;

    //Tots els frames necessaris per a l'aplicació, a ampliar
    private JFrame frameMenu;
    private JFrame frameHelp;
    private JFrame frameConfig;
    private JFrame frameTauler;
    private JFrame frameRanking;
    private JFrame frameFinal;
    private int dificultat;

    private RankForm rankForm;

    /**Constructora de la classe. Prepara totes les vistes de l'aplicacio.*/
    public UIController(){
        //Inicialitzem classes complementaries necessaries
        rankForm = new RankForm(this);

        //Inicialitzem els frames necessaris en el seu estat inicial
        //TODO: Ampliar segons anem implementant noves vistes
        frameMenu = new JFrame("Menu Principal");
        frameMenu.setContentPane(new PrincipalMenu(this).getPanel());
        frameMenu.setDefaultCloseOperation(frameMenu.EXIT_ON_CLOSE);
        frameMenu.pack();

        frameHelp = new JFrame("Ajuda");
        frameHelp.setContentPane(new HelpForm(this).getPanel());
        frameHelp.setDefaultCloseOperation(frameHelp.EXIT_ON_CLOSE);
        frameHelp.pack();

        frameConfig = new JFrame("Configuració de Partida");
        frameConfig.setContentPane(new Configurations(this).getPanel());
        frameConfig.setDefaultCloseOperation(frameHelp.EXIT_ON_CLOSE);
        frameConfig.pack();

        /*NO S'HAURIA DE GENERAR ARA, PERO DE MOMENT ES QUEDA AIXI*/
        frameTauler = new JFrame("Tauler");
        frameTauler.setContentPane(new TaulerPanel(15, 4, 5, this)); //TODO: valors experimentals
        frameTauler.setDefaultCloseOperation(frameHelp.EXIT_ON_CLOSE);
        frameTauler.pack();

        frameRanking = new JFrame("Ranking");
        frameRanking.setContentPane(rankForm.getPanel());
        frameRanking.setDefaultCloseOperation(frameHelp.EXIT_ON_CLOSE);
        frameRanking.pack();

        frameFinal = new JFrame("PantallaFinal");
        frameFinal.setContentPane(new PantallaFinal(this).getPanel());
        frameFinal.setDefaultCloseOperation(frameHelp.EXIT_ON_CLOSE);
        frameFinal.pack();
    }

    /**Funció per a iniciar la visibilitat de les vistes necessàries.*/
    public void inicialitza(){
        //TODO: Aquesta secció s'ha d'ampliar amb els frames necessaris
        frameMenu.setVisible(true);
        frameHelp.setVisible(false);
        frameConfig.setVisible(false);
        frameTauler.setVisible(false);
        frameRanking.setVisible(false);
        frameFinal.setVisible(false);
    }

    //---------------------FUNCIONS DE CANVI DE FRAME---------------------------//

    /** Funció que permet canviar entre el frame d'ajuda i el menú principal.*/
    public void helpToMenu(){
        frameHelp.setVisible(false);
        frameMenu.setVisible(true);
    }

    /** Funció que permet canviar entre el frame del menú principal i l'ajuda.*/
    public void menuToHelp(){
        frameMenu.setVisible(false);
        frameHelp.setVisible(true);
    }

    /** Funció que permet canviar entre el frame de configuració i el menú principal.*/
    public void configurationsToMenu(){
        frameConfig.setVisible(false);
        frameMenu.setVisible(true);
    }

    /** Funció que permet canviar entre el menú principal i el frame de configuració.*/
    public void menuToConfigurations(){
        frameMenu.setVisible(false);
        frameConfig.setVisible(true);
    }

    /** Funció que permet canviar entre el menú de configuració i el tauler del joc*/
    public void configurationsToTauler(){
        //TODO: falta passar els parametres de humaVSpc o pcVSpc
        int numLinies, numColumnes, numColors;
        numLinies = 15;

        switch(dificultat){
            case 0: //Easy
                numColumnes = 3;
                numColors = 4;
                break;
            case 1: //Normal
                numColumnes = 4;
                numColors = 5;
                break;
            case 2: //Difficult
                numColumnes = 5;
                numColors = 6;
                break;
            default:
                numColumnes = 4;
                numColors = 5;
                break;
        }

        //Generació nova vista de tauler
        frameTauler = new JFrame("Tauler");
        frameTauler.setContentPane(new TaulerPanel(numLinies, numColumnes, numColors, this));
        frameTauler.setDefaultCloseOperation(frameHelp.EXIT_ON_CLOSE);
        frameTauler.pack();

        frameConfig.setVisible(false);
        frameTauler.setVisible(true);
    }

    /** Funció que permet canviar entre el menú principal i el frame de ranking.*/
    public void menuToRanking(){
        frameMenu.setVisible(false);
        frameRanking.setVisible(true);
    }

    /**Funcio que permet canviar entre el frame de ranking i el menu principal*/
    public void rankingToMenu(){
        frameRanking.setVisible(false);
        frameMenu.setVisible(true);
    }
    /**Funcio que permet canviar entre el frame Final i el menu principal*/
    public void FinalToMenu(){
        frameFinal.setVisible(false);
        frameMenu.setVisible(true);
    }

    /** Funció que permet passar del tauler al menú principal. Guarda l'estat de la partida si és necessari.
     *
     * @param guardar Si volem guardar la partida, guardar = 1. 0 altrament.
     */
    public void taulerToMenu(boolean guardar){
        if (guardar){
            //TODO: Guarda la partida, tornem al menu
        }
        else{
            //Tornem al menu sense guardar res.
            //No destruim el tauler ja que s'haurà de recrear per arribar a aquí un altre cop.
            frameTauler.setVisible(false);
            frameMenu.setVisible(true);
        }
    }

    /** Funció que permet passar del tauler a la pantalla de final de partida.*/
    public void taulerToFinal(){
        //TODO
    }
    //-------------------------PAS DE PARAMETRES---------------------------------//

    public void setDificultat(int n){
        dificultat = n;
    }

    public int getDificultat(){
        return dificultat;
    }

    //------------------------FUNCIONS AUXILIARS---------------------------------//

    /** Funció que llegeix el ranking i fa refresh de la taula gràfica*/
    public void refreshRanking(){
        ranking = new Ranking();
        ranking.inicialitza();
        rankForm.updateRankTable(ranking.toString());
    }

    //-----------------------------MAIN------------------------------------------//

    //Funció main, potser en un futur la implementem en una classe a part.
    //Fins que no trobem raons per no fer-ho, s'implementarà aquí.
    public static void main(String args[]){
        UIController control = new UIController();

        control.inicialitza();
        control.refreshRanking();
    }
}