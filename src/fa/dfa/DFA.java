package fa.dfa;

import java.util.*;

import fa.State;

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
    public Set<DFAState> getStates() {
        return states;
    }

    @Override
    public Set<DFAState> getFinalStates() {
        Set<DFAState> ret = new HashSet<>();
        for(DFAState state : states) {
            if(state.isFinal()) {
                ret.add(state);
            }
        }
        return ret;
    }

    @Override
    public State getStartState() {
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
        return null;
    }

    @Override
    public boolean accepts(String s) {
        return traverseFA((DFAState) getStartState(), s).isFinal();
    }

    @Override
    public State getToState(DFAState from, char onSymb) {
        return from.getNext(onSymb);
    }

    /**
     * Check if a state exists
     * @param name Name of the state to check
     * @return true if exists, false otherwise
     */
    private boolean stateNameExists(String name) {
        boolean containsName = false;
        for(DFAState state : this.getStates()) {
            containsName = state.getName().equals(name);
        }
        return containsName;
    }

    /**
     * Get a DFAState given its name
     * @param name Name of state to find
     * @return DFAState if it exists, null otherwise
     */
    private DFAState getStateByName(String name) {
        for(DFAState state : this.getStates()) {
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
        System.out.println("Trace -> State: " + state + " string: " + input);
        if(input.length() == 0) {
            System.out.println("Done");
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