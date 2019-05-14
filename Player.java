package connect_four;

public class Player {

	private String name;
	private String color;
	private int numGames;
	private int numWins;
	private int numLosses;
	private int numDraws;
	
	public Player(){
		color = " ? ";
		name = "Player Doe";
		numGames = 0;
		numWins = 0;
		numLosses = 0;
		numDraws = 0;
	}
	public Player(String symbol){
		this();//default constructor of same class
		this.color = symbol;
	}
	public Player(String name, String symbol){
		this();//default constructor of same class
		this.name = name;
		this.color = symbol;
	}

	protected void addWin(){
		numGames++;
		numWins++;
	}
	protected void addLoss(){
		numGames++;
		numLosses++;
	}
	protected void addDraw(){
		numGames++;
		numDraws++;
	}
	public String getName() {
		return name;
	}
	public String getSymbol() {
		return color;
	}
	public int getNumGames() {
		return numGames;
	}
	public int getNumWins() {
		return numWins;
	}
	public int getNumLosses() {
		return numLosses;
	}
	public int getNumDraws() {
		return numDraws;
	}
	
	//equals method
	//toString method
	//compareTo method
	
	
}