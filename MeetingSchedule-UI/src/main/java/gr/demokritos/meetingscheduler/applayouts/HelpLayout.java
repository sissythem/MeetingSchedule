package gr.demokritos.meetingscheduler.applayouts;

import com.vaadin.navigator.View;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;

public class HelpLayout extends VerticalLayout implements View {
    private ContentLayout contentLayout;

    public HelpLayout(ContentLayout contentLayout) {
        super();
        this.contentLayout = contentLayout;
    }

    private void initLayout() {
        Label title = new Label(GeneralUtils.HELP);
    }

    public ContentLayout getContentLayout() {
        return contentLayout;
    }

    public void setContentLayout(ContentLayout contentLayout) {
        this.contentLayout = contentLayout;
    }

}
