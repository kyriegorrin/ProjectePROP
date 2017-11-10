package Drivers;

import Domini.HBreaker;
import Domini.Combinacio;
import Domini.Tauler;

import java.util.Scanner;

//El driver conte una combinacio on es guarda la ultima jugada (combinacio) feta pel jugador

public class DriverHBreaker {

    public static void main(String [] args){
        String comanda;
        Scanner reader = new Scanner(System.in);
        HBreaker jugador = null;

        //STUDS
        Combinacio combinacio = new Combinacio(4);
        Tauler tauler = new Tauler(8, 4, 4);

        System.out.println("Inserta comanda (escriu help si necessites ajuda):");
        comanda = reader.nextLine();

        while(!comanda.equals("exit")){
            switch(comanda){
                case "help":
                    System.out.println("Llista de comandes:");
                    System.out.println("creaHBreaker -> Crea un jugador amb els parametres desitjats");
                    System.out.println("getNom -> Consulta el nom del jugador");
                    System.out.println("getPuntuacio -> Consulta la puntuacio del jugador");
                    System.out.println("setNom -> Canvia el nom del jugador");
                    System.out.println("setPuntuacio -> Canvia la puntuacio del jugador");
                    System.out.println("toString -> Crea un string amb els atributs del jugador " +
                                        "(i en quest cas es mostra per pantalla)");
                    System.out.println("fesJugada -> Permet inserir una jugada (combinacio)");
                    System.out.println("ultimaJugada -> Permet consultar la ultima jugada (combinacio) inserida\n");
                    System.out.println("---LES COMBINACIONS SON DE 4 POSICIONS PER DEFECTE---\n");
                    break;

                case "creaHBreaker":
                    System.out.println("Inserta nom:");
                    String nom = reader.nextLine();
                    System.out.println("Inserta puntuacio:");
                    int puntuacio = reader.nextInt();

                    jugador = new HBreaker(nom, puntuacio);

                    break;

                case "getNom":
                    if(jugador == null){
                        System.out.println("El jugador encara no s'ha creat!");
                    }else{
                        System.out.println(jugador.getNom());
                    }
                    break;

                case "getPuntuacio":
                    if(jugador == null){
                        System.out.println("El jugador encara no s'ha creat!");
                    }else{
                        System.out.println(jugador.getPuntuacio());
                    }
                    break;

                case "setNom":
                    if(jugador == null){
                        System.out.println("El jugador encara no s'ha creat!");
                    }else{
                        System.out.println("Inserta nom:");
                        jugador.setNom(reader.nextLine());
                    }
                    break;

                case "setPuntuacio":
                    if(jugador == null){
                        System.out.println("El jugador encara no s'ha creat!");
                    }else{
                        System.out.println("Inserta puntuacio:");
                        jugador.setPuntuacio(reader.nextInt());
                    }
                    break;

                case "toString":
                    if(jugador == null){
                        System.out.println("El jugador encara no s'ha creat!");
                    }else{
                        System.out.println(jugador.toString());
                    }
                    break;

                case "fesJugada":
                    if(jugador == null){
                        System.out.println("El jugador encara no s'ha creat!");
                    }else{
                        combinacio = jugador.fesJugada(tauler);
                    }
                    break;

                case "ultimaJugada":
                    if(jugador == null){
                        System.out.println("El jugador encara no s'ha creat!");
                    }else{
                        combinacio.escriu_combinacio();
                    }
                    break;

                default:
                    System.out.println("Comanda incorrecta");
                    break;
            }
            System.out.println("Inserta comanda (escriu help si necessites ajuda):");
            comanda = reader.nextLine();
        }
    }
}
