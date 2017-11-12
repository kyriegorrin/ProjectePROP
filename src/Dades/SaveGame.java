package Dades;

import java.io.*;

public class SaveGame {
    private File file;

    public SaveGame(){
        file = new File("savegame");
    }

    public boolean exists(){
        return file.exists();
    }

    public void clear(){
        if(file.exists()) file.delete();
    }

    public void save(String stringJSON){
        try{
            clear();
            FileWriter fw = new FileWriter(file);
            fw.write(stringJSON);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //Suposem que file exists!!!
    public String load(){
        String stringJSON = "";
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while(br.read() != -1) stringJSON += br.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        file.delete();
        return stringJSON;
    }
}
