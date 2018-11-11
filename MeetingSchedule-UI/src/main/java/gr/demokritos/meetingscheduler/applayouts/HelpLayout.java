package gr.demokritos.meetingscheduler.applayouts;

import com.vaadin.navigator.View;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import gr.demokritos.meetingscheduler.utils.CssUtils;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;

public class HelpLayout extends VerticalLayout implements View {
    private ContentLayout contentLayout;

    public HelpLayout(ContentLayout contentLayout) {
        super();
        this.contentLayout = contentLayout;
        initLayout();
    }

    private void initLayout() {
        Label title = new Label(GeneralUtils.HELP);
        title.setStyleName(CssUtils.PAGE_TITLE);
        Label body = generateBodyLabel();
        this.addComponents(title, body);
    }

    private Label generateBodyLabel() {
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>\n");
        sb.append("<li>Create user: add his name, last name, email, username and password</li>");
        sb.append("<ul>\n");
        sb.append("<li>You can also edit, view or delete a user</li>");
        sb.append("</ul>\n");

        sb.append("<li>Create member: refers to member attending a meeting. Give his name, last name and email</li>");
        sb.append("<ul>\n");
        sb.append("<li>You can also edit, view or delete a member</li>");
        sb.append("</ul>\n");

        sb.append("<li>Create meeting: name, duration and if it is completed are mandatory fields</li>");
        sb.append("<ul>\n");
        sb.append("<li>You can also edit, view or delete a meeting</li>");
        sb.append("</ul>\n");

        sb.append("<li>Create availability: name of the member, date, hours and if he/she can attend</li>");
        sb.append("<ul>\n");
        sb.append("<li>You can also edit, view or delete an availability</li>");
        sb.append("</ul>\n");

        sb.append("<li>Schedule meeting</li>");
        sb.append("<ul>\n");
        sb.append("<li>Press Schedule meeting button</li>");
        sb.append("<li>Give the week for which you are interested and the minimum number of members that is allowed to not attend the meeting</li>");
        sb.append("<li>Press next</li>");
        sb.append("<li>Select one of the proposed meetings: you can view the members that are attending and not attending, save meeting & send email to attending members or just save the meeting</li>");
        sb.append("</ul>\n");

        sb.append("<li>User menu</li>");
        sb.append("<ul>\n");
        sb.append("<li>Profile: view your profile and change your data (e.g. password)</li>");
        sb.append("<li>Help page (this page)</li>");
        sb.append("<li>Sign out: exit the application</li>");
        sb.append("</ul>\n");

        sb.append("</ul>\n");
        Label body = new Label();
        body.setValue(sb.toString());
        body.setContentMode(ContentMode.HTML);
        return body;
    }

    public ContentLayout getContentLayout() {
        return contentLayout;
    }

    public void setContentLayout(ContentLayout contentLayout) {
        this.contentLayout = contentLayout;
    }

}
