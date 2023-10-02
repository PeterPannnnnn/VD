package chapter2;

public class Agent {
	private AgentProgram program;
	private int score;
	
	public Agent() {
		score = 0;
	}
	

//	public Agent(AgentProgram aProgram) {
//		program = aProgram;
//	}
	public Agent(AgentProgram aProgram) {
		program = aProgram;
		score = 0;
	}
	
	public Action execute(Percept p) {
		if (program != null) {
			return program.execute(p);
		}
		return NoOpAction.NO_OP;
	}
	public Action executeExpand(Percept p) {
		if (program != null) {
			return program.executeExpand(p);
		}
		return NoOpAction.NO_OP;
	}
	 public int getScore() {
	        return score;
	    }

	    public void increScore(int points) {
	        score += points;
	    }
	
}
