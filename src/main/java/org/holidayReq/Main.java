package org.holidayReq;

public class Main {

    public static void main(String[] args) {

        // Instantiate and go!
        App app = new App();

        app.run();
    }
}

// Things to add ->

// more tests
// Add more comments in your code


// overthinking - looking at interfaces and class inheritance to try and solve a problem.
// Problem - the scanner was skipping 'enter employee number'.
// The solution was to specie to scanner that the delimiter I want to use is a "\n".
// So all scanners used needed to .useDelimiter("\n").
// This caused a lot of duplication (a common code smell!).
// To reduce duplication, I created my own custom scanner which I could use everywhere 'customScanner'
// This ensured correct behaviour (no scanner input will be skipped over).
// Solved problem - there is no longer a collision between requests with the same dates. Requests are no longer retrieved by their dates and are instead got by their index (the user can see and select the one they want to edit!)
// Major confusing issue with writing to the file. Because scanner was used to read the file and can read from previous input, this was causing issues when reading from a file. There were moments where a file had only 2 entries but the tests were showing 5! I decided to switch to using a bufferReader rather than scanner to avoid this issue.

// TODO

// Add all your tests
// Add more comments where appropriate
// Add more OOP where appropriate - Done!
// Add inheritance in areas that would benefit from it - Done!
// Add a readme file
// You'll need to submit a self assessment doc

// 1 - 3 additional features - mocks (Mockito)
// self-directed code management (Github?) = Yes. Check!