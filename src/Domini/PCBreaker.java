package Domini;

public class PCBreaker extends Jugador{
    private Algoritme algoritme;

    public PCBreaker() {
    }

    public PCBreaker(String nom, int puntuacio) {
        super(nom, puntuacio);
        algoritme = new Algoritme();
    }

    @Override
    public Combinacio fesJugada(Tauler tauler){
        Combinacio combinacio = algoritme.algoritmeGenetic(tauler);
        return combinacio;
    }
}
