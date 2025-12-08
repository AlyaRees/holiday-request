package org.holidayReq;

public class HolidayRequest {
    private String fullName;
    private String employeeNum;
    private String startDate;
    private String endDate;

    public HolidayRequest(String fullName, String employeeNum, String startDate, String endDate) {
        this.fullName = fullName;
        this.employeeNum = employeeNum;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String fileContents() {
        return "Name: " + this.fullName + " Employee Number: " + this.employeeNum + " Date: " + this.startDate + " " + this.endDate + " - PENDING APPROVAL\n";
    }
}