package gr.demokritos.meetingscheduler.layouts;

import gr.demokritos.meetingscheduler.applayouts.ContentLayout;
import gr.demokritos.meetingscheduler.business.dto.MeetingDto;
import gr.demokritos.meetingscheduler.business.dto.UserDto;
import gr.demokritos.meetingscheduler.comboboxes.SearchCombobox;
import gr.demokritos.meetingscheduler.grids.MeetingsGrid;
import gr.demokritos.meetingscheduler.providers.MeetingProvider;
import gr.demokritos.meetingscheduler.providers.UserProvider;
import gr.demokritos.meetingscheduler.textfields.SearchField;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;
import gr.demokritos.meetingscheduler.windows.MeetingWindow;
import gr.demokritos.meetingscheduler.windows.UserWindow;

import java.util.HashMap;
import java.util.Map;

public class MeetingsGridLayout extends MeetingGridLayout {
    public enum MeetingFields {
        All, Name, Date, StartTime, EndTime, Status
    }
    private ContentLayout contentLayout;
    private MeetingsGrid meetingsGrid = new MeetingsGrid(this);
    private Map<String, MeetingFields> meetingFields = new HashMap<>();
    private MeetingFields selectedField = MeetingFields.All;

    public MeetingsGridLayout(ContentLayout contentLayout) {
        super(contentLayout);
        this.contentLayout = contentLayout;
        setUpColumns();
        addValueChangeListenerToSearchComboBox();
        setUpSearchField();
        setUpLayout(meetingsGrid);
    }

    @Override
    public void setUpColumns() {
        meetingsGrid.setColumns("name", "date", "startTime", "endTime", "completed");
        meetingsGrid.getColumn("name").setCaption(VaadinElementUtils.NAME).setSortProperty("u.name").setExpandRatio(7);
        meetingsGrid.getColumn("date").setCaption(VaadinElementUtils.DATE).setSortProperty("m.date").setExpandRatio(7);
        meetingsGrid.getColumn("startTime").setCaption(VaadinElementUtils.START_TIME).setSortProperty("m.startTime").setExpandRatio(7);
        meetingsGrid.getColumn("endTime").setCaption(VaadinElementUtils.END_TIME).setSortProperty("m.endTime").setExpandRatio(7);
        meetingsGrid.getColumn("completed").setCaption(VaadinElementUtils.STATUS).setSortProperty("m.completed").setExpandRatio(7);
        putFieldsInMap();
    }

    private void putFieldsInMap() {
        meetingFields.put(VaadinElementUtils.NAME, MeetingFields.Name);
        meetingFields.put(VaadinElementUtils.DATE, MeetingFields.Date);
        meetingFields.put(VaadinElementUtils.START_TIME, MeetingFields.StartTime);
        meetingFields.put(VaadinElementUtils.END_TIME, MeetingFields.EndTime);
        meetingFields.put(VaadinElementUtils.STATUS, MeetingFields.Status);
    }

    @Override
    public void addValueChangeListenerToSearchComboBox() {
        SearchCombobox<String> searchComboBox = getSearchLayout().getSearchComboBox();
        searchComboBox.addValueChangeListener(e -> {
            String selectedValue = searchComboBox.getValue();
            if (meetingFields.containsKey(selectedValue)) {
                selectedField = meetingFields.get(selectedValue);
            } else {
                selectedField = MeetingFields.All;
            }
        });
    }

    @Override
    public void setUpSearchField() {
        SearchField searchField = getSearchLayout().getSearchField();
        searchField.addValueChangeListener(event -> {
            MeetingProvider meetingProvider = meetingsGrid.getMeetingProvider();
            meetingProvider.setFilter(selectedField, searchField.getValue());
        });
    }

    @Override
    public void onAdd() {
        new MeetingWindow(EnumUtils.Action.ADD, MessagesUtils.NEW_MEETING, meetingsGrid).show();
    }

    @Override
    public void onDelete() {
        MeetingDto selectedMeeting = meetingsGrid.getSelectedMeeting();
        if (selectedMeeting != null) {
            new MeetingWindow(EnumUtils.Action.REMOVE, MessagesUtils.REMOVE_MEETING, meetingsGrid).show();
        }
    }

    @Override
    public void onEdit() {
        MeetingDto selectedMeeting = meetingsGrid.getSelectedMeeting();
        if (selectedMeeting != null) {
            new MeetingWindow(EnumUtils.Action.EDIT, MessagesUtils.EDIT_MEETING + " " + "<b>" + selectedMeeting.getName() + "</b>",
                    meetingsGrid).show();
        }
    }

    @Override
    public void onView() {
        MeetingDto selectedMeeting = meetingsGrid.getSelectedMeeting();
        if (selectedMeeting != null) {
            new MeetingWindow(EnumUtils.Action.VIEW, MessagesUtils.VIEW_MEETING + " " + "<b>" + selectedMeeting.getName() + "</b>",
                    meetingsGrid).show();
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

    public MeetingsGrid getMeetingsGrid() {
        return meetingsGrid;
    }

    public void setMeetingsGrid(MeetingsGrid meetingsGrid) {
        this.meetingsGrid = meetingsGrid;
    }

    public Map<String, MeetingFields> getMeetingFields() {
        return meetingFields;
    }

    public void setMeetingFields(Map<String, MeetingFields> meetingFields) {
        this.meetingFields = meetingFields;
    }

    public MeetingFields getSelectedField() {
        return selectedField;
    }

    public void setSelectedField(MeetingFields selectedField) {
        this.selectedField = selectedField;
    }
}
