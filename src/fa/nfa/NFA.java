package fa.nfa;

import java.util.LinkedHashSet;
import java.util.Set;

import fa.State;
import fa.dfa.DFA;

public class NFA implements NFAInterface {
    private Set<NFAState> states;
	private NFAState start;
    private Set<Character> ordAbc;
    
    public NFA() {
        this.start = null;
        this.states = new LinkedHashSet<NFAState>();
		this.ordAbc = new LinkedHashSet<Character>();
    }

    /**
     * Add a state as the start state of the NFA
     * @param name - Name of the state
     */
    @Override
    public void addStartState(String name) {
        // TODO Auto-generated method stub
        NFAState state = new NFAState(name);
        this.start = state;
        this.states.add(state);
    }

    @Override
    public void addState(String name) {
        // TODO Auto-generated method stub
        NFAState state = new NFAState(name);
        this.states.add(state);
    }

    @Override
    public void addFinalState(String name) {
        // TODO Auto-generated method stub
        NFAState state = new NFAState(name);
        state.setFinal(true);
        this.states.add(state);
    }

    @Override
    public void addTransition(String fromState, char onSymb, String toState) {
        // TODO Auto-generated method stub
        NFAState from = getStateByName(fromState);
        NFAState to = getStateByName(toState);
        from.addTransition(onSymb, to);
    }

    @Override
    public Set<? extends State> getStates() {
        // TODO Auto-generated method stub
        return states;
    }

    @Override
    public Set<? extends State> getFinalStates() {
        // TODO Auto-generated method stub
        Set<NFAState> retval = new LinkedHashSet<NFAState>();
        for(NFAState curr : states) {
            if(curr.isFinal()){
                retval.add(curr);
            }
        }
        return retval;
    }

    @Override
    public State getStartState() {
        // TODO Auto-generated method stub
        return start;
    }

    @Override
    public Set<Character> getABC() {
        // TODO Auto-generated method stub
        return ordAbc;
    }

    @Override
    public DFA getDFA() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<NFAState> eClosure(NFAState s) {
        // TODO Auto-generated method stub
        return null;
    }

    public NFAState getStateByName(String name) {
        for(NFAState state : states) {
            if(state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }
    
}