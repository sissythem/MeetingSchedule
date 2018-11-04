package gr.demokritos.meetingscheduler.validators;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.beans.UserBean;
import gr.demokritos.meetingscheduler.business.dto.UserDto;

public class UsernameDuplicatesValidator implements Validator<String> {
    private String errorMessage;
    private UserDto selectedUser;

    public UsernameDuplicatesValidator(String errorMessage, UserDto selectedUser) {
        this.errorMessage = errorMessage;
        this.selectedUser = selectedUser;
    }

    @Override
    public ValidationResult apply(String value, ValueContext context) {
        UserBean userBean = MeetingUI.getMeetingUI().getUserBean();

        if (selectedUser == null) {
            if (userBean.usernameExists(value)) {
                return ValidationResult.error(errorMessage);
            } else {
                return ValidationResult.ok();
            }
        } else {
            if (userBean.usernameExists(value) &&
                    selectedUser.getId() != userBean.getUserByUsername(value).getId()) {
                return ValidationResult.error(errorMessage);
            } else {
                return ValidationResult.ok();
            }
        }
    }
}
