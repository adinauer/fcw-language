package at.dinauer.fcw.grammar.parser.symbols

public class SymbolRegistry {
    private Map<String, Symbol> registry
    private Set<String> nonTerminalSymbols

    public SymbolRegistry(Collection<String> nonTerminalSymbols) {
        this.registry = new HashMap<String, Symbol>()
        this.nonTerminalSymbols = nonTerminalSymbols.toSet()
    }

    public Symbol createSymbol(final String s) {
        return getOrCreateSymbol(s)
    }

    private Symbol getOrCreateSymbol(String s) {
        def symbol = registry.get(s)

        if (!symbol) {
            symbol = createSymbolOfCorrectType(s)
            registry.put(s, symbol)
        }

        return symbol
    }

    private Symbol createSymbolOfCorrectType(String s) {
        if (nonTerminalSymbols.contains(s)) {
            return new Symbol(s, Symbol.Type.NON_TERMINAL)
        } else {
            return new Symbol(s, Symbol.Type.TERMINAL)
        }
    }
}
