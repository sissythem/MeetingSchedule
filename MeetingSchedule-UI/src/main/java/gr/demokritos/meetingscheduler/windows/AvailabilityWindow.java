package gr.demokritos.meetingscheduler.windows;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.dto.*;
import gr.demokritos.meetingscheduler.forms.AvailabilityForm;
import gr.demokritos.meetingscheduler.grids.AvailabilitiesGrid;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;

import java.time.LocalTime;

public class AvailabilityWindow extends ParentWindow {

    private static final ThemeResource WARNING_ICON = new ThemeResource(GeneralUtils.WARN_ICON_PATH);
    private EnumUtils.Action action;
    private AvailabilitiesGrid availabilitiesGrid;

    public AvailabilityWindow(EnumUtils.Action action, String title, AvailabilitiesGrid availabilitiesGrid) {
        super();
        this.action = action;
        this.setCaption(title);
        this.availabilitiesGrid = availabilitiesGrid;
        setUpFormLayout();
        setUpWindow();
    }

    private void setUpFormLayout() {
        switch (action) {
            case ADD:
                formLayout = new AvailabilityForm(action);
                break;
            case EDIT:
                onEditLayout();
                break;
            case REMOVE:
                onRemoveLayout();
                break;
            case VIEW:
                onViewLayout();
                break;
        }
    }

    private void onEditLayout() {
        AvailabilityDto availabilityDto = MeetingUI.getMeetingUI().getAvailabilityBean()
                .getAvailabilityById(availabilitiesGrid.getSelectedAvailability().getId());
        formLayout = new AvailabilityForm(action);
        ((AvailabilityForm)formLayout).fillForm(availabilityDto);
        ((AvailabilityForm)formLayout).addValidation();
    }

    private void onRemoveLayout() {
        formLayout = new VerticalLayout();
        HorizontalLayout hl = new HorizontalLayout();
        Image icon = new Image();
        icon.setIcon(WARNING_ICON);
        Label message = new Label(MessagesUtils.REMOVE_AVAILABILITY_MSG);
        hl.addComponents(icon, message);
        formLayout.addComponents(hl);
        getSaveBtn().setCaption(VaadinElementUtils.DELETE_BUTTON);
    }

    private void onViewLayout() {
        AvailabilityDto availabilityDto = MeetingUI.getMeetingUI().getAvailabilityBean()
                .getAvailabilityById(availabilitiesGrid.getSelectedAvailability().getId());
        formLayout = new AvailabilityForm(action);
        getSaveBtn().setCaption(VaadinElementUtils.OK_BUTTON);
        getCancelBtn().setVisible(false);
        ((AvailabilityForm)formLayout).fillForm(availabilityDto);
        ((AvailabilityForm)formLayout).setReadOnly();
    }

    @Override
    public void onSave() {
        switch (action) {
            case ADD:
                addNewAvailability();
                break;
            case EDIT:
                saveEditedAvailability();
                break;
            case REMOVE:
                deleteAvailability();
                break;
            case VIEW:
                this.close();
                break;
        }

    }

    private void addNewAvailability() {
        try {
            AvailabilityDto availabilityDto = new AvailabilityDto();
            availabilityDto = setAvailabilityFromFields(availabilityDto);
            ((AvailabilityForm) formLayout).getBinder().writeBean(availabilityDto);
            MeetingUI.getMeetingUI().getAvailabilityBean().addAvailability(availabilityDto);
            updateAvailabilitiesGrid();
        } catch (Exception e) {
            Message.show(MessagesUtils.ERROR, MessagesUtils.VALIDATION_MESSAGE, EnumUtils.MessageType.ERROR);
        }
    }

    private void saveEditedAvailability() {
        AvailabilityForm availabilityForm = (AvailabilityForm) formLayout;
        AvailabilityDto availabilityDto = MeetingUI.getMeetingUI().getAvailabilityBean().getAvailabilityById(availabilitiesGrid.getSelectedAvailability().getId());
        try {
            availabilityDto = setAvailabilityFromFields(availabilityDto);
            availabilityForm.getBinder().writeBean(availabilityDto);
            MeetingUI.getMeetingUI().getAvailabilityBean().updateAvailability(availabilityDto);
            updateAvailabilitiesGrid();
        } catch (Exception e) {
            Message.show(MessagesUtils.ERROR, MessagesUtils.VALIDATION_MESSAGE, EnumUtils.MessageType.ERROR);
        }

    }

    private void deleteAvailability() {
        MeetingUI.getMeetingUI().getAvailabilityBean().removeAvailability(
                MeetingUI.getMeetingUI().getAvailabilityBean().getAvailabilityById(availabilitiesGrid.getSelectedAvailability().getId()));
        updateAvailabilitiesGrid();
    }

    private AvailabilityDto setAvailabilityFromFields(AvailabilityDto availabilityDto) throws Exception {
        MemberDto memberDto = MeetingUI.getMeetingUI().getMemberBean().getMemberByNameAndLastName(
                ((AvailabilityForm)formLayout).getMemberNameCb().getValue(),
                ((AvailabilityForm)formLayout).getMemberLastNameCb().getValue());
        MeetingDto meetingDto = MeetingUI.getMeetingUI().getMeetingBean().getMeetingByName(((AvailabilityForm)formLayout).getMeetingNameCb().getValue());
        DayDto dayDto = MeetingUI.getMeetingUI().getDayBean().getDayByDate(((AvailabilityForm)formLayout).getMeetingDateTf().getValue());
        TimezoneDto timezoneDto = MeetingUI.getMeetingUI().getTimezoneBean().getTimezoneByStartAndEndTime(
                LocalTime.parse(((AvailabilityForm)formLayout).getStartTimeCb().getValue()),
                LocalTime.parse(((AvailabilityForm)formLayout).getEndTimeCb().getValue()));
        if(memberDto == null || meetingDto == null) {
            throw new Exception();
        }
        if(dayDto == null) {
            dayDto = new DayDto();
            dayDto.setDate(((AvailabilityForm)formLayout).getMeetingDateTf().getValue());
            dayDto.setDayOfWeek(dayDto.getDate().getDayOfWeek());
                dayDto.setName(dayDto.getDayOfWeek().toString());
            dayDto = MeetingUI.getMeetingUI().getDayBean().addDay(dayDto);
        }
        if(timezoneDto==null) {
            timezoneDto = new TimezoneDto(LocalTime.parse(((AvailabilityForm)formLayout).getStartTimeCb().getValue()),
                    LocalTime.parse(((AvailabilityForm)formLayout).getEndTimeCb().getValue()));
            timezoneDto = MeetingUI.getMeetingUI().getTimezoneBean().addTimezone(timezoneDto);
        }
        availabilityDto.setMemberDto(memberDto);
        availabilityDto.setDayDto(dayDto);
        availabilityDto.setTimezoneDto(timezoneDto);
        availabilityDto.setMeetingDto(meetingDto);
        return availabilityDto;
    }

    private void updateAvailabilitiesGrid() {
        availabilitiesGrid.getDataProvider().refreshAll();
        availabilitiesGrid.deselectAll();
        availabilitiesGrid.setSelectedAvailability(null);
        this.close();
    }

    public EnumUtils.Action getAction() {
        return action;
    }

    public void setAction(EnumUtils.Action action) {
        this.action = action;
    }

    public AvailabilitiesGrid getAvailabilitiesGrid() {
        return availabilitiesGrid;
    }

    public void setAvailabilitiesGrid(AvailabilitiesGrid availabilitiesGrid) {
        this.availabilitiesGrid = availabilitiesGrid;
    }
}
