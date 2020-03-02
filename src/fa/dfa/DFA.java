package fa.dfa;

import java.util.*;

import fa.State;

/**
 * A Java implementation of a deterministic finite automaton.
 *
 * @author Nate St. George, Zach Luciano
 */
public class DFA implements DFAInterface {

    private HashSet<DFAState> states = new HashSet<>();
    private HashSet<Character> language = new HashSet<>();

    /**
     * Adds a state to the DFA as a start state
     * @param name - The name of the state to add
     */
    @Override
    public void addStartState(String name) {
        DFAState start = new DFAState();
        start.setName(name);
        start.setStart(true);
        states.add(start);
    }

    /**
     * Adds an intermediate state to the DFA
     * @param name - The name of the state to add
     */
    @Override
    public void addState(String name) {
        DFAState newState = new DFAState();
        newState.setName(name);
        states.add(newState);
    }

    /**
     * Adds a state to the DFA as an accepted final state
     * @param name - The name of the state to add
     */
    @Override
    public void addFinalState(String name) {
        DFAState finalState = new DFAState();
        finalState.setName(name);
        finalState.setFinal(true);
        states.add(finalState);
    }

    /**
     * Assigns a transition to the DFA
     * @param fromState - The state from which to travel from
     * @param onSymb - The transition symbol
     * @param toState - The state to go after seeing the transition symbol
     */
    @Override
    public void addTransition(String fromState, char onSymb, String toState) {
        //Add the language character if it's not already in the language
        language.add(onSymb);
        // Check that both states exist
        if(stateNameExists(fromState) && stateNameExists(toState)) {
            DFAState from = getStateByName(fromState);
            // Check for existing connections from this state
            if(from.hasTransition(onSymb)) {
                System.out.println("State " + fromState + " already has a transition on " + onSymb + " to " + from.getNext(onSymb));
            } else {
                from.addTransition(onSymb, getStateByName(toState));
            }
        } else {
            System.out.println("State " + (stateNameExists(fromState) ? toState : fromState) + " does not exist.");
        }
    }

    /**
     * Returns a set containing all states in the DFA
     * @return HashSet<DFAState> - HashSet containing states
     */
    @Override
    public HashSet<DFAState> getStates() {
        return states;
    }

    /**
     * Returns a set containing all final (acceptance) states in the DFA
     * @return HashSet<DFAState> - HashSet containing final states
     */
    @Override
    public HashSet<DFAState> getFinalStates() {
        HashSet<DFAState> ret = new HashSet<>();
        for(DFAState state : states) {
            if(state.isFinal()) {
                ret.add(state);
            }
        }
        return ret;
    }

    /**
     * Returns the start state of the DFA
     * @return DFAState - The start state
     */
    @Override
    public DFAState getStartState() {
        for(DFAState state : states) {
            if(state.isStart()) {
                return state;
            }
        }
        return null;
    }

    /**
     * Returns the language of the DFA
     * @param Set<Character> - Set containing all characters in the language
     */
    @Override
    public Set<Character> getABC() {
        return language;
    }

    /**
     * Creates a DFA that is the complement of the DFA of which it is called from
     * @return DFA - New complement DFA of current DFA
     */
    @Override
    public DFA complement() {
        DFA comp = new DFA();
        comp.language = this.language;
        for(DFAState state : states) {
            comp.addState(state.getName());
            comp.getStateByName(state.getName()).setFinal(!state.isFinal());
            comp.getStateByName(state.getName()).setStart(state.isStart());
        }
        for(DFAState state : states) {
        	for(Map.Entry<Character, DFAState> entry : state.getTransitions().entrySet()) {
        		String stateName = entry.getValue().getName();
        		DFAState actualState = comp.getStateByName(stateName);
        		comp.getStateByName(state.getName()).addTransition(entry.getKey(),actualState);
        	}
        }
        return comp;
    }

    //No Javadoc because it is imported from the interface
    @Override
    public boolean accepts(String s) {
        DFAState result = traverseFA(getStartState(), s);
        if(result == null) {
            return false;
        }
        return result.isFinal();
    }

  //No Javadoc because it is imported from the interface
    @Override
    public State getToState(DFAState from, char onSymb) {
        return from.getNext(onSymb);
    }

  //No Javadoc because it is imported from the interface
    @Override
    public String toString() {
        String ret = "Q = { ";
    	//Assign Q
    	for(DFAState state : states) {
    		ret += state.getName() + " ";
    	}
    	ret += "}\nSigma = { ";
    	
    	//Assign Sigma
    	for(Character c : language) {
    		ret += c + " ";
    	}
    	
    	//Assign Delta
    	ret += "}\ndelta =\n\t\t";
    	for(Character c : language) {
    		ret += c + "\t";
    	}
    	ret += "\n";
    	for(DFAState state : states) {
    		ret += "\t";
    		ret += state.getName() + "\t";
    		for(Character c : language) {
    			ret += state.getTransitions().get(c).getName() + "\t";
    		}
    		ret += "\n";
    	}
        
    	//Assign q0
    	ret += "q0 = " + getStartState().getName() + "\n";
    	
    	//Assign Final states
    	ret += "F = { ";
    	for(DFAState state : states) {
            if(state.isFinal()) {
                ret += state.getName() + " ";
            }
        }
    	ret += "}\n";
    	
        return ret;
    }

    /**
     * Make a given state into a start state
     * @param name Name of state to make a start state
     */
    public void makeStateStart(String name) {
        if(stateNameExists(name)) getStateByName(name).setStart(true);
    }

    /**
     * Make a given state into a final state
     * @param name Name of state to make a final state
     */
    public void makeStateFinal(String name) {
        if(stateNameExists(name)) getStateByName(name).setFinal(true);
    }

    /**
     * Check if a state exists
     * @param name Name of the state to check
     * @return true if exists, false otherwise
     */
    private boolean stateNameExists(String name) {
        for(DFAState state : states) {
            if(state.getName().equals(name)) return true;
        }
        return false;
    }

    /**
     * Get a DFAState given its name
     * @param name Name of state to find
     * @return DFAState if it exists, null otherwise
     */
    private DFAState getStateByName(String name) {
        for(DFAState state : states) {
            if(state.getName().equals(name)) return state;
        }
        return null;
    }

    /**
     * Recursive function for traversing the DFA
     * @param state State to start at (or current state)
     * @param input Current input string
     * @return Ending state
     */
    private DFAState traverseFA(DFAState state, String input) {
//        System.out.println("Trace -> State: " + state + " string: " + input);
        if(input.length() == 0 || (input.length() == 1 && input.contains("e"))) {
            return state;
        }
        char curr = input.toCharArray()[0];
        if(language.contains(curr)){
            DFAState next = state.getNext(curr);
            return traverseFA(next, input.substring(1));
        } else {
            return null;
        }
    }
    
}
