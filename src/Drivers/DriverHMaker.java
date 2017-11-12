package Drivers;

import Domini.HMaker;

import java.util.Scanner;

public class DriverHMaker {
    public static void main(String [] args) {
        String comanda;
        Scanner reader = new Scanner(System.in);
        HMaker hmaker = null;
        int tamany = 4;
        int colors = 6;

        //STUB
        Combinacio combinacio = null;

        System.out.println("Inserta comanda (escriu help si necessites ajuda):");
        comanda = reader.nextLine();

        while (!comanda.equals("exit")) {
            switch (comanda) {
                case "help":
                    System.out.println("Llista de comandes:");
                    System.out.println("creaHMaker -> Crea un hmaker amb els parametres desitjats");
                    System.out.println("getNom -> Consulta el nom del hmaker");
                    System.out.println("getPuntuacio -> Consulta la puntuacio del hmaker");
                    System.out.println("setNom -> Canvia el nom del hmaker");
                    System.out.println("setPuntuacio -> Canvia la puntuacio del hmaker");
                    System.out.println("triaCombinacio -> Genera una combinacio inicial (inicialment 4 posicions i 6 colors)");
                    System.out.println("mostraCombinacio -> Mostra la ultima combinacio inicial que has triat");
                    System.out.println("canviaParametres -> Permet canviar quants colors s'utilitzen i la llargada de la combinacio per a la seguent tria");
                    System.out.println("toString -> Crea un string amb els atributs del hmaker " +
                            "(i en quest cas es mostra per pantalla)");
                    System.out.println("---METODE fesJugada() NO IMPLEMENTAT JA QUE CODEMAKERS NO L'UTILITZEN EN AQUESTA IMPLEMENTACIO---\n");
                    break;

                case "creaHMaker":
                    System.out.println("Inserta nom:");
                    String nom = reader.nextLine();
                    System.out.println("Inserta puntuacio:");
                    int puntuacio = reader.nextInt();

                    hmaker = new HMaker(nom, puntuacio);

                    break;

                case "getNom":
                    if (hmaker == null) {
                        System.out.println("El hmaker encara no s'ha creat!");
                    } else {
                        System.out.println(hmaker.getNom());
                    }
                    break;

                case "getPuntuacio":
                    if (hmaker == null) {
                        System.out.println("El hmaker encara no s'ha creat!");
                    } else {
                        System.out.println(hmaker.getPuntuacio());
                    }
                    break;

                case "setNom":
                    if (hmaker == null) {
                        System.out.println("El hmaker encara no s'ha creat!");
                    } else {
                        System.out.println("Inserta nom:");
                        hmaker.setNom(reader.nextLine());
                    }
                    break;

                case "setPuntuacio":
                    if (hmaker == null) {
                        System.out.println("El hmaker encara no s'ha creat!");
                    } else {
                        System.out.println("Inserta puntuacio:");
                        hmaker.setPuntuacio(reader.nextInt());
                    }
                    break;

                case "triaCombinacio":
                    if(hmaker == null){
                        System.out.println("El hmaker encara no s'ha creat!");
                    }
                    else
                        combinacio = hmaker.triaCombinacio(tamany, colors);
                    break;

                case "mostraCombinacio":
                    if (hmaker == null)
                        System.out.println("El hmaker encara no s'ha creat!");
                    else{
                        if(combinacio == null)
                            System.out.println("Encara no has triat cap combinacio!");
                        else{
                            combinacio.escriu_combinacio();
                        }
                    }
                    break;

                case "canviaParametres":
                    System.out.println("Escriu els nous parametres de combinacio(format: <numPosicions> <numColors>)");
                    tamany = reader.nextInt();
                    colors = reader.nextInt();
                    break;

                case "toString":
                    if (hmaker == null) {
                        System.out.println("El hmaker encara no s'ha creat!");
                    } else {
                        System.out.println(hmaker.toString());
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
