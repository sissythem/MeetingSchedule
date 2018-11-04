package gr.demokritos.meetingscheduler.windows;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.dto.UserDto;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;
import gr.demokritos.meetingscheduler.validators.EmailDuplicatesValidator;
import org.apache.commons.lang3.StringUtils;

public class ProfileWindow extends ParentWindow {
    private UserDto loggedInUser;
    private TextField userFirstNameTf = new TextField("");
    private TextField userLastNameTf = new TextField("");
    private TextField emailTf = new TextField();
    private final Binder<UserDto> binder = new Binder<>();
    private Button changePasswordBtn = new Button(VaadinElementUtils.CHANGE_PASSWORD_BUTTON);
    private ChangePasswordWindow changePasswordWindow;

    public ProfileWindow(UserDto appUserDto, String windowCaption) {
        super();
        this.setCaption(windowCaption);
        this.loggedInUser = appUserDto;
        this.formLayout = new VerticalLayout();
        fillForm();
        addValidation();
        setUpWindow();
    }

    public void fillForm() {
        userFirstNameTf.setValue(loggedInUser.getName());
        userLastNameTf.setValue(loggedInUser.getLastName());
        emailTf.setValue(loggedInUser.getEmail());
        userFirstNameTf.setCaption(VaadinElementUtils.NAME);
        userLastNameTf.setCaption(VaadinElementUtils.LAST_NAME);
        emailTf.setCaption(VaadinElementUtils.EMAIL);
        setupChangePasswordButton(loggedInUser);
        formLayout.addComponent(setUpFormLayout());
    }

    private void setupChangePasswordButton(UserDto selectedAppUser) {
        changePasswordBtn.setStyleName(MaterialTheme.BUTTON_LINK);
        changePasswordBtn.addStyleName("v-button.link");
        changePasswordBtn.addClickListener(event -> {
            changePasswordWindow = new ChangePasswordWindow(VaadinElementUtils.CHANGE_PASSWORD_BUTTON, selectedAppUser, EnumUtils.PasswordChangeFrom.PROFILE);
            changePasswordWindow.show();
        });
    }

    private void addValidation() {
        binder.forField(userFirstNameTf).asRequired(MessagesUtils.MANDATORY_FIELDS).bind(UserDto::getName, UserDto::setName);
        binder.forField(userLastNameTf).asRequired(MessagesUtils.MANDATORY_FIELDS).bind(UserDto::getLastName, UserDto::setLastName);
        binder.forField(emailTf).asRequired(MessagesUtils.USERNAME_MANDATORY).withValidator(new EmailValidator(MessagesUtils.INVALID_EMAIL))
                .withValidator(new EmailDuplicatesValidator(MessagesUtils.DUPLICATE_EMAIL, loggedInUser)).bind(UserDto::getUsername, UserDto::setUsername);
    }

    private VerticalLayout setUpFormLayout() {
        VerticalLayout formLayout = new VerticalLayout();
        formLayout.addComponents(userFirstNameTf, userLastNameTf, emailTf, changePasswordBtn);
        return formLayout;
    }

    @Override
    public void onSave() {
        try {
            binder.writeBean(loggedInUser);
            updateUserFields();
            MeetingUI.getMeetingUI().getUserBean().updateUser(loggedInUser);
            this.close();
        } catch (
                ValidationException e) {
            Message.show(MessagesUtils.ERROR, MessagesUtils.PERSISTENCE_MESSAGE, EnumUtils.MessageType.ERROR);
        }
    }

    private void updateUserFields() {
        loggedInUser.setName(userFirstNameTf.getValue());
        loggedInUser.setLastName(userLastNameTf.getValue());
        loggedInUser.setEmail(emailTf.getValue());
        updateUserPassword();
    }

    private void updateUserPassword() {
        if (changePasswordWindow != null && !StringUtils.isBlank(changePasswordWindow.getChangedPassword())) {
            loggedInUser.setPassword(changePasswordWindow.getChangedPassword());
        }
    }

}
