package org.holidayReq;

public class Holiday extends Absence {

    protected String startDate;
    protected String endDate;

    public Holiday(String fullName, String employeeNum, String startDate, String endDate) {
        super(fullName, employeeNum);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String requestType() {
        return "Holiday - ";
    }

    public String getAdditionalInfo() {
        return this.startDate + " " + this.endDate;
    }
}
