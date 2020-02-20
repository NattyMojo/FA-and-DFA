package fa.dfa;

import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedList;
import fa.State;

public class DFA implements DFAInterface {

    LinkedList<DFAState> states = new LinkedList<DFAState>();

    @Override
    public void addStartState(String name) {
        DFAState start = new DFAState();
        start.setName(name);
        start.setStart(true);
        states.add(0, start);
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

    }

    @Override
    public Set<? extends State> getStates() {
        Set<DFAState> ret = new TreeSet<DFAState>();
        for(int i = 0; i < states.size(); i++) {
            ret.add(states.get(i));
        }
        return ret;
    }

    @Override
    public Set<? extends State> getFinalStates() {
        Set<DFAState> ret = new TreeSet<DFAState>();
        for(DFAState eachState : states) {
            if(eachState.getFinal()) {
                ret.add(eachState);
            }
        }
        return ret;
    }

    @Override
    public State getStartState() {
        State ret = null;
        for(DFAState eachState : states) {
            if(eachState.getStart()) {
                ret = eachState;
            }
        }
        return ret;
    }

    @Override
    public Set<Character> getABC() {

        return null;
    }

    @Override
    public DFA complement() {

        return null;
    }

    @Override
    public boolean accepts(String s) {

        return false;
    }

    @Override
    public State getToState(DFAState from, char onSymb) {

        return null;
    }

}