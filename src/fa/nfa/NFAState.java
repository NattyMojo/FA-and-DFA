package fa.nfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fa.State;

public class NFAState extends State {

    private HashMap<Character,NFAState> transitions;//delta
	private boolean isFinal;//remembers its type
	
	/**
	 * Default constructor
	 * @param name the state name
	 */
	public NFAState(String name){
		initDefault(name);
		isFinal = false;
	}
	
	/**
	 * Overlaoded constructor that sets the state type
	 * @param name the state name
	 * @param isFinal the type of state: true - final, false - nonfinal.
	 */
	public NFAState(String name, boolean isFinal){
		initDefault(name);
		this.isFinal = isFinal;
	}
	
	private void initDefault(String name ){
		this.name = name;
		transitions = new HashMap<Character, NFAState>();
	}
    
    /**
     * Setting if a state is final
     * @param bool true = is a final state; false = not a final state
     */
    public void setFinal(boolean bool) {
        this.isFinal = bool;
    }

	/**
	 * Accessor for the state type
	 * @return true if final and false otherwise
	 */
	public boolean isFinal(){
		return isFinal;
	}
	

	/**
	 * Add the transition from <code> this </code> object
	 * @param onSymb the alphabet symbol
	 * @param toState to DFA state
	 */
	public void addTransition(char onSymb, NFAState toState){
		transitions.put(onSymb, toState);
	}
	
	/**
	 * Retrieves the state that <code>this</code> transitions to
	 * on the given symbol
	 * @param symb - the alphabet symbol
	 * @return the new state 
	 */
	public Set<NFAState> getTo(char symb){
		Set<NFAState> ret = new HashSet<NFAState>();
		for(Map.Entry<Character,NFAState> entry : transitions.entrySet()){
			if(entry.getKey().equals(symb)){
				ret.add(entry.getValue());
			}
		}
		return ret;
	}
}