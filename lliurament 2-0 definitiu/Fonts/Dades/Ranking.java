package Dades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/** <h1>Classe de gestió de ranking.</h1>
 *
 *  <p>La classe Ranking és l'encarregada de gestionar i assegurar la permanència del ranking entre execucions de diferents partides.
 *  Ho aconsegueix creant un arxiu a disc (ranking.txt), on escribim i llegim el ranking en format string, ordenat per puntuació dels jugadors.
 *  Per defecte guarda 10 jugadors màxim. Arribats a aquest punt, futures insercions impliquen descartar el jugador de puntuació més baixa,
 *  en cas que el jugador a inserir tingui una puntuació més alta que algun dels integrants del ranking.
 *  </p>
 *
 *  <p><b>Nota important:</b> qualsevol interacció amb el fitxer generat per aquesta classe que es produeixi de manera externa a
 *                      l'execució del programa o driver està <b>terminantment prohibida</b> i pot causar la inoperabilitat del programa.</p>
 *
 *  @author Ricard Zarco Badia
 */
public class Ranking {
    private static final int MAXJUGADORS = 10;

    private ArrayList<String> nomJugadors;
    private ArrayList<Integer> puntuacioJugadors;
    private boolean init; //Indica si s'ha inicialitzat

    private File file;

    /** Constructora per defefecte de la classe.
     */
    public Ranking(){
        nomJugadors = new ArrayList<String>();
        puntuacioJugadors = new ArrayList<Integer>();
        init = false;
    }

    /** Primera funcio a cridar un cop es crea el ranking. No cridar aquesta funció implica la no permissió d'execució de certs mètodes.
     * Llegeix l'arxiu que conté el ranking si existeix. Si l'arxiu no existeix, el crea.
     */
    public void inicialitza(){
        if(!init){
            String linia;
            file = new File("ranking.txt");
            String [] componentsLinia;

            if(file.exists()){
                //Simplement llegim linies fins que no en trobem mes
                try{
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);

                    //Llegim linies fins que s'acaba l'arxiu
                    while((linia = br.readLine()) != null){
                        //Tractem la linia i afegim el contingut a les variables de classe
                        componentsLinia = linia.split(" ");
                        nomJugadors.add(componentsLinia[0]);
                        puntuacioJugadors.add(Integer.parseInt(componentsLinia[1]));
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
            }else{
                //Creem arxiu
                try{
                    file.createNewFile();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            //Indiquem que tot ha estat inicialitzat
            init = true;
        }else{
            System.out.println("Ja has inicialitzat el ranking!");
        }
    }

    /** Inserta un jugador de manera ordenada al ranking si ha estat incialitzat abans.
     *  Si no s'ha inicialitzat, ho recorda a l'usuari.
     * @param jugador String que defineix a un jugador. Ha de ser un string generat <b>única i exclusivament</b>
     *                generat pel mètode toString() de la classe Jugador o una de les seves subclasses.
     */
    public void insertaJugador(String jugador){
        String[] componentsJugador = jugador.split(" ");
        int puntuacio = Integer.parseInt(componentsJugador[1]);

        //Si els atributs de classe no estan inicialitzats, error
        if (!init){
            System.out.println("Inicialitza el ranking abans de fer res!");
        }else{
            //Mirem si el ranking està buit, en cas positiu afegim directament
            if (nomJugadors.isEmpty()){
                nomJugadors.add(componentsJugador[0]);
                puntuacioJugadors.add(puntuacio);
            }else{
                //Si no, busquem quin és el seu lloc i l'insertem
                boolean trobat = false;
                int i;

                for(i = 0; i < puntuacioJugadors.size() && !trobat; ++i){
                    if(puntuacioJugadors.get(i) < puntuacio) trobat = true;
                }
                //Insertem a la posicio que toca
                if(trobat){
                    nomJugadors.add(i-1, componentsJugador[0]);
                    puntuacioJugadors.add(i-1, puntuacio);
                }else{
                    nomJugadors.add(componentsJugador[0]);
                    puntuacioJugadors.add(puntuacio);
                }
            }
            //Un cop modificat, sobreescribim els canvis a l'arxiu
            flushRanking();
        }
    }

    /** Mètode que borra el ranking complet a nivell d'arxiu i descarta els atributs de la classe.
     *  És necessari tornar a cridar el mètode inicialitza() un cop s'ha cridat si es vol continuar utilitzant el ranking.
     */
    public void clear(){
        if(file.exists()){
            file.delete();
        }
        init = false;
        nomJugadors = new ArrayList<String>();
        puntuacioJugadors = new ArrayList<Integer>();
    }

    /** Mètode que retorna un string contenint el ranking.
     * Està limitat a MAXJUGADORS = 10, encara que els atributs de classe en continguin més en aquell moment.
     * @return String que conforma el ranking.
     */
    public String toString(){
        String stringRanking = "";
        if(init){
            for(int i = 0; i < nomJugadors.size() && i < MAXJUGADORS; ++i){
                stringRanking += (i+1) + " " + nomJugadors.get(i) + " " + puntuacioJugadors.get(i) +"\n";
            }
        }else{
            System.out.println("Ranking no inicialitzat!");
        }
        return stringRanking;
    }

    /** Mètode intern per a actualitzar (sobrescriure) l'arxiu que conté el ranking
     * Sempre escriu un maxim de MAXJUGADORS = 10 jugadors ordenats per puntuació.
     */
    private void flushRanking(){
        try{
            file.delete();
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i = 0; i < nomJugadors.size() && i < MAXJUGADORS; ++i){
                bw.write(nomJugadors.get(i) + " " + String.valueOf(puntuacioJugadors.get(i)));
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
