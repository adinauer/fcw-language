package at.dinauer.fcw.grammar.parser.symbols

public class TerminalSymbol extends Symbol {
    public TerminalSymbol(String symbol) {
        super(symbol, Symbol.Type.TERMINAL)
    }
}
