package Interface;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;

import javax.sound.midi.MetaMessage;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RankForm {
    private JTable table1;
    private JButton tornarButton;
    private JPanel panel1;
    private JLabel rankLabel;

    private DefaultTableModel tableModel;

    /**Constructora per defecte de la classe.
     *
     * @param control Instancia de UIController. Necessaria per a coordinació entre vistes.
     */
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

    public void updateRankTable(String rankString){
        String parts[] = rankString.split("\n");

        String[] columnNames = {"Posició", "Nom", "Puntuació"};
        Object[][] data = new Object[10][3];

        int i = 0;
        for(i = 0; i < parts.length; ++i){
            String[] auxParts = parts[i].split(" ");
            for(int j = 0; j < auxParts.length; j++){
                data[i][j] = auxParts[j];
            }
        }

        tableModel = new DefaultTableModel(data, columnNames);
        table1 = new JTable(tableModel);
        table1.repaint();
    }
}
