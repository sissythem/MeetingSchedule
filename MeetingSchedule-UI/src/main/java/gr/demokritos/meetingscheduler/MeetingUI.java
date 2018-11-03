package gr.demokritos.meetingscheduler;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import gr.demokritos.meetingscheduler.applayouts.AppLayout;
import gr.demokritos.meetingscheduler.applayouts.LoginLayout;
import gr.demokritos.meetingscheduler.applayouts.MainLayout;
import gr.demokritos.meetingscheduler.business.beans.*;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;

import javax.ejb.EJB;

@SuppressWarnings("serial")
@Theme("MeetingTheme")
@CDIUI
@Title("MeetingSchedule")
@PreserveOnRefresh
public class MeetingUI extends UI {

    @EJB
    private UserBean userBean;

    @EJB
    private MemberBean memberBean;

    @EJB
    private DayBean dayBean;

    @EJB
    private TimezoneBean timezoneBean;

    @EJB
    private MeetingBean meetingBean;

    @EJB
    private PossibleMeetingBean possibleMeetingBean;

    @EJB
    private AvailabilityBean availabilityBean;

    private MainLayout mainLayout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        mainLayout = new MainLayout();
        checkForComponent();
        setContent(mainLayout);
    }

    public void checkForComponent() {
        mainLayout.removeAllComponents();
        if (getSession().getAttribute(GeneralUtils.SESSION_USER) == null) {
            mainLayout.setLoginLayout(new LoginLayout(mainLayout));
            mainLayout.addComponent(mainLayout.getLoginLayout());
            mainLayout.getLoginLayout().setSizeFull();
        } else {
            mainLayout.setAppLayout(new AppLayout(mainLayout));
            mainLayout.addComponent(mainLayout.getAppLayout());
            mainLayout.getAppLayout().setSizeFull();
        }
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public static MeetingUI getMeetingUI() {
        return (MeetingUI) UI.getCurrent();
    }

    public MemberBean getMemberBean() {
        return memberBean;
    }

    public void setMemberBean(MemberBean memberBean) {
        this.memberBean = memberBean;
    }

    public DayBean getDayBean() {
        return dayBean;
    }

    public void setDayBean(DayBean dayBean) {
        this.dayBean = dayBean;
    }

    public TimezoneBean getTimezoneBean() {
        return timezoneBean;
    }

    public void setTimezoneBean(TimezoneBean timezoneBean) {
        this.timezoneBean = timezoneBean;
    }

    public MeetingBean getMeetingBean() {
        return meetingBean;
    }

    public void setMeetingBean(MeetingBean meetingBean) {
        this.meetingBean = meetingBean;
    }

    public PossibleMeetingBean getPossibleMeetingBean() {
        return possibleMeetingBean;
    }

    public void setPossibleMeetingBean(PossibleMeetingBean possibleMeetingBean) {
        this.possibleMeetingBean = possibleMeetingBean;
    }

    public AvailabilityBean getAvailabilityBean() {
        return availabilityBean;
    }

    public void setAvailabilityBean(AvailabilityBean availabilityBean) {
        this.availabilityBean = availabilityBean;
    }

    public MainLayout getMainLayout() {
        return mainLayout;
    }

    public void setMainLayout(MainLayout mainLayout) {
        this.mainLayout = mainLayout;
    }
}
