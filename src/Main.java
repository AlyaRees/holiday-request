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
