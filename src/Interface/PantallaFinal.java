package Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaFinal {
    private JButton menuPrincipalButton;
    private JLabel guanyatPerdutLAbel;
    private JLabel cpuLabel;
    private JLabel playerLAbel;
    private JPanel panel1;
    private JLabel guanyadorLabel;
    private JButton reiniciarButton;

    public PantallaFinal(UIController control)  {
        guanyatPerdutLAbel.setText("PARTIDA ACABADA");
        menuPrincipalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.finalToMenu();
            }
        });
        reiniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Abusem el fet que la configuració està existent a UIController
                control.configurationsToTauler();
            }
        });
    }

    public void setLabels(String nom1, String nom2, int punts1, int punts2){
        if(punts1 >punts2){guanyadorLabel.setText(nom1 + " HA GUANYAT LA PARTIDA!!"); }
        else if (punts2 > punts1){guanyadorLabel.setText(nom2 + " HA GUANYAT LA PARTIDA!!"); }
        else guanyadorLabel.setText("EMPAT!!");
        playerLAbel.setText(nom1 + " ha aconseguit " + punts1 + " punts");
        cpuLabel.setText(nom2 + " ha aconseguit " + punts2 + " punts");
    }

    public JPanel getPanel(){
        return panel1;
    }
}


