package gr.demokritos.meetingscheduler.forms;

import com.vaadin.ui.*;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.dto.DayDto;
import gr.demokritos.meetingscheduler.business.dto.MeetingDto;
import gr.demokritos.meetingscheduler.business.dto.PossibleMeetingDto;
import gr.demokritos.meetingscheduler.grids.PossibleMeetingGrid;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;
import gr.demokritos.meetingscheduler.windows.EmailWindow;
import gr.demokritos.meetingscheduler.windows.MemberAvailabilityWindow;
import gr.demokritos.meetingscheduler.windows.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProposeMeetingLayout extends VerticalLayout {

    public enum PossibleMeetingFields {
        All, Name, Day, StartTime, EndTime, canCount, cannotCount
    }

    private MeetingDto meetingDto;
    private Map<DayDto, List<PossibleMeetingDto>> possibleMeetingsAndDays;
    private PossibleMeetingGrid possibleMeetingGrid;
    private HorizontalLayout actionBtnsLayout = new HorizontalLayout();
    private Button viewMembersBtn = new Button(VaadinElementUtils.VIEW_MEMBERS);
    private Button sendEmailBtn = new Button(VaadinElementUtils.SEND_EMAIL);
    private Button saveMeetingBtn = new Button(VaadinElementUtils.SAVE_MEETING);

    public ProposeMeetingLayout(MeetingDto meetingDto, Map<DayDto, List<PossibleMeetingDto>> possibleMeetingsAndDays) {
        this.meetingDto = meetingDto;
        this.possibleMeetingsAndDays = possibleMeetingsAndDays;
        this.possibleMeetingGrid = new PossibleMeetingGrid(this, possibleMeetingsAndDays);
        setUpColumns();
        setUpLayout();
        setWidth("800px");
        setHeight("600px");
    }

    private void setUpColumns() {
        possibleMeetingGrid.setColumns("name", "date", "startTime", "endTime", "duration", "completed", "canAttend", "cannotAttend");
        possibleMeetingGrid.getColumn("name").setCaption(VaadinElementUtils.NAME).setSortable(false).setExpandRatio(5);
        possibleMeetingGrid.getColumn("date").setCaption(VaadinElementUtils.DATE).setSortable(false).setExpandRatio(5);
        possibleMeetingGrid.getColumn("startTime").setCaption(VaadinElementUtils.START_TIME).setSortable(false).setExpandRatio(5);
        possibleMeetingGrid.getColumn("endTime").setCaption(VaadinElementUtils.END_TIME).setSortable(false).setExpandRatio(5);
        possibleMeetingGrid.getColumn("duration").setCaption(VaadinElementUtils.MEETING_DURATION).setSortable(false).setExpandRatio(5);
        possibleMeetingGrid.getColumn("completed").setCaption(VaadinElementUtils.COMPLETED).setSortable(false).setExpandRatio(5);
        possibleMeetingGrid.getColumn("canAttend").setSortable(false).setCaption(VaadinElementUtils.CAN_ATTEND).setExpandRatio(5);
        possibleMeetingGrid.getColumn("cannotAttend").setSortable(false).setCaption(VaadinElementUtils.CANNOT_ATTEND).setExpandRatio(5);
    }

    private void setUpLayout() {
        this.possibleMeetingGrid.setWidth("100%");
        this.possibleMeetingGrid.setHeaderRowHeight(45);
        this.possibleMeetingGrid.setBodyRowHeight(35);
//        this.possibleMeetingGrid.setHeight((Page.getCurrent().getBrowserWindowHeight() * 0.70f) - 30, Unit.PIXELS);
        setUpButtonsAndGrid();
    }

    private void setUpButtonsAndGrid() {
        actionBtnsLayout.addComponents(viewMembersBtn, sendEmailBtn, saveMeetingBtn);
        this.addComponents(actionBtnsLayout, possibleMeetingGrid);
        this.setComponentAlignment(actionBtnsLayout, Alignment.TOP_RIGHT);
        this.setComponentAlignment(possibleMeetingGrid, Alignment.MIDDLE_CENTER);
        possibleMeetingGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        addClickListeners();
        this.setExpandRatio(actionBtnsLayout, 1);
        this.setExpandRatio(possibleMeetingGrid, 5);
    }

    private void addClickListeners() {
        viewMembersBtn.addClickListener(event -> {
            if (this.possibleMeetingGrid.getSelectedPossibleMeeting() == null) {
                Message.show(MessagesUtils.WARNING, MessagesUtils.SCHEDULE_MEETING_WARNING, EnumUtils.MessageType.WARN);
            } else {
                new MemberAvailabilityWindow(VaadinElementUtils.MEETING_AVAILABILITY, possibleMeetingGrid.getSelectedPossibleMeeting(), this).show();
            }
        });
        sendEmailBtn.addClickListener(event -> {
            if (this.possibleMeetingGrid.getSelectedPossibleMeeting() == null) {
                Message.show(MessagesUtils.WARNING, MessagesUtils.SCHEDULE_MEETING_WARNING, EnumUtils.MessageType.WARN);
            } else {
                new EmailWindow(VaadinElementUtils.SEND_EMAIL, possibleMeetingGrid.getSelectedPossibleMeeting()).show();
            }
        });
        saveMeetingBtn.addClickListener(event -> {
            if (this.possibleMeetingGrid.getSelectedPossibleMeeting() == null) {
                Message.show(MessagesUtils.WARNING, MessagesUtils.SCHEDULE_MEETING_WARNING, EnumUtils.MessageType.WARN);
            } else {
                possibleMeetingGrid.getSelectedPossibleMeeting().setPossibleMeetingDtos(new ArrayList<>());
                MeetingUI.getMeetingUI().getMeetingBean().updateMeeting(possibleMeetingGrid.getSelectedPossibleMeeting());
            }
        });
    }

    public MeetingDto getMeetingDto() {
        return meetingDto;
    }

    public void setMeetingDto(MeetingDto meetingDto) {
        this.meetingDto = meetingDto;
    }

    public Map<DayDto, List<PossibleMeetingDto>> getPossibleMeetingsAndDays() {
        return possibleMeetingsAndDays;
    }

    public void setPossibleMeetingsAndDays(Map<DayDto, List<PossibleMeetingDto>> possibleMeetingsAndDays) {
        this.possibleMeetingsAndDays = possibleMeetingsAndDays;
    }

    public PossibleMeetingGrid getPossibleMeetingGrid() {
        return possibleMeetingGrid;
    }

    public void setPossibleMeetingGrid(PossibleMeetingGrid possibleMeetingGrid) {
        this.possibleMeetingGrid = possibleMeetingGrid;
    }

    public HorizontalLayout getActionBtnsLayout() {
        return actionBtnsLayout;
    }

    public void setActionBtnsLayout(HorizontalLayout actionBtnsLayout) {
        this.actionBtnsLayout = actionBtnsLayout;
    }

    public Button getViewMembersBtn() {
        return viewMembersBtn;
    }

    public void setViewMembersBtn(Button viewMembersBtn) {
        this.viewMembersBtn = viewMembersBtn;
    }

    public Button getSendEmailBtn() {
        return sendEmailBtn;
    }

    public void setSendEmailBtn(Button sendEmailBtn) {
        this.sendEmailBtn = sendEmailBtn;
    }

    public Button getSaveMeetingBtn() {
        return saveMeetingBtn;
    }

    public void setSaveMeetingBtn(Button saveMeetingBtn) {
        this.saveMeetingBtn = saveMeetingBtn;
    }
}
