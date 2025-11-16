import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n"); // Set delimiter to newline to capture full lines including spaces

        App.userPrompt("\nSelect (1) or (2)\n\n 1 - Book holiday\n 2 - Check holiday approval status\n");
        
        int chosenOption = scanner.nextInt();

        if (chosenOption == 1) {
            App.optionOneInteraction(scanner);
            } else {
            App.statusReport("\nHoliday approval status:\n");
            App.getRequestedHoliday();
        }
    }
}
