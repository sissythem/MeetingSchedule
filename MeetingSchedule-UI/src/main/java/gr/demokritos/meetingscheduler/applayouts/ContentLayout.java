package gr.demokritos.meetingscheduler.applayouts;

import com.vaadin.ui.VerticalLayout;
import gr.demokritos.meetingscheduler.layouts.MeetingGridLayout;
import gr.demokritos.meetingscheduler.panels.AppPanel;
import gr.demokritos.meetingscheduler.utils.EnumUtils;

public class ContentLayout extends VerticalLayout {
    private AppPanel appPanel;
    private MeetingGridLayout meetingGridLayout;
    private EnumUtils.LeftMenuSelection leftSelectedItem;
    private EnumUtils.TopMenuSelection topSelectedItem;

    public ContentLayout(AppPanel appPanel) {
        super();
        setHeightUndefined();
        this.appPanel = appPanel;
    }

    public AppPanel getHomeLayout() {
        return appPanel;
    }

    public void setHomeLayout(AppPanel appPanel) {
        this.appPanel = appPanel;
    }

    public AppPanel getAppLayout() {
        return appPanel;
    }

    public void setAppLayout(AppPanel appPanel) {
        this.appPanel = appPanel;
    }

    public AppPanel getAppPanel() {
        return appPanel;
    }

    public void setAppPanel(AppPanel appPanel) {
        this.appPanel = appPanel;
    }

    public EnumUtils.LeftMenuSelection getLeftSelectedItem() {
        return leftSelectedItem;
    }

    public void setLeftSelectedItem(EnumUtils.LeftMenuSelection leftSelectedItem) {
        this.leftSelectedItem = leftSelectedItem;
    }

    public EnumUtils.TopMenuSelection getTopSelectedItem() {
        return topSelectedItem;
    }

    public void setTopSelectedItem(EnumUtils.TopMenuSelection topSelectedItem) {
        this.topSelectedItem = topSelectedItem;
    }

    public MeetingGridLayout getMeetingGridLayout() {
        return meetingGridLayout;
    }

    public void setMeetingGridLayout(MeetingGridLayout meetingGridLayout) {
        this.meetingGridLayout = meetingGridLayout;
    }

}
