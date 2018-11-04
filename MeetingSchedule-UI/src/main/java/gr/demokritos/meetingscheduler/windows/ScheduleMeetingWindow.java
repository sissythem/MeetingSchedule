package gr.demokritos.meetingscheduler.windows;

import com.vaadin.data.ValidationException;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.dto.DayDto;
import gr.demokritos.meetingscheduler.business.dto.MeetingDto;
import gr.demokritos.meetingscheduler.business.dto.PossibleMeetingDto;
import gr.demokritos.meetingscheduler.business.dto.WeekDto;
import gr.demokritos.meetingscheduler.forms.ProposeMeetingLayout;
import gr.demokritos.meetingscheduler.forms.ScheduleMeetingInfoForm;
import gr.demokritos.meetingscheduler.grids.MeetingsGrid;
import gr.demokritos.meetingscheduler.layouts.MeetingGridLayout;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;

import java.util.List;
import java.util.Map;

public class ScheduleMeetingWindow extends ParentWindow {

    private Integer windowPage = 1;
    private MeetingsGrid meetingsGrid;
    private MeetingGridLayout meetingGridLayout;

    public ScheduleMeetingWindow(String caption, MeetingGridLayout meetingGridLayout, MeetingsGrid meetingsGrid) {
        super();
        this.setCaption(caption);
        this.meetingGridLayout = meetingGridLayout;
        this.meetingsGrid = meetingsGrid;
        setUpLayout();
        setUpWindow();
    }

    public void setUpLayout() {
        switch(windowPage) {
            case 1:
                onAskWeek();
                break;
            case 2:
                onMeetingPropose();
                break;
        }
    }

    private void onAskWeek() {
        this.windowPage = 1;
        formLayout = new ScheduleMeetingInfoForm(this);
        getSaveBtn().setCaption(VaadinElementUtils.NEXT);
        getCancelBtn().setVisible(false);
    }

    private void onMeetingPropose() {

    }

    @Override
    public void onSave() {
        switch (windowPage) {
            case 1:
                try {
                    ScheduleMeetingInfoForm form = (ScheduleMeetingInfoForm) formLayout;
                    WeekDto weekDto = new WeekDto(form.getWeekStartDateTf().getValue(), form.getWeekEndDateTf().getValue());
                    Integer threshold = null;
                    if(form.getThresholdTf().getValue()!=null) {
                        threshold = Integer.parseInt(form.getThresholdTf().getValue());
                    }
                    form.getBinder().writeBean(weekDto);
                    onContinue(weekDto, threshold);
                    setUpWindow();
                } catch (ValidationException e) {
                    Message.show(MessagesUtils.ERROR, MessagesUtils.VALIDATION_MESSAGE, EnumUtils.MessageType.ERROR);
                }
                break;
            case 2:
                this.close();
                break;
        }
    }

    private void onContinue(WeekDto weekDto, Integer threshold) {
        MeetingDto selectedMeeting = meetingsGrid.getSelectedMeeting();
        windowPage = 2;
        Map<DayDto, List<PossibleMeetingDto>> possibleMeetingsAndDays = MeetingUI.getMeetingUI().getPossibleMeetingBean()
                .getWeekPossibleMeetings(selectedMeeting, weekDto, threshold);
        formLayout = new ProposeMeetingLayout(selectedMeeting, possibleMeetingsAndDays);
        getSaveBtn().setCaption(VaadinElementUtils.OK_BUTTON);
    }

    public Integer getWindowPage() {
        return windowPage;
    }

    public void setWindowPage(Integer windowPage) {
        this.windowPage = windowPage;
    }

    public MeetingsGrid getMeetingsGrid() {
        return meetingsGrid;
    }

    public void setMeetingsGrid(MeetingsGrid meetingsGrid) {
        this.meetingsGrid = meetingsGrid;
    }

    public MeetingGridLayout getMeetingGridLayout() {
        return meetingGridLayout;
    }

    public void setMeetingGridLayout(MeetingGridLayout meetingGridLayout) {
        this.meetingGridLayout = meetingGridLayout;
    }
}
