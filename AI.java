public class AI extends Player {
	// AI's name
	protected String name = "AI";
	private boolean decisionIsDone = false;

	public boolean getdecisionIsDone() {
		return decisionIsDone;
	}

	// AI's decisionIsDone based on score
	public Boolean aiDecide() 
	{
		if (calcScore() >= 19)						// 100% chance to stop
			decisionIsDone = true;
		else 
		{
			int p=(int)(Math.random()*10);			 
			if(calcScore() == 18)
			{
				if(p<8)								// 80% chance to stop
					decisionIsDone = true;
			}
			else if(calcScore() == 17)
			{
				if(p<4)								// 40% chance to stop
					decisionIsDone = true;
			}
		}	
		return !decisionIsDone;
	}

}
