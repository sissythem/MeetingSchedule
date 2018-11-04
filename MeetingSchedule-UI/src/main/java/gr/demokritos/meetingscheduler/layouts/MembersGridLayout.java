package gr.demokritos.meetingscheduler.layouts;

import gr.demokritos.meetingscheduler.applayouts.ContentLayout;
import gr.demokritos.meetingscheduler.business.dto.MemberDto;
import gr.demokritos.meetingscheduler.business.dto.UserDto;
import gr.demokritos.meetingscheduler.comboboxes.SearchCombobox;
import gr.demokritos.meetingscheduler.grids.MembersGrid;
import gr.demokritos.meetingscheduler.providers.MemberProvider;
import gr.demokritos.meetingscheduler.providers.UserProvider;
import gr.demokritos.meetingscheduler.textfields.SearchField;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;
import gr.demokritos.meetingscheduler.windows.MemberWindow;
import gr.demokritos.meetingscheduler.windows.UserWindow;

import java.util.HashMap;
import java.util.Map;

public class MembersGridLayout extends MeetingGridLayout {

    public enum MemberFields {
        All, Name, LastName, Email
    }

    private ContentLayout contentLayout;
    private MembersGrid membersGrid = new MembersGrid(this);
    private Map<String,MemberFields> memberFields = new HashMap<>();
    private MemberFields selectedField = MemberFields.All;

    public MembersGridLayout(ContentLayout contentLayout) {
        super(contentLayout);
        this.contentLayout = contentLayout;
        setUpColumns();
        addValueChangeListenerToSearchComboBox();
        setUpSearchField();
        setUpLayout(membersGrid);
    }

    @Override
    public void setUpColumns() {
        membersGrid.setColumns("name", "lastName", "email");
        membersGrid.getColumn("name").setCaption(VaadinElementUtils.NAME)
                .setSortProperty("m.name").setExpandRatio(7);
        membersGrid.getColumn("lastName").setCaption(VaadinElementUtils.LAST_NAME)
                .setSortProperty("m.lastName").setExpandRatio(7);
        membersGrid.getColumn("email").setCaption(VaadinElementUtils.LAST_NAME)
                .setSortProperty("m.email").setExpandRatio(7);
        putFieldsInMap();
    }

    private void putFieldsInMap() {
        memberFields.put(VaadinElementUtils.NAME, MemberFields.Name);
        memberFields.put(VaadinElementUtils.LAST_NAME, MemberFields.LastName);
        memberFields.put(VaadinElementUtils.EMAIL, MemberFields.Email);
    }

    @Override
    public void addValueChangeListenerToSearchComboBox() {
        SearchCombobox<String> searchComboBox = getSearchLayout().getSearchComboBox();
        searchComboBox.addValueChangeListener(e -> {
            String selectedValue = searchComboBox.getValue();
            if (memberFields.containsKey(selectedValue)) {
                selectedField = memberFields.get(selectedValue);
            } else {
                selectedField = MemberFields.All;
            }
        });
    }

    @Override
    public void setUpSearchField() {
        SearchField searchField = getSearchLayout().getSearchField();
        searchField.addValueChangeListener(event -> {
            MemberProvider memberProvider = membersGrid.getMemberProvider();
            memberProvider.setFilter(selectedField, searchField.getValue());
        });
    }

    @Override
    public void onAdd() {
        new MemberWindow(EnumUtils.Action.ADD, MessagesUtils.NEW_MEMBER, membersGrid).show();
    }

    @Override
    public void onDelete() {
        MemberDto selectedMember = membersGrid.getSelectedMember();
        if (selectedMember != null) {
            new MemberWindow(EnumUtils.Action.REMOVE, MessagesUtils.REMOVE_MEMBER, membersGrid).show();
        }
    }

    @Override
    public void onEdit() {
        MemberDto selectedMember = membersGrid.getSelectedMember();
        if (selectedMember != null) {
            new MemberWindow(EnumUtils.Action.EDIT, MessagesUtils.EDIT_MEMBER + " " + "<b>" + selectedMember.getName() + "</b>",
                    membersGrid).show();
        }
    }

    @Override
    public void onView() {
        MemberDto selectedMember = membersGrid.getSelectedMember();
        if (selectedMember != null) {
            new MemberWindow(EnumUtils.Action.VIEW, MessagesUtils.VIEW_MEMBER + " " + "<b>" + selectedMember.getName() + "</b>",
                    membersGrid).show();
        }
    }

    @Override
    public ContentLayout getContentLayout() {
        return contentLayout;
    }

    @Override
    public void setContentLayout(ContentLayout contentLayout) {
        this.contentLayout = contentLayout;
    }

    public MembersGrid getMembersGrid() {
        return membersGrid;
    }

    public void setMembersGrid(MembersGrid membersGrid) {
        this.membersGrid = membersGrid;
    }

    public Map<String, MemberFields> getMemberFields() {
        return memberFields;
    }

    public void setMemberFields(Map<String, MemberFields> memberFields) {
        this.memberFields = memberFields;
    }

    public MemberFields getSelectedField() {
        return selectedField;
    }

    public void setSelectedField(MemberFields selectedField) {
        this.selectedField = selectedField;
    }
}
