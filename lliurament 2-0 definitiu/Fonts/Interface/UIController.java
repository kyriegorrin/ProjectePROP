package Interface;

import javax.swing.*;
import Domini.*;
import Dades.*;
import com.google.gson.Gson;

/** <h1>Classe gestora de la coordinació de vistes i accions.</h1>
 *
 *  <p>La classe UIController és la classe que permet a les diferents vistes comunicar-se entre elles i coordinar-se, així com provocar diferents
 *      accions depenent de les condicions internes de cada una. També conté totes les vistes i variables auxiliars per a generar-les
 *      novament en cas de que sigui necessari.
 *  </p>
 *
 *  @author Ricard Zarco Badia
 */

public class UIController {
    //Dades auxiliars per al funcionament de la partida
    private Ranking ranking;
    private int dificultat;
    private String nomJugador;
    private boolean jugador; // True = Màquina VS Màquina; False Jugador VS Màquina

    //Tots els frames necessaris per a l'aplicació, a ampliar
    private JFrame frameMenu;
    private JFrame frameHelp;
    private JFrame frameConfig;
    private JFrame frameTauler;
    private JFrame frameRanking;
    private JFrame frameFinal;

    private RankForm rankForm;
    private TaulerPanel taulerPanel;
    private PantallaFinal pantallaFinal;

    /**
     * Constructora de la classe. Prepara totes les vistes de l'aplicacio.
     */
    public UIController() {
        //Inicialitzem classes complementaries necessaries
        rankForm = new RankForm(this);
        pantallaFinal = new PantallaFinal(this);

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
        frameTauler.setContentPane(new TaulerPanel(15, 4, 5, this, "Placeholder")); //TODO: valors experimentals
        frameTauler.setDefaultCloseOperation(frameHelp.EXIT_ON_CLOSE);
        frameTauler.pack();

        frameRanking = new JFrame("Ranking");
        frameRanking.setContentPane(rankForm.getPanel());
        frameRanking.setDefaultCloseOperation(frameHelp.EXIT_ON_CLOSE);
        frameRanking.pack();

        //TAMPOC S'HAURIA DE GENERAR AQUI*/
        frameFinal = new JFrame("PantallaFinal");
        frameFinal.setContentPane(pantallaFinal.getPanel());
        frameFinal.setDefaultCloseOperation(frameHelp.EXIT_ON_CLOSE);
        frameFinal.pack();
    }

    /**
     * Funció per a iniciar la visibilitat de les vistes necessàries.
     */
    public void inicialitza() {
        //TODO: Aquesta secció s'ha d'ampliar amb els frames necessaris
        frameMenu.setVisible(true);
        frameHelp.setVisible(false);
        frameConfig.setVisible(false);
        frameTauler.setVisible(false);
        frameRanking.setVisible(false);
        frameFinal.setVisible(false);
    }

    //---------------------FUNCIONS DE CANVI DE FRAME---------------------------//

    /**
     * Funció que permet canviar entre el frame d'ajuda i el menú principal.
     */
    public void helpToMenu() {
        frameHelp.setVisible(false);
        frameMenu.setVisible(true);
    }

    /**
     * Funció que permet canviar entre el frame del menú principal i l'ajuda.
     */
    public void menuToHelp() {
        frameMenu.setVisible(false);
        frameHelp.setVisible(true);
    }

    /**
     * Funció que permet canviar entre el frame de configuració i el menú principal.
     */
    public void configurationsToMenu() {
        frameConfig.setVisible(false);
        frameMenu.setVisible(true);
    }

    /**
     * Funció que permet canviar entre el menú principal i el frame de configuració.
     * En cas d'existir una partida guardada, dona l'opcio de restaurar-la. Si no es
     * restaura, es borra.
     */
    public void menuToConfigurations() {
        //Comprovem si existeix una partida guardada.
        SaveGame savegame = new SaveGame();
        if (savegame.exists()) {
            //TODO: dialog que retorna opcio
            Object[] options = {"Sí", "No"};
            JDialog dialog = new JDialog();
            int opcio = JOptionPane.showOptionDialog(dialog,
                    "S'ha detectat una aprtida guardada. Vols restaurar-la?\n" +
                            "(No restaurar-la implica borrar les dades de guardat)",
                    "Restaurar partida",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
            if (opcio == 0) {
                //Llegim partida guardada, creem frame de tauler i restaurem el contingut visual
                String stringJSON;
                Gson gson = new Gson();
                stringJSON = savegame.load();

                Partida partida = gson.fromJson(stringJSON, Partida.class);
                jugador = partida.getMode();
                //En cas de ser una partida de IA, recarreguem la partida com a PartidaBots
                if (jugador) partida = gson.fromJson(stringJSON, PartidaBots.class);

                taulerPanel = new TaulerPanel(15, partida.getLineSize(), partida.getNumColors(), this, partida.getNomHuma());
                frameTauler = new JFrame("Tauler");
                frameTauler.setContentPane(taulerPanel);
                frameTauler.setDefaultCloseOperation(frameHelp.EXIT_ON_CLOSE);
                frameTauler.pack();

                taulerPanel.restauraPartida(partida);

                //També hem de posar les variables d'aquesta classe preparades per si hem de fer un reiniciar partida
                nomJugador = partida.getNomHuma();
                if (partida.getLineSize() == 3) dificultat = 0;
                else if (partida.getLineSize() == 4) dificultat = 1;
                else dificultat = 2;

                //Anem al frame del tauler directament
                frameMenu.setVisible(false);
                frameTauler.setVisible(true);
            } else {
                savegame.clear();
                frameMenu.setVisible(false);
                frameConfig.setVisible(true);
            }
        } else {
            frameMenu.setVisible(false);
            frameConfig.setVisible(true);
        }
    }

    /**
     * Funció que permet canviar entre el menú de configuració i el tauler del joc
     */
    public void configurationsToTauler() {
        //TODO: falta passar els parametres de humaVSpc o pcVSpc
        int numLinies, numColumnes, numColors;
        numLinies = 15;

        switch (dificultat) {
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

        //Generació nova vista de tauler personalitzada
        frameTauler = new JFrame("Tauler");
        taulerPanel = new TaulerPanel(numLinies, numColumnes, numColors, this, nomJugador);
        if (jugador) taulerPanel.setModeIA(); //En cas de que tinguem mode IA vs IA
        frameTauler.setContentPane(taulerPanel);
        frameTauler.setDefaultCloseOperation(frameHelp.EXIT_ON_CLOSE);
        frameTauler.pack();

        frameConfig.setVisible(false);
        frameTauler.setVisible(true);
    }

    /**
     * Funció que permet canviar entre el menú principal i el frame de ranking.
     */
    public void menuToRanking() {
        frameMenu.setVisible(false);
        frameRanking.setVisible(true);
    }

    /**
     * Funcio que permet canviar entre el frame de ranking i el menu principal
     */
    public void rankingToMenu() {
        frameRanking.setVisible(false);
        frameMenu.setVisible(true);
    }

    /**
     * Funcio que permet canviar entre el frame Final i el menu principal
     */
    public void finalToMenu() {
        frameFinal.setVisible(false);
        frameMenu.setVisible(true);
    }

    /**
     * Funció que permet passar del tauler al menú principal. Guarda l'estat de la partida si és necessari.
     *
     * @param guardar Si volem guardar la partida, guardar = 1. 0 altrament.
     */
    public void taulerToMenu(boolean guardar) {
        if (guardar) {
            taulerPanel.guardaPartida();
        }
        //No destruim el tauler ja que s'haurà de recrear per arribar aquí un altre cop i ja es farà llavors.
        frameTauler.setVisible(false);
        frameMenu.setVisible(true);
    }

    /** Funció que permet passar del tauler a la pantalla de final de partida.
     * @param nom1 Nom del jugador 1.
     * @param nom2 Nom del jugador 2.
     * @param punts1 Punts del jugador 1.
     * @param punts2 Punts del jugador 2.
     */
    public void taulerToFinal(String nom1, String nom2, int punts1, int punts2) {
        pantallaFinal.setLabels(nom1, nom2, punts1, punts2);
        //Fem update del ranking de la GUI
        refreshRanking();
        //Canviem el frame
        frameTauler.setVisible(false);
        frameFinal.setVisible(true);
    }
    //-------------------------PAS DE PARAMETRES---------------------------------//

    /**Setter de la variable interna de dificultat.
     * @param n Dificultat a inserir. */
    public void setDificultat(int n) {
        dificultat = n;
    }

    /**Setter de la variable interna del nom de jugador.
     * @param nom Nom a inserir. */
    public void setNomJugador(String nom) {
        nomJugador = nom;
    }

    /**Setter de la variable interna de mode de joc.
     * @param b True = mode IAvsIA, False = HumavsCPU.*/
    public void setJugador(boolean b) {
        jugador = b;
    }

    //------------------------FUNCIONS AUXILIARS---------------------------------//

    /**Funció que llegeix el ranking i fa refresh de la taula gràfica.*/
    public void refreshRanking() {
        ranking = new Ranking();
        ranking.inicialitza();
        rankForm.updateRankTable(ranking.toString());
    }

    //-----------------------------MAIN------------------------------------------//

    /** Main del programa sencer.
     * @param args Arguments d'entrada del programa. */
    public static void main(String args[]){
        UIController control = new UIController();

        control.inicialitza();
        control.refreshRanking();
    }
}
