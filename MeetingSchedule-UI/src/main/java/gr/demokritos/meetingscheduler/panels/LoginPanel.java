package gr.demokritos.meetingscheduler.panels;

import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import gr.demokritos.meetingscheduler.applayouts.LoginLayout;
import gr.demokritos.meetingscheduler.forms.LoginForm;
import gr.demokritos.meetingscheduler.utils.CssUtils;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;

public class LoginPanel extends Panel {

    private LoginLayout loginLayout;
    private HorizontalSplitPanel hsplit;
    private LoginForm loginForm;
    private static final ThemeResource LOGO_ICON = new ThemeResource(GeneralUtils.LOGO_ICON_PATH);

    public LoginPanel(LoginLayout loginLayout) {
        super();
        this.loginLayout = loginLayout;
        this.setStyleName(CssUtils.SCSS_TRANSPARENT_PANEL);
        setWidth(750, Sizeable.Unit.PIXELS);
        setHeight(450, Sizeable.Unit.PIXELS);
        this.loginForm = new LoginForm(this);
        VerticalLayout logo = createLeftComponent();
        createHorizontalSplit(logo);
    }

    private void createHorizontalSplit(VerticalLayout logo) {
        hsplit = new HorizontalSplitPanel();
        hsplit.setSplitPosition(55, Sizeable.Unit.PERCENTAGE);
        hsplit.setFirstComponent(logo);
        hsplit.setSecondComponent(loginForm);
        hsplit.setLocked(true);
        setContent(hsplit);
    }

    private VerticalLayout createLeftComponent() {
        VerticalLayout vl = new VerticalLayout();
        vl.setMargin(true);
        Image icon = new Image();
        icon.setIcon(LOGO_ICON);
        Label name = new Label(GeneralUtils.APP_LOGO);
        name.setStyleName(CssUtils.SCSS_LOGIN_FORM_TEXT);
        Label text = new Label(GeneralUtils.APP_SUB_LOGO);
        text.setStyleName(CssUtils.SCSS_SUB_LOGO_LABEL);
        text.setStyleName(CssUtils.SCSS_SUB_LOGO_LABEL);
        vl.addComponents(icon, name, text);
        vl.setComponentAlignment(icon, Alignment.BOTTOM_CENTER);
        vl.setComponentAlignment(name, Alignment.BOTTOM_CENTER);
        vl.setComponentAlignment(text, Alignment.BOTTOM_CENTER);
        return vl;
    }

    public LoginLayout getLoginLayout() {
        return loginLayout;
    }

    public void setLoginLayout(LoginLayout loginLayout) {
        this.loginLayout = loginLayout;
    }

    public HorizontalSplitPanel getHsplit() {
        return hsplit;
    }

    public void setHsplit(HorizontalSplitPanel hsplit) {
        this.hsplit = hsplit;
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }

    public void setLoginForm(LoginForm loginForm) {
        this.loginForm = loginForm;
    }
}
