package at.dinauer.fcw.grammar.parser

public class Rule {
    public static final String ASSIGNMENT_OPERATOR = '->'
    private ArrayList<Alternative> alternatives

    public Rule(ArrayList<Alternative> alternatives) {
        this.alternatives = alternatives
    }

    public String toString() {
        return alternatives.join(" $Alternative.SEPARATOR ")
    }

    public ArrayList<Alternative> getAlternatives() {
        return alternatives
    }
}
