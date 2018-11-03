package gr.demokritos.meetingscheduler.applayouts;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import gr.demokritos.meetingscheduler.utils.CssUtils;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;

public class TopBarLayout extends HorizontalLayout {
    private static final ThemeResource LOGO_ICON = new ThemeResource(GeneralUtils.LOGO_ICON_PATH);
    private AppLayout appLayout;
    private HorizontalLayout logoLayout;
    private UserMenuBar userMenuBar = new UserMenuBar(this);

    public TopBarLayout(AppLayout appLayout) {
        super();
        initTopBarLayout(appLayout);
        addTopBarComponents();
    }

    private void initTopBarLayout(AppLayout appLayout) {
        setSizeFull();
        this.setStyleName(CssUtils.SCSS_TOP_MENU);
        this.appLayout = appLayout;
    }

    private void addTopBarComponents() {
        initLogoLayout();
        this.addComponents(logoLayout,userMenuBar);
        this.setComponentAlignment(logoLayout, Alignment.MIDDLE_LEFT);
        this.setComponentAlignment(userMenuBar, Alignment.MIDDLE_RIGHT);
    }

    private void initLogoLayout() {
        this.logoLayout = new HorizontalLayout();
        logoLayout.addStyleName(CssUtils.SCSS_LOGO_ICON);
        Image icon = new Image();
        icon.setIcon(LOGO_ICON);
        Label logol = new Label();
        logol.setCaptionAsHtml(true);
        logol.setCaption("<b>"+ GeneralUtils.APP_LOGO + "</b>");
        logoLayout.addComponents(icon, logol);
        logoLayout.setMargin(true);
    }

    public AppLayout getAppLayout() {
        return appLayout;
    }

    public void setAppLayout(AppLayout appLayout) {
        this.appLayout = appLayout;
    }

    public HorizontalLayout getLogoLayout() {
        return logoLayout;
    }

    public void setLogoLayout(HorizontalLayout logoLayout) {
        this.logoLayout = logoLayout;
    }

    public UserMenuBar getUserMenuBar() {
        return userMenuBar;
    }

    public void setUserMenuBar(UserMenuBar userMenuBar) {
        this.userMenuBar = userMenuBar;
    }

}
