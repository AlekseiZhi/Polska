import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Stack;

public class ResultRPNWithStack {

    int temp1 = 0;
    int temp2 = 0;
    int result = 0;

    public int calculateWithStack(Stack<Object> stackInput) {
        Stack<Object> stackTemp = new Stack<>();
        while (!stackInput.isEmpty()) {
            if (!isOperation(String.valueOf(stackInput.peek()))) {
                stackTemp.push(stackInput.pop());
            } else {
                Character symbol = (Character) stackInput.pop();
                temp2 = (int) stackTemp.pop();
                temp1 = (int) stackTemp.pop();
                if (symbol == '*') {
                    result = temp1 * temp2;
                    stackTemp.push(result);
                }
                if (symbol == '+') {
                    result = temp1 + temp2;
                    stackTemp.push(result);
                }
                if (symbol == '/') {
                    result = temp1 / temp2;
                    stackTemp.push(result);
                }
                if (symbol == '-') {
                    result = temp1 - temp2;
                    stackTemp.push(result);
                }
            }
        }
        return result;
    }

    private boolean isOperation(String c) {
        return c.equals("+") || c.equals("-") || c.equals("/") || c.equals("*");
    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }
}
