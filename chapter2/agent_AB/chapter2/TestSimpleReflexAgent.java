package chapter2; 

public class TestSimpleReflexAgent {
	public static void main(String[] args) {
		// Task 1
//		Environment env = new Environment(Environment.LocationState.CLEAN, Environment.LocationState.DIRTY);
//		Agent agent = new Agent(new AgentProgram());
//		env.addAgent(agent, Environment.LOCATION_A);
//		env.step(3);
		
		// Task 2
		Environment env_2 = new Environment(Environment.LocationState.DIRTY, Environment.LocationState.DIRTY, Environment.LocationState.DIRTY, Environment.LocationState.DIRTY);
		Agent agent2 = new Agent(new AgentProgram());
		env_2.addAgent(agent2, Environment.LOCATION_A);
		env_2.stepUntilDoneExpand();
	}
	
}
