package Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaFinal {
    private JButton reiniciarPartidaButton;
    private JButton menuPrincipalButton;
    private JLabel GuanyatPerdutLAbel;
    private JLabel CPULabel;
    private JLabel PlayerLAbel;
    private JPanel panel1;

    public PantallaFinal(UIController control)  {

        reiniciarPartidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: AIXO ESTA MALAMENT REEEEEEEEEE
                control.finalToMenu();
            }
        });
        menuPrincipalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.finalToMenu();
            }
        });
    }

    public void setLabels(String nom1, String nom2, int punts1, int punts2){
        PlayerLAbel.setText(nom1 + " ha aconseguit " + punts1 + " punts");
        CPULabel.setText(nom2 + " ha aconseguit " + punts2 + " punts");
    }

    public JPanel getPanel(){
        return panel1;
    }

}


