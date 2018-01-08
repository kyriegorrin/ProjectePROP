package Interface;

import Dades.SaveGame;
import Domini.Combinacio;
import Domini.Partida;
import Domini.PartidaBots;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaulerPanel extends JPanel {

    //Logica associada
    private UIController control;
    private Partida partida;
    int torn, fase, conf;
    boolean modeJugadorIA;//True = cpuVScpu, False = humaVScpu

    //Variables de gestió de guardat de partida
    SaveGame savegame;
    Gson gson;
    String stringJSON;

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
    private JButton[] combinacio;
    private JPanel panelJugades;
    private JPanel panelPistes;
    private JPanel panelContenidor;
    private JPanel panelButtons;
    private JPanel panelCombinacio;

    private JLabel labelInfo;
    private JLabel labelCombinacio;
    private JButton exitButton;
    private JButton saveButton;
    private JButton submitButton;
    private JButton combinacioButton;


    /** Constructora de la classe. Genera la vista a la vegada que deixa inicialitzada la lògica de partida associada.
     *
     * @param line_number Nombre de files que te el tauler.
     * @param line_size Nombre de posicions per fila.
     * @param colors Nombre de colors disponibles.
     * @param control Instància de UIController que permet la interacció entre vistes.
     * @param nom Nom del jugador.
     */
    public TaulerPanel(int line_number, int line_size, int colors, UIController control, String nom){
        super();

        //Inicialitzem variables de guardat de partida
        gson = new Gson();
        savegame = new SaveGame();

        //Assignem controlador
        this.control = control;

        //Inicialitzem variables auxiliars
        this.line_number = line_number;
        this.line_size = line_size;
        this.num_colors = colors;

        //Inicialitzem lògica de partida
        conf = 0;
        partida = new Partida(nom, conf, line_size, colors);
        torn = 0;
        modeJugadorIA = false;//Inicialment, pot canviar si es crida el mètode per canviar-ho

        //Preparem el layout del panell principal
        setLayout(new BorderLayout()); //TODO: posar valors finals
        setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        setPreferredSize(new Dimension(600,600));

        //Inicialitzem els subpanels
        panelJugades = new JPanel();
        panelPistes = new JPanel();
        panelContenidor = new JPanel();
        panelButtons = new JPanel();
        panelCombinacio = new JPanel();

        panelJugades.setLayout(new GridLayout(line_number, line_size, 5, 5));
        panelJugades.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        panelPistes.setLayout(new GridLayout(line_number, line_size, 5, 5));
        panelPistes.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        panelContenidor.setLayout(new GridLayout(1,2));
        panelContenidor.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
        panelButtons.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        panelCombinacio.setLayout(new FlowLayout());

        //Inicialitzem secció de sota (combinació, butons i label)
        combinacio = new JButton[line_size];
        for(int i = 0; i < line_size; ++i){
            combinacio[i] = new JButton();
            combinacio[i].setBackground(Color.LIGHT_GRAY);
            combinacio[i].setSize(25, 25);
            combinacio[i].setEnabled(false);
        }

        labelInfo = new JLabel("TEXT RANDOM A CANVIAR");
        labelCombinacio = new JLabel("Combinació secreta");
        submitButton = new JButton("Passar torn");
        saveButton = new JButton("Guardar i sortir");
        exitButton = new JButton("Sortir");
        combinacioButton = new JButton("Selecciona combinació");

        submitButton.setPreferredSize(new Dimension(80,25));
        exitButton.setPreferredSize(new Dimension(80,25));
        saveButton.setPreferredSize(new Dimension(80,25));
        combinacioButton.setPreferredSize(new Dimension(80,25));

        labelCombinacio.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        combinacioButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //En el cas del botó de combinació, el volem deshabilitat i invisible incialment
        combinacioButton.setEnabled(false);
        combinacioButton.setVisible(false);

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

        //Afegim la combinació al seu subpanel
        for(int i = 0; i < line_size; ++i){
            panelCombinacio.add(combinacio[i]);
        }

        //Afegim els buttons al contenidor de buttons
        //També una label per a avisos/info i la combinacio a descobrir
        panelButtons.add(labelCombinacio);
        panelButtons.add(panelCombinacio);
        panelButtons.add(combinacioButton);
        panelButtons.add(Box.createRigidArea(new Dimension(5,10))); //Separador
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
                if(modeJugadorIA) controlTornIA();
                else controlTorn();
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

        combinacioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCombinacioInicial(llegeixCombInicial());
                //Tornem a fer invisible el botó de selecció de combinacio, habilitem pas de torn
                combinacioButton.setEnabled(false);
                combinacioButton.setVisible(false);
                submitButton.setEnabled(true);
                saveButton.setEnabled(true);
                //Deixem la fila de combinació inoperativa
                for(int i = 0; i < line_size; ++i){
                    combinacio[i].setEnabled(false);
                }
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
        //Listener posicions de combinacio
        for(int j = 0; j < line_size; ++j){
            //Copio els valors perque si no dona pel cul quan els vull escriure
            int finalJ = j;
            combinacio[j].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Utilitza el vector de colors que he declarat, cada click va accedeix a la posició següent
                    //i torna a la inicial quan arriba a la posició "numColors"
                    if (!comprovarColorExistentCombinacio(finalJ)) {
                        combinacio[finalJ].setBackground(vectorColor[0]); //Valor temporal
                    }
                    else {
                        int it = comprovarIteracioColorCombinacio(finalJ);
                        ++it;
                        if (it >= num_colors) it = 0;
                        combinacio[finalJ].setBackground(vectorColor[it]);
                    }
                }
            });
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

    // comprova que existeixi un background color al combinacio[auxi]
    private boolean comprovarColorExistentCombinacio(int auxi){
        Color c = combinacio[auxi].getBackground();
        boolean trobat = false;
        int cont = 0;
        while (!trobat && cont < num_colors){
            if (vectorColor[cont].equals(c)) trobat = true;
            else ++cont;
        }
        return trobat;
    }

    /** Funció que retorna l'iteracio del background color actual del combinacio[auxi].
     *
     * @param auxi Posició dins la combinació inicial a comprovar-ne el color.
     * @return Identificador de color que conté en aquella posició.*/
    private int comprovarIteracioColorCombinacio(int auxi){
        Color c = combinacio[auxi].getBackground();
        boolean trobat = false;
        int cont = 0;
        while (!trobat){
            if (vectorColor[cont].equals(c)) trobat = true;
            else ++cont;
        }
        return cont;
    }

    /** Funció que llegeix la fila indicada del tauler de combinacions de la GUI.
     *  Ho transforma a un objecte Combinacio.
     * @param numFila Fila del tauler de combinacions de la GUI a la que volem accedir.
     * @return Retorna un objecte Combinacio generat a partir de la lectura.
     */
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

    /** Funció que llegeix la fila lògica del tauler i la fa visible al seu equivalent gràfic.
     * @param fila Fila que volem accedir.*/
    private void setColorsFila(int fila){
        Combinacio combFila = partida.getFila(fila);
        for(int i = 0; i < line_size; ++i){
            butons[fila][i].setBackground(vectorColor[combFila.get_elementx(i)]);
        }
    }

    /** Funció que llegeix la fila lògica del tauler antic i la fa visible al seu equivalent gràfic.
     * @param fila Fila que volem accedir.*/
    private void setColorsFilaAntiga(int fila){
        Combinacio combFila = partida.getFilaAntiga(fila);
        for(int i = 0; i < line_size; ++i){
            butons[fila][i].setBackground(vectorColor[combFila.get_elementx(i)]);
        }
    }

    /** Funció que llegeix la fila lògica de pistes del tauler i la fa visible al seu equivalent gràfic.
     * @param fila Fila que volem accedir.*/
    private void setColorsFilaPista(int fila){
        for(int i = 0; i < line_size; ++i){
            Combinacio combPistes = partida.getPista(fila);
            int pistaAux;
            pistaAux = combPistes.get_elementx(i);
            if(pistaAux == 1) pistes[fila][i].setBackground(Color.WHITE);
            if(pistaAux == 2) pistes[fila][i].setBackground(Color.BLACK);
        }
    }

    /** Funció que llegeix la fila lògica de pistes del tauler antic i la fa visible al seu equivalent gràfic.
     * @param fila Fila que volem accedir.*/
    private void setColorsFilaPistaAntiga(int fila){
        for(int i = 0; i < line_size; ++i){
            Combinacio combPistes = partida.getPistaAntiga(fila);
            int pistaAux;
            pistaAux = combPistes.get_elementx(i);
            if(pistaAux == 1) pistes[fila][i].setBackground(Color.WHITE);
            if(pistaAux == 2) pistes[fila][i].setBackground(Color.BLACK);
        }
    }

    /** Funció per habilitar la selecció de combinació. També deshabilita el botó de passar torn i
     * i guardar partida, també fa operatiu el botó de seleccionar combinació inicial.*/
    private void habilitaCombinacio(){
        for(int i = 0; i < line_size; ++i){
            combinacio[i].setEnabled(true);
            combinacio[i].setBackground(vectorColor[0]);
        }
        submitButton.setEnabled(false);
        saveButton.setEnabled(false);
        combinacioButton.setEnabled(true);
        combinacioButton.setVisible(true);
    }

    /** Funció per a llegir la combinació inicial inserida a la GUI.
     *  @return Retorna un objecte Combinació que conté la combinació inserida.*/
    private Combinacio llegeixCombInicial(){
        int num[] = new int[line_size];

        //Llegim cada color, el passem a numero i ho transformem en una combinació
        for(int i = 0; i < line_size; ++i){
            num[i] = comprovarIteracioColorCombinacio(i);
        }
        Combinacio comb = new Combinacio(line_size);
        comb.setCombinacio(num);

        return comb;
    }

    /** Funció que accedeix a la combinació inicial lògica de la partida i la fa visible
     *  a la GUI.*/
    private void repaintCombinacioInicial(){
        Combinacio comb = partida.getCombinacioInicial();

        for(int i = 0; i < line_size; ++i){
            combinacio[i].setBackground(vectorColor[comb.get_elementx(i)]);
        }
    }

    /** Funció que insereix una combinació inicial al tauler lògic de la partida*/
    private void setCombinacioInicial(Combinacio comb){
        partida.setCombinacioInicial(comb);
    }

    /** Funció que deixa el tauler en el seu estat inicial.*/
    private void resetTauler(){
        for (int i = 0; i < line_number; ++i){
            for(int j = 0; j < line_size; ++j){
                butons[i][j].setBackground(Color.LIGHT_GRAY);//TEMPORAL
                butons[i][j].setEnabled(false);
                pistes[i][j].setBackground(Color.LIGHT_GRAY);//TEMPORAL
            }
        }
        for(int i = 0; i < line_size; ++i){
            butons[0][i].setBackground(vectorColor[0]);
        }
    }

    /** Funció que retorna el torn lògic actual. Utilitzat per a restaurar la partida.
     * @return Enter que conté el numero de torn actual.*/
    public int getTorn(){
        return partida.getTorn();
    }

    /** Funció que retorna la fase lògica actual. Utilitzat per a restaurar la partida.
     * @return Enter que conté el numero de fase actual.*/
    public int getFase(){
        return partida.getFase();
    }

    /** Funció que retorna la configuració lògica actual. Utilitzat per a restaurar la partida.
     * @return Enter que conté el numero de configuració actual.*/
    public int getConf(){
        return partida.getConf();
    }

    //--------------GESTIO PARTIDA-----------------------//

    /** Funció que controla l'estat de la partida i la congruència entre el que s'ensenya per pantalla i
     * l'estat intern lògic de la partida cada vegada que es passa de torn.
     */
    public void controlTorn(){
        int estat = partida.fesTorn(new Combinacio(llegeixFila(torn)));

        //El torn no ha provocat canvi de fase ni s'ha acabat la partida
        if(estat == 0){
            //Deshabilitem els botons actuals, habilitem els següents. Incrementem torn.

            //En cas de ser la fase en que som Codemaker, hem de posar els colors llegint el tauler logic
            if(conf == 1){
                setColorsFila(torn);
            }

            for(int i = 0; i < line_size; ++i){
                butons[torn][i].setEnabled(false);
                butons[torn+1][i].setBackground(vectorColor[0]);
                if(conf == 0) butons[torn+1][i].setEnabled(true);//Nomes ho fem si som CodeBreaker
            }
            //Posem les pistes també
            setColorsFilaPista(torn);

            torn++;
            //debug
            //partida.mostraTauler();
        }
        //El torn ha provocat un final de fase, canviem rols i reiniciem tauler
        else if(estat == 1){
            //Afegim pistes de la ultima linia tocada
            setColorsFilaPistaAntiga(torn);

            JDialog dialog = new JDialog();
            JOptionPane.showMessageDialog(dialog,
                    "Hora de canviar els rols! \nReiniciem el tauler...",
                    "Canvi de rols!",
                    JOptionPane.WARNING_MESSAGE);

            //Reiniciar torn i canviar configuracio
            torn = 0;
            conf = 1;
            //Neteja de tauler
            resetTauler();
            //Seleccio de nova combinacio com a Codemaker
            habilitaCombinacio();
        }
        //El torn ha provocat un final de partida
        else if(estat == 2){
            //Posem l'estat final del tauler afegint la ultima linia
            Combinacio combPistes = partida.getPista(torn);

            //En cas de ser la fase en que som Codemaker, hem de posar els colors llegint el tauler logic
            if(conf == 1) setColorsFila(torn);
            setColorsFilaPista(torn);

            //Avisem de que la partida ha acabat
            JDialog dialog = new JDialog();
            JOptionPane.showMessageDialog(dialog,
                    "La partida s'ha acabat!",
                    "Final de partida!",
                    JOptionPane.WARNING_MESSAGE);

            //TODO: canvi a frame final, pas de puntuacions i memes
            control.taulerToFinal(partida.getNomHuma(), partida.getNomCPU(), partida.getPuntuacioHuma(), partida.getPuntuacioCPU());
        }
        //La partida ja ha acabat, NO HAURIEM D'ENTRAR MAI AQUI
        else if(estat == 3) System.out.println("La partida ja ha finalitzat");
        else partida.mostraTauler();
    }

    /** Funció que controla l'estat de la partida i la congruència entre el que s'ensenya per pantalla i
     * l'estat intern lògic de la partida cada vegada que es passa de torn. Per a mode IA vs IA.*/
    public void controlTornIA(){
        int estat = partida.fesTorn(new Combinacio(llegeixFila(torn)));

        //El torn no ha provocat canvi de fase ni s'ha acabat la partida
        if(estat == 0){
            //Posem els colors que toquen a la fila
            setColorsFila(torn);

            for(int i = 0; i < line_size; ++i){
                butons[torn][i].setEnabled(false);
                butons[torn+1][i].setBackground(vectorColor[0]);
            }
            //Posem les pistes també
            setColorsFilaPista(torn);

            torn++;
        }
        //El torn ha provocat un final de fase, canviem rols i reiniciem tauler
        else if(estat == 1){
            //Posem la ultima linia tocada i la pista generada
            setColorsFilaPistaAntiga(torn);
            setColorsFilaAntiga(torn);

            //Mostrem dialog de final de fase
            JDialog dialog = new JDialog();
            JOptionPane.showMessageDialog(dialog,
                    "Hora de canviar rols! \nReiniciem el tauler...",
                    "Canvi de rols!",
                    JOptionPane.WARNING_MESSAGE);

            //Reiniciar torn i canviar configuracio
            torn = 0;
            conf = 1;
            //Neteja de tauler
            resetTauler();
            //Posem la nova combinacio seleccionada
            repaintCombinacioInicial();
        }
        //El torn ha provocat un final de partida
        else if(estat == 2){
            //Posem l'estat final del tauler afegint la ultima linia
            Combinacio combPistes = partida.getPista(torn);

            //En cas de ser la fase en que som Codemaker, hem de posar els colors llegint el tauler logic
            if(conf == 1){
                setColorsFila(torn);
            }

            setColorsFilaPista(torn);

            //Avisem de que la partida ha acabat
            JDialog dialog = new JDialog();
            JOptionPane.showMessageDialog(dialog,
                    "La partida s'ha acabat!",
                    "Final de partida!",
                    JOptionPane.WARNING_MESSAGE);

            //TODO: canvi a frame final, pas de puntuacions i memes
            control.taulerToFinal(partida.getNomHuma(), partida.getNomCPU(), partida.getPuntuacioHuma(), partida.getPuntuacioCPU());
        }
        //La partida ja ha acabat, NO HAURIEM D'ENTRAR MAI AQUI
        else if(estat == 3) System.out.println("La partida ja ha finalitzat");
        else partida.mostraTauler();
    }

     /** Funció per a guardar l'estat lògic de la partida.*/
    public void guardaPartida(){
        stringJSON = gson.toJson(partida);
        savegame.save(stringJSON);
    }

    /** Funció que restaura l'estat de la partida llegint el JSON de la lògica i recrear-lo visualment*/
    public void restauraPartida(Partida partidaGuardada){
        ///Carreguem la partida a la logica interna
        partida = partidaGuardada;

        //Carreguem la logica necessaria a la GUI
        torn = partida.getTorn();
        fase = partida.getFase();
        conf = partida.getConf();
        modeJugadorIA = partida.getMode();

        //Redibuixem el tauler
        for(int i = 0; i < torn; ++i){
            setColorsFila(i);
            setColorsFilaPista(i);
        }

        //Habilitem o posem el color inicial de la fila corresponent al torn
        for(int i = 0; i < line_size; ++i){
            butons[0][i].setEnabled(false);
            butons[torn][i].setBackground(vectorColor[0]);
            if(conf == 0 && !modeJugadorIA) butons[torn][i].setEnabled(true);//Nomes ho posem si som jugadors humans en conf 0
        }

        //En cas de ser codemaker o mode IA, redibuixar la combinació inicial
        if(conf == 1 || modeJugadorIA) repaintCombinacioInicial();
    }

    public void setModeIA(){
        //Canviem la partida per una de bots
        partida = new PartidaBots("CPU", conf, line_size, num_colors );
        modeJugadorIA = true;
        //Adaptem el tauler grafic deshabilitant la primera fila i posant la combinació inicial visible
        for (int i = 0; i < line_size; ++i){
            butons[0][i].setEnabled(false);
        }
        repaintCombinacioInicial();
    }
}
