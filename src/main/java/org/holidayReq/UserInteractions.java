package org.holidayReq;

import java.util.Scanner;

public class UserInteractions {

    public Scanner customScanner;

    public UserInteractions(Scanner customScanner) {
        this.customScanner = customScanner;
    }

    void closeScanner() {
        customScanner.close();
    }

    public void userPrompt(String message) {
        System.out.println(message);
    }

    int getUserInputInt() {
        return customScanner.nextInt();
    }

    public String getUserInputStr() {
        return customScanner.next();
    }
}

// Use an interface when two different classes share some commonality but are otherwise different.

// Have I used enough 'abstract' and inheritance principles in my code? - No.