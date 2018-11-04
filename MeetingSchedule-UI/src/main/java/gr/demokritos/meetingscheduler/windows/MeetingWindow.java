package gr.demokritos.meetingscheduler.windows;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.dto.MeetingDto;
import gr.demokritos.meetingscheduler.forms.MeetingForm;
import gr.demokritos.meetingscheduler.grids.MeetingsGrid;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalTime;

public class MeetingWindow extends ParentWindow {
    private static final ThemeResource WARNING_ICON = new ThemeResource(GeneralUtils.WARN_ICON_PATH);
    private EnumUtils.Action action;
    private MeetingsGrid meetingsGrid;

    public MeetingWindow(EnumUtils.Action action, String title, MeetingsGrid meetingsGrid) {
        super();
        this.action = action;
        this.setCaption(title);
        this.meetingsGrid = meetingsGrid;
        setUpFormLayout();
        setUpWindow();
    }

    private void setUpFormLayout() {
        switch (action) {
            case ADD:
                formLayout = new MeetingForm(action);
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
        MeetingDto meetingDto = MeetingUI.getMeetingUI().getMeetingBean()
                .getMeetingById(meetingsGrid.getSelectedMeeting().getId());
        formLayout = new MeetingForm(action);
        ((MeetingForm) formLayout).fillForm(meetingDto);
        ((MeetingForm) formLayout).addValidation();
    }

    private void onRemoveLayout() {
        formLayout = new VerticalLayout();
        HorizontalLayout hl = new HorizontalLayout();
        Image icon = new Image();
        icon.setIcon(WARNING_ICON);
        Label message = new Label(MessagesUtils.DELETE_MEETING_WARNING + " "
                + meetingsGrid.getSelectedMeeting().getName() + MessagesUtils.QUESTION_MARK);
        hl.addComponents(icon, message);
        formLayout.addComponents(hl);
        getSaveBtn().setCaption(VaadinElementUtils.DELETE_BUTTON);
    }

    private void onViewLayout() {
        MeetingDto meetingDto = MeetingUI.getMeetingUI().getMeetingBean()
                .getMeetingById(meetingsGrid.getSelectedMeeting().getId());
        formLayout = new MeetingForm(action);
        getSaveBtn().setCaption(VaadinElementUtils.OK_BUTTON);
        getCancelBtn().setVisible(false);
        ((MeetingForm) formLayout).fillForm(meetingDto);
        ((MeetingForm) formLayout).setReadOnly();
    }

    @Override
    public void onSave() {
        switch (action) {
            case ADD:
                addNewMeeting();
                break;
            case EDIT:
                saveEditedMeeting();
                break;
            case REMOVE:
                deleteMeeting();
                break;
            case VIEW:
                this.close();
                break;
        }

    }

    private void addNewMeeting() {
        try {
            MeetingDto meetingDto = new MeetingDto();
            meetingDto = setMeetingFromFields(meetingDto, EnumUtils.Action.ADD);
            ((MeetingForm) formLayout).getBinder().writeBean(meetingDto);
            MeetingUI.getMeetingUI().getMeetingBean().addMeeting(meetingDto);
            updateMeetingsGrid();
        } catch (Exception e) {
            Message.show(MessagesUtils.ERROR, MessagesUtils.VALIDATION_MESSAGE, EnumUtils.MessageType.ERROR);
        }
    }

    private void saveEditedMeeting() {
        MeetingForm meetingForm = (MeetingForm) formLayout;
        MeetingDto meetingDto = MeetingUI.getMeetingUI().getMeetingBean().getMeetingById(meetingsGrid.getSelectedMeeting().getId());
        try {
            meetingDto = setMeetingFromFields(meetingDto, EnumUtils.Action.EDIT);
            meetingForm.getBinder().writeBean(meetingDto);
            MeetingUI.getMeetingUI().getMeetingBean().updateMeeting(meetingDto);
            updateMeetingsGrid();
        } catch (Exception e) {
            Message.show(MessagesUtils.ERROR, MessagesUtils.VALIDATION_MESSAGE, EnumUtils.MessageType.ERROR);
        }
    }

    private void deleteMeeting() {
        MeetingUI.getMeetingUI().getMeetingBean().removeMeeting(
                MeetingUI.getMeetingUI().getMeetingBean().getMeetingById(meetingsGrid.getSelectedMeeting().getId()));
        updateMeetingsGrid();
    }

    private MeetingDto setMeetingFromFields(MeetingDto meetingDto, EnumUtils.Action action) throws Exception {
        MeetingForm meetingForm = (MeetingForm) formLayout;
        meetingDto.setName(meetingForm.getNameTf().getValue());
        meetingDto.setCompleted(meetingForm.getCompletedRb().getValue());
        if(!StringUtils.isBlank(meetingForm.getDurationTf().getValue())) {
            meetingDto.setDuration(Integer.parseInt(meetingForm.getDurationTf().getValue()));
        }
        if(!action.equals(EnumUtils.Action.ADD)) {
            meetingDto.setDate(meetingForm.getDateTf().getValue());
            String startTimeString = meetingForm.getStartTimeCb().getValue();
            String endTimeString = meetingForm.getEndTimeCb().getValue();
            LocalTime startTime = null;
            LocalTime endTime = null;
            if(!StringUtils.isBlank(startTimeString)) {
                startTime = LocalTime.parse(startTimeString);
            }
            if(!StringUtils.isBlank(endTimeString)) {
                endTime = LocalTime.parse(endTimeString);
            }
            meetingDto.setStartTime(startTime);
            meetingDto.setEndTime(endTime);
        }
        return meetingDto;
    }

    private void updateMeetingsGrid() {
        meetingsGrid.getDataProvider().refreshAll();
        meetingsGrid.deselectAll();
        meetingsGrid.setSelectedMeeting(null);
        this.close();
    }

    public EnumUtils.Action getAction() {
        return action;
    }

    public void setAction(EnumUtils.Action action) {
        this.action = action;
    }

    public MeetingsGrid getMeetingsGrid() {
        return meetingsGrid;
    }

    public void setMeetingsGrid(MeetingsGrid meetingsGrid) {
        this.meetingsGrid = meetingsGrid;
    }
}
