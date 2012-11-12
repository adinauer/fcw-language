package at.dinauer.fcw.grammar.parser

import static org.junit.Assert.*
import at.dinauer.fcw.grammar.parser.symbols.Symbol
import org.junit.Test
import at.dinauer.fcw.grammar.parser.symbols.NonTerminalSymbol

public class AlternativeTest {
    @Test
    public void testDeletableWithSingleSymbol() {
        Alternative alternative = new Alternative()

        def symbol = new NonTerminalSymbol('X')
        alternative.addSymbol(symbol)
        symbol.markDeletable()

        assert alternative.symbols[0].isDeletable()
        assert alternative.isDeletable()
    }

    @Test
    public void testDeletableWithMultipleSymbols() {
        Alternative alternative = new Alternative()

        def symbol1 = new NonTerminalSymbol('X')
        def symbol2 = new NonTerminalSymbol('Y')
        alternative.addSymbol(symbol1)
        alternative.addSymbol(symbol2)

        symbol1.markDeletable()

        assert alternative.symbols[0].isDeletable()
        assert !alternative.symbols[1].isDeletable()
        assert !alternative.isDeletable()
    }

    @Test
    public void testNonDeletableWithMultipleSymbols() {
        Alternative alternative = new Alternative()

        def symbol1 = new NonTerminalSymbol('A')
        def symbol2 = new NonTerminalSymbol('B')
        alternative.addSymbol(symbol1)
        alternative.addSymbol(symbol2)

        assert !alternative.symbols[0].isDeletable()
        assert !alternative.symbols[1].isDeletable()
        assert !alternative.isDeletable()
    }
}
