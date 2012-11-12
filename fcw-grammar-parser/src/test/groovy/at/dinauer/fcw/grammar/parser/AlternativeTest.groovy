package at.dinauer.fcw.grammar.parser

import org.junit.Test

import at.dinauer.fcw.grammar.parser.symbol.NTSym

public class AlternativeTest {
    @Test
    public void testDeletableWithSingleSymbol() {
        Alternative alternative = new Alternative()

        def symbol = new NTSym('X')
        alternative.addSymbol(symbol)
        symbol.markDeletable()

        assert alternative.symbols[0].isDeletable()
        assert alternative.isDeletable()
    }

    @Test
    public void testDeletableWithMultipleSymbols() {
        Alternative alternative = new Alternative()

        def symbol1 = new NTSym('X')
        def symbol2 = new NTSym('Y')
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

        def symbol1 = new NTSym('A')
        def symbol2 = new NTSym('B')
        alternative.addSymbol(symbol1)
        alternative.addSymbol(symbol2)

        assert !alternative.symbols[0].isDeletable()
        assert !alternative.symbols[1].isDeletable()
        assert !alternative.isDeletable()
    }
}
