package Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** <h1>Classe gestora de la vista de Help/About.</h1>
 *
 *  <p>La classe s'encarrega de gestionar la generació de la vista del Help/About.
 *  Està associada a un arxiu .form de mateix nom per a dissenyar el seu layout.
 *  </p>
 *
 *  @author Ricard Zarco Badia
 */

public class HelpForm {
    private JPanel panel1;
    private JTextPane textPane;
    private JButton tornarButton;
    //private UIController control;

    /**Constructora per defecte de la classe.     *
     * @param control Instancia de UIController. Necessaria per a coordinació entre vistes.*/
    public HelpForm(UIController control){
        //"Lliguem" el controlador, descomentar juntament amb l'atribut de dalt
        //si no podem fer-ho directament amb el paràmetre passat al constructor
        //this.control = control;

        tornarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Tornem al menu principal
                control.helpToMenu();
            }
        });
    }

    /** Funció que retorna el panell d'aquesta vista.*/
    public JPanel getPanel(){
        return panel1;
    }
}
