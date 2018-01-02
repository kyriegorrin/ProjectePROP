package Interface;

import javax.swing.*;

public class UIController {

    //Tots els frames necessaris per a l'aplicació, a ampliar
    private JFrame frameMenu;
    private JFrame frameHelp;
    private JFrame frameConfig;
    private JFrame frameTauler;
    private JFrame frameRanking;
    private JFrame frameFinal;
    private int dificultat;

    /**Constructora de la classe. Prepara totes les vistes de l'aplicacio.*/
    public UIController(){
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

        frameTauler = new JFrame("Tauler");
        frameTauler.setContentPane(new Tauler(this).getPanel());
        frameTauler.setDefaultCloseOperation(frameHelp.EXIT_ON_CLOSE);
        frameTauler.pack();

        frameRanking = new JFrame("Ranking");
        frameRanking.setContentPane(new RankForm(this).getPanel());
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

    /** FUNCIÓ DE PROBA - PROBA - PROBA - PROBA - PROBA */
    public void configurationsToTauler(){
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
    //-------------------------PAS DE PARAMETRES---------------------------------//

    public void setDificultat(int n){
        dificultat = n;
    }

    public int getDificultat(){
        return dificultat;
    }

    //-----------------------------MAIN------------------------------------------//

    //Funció main, potser en un futur la implementem en una classe a part.
    //Fins que no trobem raons per no fer-ho, s'implementarà aquí.
    public static void main(String args[]){
        UIController control = new UIController();

        control.inicialitza();
    }
}