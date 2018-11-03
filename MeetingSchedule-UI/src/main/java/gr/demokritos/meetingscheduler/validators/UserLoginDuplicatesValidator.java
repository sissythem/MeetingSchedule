package gr.demokritos.meetingscheduler.validators;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.beans.UserBean;
import gr.demokritos.meetingscheduler.business.dto.UserDto;

public class UserLoginDuplicatesValidator implements Validator<String> {

    private String errorMessage;
    private UserDto selectedAppUser;

    public UserLoginDuplicatesValidator(String errorMessage, UserDto selectedAppUser) {
        this.errorMessage = errorMessage;
        this.selectedAppUser = selectedAppUser;
    }

    @Override
    public ValidationResult apply(String value, ValueContext context) {
        UserBean appUserBean = MeetingUI.getMeetingUI().getUserBean();

        if (selectedAppUser == null) {
            if (appUserBean.emailExists(value)) {
                return ValidationResult.error(errorMessage);
            } else {
                return ValidationResult.ok();
            }
        } else {
            if (appUserBean.emailExists(value) &&
                    selectedAppUser.getId() != appUserBean.getUserByEmail(value).getId()) {
                return ValidationResult.error(errorMessage);
            } else {
                return ValidationResult.ok();
            }
        }
    }

}
