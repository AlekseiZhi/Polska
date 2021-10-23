import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Stack;

public class CreateOutputStackWithVariable {

    private static final int MAX_PRIORITY = 3;
    private static final int MIDDLE_PRIORITY = 2;
    private static final int LOW_PRIORITY = 1;
    public static final int INPUT_STEAM_END_SYMBOL = -1;
    public static final char OPENING_BRAKET = '(';
    public static final char CLOSING_BRAKET = ')';

    public Stack<Object> preparePNRStack(String input) throws Exception {

        try (InputStreamReader is = new InputStreamReader(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)))) {

            int nextToken = is.read();
            Stack<Character> stackOperation = new Stack<>();
            Stack<Object> stackOutput = new Stack<>();

            while (nextToken != INPUT_STEAM_END_SYMBOL) {
                StringBuilder result = new StringBuilder();
                char symbol = (char) nextToken;
                if (isNumber(symbol)) {
                    while (isNumber(symbol)) {
                        result.append(Character.getNumericValue(symbol));
                        nextToken = is.read();
                        symbol = (char) nextToken;
                        if (isVariable(symbol)) {
                            System.out.println("Error, please repeat expression");
                            throw new RuntimeException();
                        }
                    }
                    stackOutput.push(Integer.parseInt(result.toString()));
                }

                if (isVariable(symbol)) {
                    while (!isOperation(symbol)) {
                        result.append((symbol));
                        nextToken = is.read();
                        if (nextToken == -1) {
                            break;
                        }
                        symbol = (char) nextToken;
                    }
                    stackOutput.push((result.toString()));
                }

                if (isOperation(symbol)) {
                    if (symbol == CLOSING_BRAKET) {
                        moveStackElementsUntilOpeningBracket(stackOperation, stackOutput);
                    } else {
                        if (stackOperation.isEmpty() || symbol == OPENING_BRAKET) {
                            stackOperation.push(symbol);
                        } else {
                            moveStackElementsUntilPriorityIsHigher(stackOperation, stackOutput, symbol);
                        }
                    }
                }
                nextToken = is.read();
            }
            while (!stackOperation.isEmpty()) {
                if (stackOperation.peek() != OPENING_BRAKET && stackOperation.peek() != CLOSING_BRAKET) {
                    stackOutput.push(stackOperation.pop());
                } else {
                    stackOperation.pop();
                }
            }
            Stack<Object> stackReverse = new Stack<>();
            while (!stackOutput.isEmpty()) {
                stackReverse.push(stackOutput.pop());
            }
            System.out.println("Reverse Polska Notation");
            return stackReverse;
        }
    }

    private void moveStackElementsUntilPriorityIsHigher
            (Stack<Character> stackOperation, Stack<Object> stackOutput, char symbol) {
        while ((!stackOperation.isEmpty() && getPriority(stackOperation.peek()) >= getPriority(symbol))) {
            stackOutput.push(stackOperation.pop());
            if (stackOperation.isEmpty()) {
                break;
            }
        }
        stackOperation.push(symbol);
    }

    private void moveStackElementsUntilOpeningBracket
            (Stack<Character> stackOperation, Stack<Object> stackOutput) {
        while (!stackOperation.peek().equals(OPENING_BRAKET)) {
            stackOutput.push(stackOperation.pop());
        }
        stackOperation.pop();
    }

    private boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')';
    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isVariable(char c) {
        return c >= 'a' && c <= 'z';
    }

    private int getPriority(Character c) {
        if (c.equals('*') || c.equals('/')) {
            return MAX_PRIORITY;
        }
        if (c.equals('+') || c.equals('-')) {
            return MIDDLE_PRIORITY;
        }
        return LOW_PRIORITY;
    }
}