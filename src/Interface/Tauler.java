package Interface;

import javax.swing.*;
import java.awt.event.*;

public class Tauler {
    private JPanel panelTauler;
    private JLabel tipus;
    private JButton tornarButton;
    private JButton actualitzarButton;

    public Tauler(UIController control) {
        tornarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.menuToConfigurations();
            }
        });
        actualitzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = control.getDificultat();
                if (n == 0) tipus.setText("EASY PZ");
                else if (n == 1) tipus.setText("NORMIE");
                else tipus.setText("GIT GUD");
            }
        });
    }

    public JPanel getPanel(){
        return panelTauler;
    }

}

