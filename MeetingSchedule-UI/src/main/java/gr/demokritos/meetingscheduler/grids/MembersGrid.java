package gr.demokritos.meetingscheduler.grids;

import com.vaadin.ui.Grid;
import gr.demokritos.meetingscheduler.business.dto.MemberDto;
import gr.demokritos.meetingscheduler.layouts.MembersGridLayout;
import gr.demokritos.meetingscheduler.providers.MemberProvider;

public class MembersGrid extends Grid<MemberDto> {
    private MembersGridLayout membersGridLayout;
    private MemberProvider memberProvider = new MemberProvider();
    private MemberDto selectedMember;

    public MembersGrid(MembersGridLayout membersGridLayout) {
        super(MemberDto.class);
        this.membersGridLayout = membersGridLayout;
        this.setDataProvider(memberProvider);
        addItemClickListener(event -> setSelectedMember(event.getItem()));
    }

    public MembersGridLayout getMembersGridLayout() {
        return membersGridLayout;
    }

    public void setMembersGridLayout(MembersGridLayout membersGridLayout) {
        this.membersGridLayout = membersGridLayout;
    }

    public MemberProvider getMemberProvider() {
        return memberProvider;
    }

    public void setMemberProvider(MemberProvider memberProvider) {
        this.memberProvider = memberProvider;
    }

    public MemberDto getSelectedMember() {
        return selectedMember;
    }

    public void setSelectedMember(MemberDto selectedMember) {
        this.selectedMember = selectedMember;
    }
}
