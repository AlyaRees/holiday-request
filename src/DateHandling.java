import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateHandling {

    UserInteractions userInteractions = new UserInteractions();

    String checkAndUpdateDate(String message, String date) {
        while (!isValidFormat(date)) {
            askForDateAgain(message);
            date = userInteractions.getUserInputString();
        }
        return date;
    }

    void askForDateAgain(String message) {
        userInteractions.userPrompt("\nInvalid format. Try again.");
        userInteractions.userPrompt(message);
    }

    boolean isValidFormat(String date) {
        Pattern dateFormat = Pattern.compile("^\\d{2}\\/\\d{2}\\/\\d{4}$");
        Matcher checkFormat = dateFormat.matcher(date);

        return checkFormat.matches();
    }
}
