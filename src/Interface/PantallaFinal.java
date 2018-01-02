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
                control.FinalToMenu();
            }
        });
        menuPrincipalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    public JPanel getPanel(){
        return panel1;
    }

}


