package Dades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Ranking {

    private ArrayList<String> nomJugadors;
    private ArrayList<Integer> puntuacioJugadors;
    boolean init; //Indica si s'ha inicialitzat

    public Ranking(){
        nomJugadors = new ArrayList<String>();
        puntuacioJugadors = new ArrayList<Integer>();
        init = false;
    }

    private void test(){
        try{
            String verify, putData;
            File file = new File("file.txt");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Some text here for a reason");
            bw.flush();
            bw.close();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            while( (verify=br.readLine()) != null ){ //***editted
                //**deleted**verify = br.readLine();**
                if(verify != null){ //***edited
                    putData = verify.replaceAll("here", "there");
                    bw.write(putData);
                }
            }
            br.close();


        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //Primera funcio a cridar quan es crea el ranking, llegeix l'arxiu que conté el ranking si existeix.
    //Si l'arxiu no existeix, el crea.
    public void inicialitza(){
        String linia;
        File file = new File("ranking.txt");
        String [] componentsLinia;

        if(file.exists()){
            //Simplement llegim linies fins que no en trobem mes
            try{
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                while((linia = br.readLine()) != null){
                    componentsLinia = linia.split(" ");
                //TODO:omplir les variables de classe amb lo llegit a cada linia
                }

            }catch(IOException e){
                e.printStackTrace();
            }
        }else{
            //Creem arxiu
        }
    }

    public void insertaJugador(String jugador){

    }
}
