package gr.demokritos.meetingscheduler.panels;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import gr.demokritos.meetingscheduler.applayouts.AppLayout;
import gr.demokritos.meetingscheduler.applayouts.ContentLayout;
import gr.demokritos.meetingscheduler.applayouts.MenuBarLayout;
import gr.demokritos.meetingscheduler.utils.CssUtils;

public class AppPanel extends Panel {
    private AppLayout appLayout;
    private ContentLayout contentLayout;
    private MenuBarLayout menuBarLayout;
    private HorizontalSplitPanel hsplit;

    public AppPanel(AppLayout appLayout) {
        super();
        this.setSizeFull();
        this.setStyleName(MaterialTheme.PANEL_BORDERLESS);
        this.appLayout = appLayout;
        this.contentLayout = new ContentLayout(this);
        this.menuBarLayout = new MenuBarLayout(this, contentLayout);
        splitPanel();
    }

    private void splitPanel() {
        hsplit = new HorizontalSplitPanel();
        hsplit.setSplitPosition(12, Sizeable.Unit.PERCENTAGE);
        hsplit.setFirstComponent(menuBarLayout);
        hsplit.setSecondComponent(contentLayout);
        hsplit.setLocked(true);
        setContent(hsplit);
        this.hsplit.addStyleName(CssUtils.SCSS_APP_PANEL);
    }

    public AppLayout getAppLayout() {
        return appLayout;
    }

    public void setAppLayout(AppLayout appLayout) {
        this.appLayout = appLayout;
    }

    public ContentLayout getContentLayout() {
        return contentLayout;
    }

    public void setContentLayout(ContentLayout contentLayout) {
        this.contentLayout = contentLayout;
    }

    public MenuBarLayout getMenuBarLayout() {
        return menuBarLayout;
    }

    public void setMenuBarLayout(MenuBarLayout menuBarLayout) {
        this.menuBarLayout = menuBarLayout;
    }

}
