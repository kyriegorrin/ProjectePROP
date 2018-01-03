package Interface;

import javax.swing.*;
import java.awt.*;

public class TaulerPanel extends JPanel {

    //Elements interns
    private JButton[][] butons;
    private JButton[][] pistes;
    private JPanel panelJugades;
    private JPanel panelPistes;

    public TaulerPanel(int line_number, int line_size, int colors){
        super();

        //Preparem el layout del panell principal
        setLayout(new GridLayout(1,2)); //Valors temporals

        //Inicialitzem els subpanels
        panelJugades = new JPanel();
        panelPistes = new JPanel();
        panelJugades.setLayout(new GridLayout(line_number, line_size));
        panelPistes.setLayout(new GridLayout(line_number, line_size));

        //Inicialitzem els botons

        butons = new JButton[line_number][line_size];
        pistes = new JButton[line_number][line_size];

        for(int i = 0; i < line_number; i++){
            for(int j = 0; j < line_size; ++j){
                butons[i][j] = new JButton();
            }
        }
        for(int i = 0; i < line_number; i++){
            for(int j = 0; j < line_size; ++j){
                pistes[i][j] = new JButton();
                pistes[i][j].setEnabled(false);
            }
        }

        //Afegim els components als subpanels
        for(int i = 0; i < line_number; i++){
            for(int j = 0; j < line_size; ++j){
                panelJugades.add(butons[i][j]);
                panelPistes.add(pistes[i][j]);
            }
        }

        //Afegim els subpanels al panell
        add(panelJugades);
        add(panelPistes);
    }
}
