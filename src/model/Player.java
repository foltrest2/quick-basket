package model;

public class Player{
	private int id;
    private String fullName;
    private int age;
    private String team;
    private double pointsPerGame;
    private double reboundsPerGame;
    private double assistsPerGame;
    private double robberiesPerGame;
    private double blocksPerGame;
    private double generalEvaluation;
    
	public Player(int id, String fullName, int age, String team, double pointsPerGame, double reboundsPerGame,
			double assistsPerGame, double robberiesPerGame, double blocksPerGame, double generalEvaluation) {
		this.id = id;
		this.fullName = fullName;
		this.age = age;
		this.team = team;
		this.pointsPerGame = pointsPerGame;
		this.reboundsPerGame = reboundsPerGame;
		this.assistsPerGame = assistsPerGame;
		this.robberiesPerGame = robberiesPerGame;
		this.blocksPerGame = blocksPerGame;
		this.generalEvaluation = generalEvaluation;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getTeam() {
		return team;
	}
	
	public void setTeam(String team) {
		this.team = team;
	}
	
	public double getPointsPerGame() {
		return pointsPerGame;
	}
	
	public void setPointsPerGame(double pointsPerGame) {
		this.pointsPerGame = pointsPerGame;
	}
	
	public double getReboundsPerGame() {
		return reboundsPerGame;
	}
	
	public void setReboundsPerGame(double reboundsPerGame) {
		this.reboundsPerGame = reboundsPerGame;
	}
	
	public double getAssistsPerGame() {
		return assistsPerGame;
	}
	
	public void setAssistsPerGame(double assistsPerGame) {
		this.assistsPerGame = assistsPerGame;
	}
	
	public double getRobberiesPerGame() {
		return robberiesPerGame;
	}
	
	public void setRobberiesPerGame(double robberiesPerGame) {
		this.robberiesPerGame = robberiesPerGame;
	}
	
	public double getBlocksPerGame() {
		return blocksPerGame;
	}
	
	public void setBlocksPerGame(double blocksPerGame) {
		this.blocksPerGame = blocksPerGame;
	}
	
	public double getGeneralEvaluation() {
		return generalEvaluation;
	}
	
	public void setGeneralEvaluation(double generalEvaluation) {
		this.generalEvaluation = generalEvaluation;
	}

	@Override
	public String toString() {
		return "Player [fullName=" + fullName + ", age=" + age + ", team=" + team + ", pointsPerGame=" + pointsPerGame
				+ ", reboundsPerGame=" + reboundsPerGame + ", assistsPerGame=" + assistsPerGame + ", robberiesPerGame="
				+ robberiesPerGame + ", blocksPerGame=" + blocksPerGame + ", generalEvaluation=" + generalEvaluation
				+ "]"+"\n";
	}
}