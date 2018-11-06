package gr.demokritos.meetingscheduler.windows;

import com.sun.mail.smtp.SMTPTransport;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.dto.MeetingDto;
import gr.demokritos.meetingscheduler.business.utils.LoggerFactory;
import gr.demokritos.meetingscheduler.forms.EmailForm;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;
import org.apache.commons.lang3.StringUtils;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class EmailWindow extends ParentWindow {

    private MeetingDto selectedMeeting;

    public EmailWindow(String caption, MeetingDto selectedMeeting) {
        super();
        this.setCaption(caption);
        this.selectedMeeting = selectedMeeting;
        setUpFormLayout();
        setUpWindow();
        getSaveBtn().setCaption(VaadinElementUtils.SEND_EMAIL);
        getCancelBtn().setCaption(VaadinElementUtils.CANCEL_BUTTON);
    }

    private void setUpFormLayout() {
        formLayout = new EmailForm(selectedMeeting);
    }

    @Override
    public void onSave() {
        try {
            EmailForm form = (EmailForm) formLayout;
            if (form.getBinder().isValid() && areFieldsValid(form)) {
                selectedMeeting.setPossibleMeetingDtos(new ArrayList<>());
                MeetingUI.getMeetingUI().getMeetingBean().updateMeeting(selectedMeeting);
                sendEmail(form);
                this.close();
            } else {
                Message.show(MessagesUtils.ERROR, "Please complete all fields", EnumUtils.MessageType.ERROR);
            }
        } catch (Exception e) {
            Message.show(MessagesUtils.ERROR, "Please complete all fields and use a Gmail account", EnumUtils.MessageType.ERROR);
        }
    }

    private void sendEmail(EmailForm form) throws MessagingException, IOException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");

        /*
        If set to false, the QUIT command is sent and the connection is immediately closed. If set
        to true (the default), causes the transport to wait for the response to the QUIT command.
        ref :   http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
                http://forum.java.sun.com/thread.jspa?threadID=5205249
                smtpsend.java - demo program from javamail
        */
        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, null);

        // -- Create a new message --
        final MimeMessage msg = new MimeMessage(session);

        // -- Set the FROM and TO fields --
        msg.setFrom(new InternetAddress(form.getFromEmail().getValue()));
        msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(form.getEmailList().getValue(), false));

        msg.setSubject(form.getTheme().getValue());
        msg.setText(form.getMessage().getValue(), "utf-8");
        msg.setSentDate(new Date());

        SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

        t.connect("smtp.gmail.com", form.getFromEmail().getValue(), form.getPasswordField().getValue());
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
    }

    private boolean areFieldsValid(EmailForm form) {
        return !StringUtils.isBlank(form.getFromEmail().getValue())
                && !StringUtils.isBlank(form.getEmailList().getValue())
                && !StringUtils.isBlank(form.getTheme().getValue())
                && !StringUtils.isBlank(form.getMessage().getValue())
                && !StringUtils.isBlank(form.getPasswordField().getValue())
                && form.getFromEmail().getValue().contains("@gmail.com");
    }

    public MeetingDto getSelectedMeeting() {
        return selectedMeeting;
    }

    public void setSelectedMeeting(MeetingDto selectedMeeting) {
        this.selectedMeeting = selectedMeeting;
    }
}
