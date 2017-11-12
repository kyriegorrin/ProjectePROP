package Domini;

/**
 * CLASE TAULER
 *
 */

public class Tauler { 

	///ATRIBUTS 
	private int line_number; /// nombre de linies del MM
	private int line_size; /// nombre d'elements per linia
	private int ultima; ///ultima linia a modificar
	private Combinacio comb_ini;///combinacio a endivinar
	private Combinacio[] matriu; /// solucio del MM
	private Combinacio[] solucio; ///solicio de blancs/negres
	private int colors; /// ultima linia a modificar
	
	///-----COLORS-----
	/// 0-> BUIT
	/// 1-> NEGRE bé però en pos incorrecte
	/// 2-> BLANC ok

	///-----PUNTUACIO-----
	/// 500-> LINIES PER ACABAR EL TAULER
	/// 200-> PER CADA ENCERT A LA SULUCIO
	/// 100-> PER CADA ENCERTA LA SOLUCIO PERO EN DIFERENT POSICIO
	/// CONSTRUCTORS
	
	/** Constructora que creaun objecte tauler.
	 * 
	 * @param line_number nombre de linies del MM.
	 * @param line_size nombre d'elements per linia.
	 * @param colors ultima linia a modificar.
	 * @return retorna un objecte Tauler
	 */
	public Tauler(int line_number, int line_size, int colors){
		
		this.line_number = line_number;
		this.line_size = line_size;
		this.colors = colors;
		ultima = line_number-1;
		matriu = new Combinacio[line_number];
		comb_ini = new Combinacio(line_size);
		solucio = new Combinacio[line_number];
		for(int i = 0; i < matriu.length; ++i){
			matriu[i] = new Combinacio(line_size);
			solucio[i] = new Combinacio(line_size);
		}
	}
	/// SETTERS I GETTERS
	
	/**
	 * @brief Getter de la proxima linia del tauler que s'ha de modificar.
	 * @return L'atribut ultima.
	 */
	// retorna el numero de la ultima linia modificada
	public int getUltima() {
		return ultima;
	}
	
	/**
	 * @brief Getter de el numero total de linies.
	 * @return L'atribut line_number.
	 */
	// retorna el nombre de linies del MM
	public int getLine_number(){
		return line_number;
	}
	/**
	 * @brief Setter del numero de linies que te el tauler.
	 * @param Enter >0
	 * 
	 */
	// modifica el nombre de linies del MM
	public void setLine_number(int line_number){
		this.line_number = line_number;
	}
	/**
	 * @brief Getter del numero d'elements que te cada combinacio.
	 * @return L'atribut line_size.
	 */
	// retorna el nombre d'elements per linia
	public int getLine_size(){
		return line_size;
	}
	/**
	 * @brief Setter del numero d'elements que te cada combinacio.
	 * @param Enter >0.
	 */
	// modifica el nombre d'elements per linia
	public void setLine_size(int line_size){
		this.line_size = line_size;
	}
	
	/**
	 * @brief Getter de la combinacio d'una linia concreta.
	 * @param 0 >=line >= line_number-1
	 * @return Es retorna la combinacio del tauler de la posicio line
	 */
	// retorna la combinacio la linia "line"
	public Combinacio getlinia(int line){
		return matriu[line];
	}
	
	/**
	 * @brief Getter de la solucio d'una linia concreta.
	 * @param  0 >=linia >= line_number-1.
	 * @return Es retorna la combinacio de la solucio de la posicio linia.
	 */
	public Combinacio get_solucio_linia(int linia){
		return solucio[linia];
	}

	/**
	 * @brief Getter de tot el tauler.
	 * @return Es retorna un vector de combinacions igual que matriu.
	 */
	public Combinacio[] getTauler(){
		return matriu;
	}

	/**
	 * @brief Setter del Tauler
	 * @param matriu és un vector de combinacions inicialitzat
	 */
	// modifica la matriu tauler
	public void setTauler(Combinacio[] matriu){
		this.matriu = matriu;
	}

	/**
	 * Getter de la combinacio a endivinar.
	 * @return Es retorna L'atribut comb_ini.
	 */
	// retorna la combinacio inicial
	public Combinacio get_comb_ini(){
		return comb_ini;
	}

	/**
	 * Setter de la combinacio a endivinar.
	 * @param comb_ini comb_ini es una combinacio inicialitzada.
	 */
	// modifica la combinacio inicial
	public void setInitial_line(Combinacio comb_ini){
		this.comb_ini = comb_ini;
	}
	
	/**
	 * Setter de tauler de solucions.
	 * @param solucio Solucio es un vector de combinacions inicialitzades.
	 */
	// modifica la matriu solucio
	public void setSolucio(Combinacio[] solucio) {
		this.solucio = solucio;
	}
	
	/**
	 * Getter de la taula de solucions.
	 * @return es retorna un vector de combinacions amb els mateixos valors que
	 * l'atribut solucio.
	 */
	// retorna la matriu solucio
	public Combinacio[] getSolucio() {
		return solucio;
	}

	/**
	 * Getter del numbre de colors de la partida.
	 * @return Es retorna l'atribut colors.
	 */
	
	public int getColors() {
		return colors;
	}

	/**
	 * Setter del nombrede colors de la partida.
	 * @param colors colors es un enter > 0.
	 */
	public void setColors(int colors) {
		this.colors = colors;
	}

	
	/// ESCRIPTORS
	
	// escriu el tauler per pantalla
	/**
	 * Escriptor del tauler de combinacions.
	 */
	public void escriu_tauler(){
		for(int i = 0; i < line_number; ++i){
			matriu[i].escriu_combinacio();
			System.out.println();
		}
	}
	
	// escriu la solucio per pantalla
	/**
	 * Escriptor del tauler de solucions.
	 */
	public void escriu_solucio(){
		for(int i = 0; i < line_number; ++i){
			solucio[i].escriu_combinacio();
			System.out.println();
		}
	}
	// escriu el tauler seguidament de la solucio corresponent
	/**
	 * Escriptor del tauler combinacio seguit del tauler de solucions.
	 */
	public void escriu_tot(){
		for(int i = 0; i < line_number; ++i ){
			matriu[i].escriu_combinacio();
			System.out.print("////");
			solucio[i].escriu_combinacio();
			System.out.println();
		}
		
	}
	///OTHERS
	
	
	/**
	 * Comprovant de si el tauler esta ple
	 * @return Es retorna true si el vector esta ple, si no es retorna false.
	 */
	public boolean tauler_ple(){
		return ultima == -1;
	}
	/**
	 * Comprovant de si s'ha encertat la combinacio a endivinar
	 * @return Es retorna true si l'ultim element del tauler de combinacions 
	 * es igual a la combinacio a enfivinar, en cas negatiu es retorna false.
	 */
	public boolean encert(){
		for(int i = 0; i < line_size; ++i){
			if(solucio[ultima+1].get_elementx(i) != 2)return false;
		}
		return true;
		
	}
	
	/**
	 * Consultora de la puntuacio de la partida actual
	 * @return Es retorna un enter amb la puntuacio de totes les jugades.
	 */
	public int puntuacio(){
		
		int aux = 0;
			for(int i = line_number-1; i > ultima; --i)
			{	
				aux += solucio[i].get_puntuacio();
			}
			aux += (ultima+1)*500;
		return aux;
	}
	
	/**
	 * Es posa valor a la seguent linia que toca modificar del tauler de combinacions i 
	 * amb la informacio de la combinacio a introduir s'emplena el corresponent tauler 
	 * de solucions, en cas que el tauler ja estigui ple o la combinacio sigui erronea
	 * no realitza res.
	 * @param c c Es una combinacio inicialitzada.
	 */
	public void set_ultima_linia(Combinacio c){
		if(c.comprovar_colors(colors) == -1)System.out.println("ERROR: La combinacio no esta dins el rang de colors.");
		else if(ultima < 0)	System.out.println("ERROR: El mastermind ja esta omplert.");
				//ja tenim el mastermind ple per tant no podem posar + linies
		else{
			matriu[ultima] = c;
			// Emplenem la solucio
			actualitzar(c);	
			--ultima;

		}
	}
	

	// donada una nova linia comparar-la amb la combinacio inicial i
		//emplenar la combinacio solucio corresponent
		/**
		 * Actualitza el tauler de solucions amb la solucio de la ultima linia afegida
		 * al tauler de combinacions.
		 * @param c es una combinacio inicialitzada.
		 */
       private void actualitzar(Combinacio c){
    	   int blancs = 0;
    	   int negres = 0;
    	   //el vector s'utilitza per que si veiem que una bola està bé
    	   //no la conti 2 cops si una és del mateix color en mala pos
    	   int[] comprovant = new int[line_size];
    	   for(int i = 0; i < line_size; ++i){
    		   if(c.get_elementx(i)== comb_ini.get_elementx(i)){
    			   ++blancs;
    			   if(comprovant[i] == 1)--negres;
    			   comprovant[i] = 2;
    		   }
    		   else{
    			   int j = 0;
   					boolean trobat = false;
   					while(j < line_size && !trobat){
   						if(c.get_elementx(i) == comb_ini.get_elementx(j)&& comprovant[j] ==0 ){
   						comprovant[j] = 1;
   						++negres;
   						trobat = true;
   					}
   					++j;
   				}
    		   }
    	   }
    	   solucio[ultima].omplir(blancs, negres);  
       }
}
