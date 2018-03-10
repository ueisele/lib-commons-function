package de.ux.commons.function.parser;

import de.ux.commons.function.function.Function;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import static java.lang.String.format;

public class ContextExpressionParser implements Function<String, Object> {

    private final ExpressionParser parser;
    private final EvaluationContext evaluationContext;

    public ContextExpressionParser(Object rootObject) {
        this(new StandardEvaluationContext(rootObject));
    }

    public ContextExpressionParser(EvaluationContext evaluationContext) {
        this(new SpelExpressionParser(), evaluationContext);
    }

    public ContextExpressionParser(ExpressionParser parser, EvaluationContext evaluationContext) {
        this.parser = parser;
        this.evaluationContext = evaluationContext;
    }

    @Override
    public Object apply(String expressionString) throws ExpressionException, EvaluationException {
        return evaluate(parse(expressionString));
    }

    private Expression parse(String expressionString) throws ExpressionException {
        try {
            return parser.parseExpression(expressionString);
        } catch (ParseException e) {
            throw new ExpressionException(format("Could not parse expression: %s", expressionString), e);
        }
    }

    private Object evaluate(Expression expression) throws EvaluationException {
        try {
            return expression.getValue(evaluationContext);
        } catch (org.springframework.expression.EvaluationException e) {
            throw new EvaluationException(format("Could not evaluate expression: %s", expression.getExpressionString()), e);
        }
    }

}