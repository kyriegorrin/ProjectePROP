package Domini;

import java.util.Scanner;

public class Main {
    public static void main(String [] args){
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
                    //TODO
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
