package Interface;

import javax.swing.*;

public class UIController {

    //Tots els frames necessaris per a l'aplicació, a ampliar
    private JFrame frameMenu;
    private JFrame frameHelp;

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
    }

    /**Funció per a iniciar la visibilitat de les vistes necessàries.*/
    public void inicialitza(){
        //TODO: Aquesta secció s'ha d'ampliar amb els frames necessaris
        frameMenu.setVisible(true);
        frameHelp.setVisible(false);
    }

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

    //Funció main, potser en un futur la implementem en una classe a part.
    //Fins que no trobem raons per no fer-ho, s'implementarà aquí.
    public static void main(String args[]){
        UIController control = new UIController();

        control.inicialitza();
    }
}
