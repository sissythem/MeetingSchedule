package gr.demokritos.meetingscheduler.windows;

import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import gr.demokritos.meetingscheduler.business.dto.MeetingDto;
import gr.demokritos.meetingscheduler.business.dto.MemberDto;
import gr.demokritos.meetingscheduler.forms.ProposeMeetingLayout;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;

public class MemberAvailabilityWindow extends ParentWindow {

    private MeetingDto possibleMeeting;
    private ProposeMeetingLayout proposeMeetingLayout;
    private Grid<MemberDto> memberAvailabilityGrid;
    private Grid<MemberDto> memberUnavailabilityGrid;

    public MemberAvailabilityWindow(String caption, MeetingDto possibleMeeting, ProposeMeetingLayout proposeMeetingLayout) {
        super();
        this.setCaption(caption);
        this.possibleMeeting = possibleMeeting;
        this.proposeMeetingLayout = proposeMeetingLayout;
        this.memberAvailabilityGrid = new Grid<>(MemberDto.class);
        this.memberUnavailabilityGrid = new Grid<>(MemberDto.class);
        setUpFormLayout();
        setUpWindow();
        setUpButtons();
    }

    private void setUpButtons() {
        getSaveBtn().setCaption(VaadinElementUtils.OK_BUTTON);
        getCancelBtn().setVisible(false);
    }

    @Override
    public void onSave() {
        this.close();
    }

    private void setUpFormLayout() {
        this.formLayout = new VerticalLayout();
        HorizontalLayout gridLayout = new HorizontalLayout();
        setUpGrids();
        gridLayout.addComponents(memberAvailabilityGrid, memberUnavailabilityGrid);
        formLayout.addComponent(gridLayout);
//        formLayout.setWidth("800px");
//        formLayout.setHeight("600px");
    }

    private void setUpGrids() {
        this.memberAvailabilityGrid.setCaption("Can Attend meeting");
        this.memberAvailabilityGrid.setWidth("100%");
        this.memberAvailabilityGrid.setHeaderRowHeight(45);
        this.memberAvailabilityGrid.setBodyRowHeight(35);
        this.memberAvailabilityGrid.setColumns("name", "lastName");
        this.memberAvailabilityGrid.getColumn("name").setCaption(VaadinElementUtils.MEMBER_NAME)
                .setSortable(false).setExpandRatio(7);
        this.memberAvailabilityGrid.getColumn("lastName").setCaption(VaadinElementUtils.MEMBER_LAST_NAME)
                .setSortable(false).setExpandRatio(7);
        this.memberAvailabilityGrid.setItems(possibleMeeting.getCanAttendList());

        this.memberUnavailabilityGrid.setCaption("Cannot Attend meeting");
        this.memberUnavailabilityGrid.setWidth("100%");
        this.memberUnavailabilityGrid.setHeaderRowHeight(45);
        this.memberUnavailabilityGrid.setBodyRowHeight(35);
        this.memberUnavailabilityGrid.setColumns("name", "lastName");
        this.memberUnavailabilityGrid.getColumn("name").setCaption(VaadinElementUtils.MEMBER_NAME)
                .setSortable(false).setExpandRatio(7);
        this.memberUnavailabilityGrid.getColumn("lastName").setCaption(VaadinElementUtils.MEMBER_LAST_NAME)
                .setSortable(false).setExpandRatio(7);
        this.memberUnavailabilityGrid.setItems(possibleMeeting.getCannotAttendList());
    }

    public MeetingDto getPossibleMeeting() {
        return possibleMeeting;
    }

    public void setPossibleMeeting(MeetingDto possibleMeeting) {
        this.possibleMeeting = possibleMeeting;
    }

    public ProposeMeetingLayout getProposeMeetingLayout() {
        return proposeMeetingLayout;
    }

    public void setProposeMeetingLayout(ProposeMeetingLayout proposeMeetingLayout) {
        this.proposeMeetingLayout = proposeMeetingLayout;
    }

    public Grid<MemberDto> getMemberAvailabilityGrid() {
        return memberAvailabilityGrid;
    }

    public void setMemberAvailabilityGrid(Grid<MemberDto> memberAvailabilityGrid) {
        this.memberAvailabilityGrid = memberAvailabilityGrid;
    }

    public Grid<MemberDto> getMemberUnavailabilityGrid() {
        return memberUnavailabilityGrid;
    }

    public void setMemberUnavailabilityGrid(Grid<MemberDto> memberUnavailabilityGrid) {
        this.memberUnavailabilityGrid = memberUnavailabilityGrid;
    }
}
