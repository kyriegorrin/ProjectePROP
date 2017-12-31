package Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalMenu {
    private JPanel panelMain;
    private JButton jugarButton;
    private JButton ranquingButton;
    private JButton ajudaButton;
    private JButton sortirButton;
    private JLabel ImageLogo;


    public PrincipalMenu(UIController control) {
        sortirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        ajudaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.menuToHelp();
            }
        });
        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.menuToConfigurations();
            }
        });
        ranquingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // FALTA AQUI!
            }
        });
    }

    private void createUIComponents(){
        ImageLogo = new JLabel(new ImageIcon("MastermindLogo.png"));
    }

    public JPanel getPanel(){
        return panelMain;
    }
    /*
    public static void main(String[] args){
        JFrame frame = new JFrame("Menu Principal");
        frame.setContentPane(new PrincipalMenu().panelMain);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }*/
}