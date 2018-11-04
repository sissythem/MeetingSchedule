package gr.demokritos.meetingscheduler.forms;

import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.dto.AvailabilityDto;
import gr.demokritos.meetingscheduler.business.dto.MeetingDto;
import gr.demokritos.meetingscheduler.business.dto.MemberDto;
import gr.demokritos.meetingscheduler.business.dto.TimezoneDto;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AvailabilityForm extends VerticalLayout {
    private ComboBox<String> memberNameCb = new ComboBox<>("");
    private ComboBox<String> memberLastNameCb = new ComboBox<>("");
    private DateField meetingDateTf = new DateField("");
    private ComboBox<String> startTimeCb = new ComboBox<>("");
    private ComboBox<String> endTimeCb = new ComboBox<>("");
    private ComboBox<String> meetingNameCb = new ComboBox<>("");
    private RadioButtonGroup<Boolean> isAvailableRb = new RadioButtonGroup<>(VaadinElementUtils.AVAILABLE);
    private final Binder<AvailabilityDto> binder = new Binder<>();
    private EnumUtils.Action action;

    public AvailabilityForm(EnumUtils.Action action) {
        this.action = action;
        setUpRadioButton();
        setupComboBoxes();
        addPlaceHolders();
        if (action.equals(EnumUtils.Action.ADD)) {
            addValidation();
        }
        removeRequiredIndicators();
        addComponent(setUpFormLayout());
    }

    private void removeRequiredIndicators() {
        memberNameCb.setRequiredIndicatorVisible(false);
        memberLastNameCb.setRequiredIndicatorVisible(false);
        meetingDateTf.setRequiredIndicatorVisible(false);
        startTimeCb.setRequiredIndicatorVisible(false);
        endTimeCb.setRequiredIndicatorVisible(false);
        meetingNameCb.setRequiredIndicatorVisible(false);
    }

    private void setUpRadioButton() {
        isAvailableRb.setItems(Boolean.TRUE, Boolean.FALSE);
        isAvailableRb.setItemCaptionGenerator((ItemCaptionGenerator<Boolean>) item -> item ? VaadinElementUtils.YES_BUTTON : VaadinElementUtils.NO_BUTTON);
        isAvailableRb.setSelectedItem(Boolean.TRUE);
        isAvailableRb.setRequiredIndicatorVisible(true);
        isAvailableRb.setStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
    }

    public void setReadOnly() {
        memberNameCb.setReadOnly(true);
        memberLastNameCb.setReadOnly(true);
        meetingDateTf.setReadOnly(true);
        startTimeCb.setReadOnly(true);
        endTimeCb.setReadOnly(true);
        meetingNameCb.setReadOnly(true);
        isAvailableRb.setReadOnly(true);
    }

    public void fillForm(AvailabilityDto selectedAvailability) {
        isAvailableRb.setValue(selectedAvailability.getIsAvailable());
        if (selectedAvailability.getMemberDto() != null && !StringUtils.isBlank(selectedAvailability.getMemberDto().getName())) {
            memberNameCb.setValue(selectedAvailability.getMemberDto().getName());
            memberNameCb.setCaption(VaadinElementUtils.MEMBER_NAME);
        }
        if (selectedAvailability.getMemberDto() != null && !StringUtils.isBlank(selectedAvailability.getMemberDto().getLastName())) {
            memberLastNameCb.setValue(selectedAvailability.getMemberDto().getLastName());
            memberLastNameCb.setCaption(VaadinElementUtils.MEMBER_LAST_NAME);
        }
        if (selectedAvailability.getDayDto() != null && selectedAvailability.getDayDto().getDate() != null) {
            meetingDateTf.setValue(selectedAvailability.getDayDto().getDate());
            meetingDateTf.setCaption(VaadinElementUtils.MEETING_DATE);
        }
        if (selectedAvailability.getTimezoneDto() != null && selectedAvailability.getTimezoneDto().getStartTime() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GeneralUtils.TIME24HFORMAT);
            startTimeCb.setValue(selectedAvailability.getTimezoneDto().getStartTime().format(formatter));
            startTimeCb.setCaption(VaadinElementUtils.START_TIME);
        }
        if (selectedAvailability.getTimezoneDto() != null && selectedAvailability.getTimezoneDto().getEndTime() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GeneralUtils.TIME24HFORMAT);
            endTimeCb.setValue(selectedAvailability.getTimezoneDto().getEndTime().format(formatter));
            endTimeCb.setCaption(VaadinElementUtils.END_TIME);
        }
        if (selectedAvailability.getMeetingDto() != null && !StringUtils.isBlank(selectedAvailability.getMeetingDto().getName())) {
            meetingNameCb.setValue(selectedAvailability.getMeetingDto().getName());
            meetingNameCb.setCaption(VaadinElementUtils.MEETING_NAME);
        }
        removeAllComponents();
        addComponent(setUpFormLayout());
    }

    private void setupComboBoxes() {
        memberNameCb.setEmptySelectionAllowed(false);
        memberLastNameCb.setEmptySelectionAllowed(false);
        startTimeCb.setEmptySelectionAllowed(false);
        endTimeCb.setEmptySelectionAllowed(false);
        meetingNameCb.setEmptySelectionAllowed(false);

        if (!action.equals(EnumUtils.Action.VIEW)) {
            List<MemberDto> members = MeetingUI.getMeetingUI().getMemberBean().getAllMembers();
            List<TimezoneDto> timezones = MeetingUI.getMeetingUI().getTimezoneBean().getAllTimezones();
            List<MeetingDto> meetings = MeetingUI.getMeetingUI().getMeetingBean().getAllMeetings();
            if (!CollectionUtils.isEmpty(members)) {
                memberNameCb.setItems(members.stream().map(MemberDto::getName).collect(Collectors.toList()));
                memberLastNameCb.setItems(members.stream().map(MemberDto::getLastName).collect(Collectors.toList()));
            }
            if (!CollectionUtils.isEmpty(timezones)) {
                Pair<List<String>, List<String>> timezonesForCb = GeneralUtils.getTimezonesForComboboxes(timezones);
                if (timezonesForCb != null) {
                    startTimeCb.setItems(timezonesForCb.getLeft());
                    endTimeCb.setItems(timezonesForCb.getRight());
                }
            }
            if (!CollectionUtils.isEmpty(meetings)) {
                meetingNameCb.setItems(meetings.stream().map(MeetingDto::getName).collect(Collectors.toList()));
            }
        }
    }

    private void addPlaceHolders() {
        memberNameCb.setPlaceholder(VaadinElementUtils.MEMBER_NAME);
        memberLastNameCb.setPlaceholder(VaadinElementUtils.MEMBER_LAST_NAME);
        meetingDateTf.setPlaceholder(VaadinElementUtils.MEETING_DATE);
        startTimeCb.setPlaceholder(VaadinElementUtils.START_TIME);
        endTimeCb.setPlaceholder(VaadinElementUtils.END_TIME);
        meetingNameCb.setPlaceholder(VaadinElementUtils.MEETING_NAME);
        if(!action.equals(EnumUtils.Action.VIEW)) {
            memberNameCb.addBlurListener(event -> {
                if (StringUtils.isBlank(memberNameCb.getValue())) {
                    memberNameCb.setPlaceholder(VaadinElementUtils.MEMBER_NAME);
                    memberNameCb.setCaption("");
                    memberNameCb.setRequiredIndicatorVisible(false);
                }
            });

            memberNameCb.addFocusListener(event -> {
                memberNameCb.setPlaceholder("");
                memberNameCb.setCaption(VaadinElementUtils.MEMBER_NAME);
                memberNameCb.setRequiredIndicatorVisible(true);
            });

            memberLastNameCb.addBlurListener(event -> {
                if (StringUtils.isBlank(memberLastNameCb.getValue())) {
                    memberLastNameCb.setPlaceholder(VaadinElementUtils.MEMBER_LAST_NAME);
                    memberLastNameCb.setCaption("");
                    memberLastNameCb.setRequiredIndicatorVisible(false);
                }
            });

            memberLastNameCb.addFocusListener(event -> {
                memberLastNameCb.setPlaceholder("");
                memberLastNameCb.setCaption(VaadinElementUtils.MEMBER_LAST_NAME);
                memberLastNameCb.setRequiredIndicatorVisible(true);
            });

            meetingDateTf.addBlurListener(event -> {
                if (meetingDateTf.getValue() != null) {
                    meetingDateTf.setPlaceholder(VaadinElementUtils.MEETING_DATE);
                    meetingDateTf.setCaption("");
                    meetingDateTf.setRequiredIndicatorVisible(false);
                }
            });

            meetingDateTf.addFocusListener(event -> {
                meetingDateTf.setPlaceholder("");
                meetingDateTf.setCaption(VaadinElementUtils.MEETING_DATE);
                meetingDateTf.setRequiredIndicatorVisible(true);
            });

            startTimeCb.addBlurListener(event -> {
                if (StringUtils.isBlank(startTimeCb.getValue())) {
                    startTimeCb.setPlaceholder(VaadinElementUtils.START_TIME);
                    startTimeCb.setCaption("");
                    startTimeCb.setRequiredIndicatorVisible(false);
                }
            });

            startTimeCb.addFocusListener(event -> {
                startTimeCb.setPlaceholder("");
                startTimeCb.setCaption(VaadinElementUtils.START_TIME);
                startTimeCb.setRequiredIndicatorVisible(true);
            });

            endTimeCb.addBlurListener(event -> {
                if (StringUtils.isBlank(endTimeCb.getValue())) {
                    endTimeCb.setPlaceholder(VaadinElementUtils.END_TIME);
                    endTimeCb.setCaption("");
                    endTimeCb.setRequiredIndicatorVisible(false);
                }
            });

            endTimeCb.addFocusListener(event -> {
                endTimeCb.setPlaceholder("");
                endTimeCb.setCaption(VaadinElementUtils.END_TIME);
                endTimeCb.setRequiredIndicatorVisible(true);
            });

            meetingNameCb.addBlurListener(event -> {
                if (StringUtils.isBlank(meetingNameCb.getValue())) {
                    meetingNameCb.setPlaceholder(VaadinElementUtils.MEETING_NAME);
                    meetingNameCb.setCaption("");
                    meetingNameCb.setRequiredIndicatorVisible(false);
                }
            });

            meetingNameCb.addFocusListener(event -> {
                meetingNameCb.setPlaceholder("");
                meetingNameCb.setCaption(VaadinElementUtils.MEETING_NAME);
                meetingNameCb.setRequiredIndicatorVisible(true);
            });
        }
    }

    public void addValidation() {
        binder.forField(memberNameCb).asRequired(MessagesUtils.MANDATORY_FIELDS)
                .bind(availabilityDto -> availabilityDto.getMemberDto().getName(), (availabilityDto, s) -> availabilityDto.getMemberDto().setName(s));
        binder.forField(memberLastNameCb).asRequired(MessagesUtils.MANDATORY_FIELDS)
                .bind(availabilityDto -> availabilityDto.getMemberDto().getLastName(), (availabilityDto, s) -> availabilityDto.getMemberDto().setLastName(s));
        binder.forField(meetingDateTf).asRequired(MessagesUtils.MANDATORY_FIELDS)
                .bind(availabilityDto -> availabilityDto.getDayDto().getDate(), (availabilityDto, s) -> availabilityDto.getDayDto().setDate(s));
        binder.forField(isAvailableRb).asRequired("").bind(AvailabilityDto::getIsAvailable, AvailabilityDto::setIsAvailable);
        binder.forField(startTimeCb).asRequired(MessagesUtils.MANDATORY_FIELDS)
                .bind(availabilityDto -> availabilityDto.getTimezoneDto().getStartTime().format(DateTimeFormatter.ofPattern(GeneralUtils.TIME24HFORMAT)),
                        (availabilityDto, s) -> availabilityDto.getTimezoneDto().setStartTime(LocalTime.parse(s)));
        binder.forField(endTimeCb).asRequired(MessagesUtils.MANDATORY_FIELDS)
                .bind(availabilityDto -> availabilityDto.getTimezoneDto().getEndTime().format(DateTimeFormatter.ofPattern(GeneralUtils.TIME24HFORMAT)),
                        (availabilityDto, s) -> availabilityDto.getTimezoneDto().setEndTime(LocalTime.parse(s)));
        binder.forField(meetingNameCb).asRequired(MessagesUtils.MANDATORY_FIELDS)
                .bind(availabilityDto -> availabilityDto.getMeetingDto().getName(), (availabilityDto, s) -> availabilityDto.getMeetingDto().setName(s));
    }

    private VerticalLayout setUpFormLayout() {
        VerticalLayout formLayout = new VerticalLayout();
        HorizontalLayout textFieldsLayout = new HorizontalLayout();
        VerticalLayout leftFormFields = new VerticalLayout();
        VerticalLayout rightFormFields = new VerticalLayout();
        leftFormFields.addComponents(memberNameCb, memberLastNameCb, meetingDateTf);
        rightFormFields.addComponents(meetingNameCb, startTimeCb, endTimeCb, isAvailableRb);
        textFieldsLayout.addComponents(leftFormFields, rightFormFields);
        formLayout.addComponents(textFieldsLayout);
        return formLayout;
    }

    public ComboBox<String> getMemberNameCb() {
        return memberNameCb;
    }

    public void setMemberNameCb(ComboBox<String> memberNameCb) {
        this.memberNameCb = memberNameCb;
    }

    public ComboBox<String> getMemberLastNameCb() {
        return memberLastNameCb;
    }

    public void setMemberLastNameCb(ComboBox<String> memberLastNameCb) {
        this.memberLastNameCb = memberLastNameCb;
    }

    public DateField getMeetingDateTf() {
        return meetingDateTf;
    }

    public void setMeetingDateTf(DateField meetingDateTf) {
        this.meetingDateTf = meetingDateTf;
    }

    public ComboBox<String> getStartTimeCb() {
        return startTimeCb;
    }

    public void setStartTimeCb(ComboBox<String> startTimeCb) {
        this.startTimeCb = startTimeCb;
    }

    public ComboBox<String> getEndTimeCb() {
        return endTimeCb;
    }

    public void setEndTimeCb(ComboBox<String> endTimeCb) {
        this.endTimeCb = endTimeCb;
    }

    public ComboBox<String> getMeetingNameCb() {
        return meetingNameCb;
    }

    public void setMeetingNameCb(ComboBox<String> meetingNameCb) {
        this.meetingNameCb = meetingNameCb;
    }

    public RadioButtonGroup<Boolean> getIsAvailableRb() {
        return isAvailableRb;
    }

    public void setIsAvailableRb(RadioButtonGroup<Boolean> isAvailableRb) {
        this.isAvailableRb = isAvailableRb;
    }

    public Binder<AvailabilityDto> getBinder() {
        return binder;
    }

    public EnumUtils.Action getAction() {
        return action;
    }

    public void setAction(EnumUtils.Action action) {
        this.action = action;
    }
}
