import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUserInterface {

    public void userConsoleInterface() throws Exception {
        String input;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println();
            System.out.println("enter number 1 for evaluate expression");
            System.out.println("enter number 2 for create revrsePolskaNotation");
            System.out.println("enter number 3 for exit");

            input = reader.readLine();
            if (input.equals("1")) {
                System.out.println("enter expressions");
                input = reader.readLine();
                CalculateRPN calculateRPN = new CalculateRPN();
                System.out.println(calculateRPN.calculate(input));
            }

            if (input.equals("2")) {
                System.out.println("enter expressions");
                input = reader.readLine();
                CreateOutputStackWithVariable withVariable = new CreateOutputStackWithVariable();
                System.out.println(withVariable.preparePNRStack(input));
            }

            if (input.equals("3")) {
                System.out.println("Goodbye");
                break;
            }
        }
    }
}
