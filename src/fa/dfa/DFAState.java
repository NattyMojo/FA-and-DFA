package fa.dfa;

import fa.State;

import java.util.HashMap;

public class DFAState extends State {
    
    private boolean isStart, isFinal = false;

    private HashMap<Character, DFAState> transitions = new HashMap<>();

    /**
     * @return the HashMap of transitions associated with this state
     */
    public HashMap<Character, DFAState> getTransitions() {
        return transitions;
    }

    /**
     * Add a transition to this state
     * @param c Input to map this transition to
     * @param next State to map to
     * @return false if a transition on this character exists, true if the transition was added
     */
    public void addTransition(char c, DFAState next) {
        if(!transitions.containsKey(c)) transitions.put(c, next);
    }

    /**
     * Check if an input is already mapped to a transition
     * @param c Transition input
     * @return Whether or not this state has a transition on this input
     */
    public boolean hasTransition(char c) {
        return transitions.containsKey(c);
    }

    /**
     * Get the next state given an input
     * @param c Input to check
     * @return DFAState if a map exists for this input, null if no map exists
     */
    public DFAState getNext(char c) {
        return transitions.get(c);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    /**
     * Set the name of this state
     * @param name New state name
     */
    public void setName(String name) {
        super.name = name;
    }

    /**
     * Set whether this state is a start state or not
     * @param bool New start state value
     */
    public void setStart(boolean bool) {
        this.isStart = bool;
    }

    /**
     * @return Whether this state is a start state or not
     */
    public boolean isStart() {
        return isStart;
    }

    /**
     * Set whether this state is a final state or not
     * @param bool New final state value
     */
    public void setFinal(boolean bool) {
        this.isFinal = bool;
    }

    /**
     *
     * @return Whether this state is a final state or not
     */
    public boolean isFinal() {
        return isFinal;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}