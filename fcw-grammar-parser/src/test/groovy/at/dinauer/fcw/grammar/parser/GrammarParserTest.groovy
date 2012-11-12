package at.dinauer.fcw.grammar.parser

import static org.junit.Assert.*
import org.junit.Test

import at.dinauer.fcw.grammar.parser.symbols.NonTerminalSymbol
import at.dinauer.fcw.grammar.parser.symbols.TerminalSymbol
import at.dinauer.fcw.grammar.parser.symbols.Symbol

class GrammarParserTest {

    @Test
    public void testDetermineRootElementForDefinitionWithSpaces() {
        GrammarParser parser = new GrammarParser()

        Grammar grammar = parser.parse(' G( RootElement ): ')
        assert new NonTerminalSymbol('RootElement') == grammar.root
    }

    @Test(expected = IllegalGrammarException)
    public void testNoRootElementDefinition() {
        GrammarParser parser = new GrammarParser()

        Grammar grammar = parser.parse('A -> b')
        fail("\n${grammar.toString()}")
    }

    @Test
    public void testParseGrammar() {
        GrammarParser parser = new GrammarParser()

        def grammarAsText = '''
            G(S):
            S -> A ;
            A -> b S | c d a
        '''
        Grammar grammar = parser.parse(grammarAsText)

        assert new NonTerminalSymbol('S') == grammar.root
        assert 'A ;' == grammar['S'].toString()
            assert 'b S' == grammar['A'].alternatives[0].toString()
                assert new TerminalSymbol('b') == grammar['A'].alternatives[0].symbols[0]
                assert new NonTerminalSymbol('S') == grammar['A'].alternatives[0].symbols[1]
            assert 'c d a' == grammar['A'].alternatives[1].toString()
                assert new TerminalSymbol('c') == grammar['A'].alternatives[1].symbols[0]
                assert new TerminalSymbol('d') == grammar['A'].alternatives[1].symbols[1]
                assert new TerminalSymbol('a') == grammar['A'].alternatives[1].symbols[2]
    }

    @Test
    public void testParseGrammarContainingDeletableSymbols() {
        GrammarParser parser = new GrammarParser()

        def grammarAsText = """
            G(S):
            S -> A ;
            A -> B | a
            B -> $Symbol.EPSILON | b
        """
        Grammar grammar = parser.parse(grammarAsText)

        assert grammar['B'].alternatives[0].isDeletable()
        assert grammar['A'].alternatives[0].isDeletable()
        assert !grammar['S'].alternatives[0].isDeletable()
    }
}
