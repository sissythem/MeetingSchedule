package gr.demokritos.meetingscheduler.layouts;

import gr.demokritos.meetingscheduler.applayouts.ContentLayout;
import gr.demokritos.meetingscheduler.business.dto.AvailabilityDto;
import gr.demokritos.meetingscheduler.business.dto.MemberDto;
import gr.demokritos.meetingscheduler.comboboxes.SearchCombobox;
import gr.demokritos.meetingscheduler.grids.AvailabilitiesGrid;
import gr.demokritos.meetingscheduler.providers.AvailabilityProvider;
import gr.demokritos.meetingscheduler.providers.MemberProvider;
import gr.demokritos.meetingscheduler.textfields.SearchField;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;
import gr.demokritos.meetingscheduler.windows.AvailabilityWindow;
import gr.demokritos.meetingscheduler.windows.MemberWindow;

import java.util.HashMap;
import java.util.Map;

public class AvailabilitiesGridLayout extends MeetingGridLayout {

    public enum AvailabilityFields {
        All, MemberName, Date, StartTime, EndTime, MeetingName, Status
    }
    private ContentLayout contentLayout;
    private AvailabilitiesGrid availabilitiesGrid = new AvailabilitiesGrid(this);
    private Map<String, AvailabilityFields> availabilityFields = new HashMap<>();
    private AvailabilityFields selectedField = AvailabilityFields.All;

    public AvailabilitiesGridLayout(ContentLayout contentLayout) {
        super(contentLayout);
        this.contentLayout = contentLayout;
        setUpColumns();
        addValueChangeListenerToSearchComboBox();
        setUpSearchField();
        setUpLayout(availabilitiesGrid);
    }

    @Override
    public void setUpColumns() {
        availabilitiesGrid.addColumn(availability -> (availability.getMemberDto() != null) ? availability.getMemberDto().getName() : "")
                .setSortable(true).setCaption(VaadinElementUtils.MEMBER_NAME)
                .setSortProperty("a.member.name").setExpandRatio(7);
        availabilitiesGrid.addColumn(availability -> (availability.getDayDto() != null) ? availability.getDayDto().getDate() : "")
                .setSortable(true).setCaption(VaadinElementUtils.MEETING_DATE)
                .setSortProperty("a.day.date").setExpandRatio(7);
        availabilitiesGrid.addColumn(availability -> (availability.getTimezoneDto() != null) ? availability.getTimezoneDto().getStartTime() : "")
                .setSortable(true).setCaption(VaadinElementUtils.START_TIME)
                .setSortProperty("a.timezone.startTime").setExpandRatio(7);
        availabilitiesGrid.addColumn(availability -> (availability.getTimezoneDto() != null) ? availability.getTimezoneDto().getEndTime() : "")
                .setSortable(true).setCaption(VaadinElementUtils.END_TIME)
                .setSortProperty("a.timezone.endTime").setExpandRatio(7);
        availabilitiesGrid.addColumn(availability -> (availability.getMeetingDto() != null) ? availability.getMeetingDto().getName() : "")
                .setSortable(true).setCaption(VaadinElementUtils.MEETING_NAME)
                .setSortProperty("a.meeting.name").setExpandRatio(7);
        availabilitiesGrid.addColumn("isAvailable");
        availabilitiesGrid.getColumn("isAvailable").setCaption(VaadinElementUtils.STATUS).setSortProperty("a.isAvailable").setExpandRatio(7);
        putFieldsInMap();
    }

    private void putFieldsInMap() {
        availabilityFields.put(VaadinElementUtils.MEMBER_NAME, AvailabilityFields.MemberName);
        availabilityFields.put(VaadinElementUtils.MEETING_DATE, AvailabilityFields.Date);
        availabilityFields.put(VaadinElementUtils.START_TIME, AvailabilityFields.StartTime);
        availabilityFields.put(VaadinElementUtils.END_TIME, AvailabilityFields.EndTime);
        availabilityFields.put(VaadinElementUtils.MEETING_NAME, AvailabilityFields.MeetingName);
    }

    @Override
    public void addValueChangeListenerToSearchComboBox() {
        SearchCombobox<String> searchComboBox = getSearchLayout().getSearchComboBox();
        searchComboBox.addValueChangeListener(e -> {
            String selectedValue = searchComboBox.getValue();
            if (availabilityFields.containsKey(selectedValue)) {
                selectedField = availabilityFields.get(selectedValue);
            } else {
                selectedField = AvailabilityFields.All;
            }
        });
    }

    @Override
    public void setUpSearchField() {
        SearchField searchField = getSearchLayout().getSearchField();
        searchField.addValueChangeListener(event -> {
            AvailabilityProvider availabilityProvider = availabilitiesGrid.getAvailabilityProvider();
            availabilityProvider.setFilter(selectedField, searchField.getValue());
        });
    }

    @Override
    public void onAdd() {
        new AvailabilityWindow(EnumUtils.Action.ADD, MessagesUtils.NEW_AVAILABILITY, availabilitiesGrid).show();
    }

    @Override
    public void onDelete() {
        AvailabilityDto selectedAvailability = availabilitiesGrid.getSelectedAvailability();
        if (selectedAvailability != null) {
            new AvailabilityWindow(EnumUtils.Action.REMOVE, MessagesUtils.DELETE_AVAILABILITY, availabilitiesGrid).show();
        }
    }

    @Override
    public void onEdit() {
        AvailabilityDto selectedAvailability = availabilitiesGrid.getSelectedAvailability();
        if (selectedAvailability != null) {
            new AvailabilityWindow(EnumUtils.Action.EDIT, MessagesUtils.EDIT_AVAILABILITY + "</b>",
                    availabilitiesGrid).show();
        }
    }

    @Override
    public void onView() {
        AvailabilityDto selectedAvailability = availabilitiesGrid.getSelectedAvailability();
        if (selectedAvailability != null) {
            new AvailabilityWindow(EnumUtils.Action.VIEW, MessagesUtils.VIEW_AVAILABILITY + "</b>",
                    availabilitiesGrid).show();
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

    public AvailabilitiesGrid getAvailabilitiesGrid() {
        return availabilitiesGrid;
    }

    public void setAvailabilitiesGrid(AvailabilitiesGrid availabilitiesGrid) {
        this.availabilitiesGrid = availabilitiesGrid;
    }

    public Map<String, AvailabilityFields> getAvailabilityFields() {
        return availabilityFields;
    }

    public void setAvailabilityFields(Map<String, AvailabilityFields> availabilityFields) {
        this.availabilityFields = availabilityFields;
    }

    public AvailabilityFields getSelectedField() {
        return selectedField;
    }

    public void setSelectedField(AvailabilityFields selectedField) {
        this.selectedField = selectedField;
    }
}
