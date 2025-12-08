package org.holidayReq;

public class HolidayInteractions {

    UserInteractions userInteractions = new UserInteractions();

    interface AdminReviewStatus {
        String approved = "- APPROVED";
        String declined = "- DECLINED";
    }

    String approveOrDecline(int userInput) {
        String adminResponse = "";
        switch (userInput) {
            case 1 -> adminResponse = AdminReviewStatus.approved;
            case 2 -> adminResponse = AdminReviewStatus.declined;
            default -> {
                App.display("\nInvalid input. Please try again.\n");
            }
        }
        return adminResponse;
    }
}
