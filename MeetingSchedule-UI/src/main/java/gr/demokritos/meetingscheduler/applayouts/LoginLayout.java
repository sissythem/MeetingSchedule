package gr.demokritos.meetingscheduler.applayouts;

import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import gr.demokritos.meetingscheduler.panels.LoginPanel;
import gr.demokritos.meetingscheduler.utils.CssUtils;

public class LoginLayout extends VerticalLayout implements View {
    private MainLayout mainLayout;
    private LoginPanel loginPanel;

    public LoginLayout(MainLayout mainLayout) {
        this.mainLayout = mainLayout;
        loginPanel = new LoginPanel(this);
        setSizeFull();
        addStyleName(CssUtils.SCSS_LOGIN_IMAGE);
        addComponent(loginPanel);
        setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
    }

    public MainLayout getMainLayout() {
        return mainLayout;
    }

    public void setMainLayout(MainLayout mainLayout) {
        this.mainLayout = mainLayout;
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public void setLoginPanel(LoginPanel loginPanel) {
        this.loginPanel = loginPanel;
    }

}
