package Dades;

import java.io.*;

/** <h1>Classe gestora del guardat de partides.</h1>
 *
 *  <p>La classe SaveGame és l'encarregada de gestionar i assegurar la permanència de la partida guardada en cas de que l'usuari així ho necessiti.
 *  Ho aconsegueix creant un arxiu a disc (savegame.json), on escribim i llegim l'estat d'una partida en format JSON.
 *  Només pot existir una partida guardada en qualsevol moment, la classe assegura que així sigui.
 *  </p>
 *
 *  <p><b>Nota important:</b> qualsevol interacció amb el fitxer generat per aquesta classe que es produeixi de manera externa a
 *                      l'execució del programa o driver està <b>terminantment prohibida</b> i pot causar la inoperabilitat del programa.</p>
 *
 *  @author Ricard Zarco Badia
 */

public class SaveGame {
    private File file;

    /** Constructora per defecte.
     */
    public SaveGame(){
        file = new File("savegame.json");
    }

    /** Comprova si existeix un arxiu amb una partida guardada al disc.
     * @return Retorna un booleà indicant si existeix (true) o no (false) l'arxiu.
     */
    public boolean exists(){
        return file.exists();
    }

    /** Si existeix, destrueix l'arxiu de partida guardada al disc. Si no, no fa res.
     */
    public void clear(){
        if(file.exists()) file.delete();
    }

    /** Guarda el stringJSON en l'arxiu "savegame.json".
     * L'execució d'aques mètode sempre reescriu l'arxiu.
     * @param stringJSON És un string que defineix l'estat de la partida en format JSON.
     *                   Ha de ser un string generat <b>única i exclusivament</b> pel mètode toJson() de la classe Gson.
     */
    public void save(String stringJSON){
        try{
            clear();
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            fw.write(stringJSON);
            fw.flush();
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /** Lectura de l'arxiu de guardat de partida. Guarda el seu contingut en un string i el retorna.
     * Si l'arxiu no existeix, ho notifica a l'usuari. En execució del programa principal, això no hauria de passar mai.
     * <b>Nota:</b> les lectures són destructives. L'arxiu s'elimina un cop executat el mètode.
     * @return String que conté el contingut de l'arxiu de guardat de partida. En cas de que no existeixi l'arxiu, retorna un string buit.
     */
    public String load(){
        if(exists()){
            String stringJSON = "";
            try{
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                stringJSON = br.readLine();
                fr.close();
            }catch(IOException e){
                e.printStackTrace();
            }
            file.delete();
            return stringJSON;
        }
        else System.out.println("No existeix l'arxiu.");
        return "";
    }
}
