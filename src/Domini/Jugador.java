package Domini;//TODO: fes la classe Tauler i importa-la (si és necessari, si no canvia el parametre de fesJugada())

public class Jugador {

    private String nom;
    private int puntuacio;

    public Jugador(){
        this.nom = null;
        this.puntuacio = 0;
    }

    public Jugador(String nom, int puntuacio) {
        this.nom = nom;
        this.puntuacio = puntuacio;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPuntuacio() {
        return puntuacio;
    }

    public void setPuntuacio(int puntuacio) {
        this.puntuacio = puntuacio;
    }

    public Jugador getJugador(){
        return this;
    }

    //Funcio virtual, diferent per a cada subclasse, retorna una combinació en forma d'enter
    public int fesJugada(){
        //WARNING: El return està perque m'obliga
        return 0;
    }

    public String toString(){
        return nom + " " + puntuacio;
    }
}
