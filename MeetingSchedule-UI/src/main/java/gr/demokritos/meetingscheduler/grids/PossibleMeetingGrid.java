package gr.demokritos.meetingscheduler.grids;

import com.vaadin.data.TreeData;
import com.vaadin.ui.TreeGrid;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.dto.DayDto;
import gr.demokritos.meetingscheduler.business.dto.MeetingDto;
import gr.demokritos.meetingscheduler.business.dto.PossibleMeetingDto;
import gr.demokritos.meetingscheduler.forms.ProposeMeetingLayout;
import gr.demokritos.meetingscheduler.providers.PossibleMeetingProvider;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;

public class PossibleMeetingGrid extends TreeGrid<MeetingDto> {

    private ProposeMeetingLayout proposeMeetingLayout;
    private MeetingDto selectedPossibleMeeting;
    private PossibleMeetingProvider possibleMeetingProvider;

    public PossibleMeetingGrid(ProposeMeetingLayout proposeMeetingLayout, Map<DayDto, List<PossibleMeetingDto>> possibleMeetingsAndDays) {
        super(MeetingDto.class);
        this.proposeMeetingLayout = proposeMeetingLayout;
        addDataProvider(possibleMeetingsAndDays);
        addItemClickListener(event -> setSelectedPossibleMeeting(event.getItem()));
    }

    private void addDataProvider(Map<DayDto, List<PossibleMeetingDto>> possibleMeetingsAndDays) {
        List<MeetingDto> roots = getHierarchicalMeetings(possibleMeetingsAndDays);
        TreeData<MeetingDto> treeData = createTreeData(roots);
        this.possibleMeetingProvider = new PossibleMeetingProvider(treeData);
        this.setDataProvider(possibleMeetingProvider);
        expandAllItems(treeData);
    }

    private void expandAllItems(TreeData<MeetingDto> treeData) {
        List<MeetingDto> rootItems = treeData.getRootItems();
        if (!CollectionUtils.isEmpty(rootItems)) {
            this.expand(rootItems);
        }
    }

    private List<MeetingDto> getHierarchicalMeetings(Map<DayDto, List<PossibleMeetingDto>> possibleMeetingsAndDays) {
        List<MeetingDto> roots = MeetingUI.getMeetingUI().getPossibleMeetingBean().getHierarchicalMeetings(possibleMeetingsAndDays);
        return roots;
    }

    private TreeData<MeetingDto> createTreeData(List<MeetingDto> roots) {
        TreeData<MeetingDto> treeData = null;
        treeData = getTreeData(roots);
        return treeData;
    }

    private TreeData<MeetingDto> getTreeData(List<MeetingDto> roots) {
        TreeData<MeetingDto> treeData = new TreeData<>();
        treeData.addRootItems(roots);
        roots.forEach(root -> addTreeData(treeData, root));
        return treeData;
    }

    private void addTreeData(TreeData<MeetingDto> treeData, MeetingDto parent) {
        List<MeetingDto> children = parent.getLessPossibleMeetings();
        if (!CollectionUtils.isEmpty(children)) {
            treeData.addItems(parent, children);
            children.stream().forEach(child -> addTreeData(treeData, child));
        }
    }

    public ProposeMeetingLayout getProposeMeetingLayout() {
        return proposeMeetingLayout;
    }

    public void setProposeMeetingLayout(ProposeMeetingLayout proposeMeetingLayout) {
        this.proposeMeetingLayout = proposeMeetingLayout;
    }

    public MeetingDto getSelectedPossibleMeeting() {
        return selectedPossibleMeeting;
    }

    public void setSelectedPossibleMeeting(MeetingDto selectedPossibleMeeting) {
        this.selectedPossibleMeeting = selectedPossibleMeeting;
    }

    public PossibleMeetingProvider getPossibleMeetingProvider() {
        return possibleMeetingProvider;
    }

    public void setPossibleMeetingProvider(PossibleMeetingProvider possibleMeetingProvider) {
        this.possibleMeetingProvider = possibleMeetingProvider;
    }
}
