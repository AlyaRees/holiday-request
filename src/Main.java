public class Main {

    public static void main(String[] args) {

        App app = new App();
        UserInteractions userInteractions = new UserInteractions();

        UserInteractions.userPrompt("\nSelect (1) or (2)\n\n 1 - Book holiday\n 2 - Check holiday approval status\n");

        if (userInteractions.getUserInputInt() == 1) {
            app.optionOneInteraction();
        } else {
            app.statusReport("\nHoliday approval status:\n");
            app.getRequestedHoliday();
        }
    }
}

// Things to add ->

// With duplication - add a base class to reduce duplication

// in the main method - instantiate and 'go'

// you can use some switches (where appropriate) to replace if-else

// you need to create base classes, use inheritance

// further down the line maybe mock the scanner ?

// overthinking - looking at interfaces and class inheritance to try and solve a problem.
// Problem - the scanner was skipping 'enter employee number'.
// The solution was to specie to scanner that the delimiter I want to use is a "\n".
// So all scanners used needed to .useDelimiter("\n").
// This caused a lot of duplication (a common code smell!).
// To reduce duplication, I created my own custom scanner which I could use everywhere 'customScanner'
// This ensured correct behaviour (no scanner input will be skipped over).