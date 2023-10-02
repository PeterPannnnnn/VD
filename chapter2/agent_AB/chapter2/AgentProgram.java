package chapter2;

import java.util.Random;

import chapter2.Environment.LocationState;

public class AgentProgram {
	
	public Action execute(Percept p) {// location, status
		if(p.getLocationState() == LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
		} else if(p.getAgentLocation().equals(Environment.LOCATION_A)) {
			return Environment.MOVE_RIGHT;
		}else if (p.getAgentLocation().equals(Environment.LOCATION_B)) {
			return Environment.MOVE_LEFT;
		}
		return NoOpAction.NO_OP;
	}
	public Action executeExpand(Percept p) {// location, status
		if(p.getLocationState() == LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
		}else {
			Random ramdom = new Random();
			int randomAction = ramdom.nextInt(4);
			return Environment.ACTIONS[randomAction];
		}
	}
}