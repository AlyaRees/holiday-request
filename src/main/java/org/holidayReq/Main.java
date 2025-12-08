package org.holidayReq;

public class Main {

    public static void main(String[] args) {

        App app = new App();

        app.run();
    }
}

// Things to add ->

// With duplication - add a base class to reduce duplication

// Add more comments in your code

// you can use some switches (where appropriate) to replace if-else

// you need to create base classes, use inheritance

// overthinking - looking at interfaces and class inheritance to try and solve a problem.
// Problem - the scanner was skipping 'enter employee number'.
// The solution was to specie to scanner that the delimiter I want to use is a "\n".
// So all scanners used needed to .useDelimiter("\n").
// This caused a lot of duplication (a common code smell!).
// To reduce duplication, I created my own custom scanner which I could use everywhere 'customScanner'
// This ensured correct behaviour (no scanner input will be skipped over).
// Solved problem - there is no longer a collision between requests with the same dates. Requests are no longer retrieved by their dates and are instead got by their index (the user can see and select the one they want to edit!)

// TODO

// Add all your tests
// Add more comments where appropriate
// Add more OOP where appropriate
// Add inheritance in areas that would benefit from it
// Add a readme file
// You'll need to submit a self assessment doc

// 1 - 3 additional features (what does that mean?) - mocks (Mockito)
// self-directed code management (Github?) = Yes. Check!