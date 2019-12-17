package expression.parser;

import expression.PriorityExpression;

public class Main {
    public static void main(String[] args) {
        // ExpressionParser parser = new ExpressionParser(new StringSource("((((-1273988855) - ((697355327) * ((1751609598) - (-820624248)))) * (((((z) << ((-448605695))) - (-647942712)) - ((((1761069089) + (944742580)) * (-1422254713)) / (z))) * (reverse ((digits (2065523746)) << (y))))))"));
        // ((((-1273988855) - ((697355327) * ((1751609598) - (-820624248)))) * (((((z) << ((-448605695))) - (-647942712)) - ((((1761069089) + (944742580)) * (-1422254713)) / (z))) * (reverse ((digits (2065523746)) << (y))))))
        ExpressionParser parser = new ExpressionParser(new StringSource("(reverse (2065523746))"));
        PriorityExpression result = parser.parseExpression();
        System.out.println();
    }
}
