package at.dinauer.fcw.grammar.parser

import at.dinauer.fcw.grammar.parser.symbols.NonTerminalSymbol
import at.dinauer.fcw.grammar.parser.symbols.Symbol

import at.dinauer.fcw.grammar.parser.symbols.SymbolRegistry

public class GrammarParser {
    private static final String RULE_SPLIT_REGEX = /(\w+) *?$Rule.ASSIGNMENT_OPERATOR *?(.*)/
    private static final String ALTERNATIVE_SPLIT_REGEX = "\\${Alternative.SEPARATOR}"
    private static final String SYMBOL_SPLIT_REGEX = "\\${Symbol.SEPARATOR}"

    private SymbolRegistry symbolRegistry
    private Grammar grammar
    private String grammarAsText

	public Grammar parse(String grammarAsText) {
		grammar = new Grammar()
        this.grammarAsText = grammarAsText

        setupRegistry()

        determineRootElement()
        validateRoot()

        addRulesToGrammar()

        markDeletableSymbols()

		return grammar
	}

    private void setupRegistry() {
        Set<String> nonTerminalSymbols = collectNonTerminalSymbols()
        symbolRegistry = new SymbolRegistry(nonTerminalSymbols)
    }

    /**
     * Collects all symbols that define a rule
     */
    private Set<String> collectNonTerminalSymbols() {
        Set<String> detectedNonTerminalSymbols = new HashSet<String>()

        grammarAsText.findAll(RULE_SPLIT_REGEX) { line, nonTerminalSymbol, rule ->
            detectedNonTerminalSymbols.add(nonTerminalSymbol.trim())
        }

        return detectedNonTerminalSymbols
    }

    /**
     * Looks for a definition like "G(S):" in the grammar and sets S as root element
     */
    private String determineRootElement() {
        def rootDefinitionRegex = /G\((.*)\):/

        grammarAsText.find(rootDefinitionRegex) { line, symbol ->
            grammar.root = new NonTerminalSymbol(symbol)
        }
    }

    private void validateRoot() {
        if (!grammar.root) {
            throw new IllegalGrammarException('No definition of root element found!');
        }
    }

    /**
     * Splits a rule into Alternatives
     */
    private ArrayList<Alternative> extractAlternatives(String rule) {
        def alternatives = new ArrayList<Alternative>()

        rule.split(ALTERNATIVE_SPLIT_REGEX).each { alternativeAsString ->
            def alternative = new Alternative()

            addSymbolsToAlternative(alternative, alternativeAsString)

            alternatives.add(alternative)
        }

        return alternatives
    }

    private void addSymbolsToAlternative(Alternative alternative, String alternativeAsString) {
        alternativeAsString.split(SYMBOL_SPLIT_REGEX).each { symbol ->
            def trimmedSymbol = symbol.trim()
            if (trimmedSymbol) {
                alternative.addSymbol(symbolRegistry.createSymbol(trimmedSymbol))
            }
        }
    }

    private void addRulesToGrammar() {
        grammarAsText.findAll(RULE_SPLIT_REGEX) { line, nonTerminalSymbol, rule ->
            grammar.addRule(symbolRegistry.createSymbol(nonTerminalSymbol), new Rule(extractAlternatives(rule)))
        }
    }

    private void markDeletableSymbols() {
        markEpsilonSymbolsDeletable()
        markSymbolsDeletableThatAreAffectedByEpsilons()
    }

    private void markEpsilonSymbolsDeletable() {
        grammar.rules.each { nt, rule ->
            rule.alternatives.each { alternative ->
                alternative.symbols.each { symbol ->
                    if (symbol.isEpsilon()) {
                        symbol.markDeletable()
                        grammar.setEpsilonFree(false)
                    }
                }
            }
        }
    }

    private void markSymbolsDeletableThatAreAffectedByEpsilons() {
        Boolean foundNewDeletableSymbol = !grammar.isEpsilonFree()
        Set<Symbol> detectedDeletableSymbols = new HashSet<Symbol>()

        while(foundNewDeletableSymbol) {
            foundNewDeletableSymbol = false
            grammar.rules.each { nonTerminalSymbol, rule ->
                if (!detectedDeletableSymbols.contains(nonTerminalSymbol)) {
                    rule.alternatives.each { alternative ->
                        if (alternative.isDeletable()) {
                            nonTerminalSymbol.markDeletable()
                            detectedDeletableSymbols.add(nonTerminalSymbol)
                            foundNewDeletableSymbol = true
                        }
                    }
                }
            }
        }
    }
}
