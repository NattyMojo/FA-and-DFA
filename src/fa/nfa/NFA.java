package fa.nfa;

import java.util.*;

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
        if(!ordAbc.contains(onSymb)){
			ordAbc.add(onSymb);
		}
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
        Set<Set<NFAState>> DFAStateTracker = new HashSet<>();
        Queue<Set<NFAState>> queue = new LinkedList<>();
        DFA dfa = new DFA();
        Set<NFAState> initial = eClosure(start);
        dfa.addStartState(getStateName(initial));
        queue.add(initial);
        DFAStateTracker.add(initial);
        while(!queue.isEmpty()) {
            Set<NFAState> current = queue.poll();
            // Find all connected states
            for(char c : getABC()) {
                Set<NFAState> next = statesByInput(current, c);
                System.out.println(next);
                if(!DFAStateTracker.contains(next)) {
                    System.out.println("New state");
                    DFAStateTracker.add(next);
                    queue.add(next);
                    boolean isFinal = false;
                    for(NFAState state : next) {
                        if(state.isFinal()) {
                            isFinal = true;
                        }
                    }
                    if (isFinal) {
                        dfa.addFinalState(getStateName(next));
                    } else {
                        dfa.addState(getStateName(next));
                    }
                }
                dfa.addTransition(getStateName(current), c, getStateName(next));
            }
        }
        return dfa;
    }

    /**
     * Get all states that lead from a set of states given an input.
     * @param pseudoDFAState Set of states representing a DFA state
     * @param input input to consider
     * @return All states leading from pseudoDFAState on input
     */
    private Set<NFAState> statesByInput(Set<NFAState> pseudoDFAState, char input) {
        Set<NFAState> result = new HashSet<>();
        for(NFAState state : pseudoDFAState) {
            result.addAll(state.getTo(input));
        }
        return result;
    }

    private String getStateName(Set<NFAState> states) {
        if(states.size() == 0) return "[]";
        StringBuilder result = new StringBuilder("[");
        for(NFAState state : states) {
            result.append(state.getName()).append(", ");
        }
        // Remove last comma and space -- lazy way
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }

    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        // TODO Auto-generated method stub
        return from.getTo(onSymb);
    }

    @Override
    public Set<NFAState> eClosure(NFAState s) {
        Set<NFAState> result = new HashSet<>();
        result.add(s);
        // Base case: state has no empty transitions
        if(s.getTo('e').size() == 0) {
            return result;
        }
        // DFS -- s.getTo is an abstracted recursive function
        for(NFAState state : s.getTo('e')) {
            result.addAll(eClosure(state));
        }
        return result;
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