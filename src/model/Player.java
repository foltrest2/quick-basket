package model;

public class Player implements Comparable<Player>{
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

	/**
	 * Constructor of player
	 * @param id id of the player
	 * @param fullName full name of the player
	 * @param age age of the player
	 * @param team team of the player
	 * @param pointsPerGame points per game of the player
	 * @param reboundsPerGame rebounds per game of the player
	 * @param assistsPerGame assists per game of the player
	 * @param robberiesPerGame robberies per game of the player 
	 * @param blocksPerGame blocks per game of the player
	 * @param generalEvaluation general evaluation of the player
	 */
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
	/**
	 * This method makes a string with all the information of the actual player
	 */
	@Override
	public String toString() {
		return "Player [id=" + id + ", fullName=" + fullName + ", age=" + age + ", team=" + team + ", pointsPerGame="
				+ pointsPerGame + ", reboundsPerGame=" + reboundsPerGame + ", assistsPerGame=" + assistsPerGame
				+ ", robberiesPerGame=" + robberiesPerGame + ", blocksPerGame=" + blocksPerGame + ", generalEvaluation="
				+ generalEvaluation + "]"+"\n";
	}

	@Override
	public int compareTo(Player p) {
		int compareage = ((Player)p).getId();
		return this.getId()-compareage;
	}
}