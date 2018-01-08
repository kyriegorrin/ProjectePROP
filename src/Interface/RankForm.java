package Interface;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;

import javax.sound.midi.MetaMessage;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** <h1>Classe gestora de la vista del ranking.</h1>
 *
 *  <p>La classe RankForm genera i gestionauna vista que conté el ranking de jugadors. Està associat
 *  a un arxiu .form de mateix nom per a generar el disseny de la vista.
 *  </p>
 *
 *  @author Ricard Zarco Badia
 */

public class RankForm {
    private JTable table1;
    private JButton tornarButton;
    private JPanel panel1;
    private JLabel rankLabel;

    private DefaultTableModel tableModel;

    /**Constructora per defecte de la classe.     *
     * @param control Instancia de UIController. Necessaria per a coordinació entre vistes.*/
    public RankForm(UIController control){

        tornarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.rankingToMenu();
            }
        });
    }

    /** Funció que retorna el JPanel contenidor de la vista d'aquesta classe.
     * @return JPanel associat a la classe.*/
    public JPanel getPanel(){
        return panel1;
    }

    /** Funció que refresca el contingut del ranking a la vista.
     * @param rankString Conté el ranking en format String.*/
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
        tableModel.setColumnIdentifiers(columnNames);
        table1.setModel(tableModel);
    }
}
