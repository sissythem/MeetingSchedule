package gr.demokritos.meetingscheduler.applayouts;

import com.vaadin.ui.VerticalLayout;

public class MainLayout extends VerticalLayout {
    private AppLayout appLayout = new AppLayout(this);
    private LoginLayout loginLayout = new LoginLayout(this);

    public MainLayout() {
        setSizeFull();
        setMargin(false);
        setSpacing(false);
        addComponent(loginLayout);
        loginLayout.setSizeFull();
    }

    public AppLayout getAppLayout() {
        return appLayout;
    }

    public void setAppLayout(AppLayout appLayout) {
        this.appLayout = appLayout;
    }

    public LoginLayout getLoginLayout() {
        return loginLayout;
    }

    public void setLoginLayout(LoginLayout loginLayout) {
        this.loginLayout = loginLayout;
    }
}
