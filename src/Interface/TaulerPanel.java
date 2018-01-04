package Interface;

import Domini.Partida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private JLabel labelInfo;
    private JButton exitButton;
    private JButton saveButton;
    private JButton submitButton;

    public TaulerPanel(int line_number, int line_size, int colors, UIController control){
        super();

        //Preparem el layout del panell principal
        setLayout(new BorderLayout()); //TODO: posar valors finals
        setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        setPreferredSize(new Dimension(600,600));

        //Inicialitzem els subpanels
        panelJugades = new JPanel();
        panelPistes = new JPanel();
        panelContenidor = new JPanel();
        panelButtons = new JPanel();

        panelJugades.setLayout(new GridLayout(line_number, line_size, 5, 5));
        panelJugades.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        panelPistes.setLayout(new GridLayout(line_number, line_size, 5, 5));
        panelPistes.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        panelContenidor.setLayout(new GridLayout(1,2));
        panelContenidor.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
        panelButtons.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        //Inicialitzem secció de sota (butons i label)
        labelInfo = new JLabel("TEXT RANDOM A CANVIAR");
        submitButton = new JButton("Passar torn");
        saveButton = new JButton("Guardar i sortir");
        exitButton = new JButton("Sortir");

        submitButton.setPreferredSize(new Dimension(80,25));
        exitButton.setPreferredSize(new Dimension(80,25));
        saveButton.setPreferredSize(new Dimension(80,25));

        labelInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Inicialitzem posicions del tauler
        //WARNING: EN CAS DE QUE ELS BUTONS NO ES CORRESPONGUIN AMB LES POSICIONS
        //LOGIQUES DEL TAULER, PROBABLEMENT S'HAGIN D'INSERIR LES FILES EN ORDRE INVERS
        butons = new JButton[line_number][line_size];
        pistes = new JButton[line_number][line_size];

        for(int i = 0; i < line_number; i++) {
            for (int j = 0; j < line_size; ++j) {
                butons[i][j] = new JButton();
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
        //També una label per a avisos/info
        panelButtons.add(labelInfo);
        panelButtons.add(Box.createRigidArea(new Dimension(5,10))); //Separador
        panelButtons.add(submitButton);
        panelButtons.add(Box.createRigidArea(new Dimension(5,25))); //Separador
        panelButtons.add(saveButton);
        panelButtons.add(Box.createRigidArea(new Dimension(5,5))); //Separador
        panelButtons.add(exitButton);

        //Afegim subcomponents al panell final
        add(panelContenidor, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.PAGE_END);

        //----------------LISTENERS-------------------//
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.taulerToMenu(true);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.taulerToMenu(false);
            }
        });
    }
}
