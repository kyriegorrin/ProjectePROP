package Drivers;

import Domini.Combinacio;
import Domini.PCMaker;

import java.util.Scanner;

public class DriverPCMaker {
    public static void main(String [] args) {
        String comanda;
        Scanner reader = new Scanner(System.in);
        PCMaker pcmaker = null;
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
                    System.out.println("creaPCMaker -> Crea un pcmaker amb els parametres desitjats");
                    System.out.println("getNom -> Consulta el nom del pcmaker");
                    System.out.println("getPuntuacio -> Consulta la puntuacio del pcmaker");
                    System.out.println("setNom -> Canvia el nom del pcmaker");
                    System.out.println("setPuntuacio -> Canvia la puntuacio del pcmaker");
                    System.out.println("triaCombinacio -> Genera una combinacio inicial (inicialment 4 posicions i 6 colors)");
                    System.out.println("mostraCombinacio -> Mostra la ultima combinacio inicial que has triat");
                    System.out.println("canviaParametres -> Permet canviar quants colors s'utilitzen i la llargada de la combinacio per a la seguent tria");
                    System.out.println("toString -> Crea un string amb els atributs del pcmaker " +
                            "(i en quest cas es mostra per pantalla)");
                    System.out.println("---METODE fesJugada() NO IMPLEMENTAT JA QUE CODEMAKERS NO L'UTILITZEN EN AQUESTA IMPLEMENTACIO---\n");
                    break;

                case "creaPCMaker":
                    System.out.println("Inserta nom:");
                    String nom = reader.nextLine();
                    System.out.println("Inserta puntuacio:");
                    int puntuacio = reader.nextInt();

                    pcmaker = new PCMaker(nom, puntuacio);

                    break;

                case "getNom":
                    if (pcmaker == null) {
                        System.out.println("El pcmaker encara no s'ha creat!");
                    } else {
                        System.out.println(pcmaker.getNom());
                    }
                    break;

                case "getPuntuacio":
                    if (pcmaker == null) {
                        System.out.println("El pcmaker encara no s'ha creat!");
                    } else {
                        System.out.println(pcmaker.getPuntuacio());
                    }
                    break;

                case "setNom":
                    if (pcmaker == null) {
                        System.out.println("El pcmaker encara no s'ha creat!");
                    } else {
                        System.out.println("Inserta nom:");
                        pcmaker.setNom(reader.nextLine());
                    }
                    break;

                case "setPuntuacio":
                    if (pcmaker == null) {
                        System.out.println("El pcmaker encara no s'ha creat!");
                    } else {
                        System.out.println("Inserta puntuacio:");
                        pcmaker.setPuntuacio(reader.nextInt());
                    }
                    break;

                case "triaCombinacio":
                    if(pcmaker == null){
                        System.out.println("El pcmaker encara no s'ha creat!");
                    }
                    else
                        combinacio = pcmaker.triaCombinacio(tamany, colors);
                    break;

                case "mostraCombinacio":
                    if (pcmaker == null)
                        System.out.println("El pcmaker encara no s'ha creat!");
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
                    if (pcmaker == null) {
                        System.out.println("El pcmaker encara no s'ha creat!");
                    } else {
                        System.out.println(pcmaker.toString());
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
