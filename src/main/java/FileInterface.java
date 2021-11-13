import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileInterface {
    private final String pathFile = "C:/Users/laise/Desktop/main.txt";
    private final File file = new File(pathFile);

    public int calculateExpression() throws FileNotFoundException {
        CalculateRPN calculateRPN = new CalculateRPN();
        return calculateRPN.calculate(createExpressionWithoutVariable(createMap()));
    }

    private Map<String, String> createMap() throws FileNotFoundException {
        String[] words;
        Map<String, String> map = new HashMap<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("is")) {
                words = line.split(" is ");
                map.put(words[0], words[1]);
            }
        }
        scanner.close();
        return map;
    }

    private String createExpressionWithoutVariable(Map<String, String> map) {
        StringBuilder expression = new StringBuilder(searchExpression(map));
        for (String key : map.keySet()
        ) {
            if (expression.indexOf(key) != -1) {
                expression.replace(expression.indexOf(key), expression.indexOf(key) + 1, map.get(key));
            }
        }
        return String.valueOf(expression);
    }

    private String searchExpression(Map<String, String> map) {
        return map.get("result");
    }
}