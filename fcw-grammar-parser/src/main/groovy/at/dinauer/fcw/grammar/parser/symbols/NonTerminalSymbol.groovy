package at.dinauer.fcw.grammar.parser.symbols

public class NonTerminalSymbol extends Symbol {
    public NonTerminalSymbol(String symbol) {
        super(symbol, Symbol.Type.NON_TERMINAL)
    }
}
