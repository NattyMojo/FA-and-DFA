#Project: P1: Deterministic Finite Automata

#Authors: Nate St. George, Zach Luciano
#Date: 3/1/2020
#Section: Tues-Thurs 12:00-1:15

## Overview
Our program takes an input file that describes a Deterministic Finite Automata (DFA) which will
parse the file and create a DFA from it. This DFA can be traversed, tested, and have it's
information printed for the user to see.

## Compiling and Using
To start using the program ensure a file exists that matches the formatting for the description
of a DFA, this format is described below:
	• The 1st line contains the names of the final states, i.e., elements of F. The names are
	separated by the white space. It can be empty if there are no final states.
	• The 2nd line contains the name of the start state, i.e., q0.
	• The 3rd line contains the rest of the DFA’s states, i.e., those states that are not F or
	q0. It can be empty if all states have been specified in the previous two lines.
	• The 4th line lists the transitions. Transitions are separated by the white space. Three
	symbols compose a transition s0s1s2, where
		– The first symbol s0 is the name of the “from” state.
		– The second symbol s1 is the symbol from the alphabet, i.e., s1 ∈ Σ.
		– The third symbol s2 is the name of the “to” state.
	• Starting from line 5, each line contains a string for which we need to determine whether
	it is in the language of the DFA. The strings are over the DFA’s alphabet and we use
	‘e’ symbol to represent the empty string ε.

Once a file exists following these rules, compile the program using:
===
$javac DFADriver.java
===

Then run the program with the following command:
===
$java DFAdriver {input file}
===

## Discussion
The main concern we had going into this project was how to traverse the DFA. We tried some
different methods of storing the transitions in the sets and deciding where to go by going
set by set. We ended up using a HashMap within the set that could store a key containing the
transition value and return the set that we were to transition to. Once this was figured out
everything else fell into place.

## Testing
To test this system we used the 3 provided test files and matched the outputs to the expected
output. Each passed with flying colors.
