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
	 * Retrieves the states that <code>this</code> transitions to
	 * on the given symbol
	 * @param symb - the alphabet symbol
	 * @return the new states
	 */
	public Set<NFAState> getTo(char symb){
//		Set<NFAState> ret = new HashSet<>();
//		for(Map.Entry<Character,NFAState> entry : transitions.entrySet()){
//			if(entry.getKey().equals(symb)){
//				ret.add(entry.getValue());
//			}
//			// Include cases where empty transitions are used to get to a location
//			if (entry.getKey().equals('e')) {
//				ret.addAll(entry.getValue().getTo(symb));
//			}
//		}
//		return ret;
		return recurseBuildStates(symb, false, new HashSet<>());
	}

	/**
	 * Recursively build the states that are accessible from this state on a certain symbol.
	 * @param symb Symbol to check for
	 * @param consumed Whether or not this symbol has already been consumed on this path
	 * @return The states that are available on this symbol
	 */
	private Set<NFAState> recurseBuildStates(char symb, boolean consumed, Set<NFAState> visited) {
		Set<NFAState> ret = new HashSet<>();
		for(Map.Entry<Character,NFAState> entry : transitions.entrySet()) {
			// If this transition consumes the symbol, mark it as consumed for the rest of the path and add the state to the set
			if (entry.getKey().equals(symb) && !consumed) {
				ret.add(entry.getValue());
				visited.add(entry.getValue());
				ret.addAll(entry.getValue().recurseBuildStates(symb, true, visited));
			}
			// If the empty transition exists and input has already been consumed, add the value and all valid children
			else if (entry.getKey().equals('e') && !visited.contains(entry.getValue())) {
				if(consumed) ret.add(entry.getValue());
				visited.add(entry.getValue());
				ret.addAll(entry.getValue().recurseBuildStates(symb, consumed, visited));
			}
		}
		return ret;
	}
}