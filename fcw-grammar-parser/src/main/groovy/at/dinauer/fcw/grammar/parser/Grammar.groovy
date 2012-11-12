package at.dinauer.fcw.grammar.parser

import at.dinauer.fcw.grammar.parser.symbols.Symbol
import at.dinauer.fcw.grammar.parser.symbols.NonTerminalSymbol

public class Grammar {
	private Symbol root
    private Map<Symbol, Rule> rules
    private Boolean epsilonFree

    public Grammar() {
        rules = new HashMap<Symbol, Rule>()
        epsilonFree = false
    }

	public Symbol getRoot() {
		return root
	}

    public void setRoot (Symbol root) {
        this.root = root
    }

	public Rule getAt(String symbol) {
        return rules[new NonTerminalSymbol(symbol)]
    }

    public Rule getAt(Symbol symbol) {
        return rules[symbol]
    }

    public void addRule(Symbol symbol, Rule rule) {
        rules.put(symbol, rule)
    }

    public Map<Symbol, Rule> getRules() {
        return rules
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("G($root):\n")

        rules.each { symbol, rule ->
            sb.append("$symbol $Rule.ASSIGNMENT_OPERATOR $rule\n")
        }

        return sb
    }

    public void setEpsilonFree(Boolean containsEpsilon) {
        this.epsilonFree = containsEpsilon
    }

    public Boolean isEpsilonFree() {
        return epsilonFree
    }
}
