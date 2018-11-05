package gr.demokritos.meetingscheduler.providers;

import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import gr.demokritos.meetingscheduler.business.dto.MeetingDto;

public class PossibleMeetingProvider extends TreeDataProvider<MeetingDto> {
    public PossibleMeetingProvider(TreeData<MeetingDto> treeData) {
        super(treeData);
    }

    public void updateTreeData(TreeData<MeetingDto> treeData) {
        updateTreeData(treeData);
    }
}
