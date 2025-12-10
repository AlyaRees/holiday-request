package org.holidayReq;

public abstract class Absence {
    private String fullName;
    private String employeeNum;

    public Absence(String fullName, String employeeNum) {
        this.fullName = fullName;
        this.employeeNum = employeeNum;
    }

    public abstract String requestType();

    public abstract String getAdditionalInfo();

    public String fileContents() {
        return "Request: " + requestType() + "Name: " + this.fullName + " Employee Number: " + this.employeeNum + " Date: " + getAdditionalInfo() + " - PENDING APPROVAL\n";
    }
}

// sick leave

// lateness for work

// holiday

