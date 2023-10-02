package chapter2;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";

	public static final Action[] ACTIONS = { MOVE_LEFT, MOVE_RIGHT, MOVE_UP, MOVE_DOWN };

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;

	public Environment(LocationState locAState, LocationState locBState) {
		envState = new EnvironmentState(locAState, locBState);
	}

	public Environment(LocationState locAState, LocationState locBState, LocationState locCState,
			LocationState locDState) {
		envState = new EnvironmentState(locAState, locBState, locCState, locDState);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
		this.agent = agent;
		envState.setAgentLocation(location);

	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {

		if (action instanceof DynamicAction) {

			String agentLocation = envState.getAgentLocation();
			LocationState agentLocationState = envState.getLocationState(agentLocation);

			if (action == MOVE_LEFT) {
				String newLocation = agentLocation.equals(LOCATION_A) ? LOCATION_B : LOCATION_A;
				envState.setAgentLocation(newLocation);
			} else if (action == MOVE_RIGHT) {
				String newLocation = agentLocation.equals(LOCATION_A) ? LOCATION_B : LOCATION_A;
				envState.setAgentLocation(newLocation);
			} else if (action == SUCK_DIRT) {
				envState.setLocationState(agentLocation, LocationState.CLEAN);
			}
		}

		return envState;
	}

	public EnvironmentState executeActionExpand(Action action) {

		if (action instanceof DynamicAction) {
			String agentLocation = envState.getAgentLocation();
			LocationState locationState = envState.getLocationState(agentLocation);
			if (action == SUCK_DIRT) {
				if (locationState == LocationState.DIRTY) {
					envState.setLocationState(agentLocation, LocationState.CLEAN);
					agent.increScore(500); 
				}
			} else if (action == MOVE_UP) {
				String newLocation = getLocationUp(agentLocation);
				if (!newLocation.equals(agentLocation)) {
					envState.setAgentLocation(newLocation);
				} else {
					agent.increScore(-100); 
				}
			} else if (action == MOVE_DOWN) {
				String newLocation = getLocationDown(agentLocation);
				if (!newLocation.equals(agentLocation)) {
					envState.setAgentLocation(newLocation);
				} else {
					agent.increScore(-100);
				}
			} else if (action == MOVE_LEFT) {
				String newLocation = getLocationLeft(agentLocation);
				if (!newLocation.equals(agentLocation)) {
					envState.setAgentLocation(newLocation);
				} else {
					agent.increScore(-100);
				}
			} else if (action == MOVE_RIGHT) {
				String newLocation = getLocationRight(agentLocation);
				if (!newLocation.equals(agentLocation)) {
					envState.setAgentLocation(newLocation);
				} else {
					agent.increScore(-100);
				}
			} else {
				agent.increScore(-10);
			}
		}

		return envState;
	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		String agentLocation = envState.getAgentLocation();
		LocationState locationState = envState.getLocationState(agentLocation);

		return new Percept(agentLocation, locationState);
	}

	public void step() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);

		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN))
			isDone = true;// if both squares are clean, then agent do not need to do any action
		es.display();
	}

	public void stepExpand() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.executeExpand(getPerceptSeenBy());
		EnvironmentState es = executeActionExpand(anAction);

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);
		System.out.println("Score: " + agent.getScore());

		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_C) == LocationState.CLEAN
						&& (es.getLocationState(LOCATION_D) == LocationState.CLEAN)))
			isDone = true;// if both squares are clean, then agent do not need to do any action
		es.display();
		System.out.println("Total Score: " + agent.getScore());

	}

	private String getLocationUp(String location) {
		if (location.equals(LOCATION_A) || location.equals(LOCATION_B)) {
			return location;
		} else if (location.equals(LOCATION_C)) {
			return LOCATION_A;
		} else {
			return LOCATION_C;
		}
	}

	private String getLocationDown(String location) {
		if (location.equals(LOCATION_C) || location.equals(LOCATION_D)) {
			return location;
		} else if (location.equals(LOCATION_A)) {
			return LOCATION_C;
		} else { 
			return LOCATION_D;
		}
	}

	private String getLocationLeft(String location) {
		if (location.equals(LOCATION_A) || location.equals(LOCATION_C)) {
			return location;
		} else if (location.equals(LOCATION_B)) {
			return LOCATION_A;
		} else { 
			return LOCATION_C;
		}
	}

	private String getLocationRight(String location) {
		if (location.equals(LOCATION_B) || location.equals(LOCATION_D)) {
			return location; 
		} else if (location.equals(LOCATION_A)) {
			return LOCATION_B;
		} else { 
			return LOCATION_D;
		}
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}

	public void stepExpand(int n) {
		for (int i = 0; i < n; i++) {
			stepExpand();
			System.out.println("-------------------------");

		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}

	public void stepUntilDoneExpand() {
		int i = 0;

		while (!isDone) {

			System.out.println("step: " + i++);
			stepExpand();
		}

	}

}
