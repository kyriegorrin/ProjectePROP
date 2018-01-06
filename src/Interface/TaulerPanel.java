package Interface;

import Domini.Combinacio;
import Domini.Partida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaulerPanel extends JPanel {

    //Logica associada
    private Partida partida;
    int torn;
    boolean codeMaker;

    //Variables auxiliars
    private int line_number, line_size, num_colors;
    private Color vectorColor[] = {
            Color.GRAY,
            Color.CYAN,
            Color.ORANGE,
            Color.RED,
            Color.PINK,
            Color.GREEN
    };

    //Elements visuals interns
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


    /** Constructora de la classe.
     *
     * @param line_number Nombre de files que te el tauler.
     * @param line_size Nombre de posicions per fila.
     * @param colors Nombre de colors disponibles.
     * @param control Instància de UIController que permet la interacció entre vistes.
     * @param nom Nom del jugador.
     */
    public TaulerPanel(int line_number, int line_size, int colors, UIController control, String nom){
        super();

        //Inicialitzem variables auxiliars
        this.line_number = line_number;
        this.line_size = line_size;
        this.num_colors = colors;

        //Inicialitzem lògica de partida
        partida = new Partida(nom, 0, line_size, colors);
        torn = 0;

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
        butons = new JButton[line_number][line_size];
        pistes = new JButton[line_number][line_size];

        for(int i = 0; i < line_number; i++) {
            for (int j = 0; j < line_size; ++j) {
                butons[i][j] = new JButton();
                pistes[i][j] = new JButton();
                //Els deshabilitem, només habilitarem la fila que necessitem
                butons[i][j].setEnabled(false);
                pistes[i][j].setEnabled(false);
                //Posem color inicial
                butons[i][j].setBackground(Color.LIGHT_GRAY);
                pistes[i][j].setBackground(Color.LIGHT_GRAY);
            }
        }

        //Habilitem només la primera fila de butons
        for (int i = 0; i < line_size; ++i) {
            butons[0][i].setEnabled(true);
            butons[0][i].setBackground(vectorColor[0]);
        }

        //Afegim els components als subpanels
        //NOTA: inserim les files en ordre invers perque aquest layout coloca totes les
        //merdes de dalt a baix i d'esquerra a dreta, i ens interessa omplir de baix a dalt.
        for(int i = line_number - 1; i >= 0; i--){
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
                //TODO: adaptar això per a fase 2
                int estat = partida.fesTorn(new Combinacio(llegeixFila(torn)));

                //El torn no ha provocat canvi de fase ni s'ha acabat la partida
                if(estat == 0){
                    //Deshabilitem els botons actuals, habilitem els següents. Incrementem torn.
                    //Posem les pistes també.
                    Combinacio combPistes = partida.getPista(torn);
                    int pistaAux;
                    for(int i = 0; i < line_size; ++i){
                        butons[torn][i].setEnabled(false);
                        butons[torn+1][i].setBackground(vectorColor[0]);
                        butons[torn+1][i].setEnabled(true);

                        pistaAux = combPistes.get_elementx(i);
                        if(pistaAux == 1) pistes[torn][i].setBackground(Color.WHITE);
                        if(pistaAux == 2) pistes[torn][i].setBackground(Color.BLACK);
                    }
                    torn++;
                    //debug
                    //partida.mostraTauler();
                }
                //El torn ha provocat un final de fase, canviem rols i reiniciem tauler
                else if(estat == 1){
                    //TODO: afegir les accions de lògica necessàries
                    JDialog dialog = new JDialog();
                    JOptionPane.showMessageDialog(dialog,
                            "Hora de canviar els panyals! \nReiniciem el tauler...",
                            "Canvi de rols!",
                            JOptionPane.WARNING_MESSAGE);

                    //Reiniciar torn
                    torn = 0;

                    //Neteja de tauler
                    resetTauler();

                }
                //El torn ha provocat un final de partida
                else if(estat == 2){
                    //TODO
                }
                //La partida ja ha acabat
                //TODO
                else if(estat == 3) System.out.println("La partida ja ha finalitzat");
                else partida.mostraTauler();
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

        //Listeners de les posicions del tauler
        for(int i = 0; i < line_number; ++i){
            for(int j = 0; j < line_size; ++j){
                //Copio els valors perque si no dona pel cul quan els vull escriure
                int finalI = i;
                int finalJ = j;
                butons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Utilitza el vector de colors que he declarat, cada click va accedeix a la posició següent
                        //i torna a la inicial quan arriba a la posició "numColors"
                        if (!comprovarColorExistent(finalI, finalJ)) {
                            butons[finalI][finalJ].setBackground(vectorColor[0]); //Valor temporal
                        }
                        else {
                            int it = comprovarIteracioColor(finalI, finalJ);
                            ++it;
                            if (it >= num_colors) it = 0;
                            butons[finalI][finalJ].setBackground(vectorColor[it]);
                        }
                    }
                });
            }
        }
    }

    //--------------FUNCIONS EXTRA---------------//

    // comprova que existeixi un background color al "boto[auxi][auxj]"
    private boolean comprovarColorExistent(int auxi, int auxj){
        Color c = butons[auxi][auxj].getBackground();
        boolean trobat = false;
        int cont = 0;
        while (!trobat && cont < num_colors){
            if (vectorColor[cont].equals(c)) trobat = true;
            else ++cont;
        }
        return trobat;
    }

    // retorna l'iteracio del background color actual del "boto[auxi][auxj]"
    private int comprovarIteracioColor(int auxi, int auxj){
        Color c = butons[auxi][auxj].getBackground();
        boolean trobat = false;
        int cont = 0;
        while (!trobat){
            if (vectorColor[cont].equals(c)) trobat = true;
            else ++cont;
        }
        return cont;
    }

    // retorna la combinació inserida a la fila corresponent
    private Combinacio llegeixFila(int numFila){
        int num[] = new int[line_size];

        //Llegim cada color, el passem a numero i ho transformem en una combinació
        for(int i = 0; i < line_size; ++i){
            num[i] = comprovarIteracioColor(numFila, i);
        }
        Combinacio comb = new Combinacio(line_size);
        comb.setCombinacio(num);

        return comb;
    }

    /** Funció que deixa el tauler en el seu estat inicial.*/
    private void resetTauler(){
        for (int i = 0; i < line_number; ++i){
            for(int j = 0; j < line_size; ++j){
                butons[i][j].setBackground(Color.LIGHT_GRAY);//TEMPORAL
                pistes[i][j].setBackground(Color.LIGHT_GRAY);//TEMPORAL
            }
        }
        for(int i = 0; i < line_size; ++i){
            butons[0][i].setBackground(vectorColor[0]);
        }
    }
}
