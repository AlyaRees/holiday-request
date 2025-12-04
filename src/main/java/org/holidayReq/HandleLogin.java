package org.holidayReq;

public class HandleLogin {
    UserInteractions userInteractions = new UserInteractions();

    void adminLogin() {
        userInteractions.userPrompt("\nEnter admin password: \n");
        String enteredPassword = userInteractions.returnUserInputStr();
        String password = "password";

        while (!enteredPassword.equals(password)) {
            App.statusReport("\nIncorrect password entered.\n");
            enteredPassword = userInteractions.returnUserInputStr();
        }
        App.statusReport("\nLogin successful.");
    }
}
