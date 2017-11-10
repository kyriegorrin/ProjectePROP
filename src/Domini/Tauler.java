package Domini;

public class Tauler { // tauler de linies del MM
	private int line_number; // nombre de linies del MM
	private int line_size; // nombre d'elements per linia
	private int ultima; //ultima linia modificada
	private Combinacio comb_ini;
	private Combinacio[] matriu; // solucio del MM
	private Combinacio[] solucio;
	private int colors;
	
	//-----COLORS-----
	// 0-> BUIT
	// 1-> NEGRE bé però en pos incorrecte
	// 2-> BLANC ok

	// CONSTRUCTOR
	
	
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
	// SETTER I GETTERS
	
	// retorna el numero de la ultima linia modificada
	public int getUltima() {
		return ultima;
	}
	
	// retorna el nombre de linies del MM
	public int getLine_number(){
		return line_number;
	}

	// modifica el nombre de linies del MM
	public void setLine_number(int line_number){
		this.line_number = line_number;
	}

	// retorna el nombre d'elements per linia
	public int getLine_size(){
		return line_size;
	}

	// modifica el nombre d'elements per linia
	public void setLine_size(int line_size){
		this.line_size = line_size;
	}
	
	// retorna la combinacio la linia "line"
	public Combinacio getlinia(int line){
		return matriu[line_number-1-line];
	}
	public Combinacio get_solucio_linia(int linia){
		return solucio[line_number-1-linia];
	}

	// retorna el vector de combinacions
	public Combinacio[] getTauler(){
		return matriu;
	}

	// modifica la matriu tauler
	public void setTauler(Combinacio[] matriu){
		this.matriu = matriu;
	}

	// retorna la combinacio inicial
	public Combinacio get_comb_ini(){
		return comb_ini;
	}

	// modifica la combinacio inicial
	public void setInitial_line(Combinacio comb_ini){
		this.comb_ini = comb_ini;
	}
	
	// modifica la matriu solucio
	public void setSolucio(Combinacio[] solucio) {
		this.solucio = solucio;
	}
	
	// retorna la matriu solucio
	public Combinacio[] getSolucio() {
		return solucio;
	}


	
	// ESCRIPTORS
	
	// escriu el tauler per pantalla
	public void escriu_tauler(){
		for(int i = 0; i < line_number; ++i){
			matriu[i].escriu_combinacio();
		}
	}
	
	// escriu la solucio per pantalla
	public void escriu_solucio(){
		for(int i = 0; i < line_number; ++i){
			solucio[i].escriu_combinacio();
		}
	}
	
	//OTHERS
	
	//afegeix una combinacio a la ultima linia no modificada
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
		public int getColors() {
		return colors;
	}

	public void setColors(int colors) {
		this.colors = colors;
	}

	// donada una nova linia comparar-la amb la combinacio inicial i
		//emplenar la combinacio solucio corresponent
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
   						if(c.get_elementx(i) == comb_ini.get_elementx(j)&& comprovant[j] !=2 ){
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
