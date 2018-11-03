package gr.demokritos.meetingscheduler.windows;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.beans.UserBean;
import gr.demokritos.meetingscheduler.business.dto.UserDto;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;
import gr.demokritos.meetingscheduler.validators.PasswordValidator;

public class ChangePasswordWindow extends ParentWindow {
    private Image changePasswordIcon = new Image();
    private Label changePasswordLabel = new Label(MessagesUtils.PASSWORD_EXPIRED_MSG);
    private TextField username = new TextField(GeneralUtils.USERNAME);
    private PasswordField oldPasswordTf = new PasswordField(VaadinElementUtils.CURRENT_PASSWORD);
    private PasswordField newPasswordTf = new PasswordField(VaadinElementUtils.NEW_PASSWORD);
    private PasswordField repeatNewPasswordTf = new PasswordField(VaadinElementUtils.NEW_PASSWORD_REPEAT);
    private UserDto selectedUser;
    private final Binder<UserDto> binder = new Binder<>();
    private String changedPassword;
    private EnumUtils.PasswordChangeFrom passwordChangeFrom;

    public ChangePasswordWindow(String caption, UserDto selectedUser, EnumUtils.PasswordChangeFrom passwordChangeFrom) {
        super();
        this.setCaption(caption);
        this.selectedUser = selectedUser;
        this.passwordChangeFrom = passwordChangeFrom;
        initLayout();
    }

    private void initLayout() {
        formLayout.setSpacing(true);
        formLayout.setMargin(true);
        setupFormLayout();
        setUpWindow();
        getSaveBtn().setCaption(VaadinElementUtils.CHANGE_BUTTON);
        setWidth("300px");
        addValidation();
    }

    private void setupFormLayout() {
        VerticalLayout emptyLayout = new VerticalLayout();
        if (passwordChangeFrom.equals(EnumUtils.PasswordChangeFrom.LOGIN)) {
            updateWindowFromLogin(emptyLayout);
        }
        initFields(selectedUser);
        addFormComponents(emptyLayout);
    }

    private void updateWindowFromLogin(VerticalLayout emptyLayout) {
        changePasswordIcon.setIcon(new ThemeResource(GeneralUtils.INFO_ICON_PATH));
        changePasswordLabel.setWidth("190px");
        HorizontalLayout passwordChangeLayout = new HorizontalLayout(changePasswordIcon, changePasswordLabel);
        formLayout.addComponents(passwordChangeLayout, emptyLayout);
        formLayout.setComponentAlignment(passwordChangeLayout, Alignment.TOP_CENTER);
    }

    private void initFields(UserDto selectedUser) {
        username.setValue(selectedUser.getEmail());
        username.setReadOnly(true);
        if (passwordChangeFrom.equals(EnumUtils.PasswordChangeFrom.GRID)) {
            oldPasswordTf.setVisible(false);
        }
    }

    private void addFormComponents(VerticalLayout emptyLayout) {
        formLayout.addComponents(username, oldPasswordTf, newPasswordTf, repeatNewPasswordTf, emptyLayout);
        formLayout.setComponentAlignment(username, Alignment.TOP_CENTER);
        formLayout.setComponentAlignment(oldPasswordTf, Alignment.TOP_CENTER);
        formLayout.setComponentAlignment(newPasswordTf, Alignment.TOP_CENTER);
        formLayout.setComponentAlignment(repeatNewPasswordTf, Alignment.TOP_CENTER);
    }

    private void addValidation() {
        binder.forField(newPasswordTf).asRequired()
                .withValidator(
                        new PasswordValidator("Min password length: 4 characters", selectedUser, passwordChangeFrom))
                .bind(UserDto::getNewPassword, UserDto::setNewPassword);
        binder.forField(repeatNewPasswordTf).asRequired()
                .withValidator(
                        new PasswordValidator("Min password length: 4 characters", selectedUser, passwordChangeFrom))
                .bind(UserDto::getRepeatNewPassword, UserDto::setRepeatNewPassword);
    }

    public void show() {
        UI.getCurrent().addWindow(this);
    }

    @Override
    public void onSave() {
        changePassword();
    }

    private boolean changePassword() {
        try {
            binder.writeBean(selectedUser);
            if (!passwordChangeFrom.equals(EnumUtils.PasswordChangeFrom.GRID)) {
                if (oldPasswordTf.getValue().equals(selectedUser.getPassword())) {
                    return checkNewPassword();
                } else {
                    Message.show(MessagesUtils.ERROR, MessagesUtils.CURRENT_PASSWORD_WRONG_MSG,
                            EnumUtils.MessageType.ERROR);
                    return false;
                }
            } else {
                return checkNewPassword();
            }

        } catch (ValidationException e) {
            Message.show(MessagesUtils.ERROR, MessagesUtils.VALIDATION_MESSAGE, EnumUtils.MessageType.ERROR);
            return false;
        }
    }

    private boolean checkNewPassword() {
        if (newPasswordTf.getValue().equals(repeatNewPasswordTf.getValue())) {
            setChangedPassword(newPasswordTf.getValue());
            this.close();
            if (!passwordChangeFrom.equals(EnumUtils.PasswordChangeFrom.GRID)) {
                MeetingUI.getMeetingUI().checkForComponent();
            }
            return true;
        } else {
            Message.show(MessagesUtils.ERROR,MessagesUtils.NEW_PASSWORD_MISMATCH_MSG, EnumUtils.MessageType.ERROR);
            return false;
        }
    }

    public VerticalLayout getWindowLayout() {
        return formLayout;
    }

    public void setWindowLayout(VerticalLayout windowLayout) {
        this.formLayout = windowLayout;
    }

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public PasswordField getOldPasswordTf() {
        return oldPasswordTf;
    }

    public void setOldPasswordTf(PasswordField oldPasswordTf) {
        this.oldPasswordTf = oldPasswordTf;
    }

    public PasswordField getNewPasswordTf() {
        return newPasswordTf;
    }

    public void setNewPasswordTf(PasswordField newPasswordTf) {
        this.newPasswordTf = newPasswordTf;
    }

    public PasswordField getRepeatNewPasswordTf() {
        return repeatNewPasswordTf;
    }

    public void setRepeatNewPasswordTf(PasswordField repeatNewPasswordTf) {
        this.repeatNewPasswordTf = repeatNewPasswordTf;
    }

    public String getChangedPassword() {
        return changedPassword;
    }

    public void setChangedPassword(String newPassword) {
        this.changedPassword = newPassword;
        if (passwordChangeFrom != EnumUtils.PasswordChangeFrom.GRID) {
            UserBean userBean = MeetingUI.getMeetingUI().getUserBean();
            UserDto appUserDto = userBean.getUserByEmail(selectedUser.getEmail());
            appUserDto.setPassword(changedPassword);
            userBean.updateUser(appUserDto);
        }
    }

}
