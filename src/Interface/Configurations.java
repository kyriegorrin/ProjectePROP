package Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Configurations {
    private JButton facilButton;
    private JButton normalButton;
    private JButton dificilButton;
    private JRadioButton PVPadioButton;
    private JRadioButton PVERadioButton;
    private JButton jugarButton;
    private JPanel panelConfiguration;
    private JTextField textField1;
    private JButton tornarButton;
    private boolean dificultat = false;
    private int tipus = 0;


    public Configurations(UIController control) {
        facilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dificultat = true;
                tipus = 0; // facil
            }
        });
        normalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dificultat = true;
                tipus = 1; // normal
            }
        });
        dificilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dificultat = true;
                tipus = 2; // dificil
            }
        });
        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dificultat){
                    control.setDificultat(tipus);
                    control.configurationsToTauler();
                }
                else {
                    JDialog dialog = new JDialog();
                    JOptionPane.showMessageDialog(dialog,
                            "No s'ha seleccionat cap dificultat.",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        tornarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.configurationsToMenu();
            }
        });
    }

    /**Funció que retorna la "vista"*/
    public JPanel getPanel(){
        return panelConfiguration;
    }

    /**Funció que retorna el "tipus" de dificultat*/
    public int getTipus(){
        return tipus;
    }

    /*
    public static void main(String[] args) {
        JFrame frame = new JFrame("Configurar Partida");
        frame.setContentPane(new Configurations().panelConfiguration);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    */
}