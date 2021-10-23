import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleVersion {

    public void calculate() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        int result = 0;

        try {
            while (true) {
                System.out.println("Please enter an example");
                try {
                    input = reader.readLine();
                    if (input.equals("exit")) {
                        System.out.println("Goodbye");
                        break;
                    }
                    CalculateRPN calculateRPN = new CalculateRPN();
                    result = calculateRPN.calculate(input);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(result);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }
}