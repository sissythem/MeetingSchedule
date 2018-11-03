package gr.demokritos.meetingscheduler.applayouts;

import com.vaadin.navigator.View;
import com.vaadin.ui.VerticalLayout;
import gr.demokritos.meetingscheduler.panels.AppPanel;

public class AppLayout extends VerticalLayout implements View {

    private MainLayout mainLayout;
    private AppPanel appPanel = new AppPanel(this);
    private TopBarLayout topBarLayout = new TopBarLayout(this);

    public AppLayout(MainLayout mainLayout) {
        initLayout();
        this.mainLayout = mainLayout;
        initComponents();
    }

    private void initLayout() {
        setSizeFull();
        setMargin(false);
        setSpacing(false);
    }

    private void initComponents() {
        addComponents(topBarLayout, appPanel);
        setExpandRatio(topBarLayout, 1);
        setExpandRatio(appPanel, 17);
    }

    public MainLayout getMainLayout() {
        return mainLayout;
    }

    public void setMainLayout(MainLayout mainLayout) {
        this.mainLayout = mainLayout;
    }

    public AppPanel getAppPanel() {
        return appPanel;
    }

    public void setAppPanel(AppPanel appPanel) {
        this.appPanel = appPanel;
    }

    public TopBarLayout getTopBarLayout() {
        return topBarLayout;
    }

    public void setTopBarLayout(TopBarLayout topBarLayout) {
        this.topBarLayout = topBarLayout;
    }

}
