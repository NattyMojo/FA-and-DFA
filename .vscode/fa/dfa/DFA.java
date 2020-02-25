package fa.dfa;

import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.LinkedList;
import fa.State;

public class DFA implements DFAInterface {

    LinkedList<DFAState> states = new LinkedList<DFAState>();
    ArrayList<Character> language = new ArrayList<Character>();
    LinkedList<String> transitions = new LinkedList<String>(); //What if every odd value is a transition and every even
                                                               //is the state, so q0(0) 1(1) q1(2) is q0 -> q1 on a 1?

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
        //Add the language character if it's not already in the language
        if(!langHasChar(onSymb)) {
            language.add(onSymb);
        }
        transitions.add(fromState);
        String s = String.valueOf(onSymb);
        transitions.add(s);
        transitions.add(toState);
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
        Set<Character> abc = new TreeSet<Character>();
        for(char tran : language) {
            abc.add(tran);
        }
        return abc;
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

    private boolean langHasChar(char check) {

        if(language.contains(check)) {
            return true;
        }
        return false;
    }
    
}