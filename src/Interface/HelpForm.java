package Interface;

import javax.swing.*;

public class HelpForm {
    private JPanel panel1;
    private JTextPane benvingutsAMasterMindElTextPane;
    private JButton tornarButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ajuda");
        frame.setContentPane(new HelpForm().panel1);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
