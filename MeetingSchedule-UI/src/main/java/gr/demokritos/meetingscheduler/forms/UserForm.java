package gr.demokritos.meetingscheduler.forms;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.*;
import gr.demokritos.meetingscheduler.business.dto.UserDto;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;
import gr.demokritos.meetingscheduler.validators.PasswordValidator;
import gr.demokritos.meetingscheduler.validators.EmailDuplicatesValidator;
import gr.demokritos.meetingscheduler.validators.UsernameDuplicatesValidator;
import gr.demokritos.meetingscheduler.windows.ChangePasswordWindow;
import org.apache.commons.lang3.StringUtils;

public class UserForm extends VerticalLayout {
    private TextField userFirstNameTf = new TextField("");
    private TextField userLastNameTf = new TextField("");
    private TextField userNameTf = new TextField("");
    private TextField emailTf = new TextField("");
    private PasswordField userPasswordTf = new PasswordField("");
    private Button changePasswordBtn = new Button(VaadinElementUtils.CHANGE_PASSWORD_BUTTON);
    private final Binder<UserDto> binder = new Binder<>();
    private EnumUtils.Action action;
    private ChangePasswordWindow changePasswordWindow;

    public UserForm(EnumUtils.Action action) {
        this.action = action;
        addPlaceHolders();
        if (action.equals(EnumUtils.Action.ADD)) {
            addValidation(null);
        }
        removeRequiredIndicators();
        addComponent(setUpFormLayout());
    }

    public void fillForm(UserDto selectedAppUser) {
        userFirstNameTf.setValue(selectedAppUser.getName());
        userLastNameTf.setValue(selectedAppUser.getLastName());
        userNameTf.setValue(selectedAppUser.getUsername());
        emailTf.setValue(selectedAppUser.getEmail());
        userFirstNameTf.setCaption(VaadinElementUtils.NAME);
        userLastNameTf.setCaption(VaadinElementUtils.LAST_NAME);
        userNameTf.setCaption(VaadinElementUtils.USER_NAME);
        emailTf.setCaption(VaadinElementUtils.EMAIL);
        if (action.equals(EnumUtils.Action.EDIT)) {
            setupChangePasswordButton(selectedAppUser);
        }
        removeAllComponents();
        addComponent(setUpFormLayout());
    }

    public void setReadOnly() {
        userFirstNameTf.setReadOnly(true);
        userLastNameTf.setReadOnly(true);
        userNameTf.setReadOnly(true);
        emailTf.setReadOnly(true);
    }

    private void setupChangePasswordButton(UserDto selectedAppUser) {
        changePasswordBtn.setStyleName(MaterialTheme.BUTTON_LINK);
        changePasswordBtn.addStyleName("v-button.link");
        changePasswordBtn.addClickListener(event -> {
            changePasswordWindow = new ChangePasswordWindow(VaadinElementUtils.CHANGE_PASSWORD_BUTTON, selectedAppUser, EnumUtils.PasswordChangeFrom.GRID);
            changePasswordWindow.show();
        });
    }

    private void removeRequiredIndicators() {
        userFirstNameTf.setRequiredIndicatorVisible(false);
        userLastNameTf.setRequiredIndicatorVisible(false);
        userNameTf.setRequiredIndicatorVisible(false);
        userPasswordTf.setRequiredIndicatorVisible(false);
        emailTf.setRequiredIndicatorVisible(false);
    }

    private void addPlaceHolders() {
        userFirstNameTf.setPlaceholder(VaadinElementUtils.NAME);
        userLastNameTf.setPlaceholder(VaadinElementUtils.LAST_NAME);
        userNameTf.setPlaceholder(VaadinElementUtils.USER_NAME);
        userPasswordTf.setPlaceholder(GeneralUtils.PASSWORD);
        emailTf.setPlaceholder(VaadinElementUtils.EMAIL);
        userFirstNameTf.addBlurListener(event -> {
            if (StringUtils.isBlank(userFirstNameTf.getValue())) {
                userFirstNameTf.setPlaceholder(VaadinElementUtils.NAME);
                userFirstNameTf.setCaption("");
                userFirstNameTf.setRequiredIndicatorVisible(false);
            }
        });

        userFirstNameTf.addFocusListener(event -> {
            userFirstNameTf.setPlaceholder("");
            userFirstNameTf.setCaption(VaadinElementUtils.NAME);
            userFirstNameTf.setRequiredIndicatorVisible(true);
        });

        userLastNameTf.addBlurListener(event -> {
            if (StringUtils.isBlank(userLastNameTf.getValue())) {
                userLastNameTf.setPlaceholder(VaadinElementUtils.LAST_NAME);
                userLastNameTf.setCaption("");
                userLastNameTf.setRequiredIndicatorVisible(false);
            }
        });

        userLastNameTf.addFocusListener(event -> {
            userLastNameTf.setPlaceholder("");
            userLastNameTf.setCaption(VaadinElementUtils.LAST_NAME);
            userLastNameTf.setRequiredIndicatorVisible(true);
        });

        userNameTf.addBlurListener(event -> {
            if (StringUtils.isBlank(userNameTf.getValue())) {
                userNameTf.setPlaceholder(VaadinElementUtils.USER_NAME);
                userNameTf.setCaption("");
                userNameTf.setRequiredIndicatorVisible(false);
            }
        });

        userNameTf.addFocusListener(event -> {
            userNameTf.setPlaceholder("");
            userNameTf.setCaption(VaadinElementUtils.USER_NAME);
            userNameTf.setRequiredIndicatorVisible(true);
        });

        emailTf.addBlurListener(event -> {
            if (StringUtils.isBlank(emailTf.getValue())) {
                emailTf.setPlaceholder(VaadinElementUtils.EMAIL);
                emailTf.setCaption("");
                emailTf.setRequiredIndicatorVisible(false);
            }
        });

        emailTf.addFocusListener(event -> {
            emailTf.setPlaceholder("");
            emailTf.setCaption(VaadinElementUtils.EMAIL);
            emailTf.setRequiredIndicatorVisible(true);
        });

        if (!action.equals(EnumUtils.Action.VIEW)) {

            userPasswordTf.addBlurListener(event -> {
                if (StringUtils.isBlank(userPasswordTf.getValue())) {
                    userPasswordTf.setPlaceholder(GeneralUtils.PASSWORD);
                    userPasswordTf.setCaption("");
                    userPasswordTf.setRequiredIndicatorVisible(false);
                }
            });

            userPasswordTf.addFocusListener(event -> {
                userPasswordTf.setPlaceholder("");
                userPasswordTf.setCaption(GeneralUtils.PASSWORD);
                userPasswordTf.setRequiredIndicatorVisible(true);
            });

        }
    }

    public void addValidation(UserDto selectedUser) {
        binder.forField(userFirstNameTf).asRequired(MessagesUtils.MANDATORY_FIELDS)
                .bind(UserDto::getName, UserDto::setName);
        binder.forField(userLastNameTf).asRequired(MessagesUtils.MANDATORY_FIELDS)
                .bind(UserDto::getLastName, UserDto::setLastName);
        binder.forField(userNameTf).asRequired(MessagesUtils.MANDATORY_FIELDS)
                .withValidator(new UsernameDuplicatesValidator(MessagesUtils.DUPLICATE_USERNAME, selectedUser))
                .bind(UserDto::getUsername, UserDto::setUsername);
        binder.forField(emailTf).asRequired(MessagesUtils.MANDATORY_FIELDS)
                .withValidator(new EmailValidator(MessagesUtils.INVALID_EMAIL))
                .withValidator(new EmailDuplicatesValidator(MessagesUtils.DUPLICATE_EMAIL, selectedUser))
                .bind(UserDto::getEmail, UserDto::setEmail);
        if (action.equals(EnumUtils.Action.ADD)) {
            binder.forField(userPasswordTf).asRequired(MessagesUtils.PASSWORD_MANDATORY)
                    .withValidator(new PasswordValidator("Min password length: 4 characters", null, EnumUtils.PasswordChangeFrom.GRID))
                    .bind(UserDto::getPassword, UserDto::setPassword);
        }
    }

    private VerticalLayout setUpFormLayout() {
        VerticalLayout formLayout = new VerticalLayout();
        HorizontalLayout textFieldsLayout = new HorizontalLayout();
        VerticalLayout leftFormFields = new VerticalLayout();
        VerticalLayout rightFormFields = new VerticalLayout();

        if (!action.equals(EnumUtils.Action.ADD)) {
            userPasswordTf.setVisible(false);
        }

        VerticalLayout changePasswordBtnLayout = new VerticalLayout(changePasswordBtn);
        if (!action.equals(EnumUtils.Action.EDIT)) { // change password is only visible on edit action
            changePasswordBtnLayout.setVisible(false);
        }

        leftFormFields.addComponents(userFirstNameTf,userLastNameTf, emailTf);
        rightFormFields.addComponents(userNameTf, changePasswordBtnLayout, userPasswordTf);
        textFieldsLayout.addComponents(leftFormFields, rightFormFields);
        formLayout.addComponent(textFieldsLayout);
        return formLayout;
    }

    public Binder<UserDto> getBinder() {
        return binder;
    }

    public TextField getUserFirstNameTf() {
        return userFirstNameTf;
    }

    public void setUserFirstNameTf(TextField userFirstNameTf) {
        this.userFirstNameTf = userFirstNameTf;
    }

    public TextField getUserLastNameTf() {
        return userLastNameTf;
    }

    public void setUserLastNameTf(TextField userLastNameTf) {
        this.userLastNameTf = userLastNameTf;
    }

    public TextField getUserNameTf() {
        return userNameTf;
    }

    public void setUserNameTf(TextField userNameTf) {
        this.userNameTf = userNameTf;
    }

    public TextField getEmailTf() {
        return emailTf;
    }

    public void setEmailTf(TextField emailTf) {
        this.emailTf = emailTf;
    }

    public PasswordField getUserPasswordTf() {
        return userPasswordTf;
    }

    public void setUserPasswordTf(PasswordField passwordTf) {
        this.userPasswordTf = passwordTf;
    }

    public ChangePasswordWindow getChangePasswordWindow() {
        return changePasswordWindow;
    }

    public void setChangePasswordWindow(ChangePasswordWindow changePasswordWindow) {
        this.changePasswordWindow = changePasswordWindow;
    }

}
