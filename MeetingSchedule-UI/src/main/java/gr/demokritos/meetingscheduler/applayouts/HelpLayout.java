package gr.demokritos.meetingscheduler.applayouts;

import com.vaadin.navigator.View;
import com.vaadin.ui.VerticalLayout;

public class HelpLayout extends VerticalLayout implements View {
    private ContentLayout contentLayout;

    public HelpLayout(ContentLayout contentLayout) {
        super();
        this.contentLayout = contentLayout;
    }

    public ContentLayout getContentLayout() {
        return contentLayout;
    }

    public void setContentLayout(ContentLayout contentLayout) {
        this.contentLayout = contentLayout;
    }

}
