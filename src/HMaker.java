import java.util.Scanner;

public class HMaker extends Jugador{

    public HMaker() {
    }

    public HMaker(String nom, int puntuacio) {
        super(nom, puntuacio);
    }


    public int triaCombinacio(int columnes, int numColors){
        //Retorna un enter on cada posició defineix un color depenent del numero, limitat pels parametres passats
        //Si retorna -1, error
        // TODO: TEST THIS

        System.out.println("Inserta la combinacio inicial ("
                            + columnes + " columnes i colors del 1 al "
                            + numColors + ":");
        Scanner reader = new Scanner(System.in);
        int combinacio = reader.nextInt();

        //Comprovar llargada del numero
        int length = String.valueOf(combinacio).length();
        if(length != columnes) return -1;

        //Comprovar que siguin colors valids
        int auxComb = combinacio;
        for (int i = 0; i < columnes; i++){
            if(auxComb % 10 > numColors || auxComb % 10 < 1) return -1;
            else auxComb = auxComb / 10;
        }

        //Si res ha explotat, retornem la combinacio llegida
        return combinacio;
    }

    @Override
    public void fesJugada(){
        //TODO: abans de començar, mira si necessites parametres
    }

}
