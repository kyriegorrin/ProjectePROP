package Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Configurations {
    private JButton facilButton;
    private JButton normalButton;
    private JButton dificilButton;
    private JButton jugarButton;
    private JPanel panelConfiguration;
    private JButton tornarButton;
    private JLabel visualitzadorDificultat;
    private JCheckBox pvpCheckBox;
    private JCheckBox pveCheckBox;
    private boolean dificultat = false;
    private boolean interaccioCheckBox = false;

    private JTextField nomTextField; // Nom del jugador


    /** Parametres per enviar */
    private int tipus = 0; // Tipus de dificulta: 0, 1 o 2 segons el nivell
    private boolean jugador = true; // True = Màquina VS Màquina; False Jugador VS Màquina


    public Configurations(UIController control) {
        facilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dificultat = true;
                tipus = 0; // facil
                visualitzadorDificultat.setText("Fàcil");
            }
        });
        normalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dificultat = true;
                tipus = 1; // normal
                visualitzadorDificultat.setText("Normal");
            }
        });
        dificilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dificultat = true;
                tipus = 2; // dificil
                visualitzadorDificultat.setText("Difícil");
            }
        });
        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dificultat && interaccioCheckBox){
                    if (nomTextField.getText().equals("") || nomTextField.getText().equals("Escriu el teu nom...")){
                        JDialog dialog = new JDialog();
                        JOptionPane.showMessageDialog(dialog,
                                "No s'ha escrit cap nom.",
                                "Error",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    else if (nomTextField.getText().contains(" ")){
                        JDialog dialog = new JDialog();
                        JOptionPane.showMessageDialog(dialog,
                                "El nom no ha de contenir cap espai en blanc.",
                                "Error",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        control.setDificultat(tipus);
                        control.setNomJugador(nomTextField.getText());
                        control.setJugador(jugador);
                        control.configurationsToTauler();
                    }
                }
                else {
                    if (!dificultat) {
                        JDialog dialog = new JDialog();
                        JOptionPane.showMessageDialog(dialog,
                                "No s'ha seleccionat cap dificultat.",
                                "Error",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        JDialog dialog = new JDialog();
                        JOptionPane.showMessageDialog(dialog,
                                "No s'ha seleccionat cap jugabilitat.",
                                "Error",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        tornarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.configurationsToMenu();
            }
        });
        pveCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interaccioCheckBox = true;
                jugador = false;
                pvpCheckBox.setSelected(false);

            }
        });
        pvpCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interaccioCheckBox = true;
                jugador = true;
                pveCheckBox.setSelected(false);
            }
        });
    }

    /**Funció que retorna la "vista" */
    public JPanel getPanel(){
        return panelConfiguration;
    }

}