package org.holidayReq;

import java.util.Scanner;

class UserInteractions {

    protected Scanner customScanner = new Scanner(System.in).useDelimiter("\n");

    void closeScanner() {
        customScanner.close();
    }

    static void userPrompt(String message) {
        System.out.println(message);
    }
    int returnUserInputInt() {
        return customScanner.nextInt();
    }

    String returnUserInputStr() {
        return customScanner.next();
    }
}

// Use an interface when two different classes share some commonality but are otherwise different.

// Have I used enough 'abstract' and inheritance principles in my code? - No.