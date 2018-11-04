package gr.demokritos.meetingscheduler.windows;

import gr.demokritos.meetingscheduler.grids.MembersGrid;
import gr.demokritos.meetingscheduler.utils.EnumUtils;

public class MemberWindow extends ParentWindow {

    private EnumUtils.Action action;
    private MembersGrid membersGrid;

    public MemberWindow(EnumUtils.Action action, String title, MembersGrid membersGrid) {
        super();
        this.action = action;
        this.membersGrid = membersGrid;
    }
}
