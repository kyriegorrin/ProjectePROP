package Domini;
import Domini.Tauler;

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

    //Funcio virtual, diferent per a cada subclasse, retorna una combinació en forma d'enter
    public Combinacio fesJugada(Tauler tauler){
        //El return no sera utilitzat, aixo es un metode virtual per als CodeBreakers
        return null;
    }

    public String toString(){
        return nom + " " + puntuacio;
    }
}
