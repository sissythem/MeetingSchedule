package gr.demokritos.meetingscheduler.forms;

import com.vaadin.data.Binder;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import gr.demokritos.meetingscheduler.business.dto.MeetingDto;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;

import java.time.format.DateTimeFormatter;

public class EmailForm extends VerticalLayout {
    private MeetingDto selectedMeeting;
    private TextField fromEmail = new TextField(VaadinElementUtils.FROM_EMAIL);
    private TextField emailList = new TextField(VaadinElementUtils.EMAIL_LIST);
    private TextField theme = new TextField(VaadinElementUtils.EMAIL_THEME);
    private TextArea message = new TextArea(VaadinElementUtils.EMAIL_BODY);
    private PasswordField passwordField = new PasswordField(VaadinElementUtils.PASSWORD);
    private final Binder<String> binder = new Binder<>();

    public EmailForm(MeetingDto selectedMeeting) {
        super();
        this.selectedMeeting = selectedMeeting;
        fillForm();
        addValidation();
        this.addComponents(fromEmail,emailList, theme, message, passwordField);
    }

    private void fillForm() {
        fromEmail.setPlaceholder(VaadinElementUtils.FROM_EMAIL);
        emailList.setPlaceholder(VaadinElementUtils.EMAIL_LIST);
        theme.setPlaceholder(VaadinElementUtils.EMAIL_THEME);
        message.setPlaceholder(VaadinElementUtils.EMAIL_BODY);
        passwordField.setPlaceholder(VaadinElementUtils.PASSWORD);
        emailList.setWidth("90%");
        fromEmail.setWidth("90%");
        theme.setWidth("90%");
        message.setSizeFull();
        passwordField.setWidth("90%");
        StringBuilder sb = new StringBuilder();
        selectedMeeting.getCanAttendList().forEach(memberDto -> sb.append(memberDto.getEmail()).append(";"));
        emailList.setValue(sb.toString());
        theme.setValue(selectedMeeting.getName() + " meeting arrangement");
        if (isValidMeeting()) {
            message.setValue("Are you available on " + selectedMeeting.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " and time " +
                    selectedMeeting.getStartTime().format(DateTimeFormatter.ofPattern(GeneralUtils.TIME24HFORMAT)) + "-" +
                    selectedMeeting.getEndTime().format(DateTimeFormatter.ofPattern(GeneralUtils.TIME24HFORMAT)) + " to arrange a meeting?");
        }
    }

    private boolean isValidMeeting() {
        return selectedMeeting.getDate() != null && selectedMeeting.getStartTime() != null && selectedMeeting.getEndTime() != null;
    }

    private void addValidation() {
        binder.forField(fromEmail).asRequired(MessagesUtils.MANDATORY_FIELDS);
        binder.forField(emailList).asRequired(MessagesUtils.MANDATORY_FIELDS);
        binder.forField(theme).asRequired(MessagesUtils.MANDATORY_FIELDS);
        binder.forField(message).asRequired(MessagesUtils.MANDATORY_FIELDS);
        binder.forField(passwordField).asRequired(MessagesUtils.MANDATORY_FIELDS);
    }

    public Binder<String> getBinder() {
        return binder;
    }

    public MeetingDto getSelectedMeeting() {
        return selectedMeeting;
    }

    public void setSelectedMeeting(MeetingDto selectedMeeting) {
        this.selectedMeeting = selectedMeeting;
    }

    public TextField getEmailList() {
        return emailList;
    }

    public void setEmailList(TextField emailList) {
        this.emailList = emailList;
    }

    public TextField getTheme() {
        return theme;
    }

    public void setTheme(TextField theme) {
        this.theme = theme;
    }

    public TextArea getMessage() {
        return message;
    }

    public void setMessage(TextArea message) {
        this.message = message;
    }

    public TextField getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(TextField fromEmail) {
        this.fromEmail = fromEmail;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

}
