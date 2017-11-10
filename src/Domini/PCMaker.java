package Domini;

import java.util.Random;

public class PCMaker extends Jugador {

    public PCMaker() {
    }

    public PCMaker(String nom, int puntuacio) {
        super(nom, puntuacio);
    }

    //Retorna la combinació inicial del tauler (la solucio). En aquest cas es random dintre del rang de colors.
    public Combinacio triaCombinacio(int tamany, int colors){
        Combinacio combinacio = new Combinacio(tamany);

        for(int i = 0; i < tamany; ++i){
            combinacio.set_elementx(i, new Random().nextInt(colors));
        }
        return combinacio;
    }
}
