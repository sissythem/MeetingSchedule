package gr.demokritos.meetingscheduler.grids;

import com.vaadin.ui.Grid;
import gr.demokritos.meetingscheduler.business.dto.MeetingDto;
import gr.demokritos.meetingscheduler.layouts.MeetingsGridLayout;
import gr.demokritos.meetingscheduler.providers.MeetingProvider;

public class MeetingsGrid extends Grid<MeetingDto> {
    private MeetingsGridLayout meetingsGridLayout;
    private MeetingProvider meetingProvider = new MeetingProvider();
    private MeetingDto selectedMeeting;

    public MeetingsGrid(MeetingsGridLayout meetingsGridLayout) {
        super(MeetingDto.class);
        this.meetingsGridLayout = meetingsGridLayout;
        this.setDataProvider(meetingProvider);
        addItemClickListener(event -> setSelectedMeeting(event.getItem()));
    }

    public MeetingDto getSelectedMeeting() {
        return selectedMeeting;
    }

    public void setSelectedMeeting(MeetingDto selectedMeeting) {
        this.selectedMeeting = selectedMeeting;
    }

    public MeetingsGridLayout getMeetingsGridLayout() {
        return meetingsGridLayout;
    }

    public void setMeetingsGridLayout(MeetingsGridLayout meetingsGridLayout) {
        this.meetingsGridLayout = meetingsGridLayout;
    }

    public MeetingProvider getMeetingProvider() {
        return meetingProvider;
    }

    public void setMeetingProvider(MeetingProvider meetingProvider) {
        this.meetingProvider = meetingProvider;
    }
}
