package gr.demokritos.meetingscheduler.grids;

import com.vaadin.ui.Grid;
import gr.demokritos.meetingscheduler.business.dto.PossibleMeetingDto;
import gr.demokritos.meetingscheduler.forms.ProposeMeetingLayout;

public class PossibleMeetingGrid extends Grid<PossibleMeetingDto> {

    private ProposeMeetingLayout proposeMeetingLayout;
    private PossibleMeetingDto selectedPossibleMeeting;

    public PossibleMeetingGrid(ProposeMeetingLayout proposeMeetingLayout) {
        super(PossibleMeetingDto.class);
        this.proposeMeetingLayout = proposeMeetingLayout;
        addItemClickListener(event -> setSelectedPossibleMeeting(event.getItem()));
    }

    public ProposeMeetingLayout getProposeMeetingLayout() {
        return proposeMeetingLayout;
    }

    public void setProposeMeetingLayout(ProposeMeetingLayout proposeMeetingLayout) {
        this.proposeMeetingLayout = proposeMeetingLayout;
    }

    public PossibleMeetingDto getSelectedPossibleMeeting() {
        return selectedPossibleMeeting;
    }

    public void setSelectedPossibleMeeting(PossibleMeetingDto selectedPossibleMeeting) {
        this.selectedPossibleMeeting = selectedPossibleMeeting;
    }
}
