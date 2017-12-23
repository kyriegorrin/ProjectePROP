package Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalMenu {
    private JPanel panelMain;
    private JButton jugarButton;
    private JButton rànquingButton;
    private JButton ajudaButton;
    private JButton sortirButton;
    private JLabel ImageLogo;


    public PrincipalMenu() {
        sortirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        ajudaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void createUIComponents(){
        ImageLogo = new JLabel(new ImageIcon("MastermindLogo.png"));
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Menu Principal");
        frame.setContentPane(new PrincipalMenu().panelMain);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
