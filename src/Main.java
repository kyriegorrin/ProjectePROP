import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        String comanda;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserta comanda (Escriu help si necessites ajuda):");
        comanda = scanner.nextLine();

        while(!comanda.equals("exit")){
            switch(comanda){
                case "help":
                    System.out.println("Aqui tens les diferents comandes:");
                    System.out.println("help -> Mostra ajuda");
                    System.out.println("inicia -> Inicia una partida");
                    //TODO
                    break;
            }

        }
    }
}
