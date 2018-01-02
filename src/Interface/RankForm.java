package Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RankForm {
    private JTable table1;
    private JButton tornarButton;
    private JPanel panel1;

    public RankForm(UIController control){
        tornarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.rankingToMenu();
            }
        });
    }

    public JPanel getPanel(){
        return panel1;
    }
}
