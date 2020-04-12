# Project: P2: Nondeterministic Finite Automata

#Authors: Nate St. George, Zach Luciano
#Date: 4/13/20
#Section: Tues-Thurs 12:00-1:15

## Overview
Our program takes an input file that describes a Nondeterministic Finite Automata (NFA) which will
parse the file and create an NFA from it. This NFA can be traversed, tested, and have its
information printed for the user to see.

## Compiling and Using
To start using the program ensure a file exists that matches the formatting for the description
of a NFA, this format is described below:
	• The 1st line contains the names of the final states, i.e., elements of F. The names are
	separated by the white space. It can be empty if there are no final states.
	• The 2nd line contains the name of the start state, i.e., q0.
	• The 3rd line contains the rest of the NFA’s states, i.e., those states that are not F or
	q0. It can be empty if all states have been specified in the previous two lines.
	• The 4th line lists the transitions. Transitions are separated by the white space. Three
	symbols compose a transition s0s1s2, where
		– The first symbol s0 is the name of the “from” state.
		– The second symbol s1 is the symbol from the alphabet, i.e., s1 ∈ Σ.
		– The third symbol s2 is the name of the “to” state.
	• Starting from line 5, each line contains a string for which we need to determine whether
	it is in the language of the NFA. The strings are over the NFA’s alphabet and we use
	‘e’ symbol to represent the empty string ε.

Once a file exists following these rules, compile the program using:
===
`javac NFADriver.java`
===

Then run the program with the following command:
===
`java NFADriver {input file}`
===

## Discussion
For the most part, this project was smooth sailing until the very end. We had a few infinite loop cases,
like when empty transitions would connect to each other. To resolve this, we added a set of states that had
already been visited so we could keep track of where we'd been throughout the traversal. We also realized that our transitions
could only map to one state in the data structure we were using, so we had to change that to allow for multiple outgoing transitions
on the same symbol.

## Testing
To test this system we used the 4 provided test files and matched the outputs to the expected
output. Each passed with flying colors.
