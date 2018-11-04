package gr.demokritos.meetingscheduler.forms;

import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import gr.demokritos.meetingscheduler.business.dto.DayDto;
import gr.demokritos.meetingscheduler.business.dto.MeetingDto;
import gr.demokritos.meetingscheduler.business.dto.PossibleMeetingDto;
import gr.demokritos.meetingscheduler.grids.PossibleMeetingGrid;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;

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
        setUpColumns();
        setUpLayout();
    }

    private void setUpColumns() {
        possibleMeetingGrid.addColumn(possibleMeetingDto -> (possibleMeetingDto.getMeetingDto()!=null) ? possibleMeetingDto.getMeetingDto().getName() : "")
                .setSortable(false).setCaption(VaadinElementUtils.MEETING_NAME).setExpandRatio(5);
        possibleMeetingGrid.addColumn(possibleMeetingDto -> (possibleMeetingDto.getDayDto()!=null) ? possibleMeetingDto.getDayDto().getDate() : "")
                .setSortable(false).setCaption(VaadinElementUtils.MEETING_DATE).setExpandRatio(5);
        possibleMeetingGrid.addColumn(possibleMeetingDto -> (possibleMeetingDto.getTimezoneDto()!=null) ? possibleMeetingDto.getTimezoneDto().getStartTime() : "")
                .setSortable(false).setCaption(VaadinElementUtils.START_TIME).setExpandRatio(5);
        possibleMeetingGrid.addColumn(possibleMeetingDto -> (possibleMeetingDto.getTimezoneDto()!=null) ? possibleMeetingDto.getTimezoneDto().getEndTime() : "")
                .setSortable(false).setCaption(VaadinElementUtils.END_TIME).setExpandRatio(5);
        possibleMeetingGrid.addColumn(PossibleMeetingDto::getCanAttend).setSortable(false).setCaption(VaadinElementUtils.CAN_ATTEND).setExpandRatio(5);
        possibleMeetingGrid.addColumn(PossibleMeetingDto::getCannotAttend).setSortable(false).setCaption(VaadinElementUtils.CANNOT_ATTEND).setExpandRatio(5);
    }

    private void setUpLayout() {
        this.possibleMeetingGrid.setWidth("100%");
        this.possibleMeetingGrid.setHeaderRowHeight(45);
        this.possibleMeetingGrid.setBodyRowHeight(35);
        this.possibleMeetingGrid.setHeight((Page.getCurrent().getBrowserWindowHeight() * 0.70f) - 30, Unit.PIXELS);
        setUpButtonsAndGrid();

    }

    private void setUpButtonsAndGrid() {
        actionBtnsLayout.addComponents(viewMembersBtn, sendEmailBtn, saveMeetingBtn);
        this.addComponents(actionBtnsLayout, possibleMeetingGrid);
        this.setComponentAlignment(actionBtnsLayout, Alignment.TOP_RIGHT);
        this.setComponentAlignment(possibleMeetingGrid, Alignment.MIDDLE_CENTER);
        addClickListeners();
    }

    private void addClickListeners() {
        viewMembersBtn.addClickListener(event -> {

        });
        sendEmailBtn.addClickListener(event -> {

        });
        saveMeetingBtn.addClickListener(event -> {

        });
    }
}
