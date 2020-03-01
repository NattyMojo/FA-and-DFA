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

    @Override
    public void addStartState(String name) {
        DFAState start = new DFAState();
        start.setName(name);
        start.setStart(true);
        states.add(start);
    }

    @Override
    public void addState(String name) {
        DFAState newState = new DFAState();
        newState.setName(name);
        states.add(newState);
    }

    @Override
    public void addFinalState(String name) {
        DFAState finalState = new DFAState();
        finalState.setName(name);
        finalState.setFinal(true);
        states.add(finalState);
    }

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

    @Override
    public HashSet<DFAState> getStates() {
        return states;
    }

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

    @Override
    public DFAState getStartState() {
        for(DFAState state : states) {
            if(state.isStart()) {
                return state;
            }
        }
        return null;
    }

    @Override
    public Set<Character> getABC() {
        return language;
    }

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

    @Override
    public boolean accepts(String s) {
        DFAState result = traverseFA(getStartState(), s);
        if(result == null) {
            return false;
        }
        return result.isFinal();
    }

    @Override
    public State getToState(DFAState from, char onSymb) {
        return from.getNext(onSymb);
    }

    @Override
    public String toString() {
        return "This is where a DFA would be";
        //If I had one...-_- Dinkleburgggg
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
