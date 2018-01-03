package Interface;

import Domini.Partida;

import javax.swing.*;
import java.awt.*;

public class TaulerPanel extends JPanel {

    //Logica associada
    private Partida partida;

    //Elements interns
    private JButton[][] butons;
    private JButton[][] pistes;
    private JPanel panelJugades;
    private JPanel panelPistes;
    private JPanel panelContenidor;
    private JPanel panelButtons;

    private JButton exitButton;
    private JButton saveButton;
    private JButton submitButton;

    public TaulerPanel(int line_number, int line_size, int colors){
        super();

        //Preparem el layout del panell principal
        setLayout(new BorderLayout()); //TODO: posar valors finals
        setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        //Inicialitzem els subpanels
        panelJugades = new JPanel();
        panelPistes = new JPanel();
        panelContenidor = new JPanel();
        panelButtons = new JPanel();

        panelJugades.setLayout(new GridLayout(line_number, line_size, 5, 5));
        panelPistes.setLayout(new GridLayout(line_number, line_size, 5, 5));
        panelContenidor.setLayout(new GridLayout(1,2));
        panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.PAGE_AXIS));

        //Inicialitzem els botons
        submitButton = new JButton("Passar torn");
        saveButton = new JButton("Guardar i sortir");
        exitButton = new JButton("Sortir");

        submitButton.setSize(50,25);
        exitButton.setSize(50,25);
        saveButton.setSize(50,25);

        //Inicialitzem posicions del tauler
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

        //Afegim els subpanels al panellContenidor
        panelContenidor.add(panelJugades);
        panelContenidor.add(panelPistes);

        //Afegim els buttons al contenidor de buttons
        panelButtons.add(submitButton);
        panelButtons.add(saveButton);
        panelButtons.add(exitButton);

        //Afegim subcomponents al panell final
        add(panelContenidor, BorderLayout.CENTER);
    }
}
