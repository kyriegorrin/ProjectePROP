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
    private boolean dificultat = false;
    private int files = 0;
    private int columnes = 0;
    private int colors = 0;


    public Configurations() {
        facilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dificultat = true;
                files = 8;
                columnes = 3;
                colors = 3;
            }
        });
        normalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dificultat = true;
                files = 8;
                columnes = 4;
                colors = 4;
            }
        });
        dificilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dificultat = true;
                files = 8;
                columnes = 5;
                colors = 5;
            }
        });
        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dificultat){

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
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Configurar Partida");
        frame.setContentPane(new Configurations().panelConfiguration);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}