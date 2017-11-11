package Domini;

import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        Partida partida = null;
        String nom = "";
        int conf = 0;
        int tamLinia = 0;
        int numColors = 0;

        //TODO: Afegir control de partida guardada
        String comanda;
        Scanner reader = new Scanner(System.in);

        System.out.println("Inserta comanda (Escriu help si necessites ajuda):");
        comanda = reader.nextLine();

        while(!comanda.equals("exit")){
            switch(comanda){
                case "help":
                    System.out.println("Aqui tens les diferents comandes:");
                    System.out.println("help -> Mostra ajuda");
                    System.out.println("inicia -> Inicia una partida");
                    System.out.println("fesTorn -> El jugador corresponent fa un torn");
                    System.out.println("mostraRanking -> Mostra el ranking per pantalla");
                    //TODO
                    break;

                case "inicia":
                    if(partida == null){
                        System.out.println("Inserta el teu nom:");
                        nom = reader.nextLine();
                        System.out.println("Inserta 0 per a començar sent CodeBreaker, 1 per a ser CodeMaker");
                        conf = Integer.getInteger(reader.nextLine());
                        System.out.println("Inserta quantes posicions vols per linia (recomanat: 4-6)");
                        tamLinia = Integer.getInteger(reader.nextLine());
                        System.out.println("Inserta quants colors vols (recomanat: 5-8)");
                        numColors = Integer.getInteger(reader.nextLine());
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
                    }
                    break;

                case "mostraRanking":
                    if(partida == null){
                        System.out.println("No has inicialitzat partida!");
                    }else{
                        partida.mostraRanking();
                    }

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
