package org.holidayReq;

public class Lateness extends Absence {

    protected double hoursLate;
    protected String reason;
    protected String date;

    public Lateness(String fullName, String employeeNum, String date, double hoursLate, String reason) {
        super(fullName, employeeNum);
        this.reason = reason;
        this.hoursLate = hoursLate;
        this.date = date;
    }

    public String requestType() {
        return "Lateness - ";
    }

    public String getAdditionalInfo() {
        return this.date + " Hours: " + this.hoursLate + " Reason: " + this.reason;
    }
}
