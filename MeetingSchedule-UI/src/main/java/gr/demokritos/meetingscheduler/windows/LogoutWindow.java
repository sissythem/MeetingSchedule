package gr.demokritos.meetingscheduler.windows;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;

public class LogoutWindow extends ParentWindow {
    private static final ThemeResource WARNING_ICON = new ThemeResource(GeneralUtils.WARN_ICON_PATH);

    public LogoutWindow(String windowCaption) {
        super();
        this.setCaption(windowCaption);
        this.formLayout = new VerticalLayout();
        setUpLayout();
        setUpWindow();
    }

    private void setUpLayout() {
        HorizontalLayout hl = new HorizontalLayout();
        Image icon = new Image();
        icon.setIcon(WARNING_ICON);
        Label label = new Label(MessagesUtils.LOGOUT_MESSAGE);
        hl.addComponents(icon, label);
        formLayout.addComponent(hl);
        getSaveBtn().setCaption(GeneralUtils.LOGOUT);
        this.setContent(formLayout);
    }

    @Override
    public void onSave() {
        logout();
    }

    private void logout() {
        getUI().getSession().setAttribute(GeneralUtils.SESSION_USER, null);
        MeetingUI.getMeetingUI().checkForComponent();
        this.close();
    }

}
