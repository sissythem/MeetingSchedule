package gr.demokritos.meetingscheduler.forms;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.dto.UserDto;
import gr.demokritos.meetingscheduler.panels.LoginPanel;
import gr.demokritos.meetingscheduler.utils.CssUtils;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;
import gr.demokritos.meetingscheduler.windows.Message;
import org.apache.commons.lang3.StringUtils;

public class LoginForm extends VerticalLayout {
    private final Binder<UserDto> binder = new Binder<>();
    private LoginPanel loginPanel;
    private Label signinLabel = new Label(GeneralUtils.SIGN_IN);
    private TextField email = new TextField("");
    private PasswordField password = new PasswordField("");
    private Button loginBtn = new Button(StringUtils.stripAccents(GeneralUtils.SIGN_IN));

    public LoginForm(LoginPanel loginPanel) {
        this.loginPanel = loginPanel;
        setMargin(true);
        initComponents(loginPanel);
    }

    private void initComponents(LoginPanel loginPanel) {
        this.loginPanel = loginPanel;
        initForm();
        addComponents(signinLabel, email, password, loginBtn);
        customizeLayout();
    }

    private void initForm() {
        signinLabel.setStyleName(CssUtils.SCSS_LOGIN_FORM_TEXT);
        customizeLoginButton();
        initTextFields();
    }

    private void initTextFields() {
        this.email.setPlaceholder(GeneralUtils.USERNAME);
        this.password.setPlaceholder(GeneralUtils.PASSWORD);
        email.addFocusListener(e -> {
            email.setPlaceholder("");
            email.setCaption(GeneralUtils.USERNAME);
            email.setRequiredIndicatorVisible(true);
        });
        email.addBlurListener(e -> {
            if (StringUtils.isBlank(email.getValue())) {
                email.setPlaceholder(GeneralUtils.USERNAME);
                email.setCaption("");
                email.setRequiredIndicatorVisible(false);
            }
        });

        password.addFocusListener(e -> {
            password.setPlaceholder("");
            password.setCaption(GeneralUtils.PASSWORD);
            password.setRequiredIndicatorVisible(true);
        });
        password.addBlurListener(e -> {
            if (StringUtils.isBlank(password.getValue())) {
                password.setPlaceholder(GeneralUtils.PASSWORD);
                password.setCaption("");
                password.setRequiredIndicatorVisible(false);
            }
        });

        addValidation();
        removeRequiredIndicators();
    }

    private void customizeLoginButton() {
        loginBtn.setWidth(190, Unit.PIXELS);
        loginBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        loginBtn.setStyleName(CssUtils.SCSS_LOGIN);
        loginBtn.addClickListener(event -> onLoginClick(email, password));
    }

    private void customizeLayout() {
        setComponentAlignment(signinLabel, Alignment.TOP_CENTER);
        setComponentAlignment(email, Alignment.MIDDLE_CENTER);
        setComponentAlignment(password, Alignment.MIDDLE_CENTER);
        setComponentAlignment(loginBtn, Alignment.BOTTOM_CENTER);
    }

    private void addValidation() {
        binder.forField(email).asRequired(MessagesUtils.EMAIL_MANDATORY)
                .withValidator(new EmailValidator(MessagesUtils.INVALID_EMAIL))
                .bind(UserDto::getEmail, UserDto::setEmail);
        binder.forField(password).asRequired(MessagesUtils.PASSWORD_MANDATORY).bind(UserDto::getPassword, UserDto::setPassword);
    }

    private void removeRequiredIndicators() {
        email.setRequiredIndicatorVisible(false);
        password.setRequiredIndicatorVisible(false);
    }

    public void onLoginClick(TextField email, PasswordField password) {
        UserDto appUserDto = new UserDto(email.getValue(), password.getValue());
        try {
            binder.writeBean(appUserDto);
            checkLoginResponse(email.getValue(), password.getValue());
        } catch (ValidationException e) {
            Message.show(MessagesUtils.ERROR, MessagesUtils.LOGIN_ERROR_MSG, EnumUtils.MessageType.ERROR);
        }
    }

    private void checkLoginResponse(String email, String password) {
        UserDto appUserDto = MeetingUI.getMeetingUI().getUserBean().isValidLogin(email, password);
        VaadinSession.getCurrent().setAttribute(GeneralUtils.SESSION_USER, appUserDto);
        MeetingUI.getMeetingUI().checkForComponent();
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public void setLoginPanel(LoginPanel loginPanel) {
        this.loginPanel = loginPanel;
    }

    public Label getSigninLabel() {
        return signinLabel;
    }

    public void setSigninLabel(Label signinLabel) {
        this.signinLabel = signinLabel;
    }

    public TextField getEmail() {
        return email;
    }

    public void setEmail(TextField email) {
        this.email = email;
    }

    public PasswordField getPassword() {
        return password;
    }

    public void setPassword(PasswordField password) {
        this.password = password;
    }

    public Button getLoginBtn() {
        return loginBtn;
    }

    public void setLoginBtn(Button loginBtn) {
        this.loginBtn = loginBtn;
    }

}
