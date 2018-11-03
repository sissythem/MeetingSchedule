package gr.demokritos.meetingscheduler.validators;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;
import gr.demokritos.meetingscheduler.business.dto.UserDto;
import gr.demokritos.meetingscheduler.utils.EnumUtils;

public class PasswordValidator implements Validator<String> {

    private String errorMessage;
    private UserDto appUserDto;
    private EnumUtils.PasswordChangeFrom passwordChangeFrom;
    private ValidationResult validationResult = ValidationResult.ok();

    public PasswordValidator(String errorMessage, UserDto appUserDto, EnumUtils.PasswordChangeFrom passwordChangeFrom) {
        this.errorMessage = errorMessage;
        this.appUserDto = appUserDto;
        this.passwordChangeFrom = passwordChangeFrom;
    }

    @Override
    public ValidationResult apply(String value, ValueContext context) {
        if (value.length() < 4) {
            return  ValidationResult.error(errorMessage);
        } else {
            return ValidationResult.ok();
        }

    }

}
