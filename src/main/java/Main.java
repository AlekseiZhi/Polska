public class Main {
    public static void main(String[] args) throws Exception {

//        ConsoleUserInterface userInterface = new ConsoleUserInterface();
//        userInterface.userConsoleInterface();

        FileInterface fileInterface = new FileInterface();
        System.out.println(fileInterface.calculateExpression());
    }
}