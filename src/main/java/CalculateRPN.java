import java.io.IOException;

public class CalculateRPN {

    String input;

    public int calculate(String input) {
        CreateOutputStack createOutputStack = new CreateOutputStack();
        ResultRPNWithStack resultRPNWithStack = new ResultRPNWithStack();
        int result = 0;

        try {
            result = resultRPNWithStack.calculateWithStack(createOutputStack.preparePNRStack(input));

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Result");
        return result;
    }
}