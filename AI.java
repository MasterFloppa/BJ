public class AI extends Player {
	// AI's name
	protected String name = "AI";
	// AI's decisionIsDone
	private boolean decisionIsDone = false;

	// //AI's constructor
	// public AI()
	// {
	// handSize = 2;
	// score = 0;
	// decisionIsDone = false;
	// }

	// AI's decisionIsDone
	public boolean getdecisionIsDone() {
		return decisionIsDone;
	}

	/**
	 * AI's decisionIsDone based on score and money
	 */
	public Boolean aiDecide() {
		if (calcScore() >= 19)
			decisionIsDone = true;
		return !decisionIsDone;
	}

}
