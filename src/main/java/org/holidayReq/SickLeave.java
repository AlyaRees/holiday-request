package org.holidayReq;

public class SickLeave extends Absence {

    protected String reason;
    protected String startDate;
    protected String endDate;

    public SickLeave(String fullName, String employeeNum, String startDate, String endDate, String reason) {
        super(fullName, employeeNum);
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
    }

     public String requestType() {
         return "Sickness - ";
     }

    public String getAdditionalInfo() {
        return startDate + " " + endDate + " " + reason;
    }
}
