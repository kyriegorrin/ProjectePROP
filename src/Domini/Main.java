package Domini;

import Dades.SaveGame;
import com.google.gson.Gson;

import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        Partida partida = null;
        SaveGame savegame = new SaveGame();
        Gson gson = new Gson();
        String stringJSON;
        String nom = "";
        int conf = 0;
        int tamLinia = 0;
        int numColors = 0;

        String comanda;
        Scanner reader = new Scanner(System.in);

        //Control de partida guardada
        if(savegame.exists()){
            System.out.println("S'ha detectat una partida guardada. " +
                                "Tant si la restaures com no, no la podras utilitzar en futures execucions.");
            System.out.println("Restaurar (si/no)?");
            comanda = reader.nextLine();
            if(comanda.equals("si")){
                stringJSON = savegame.load();
                partida = gson.fromJson(stringJSON, Partida.class);
                System.out.println("----PARTIDA CARREGADA----");
            }else{
                savegame.clear();
                System.out.println("----PARTIDA ANTERIOR DESCARTADA----");
                System.out.println("Ja pots procedir a executar el programa normalment.\n");
            }
        }

        System.out.println("Inserta comanda (Escriu help si necessites ajuda):");
        comanda = reader.nextLine();

        while(!comanda.equals("exit")){
            switch(comanda){
                case "help":
                    System.out.println("Aqui tens les diferents comandes:");
                    System.out.println("help -> Mostra ajuda");
                    System.out.println("inicia -> Inicia una partida");
                    System.out.println("fesTorn -> El jugador corresponent fa un torn");
                    System.out.println("guardaPartida -> Guarda estat actual de la partida i " +
                                        "tanca el joc per a continuar-lo en un altre moment");
                    System.out.println("mostraRanking -> Mostra el ranking per pantalla");
                    System.out.println("mostraTauler -> Mostra l'estat del tauler\n");
                    System.out.println("---NOTA: No seguir el format demanat pot provocar crash del programa!---\n");
                    break;

                case "inicia":
                    if(partida == null){
                        System.out.println("Inserta el teu nom:");
                        nom = reader.nextLine();
                        System.out.println("Inserta 0 per a començar sent CodeBreaker, 1 per a ser CodeMaker");
                        conf = reader.nextInt();
                        System.out.println("Inserta quantes posicions vols per linia (recomanat: 4-6)");
                        tamLinia = reader.nextInt();
                        System.out.println("Inserta quants colors vols (recomanat: 5-8)");
                        numColors = reader.nextInt();
                        partida = new Partida(nom, conf, tamLinia, numColors);
                    }
                    else{
                        System.out.println("Partida ja esta creada i iniciada.");
                    }
                    break;

                case "fesTorn":
                    if(partida == null){
                        System.out.println("No has iniciat partida!");
                    }else{
                        int estat = partida.fesTorn();
                        if(estat == 3) System.out.println("La partida ja ha finalitzat");
                        else partida.mostraTauler();
                    }
                    break;

                case "guardaPartida":
                    if (partida == null){
                        System.out.println("No has iniciat partida! Res a guardar.");
                    }
                    else{
                        stringJSON = gson.toJson(partida);
                        savegame.save(stringJSON);
                        System.out.println("Partida guardada. Sortint del joc...");
                        System.exit(0);
                    }
                    break;

                case "mostraRanking":
                    if(partida == null){
                        System.out.println("No has inicialitzat partida!");
                    }else{
                        partida.mostraRanking();
                    }
                    break;

                case "mostraTauler":
                    if(partida == null){
                        System.out.println("No has inicialitzat partida!");
                    }else{
                        partida.mostraTauler();
                    }
                    break;

                default:
                    if(!comanda.equals(""))
                        System.out.println("Comanda incorrecta.");
                    break;
            }
            if (!comanda.equals(""))
                System.out.println("Inserta comanda (escriu help si necessites ajuda):");

            comanda = reader.nextLine();
        }
    }
}
