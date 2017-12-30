package Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpForm {
    private JPanel panel1;
    private JTextPane textPane;
    private JButton tornarButton;
    //private UIController control;

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
