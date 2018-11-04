package gr.demokritos.meetingscheduler.forms;

import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.dto.MeetingDto;
import gr.demokritos.meetingscheduler.business.dto.TimezoneDto;
import gr.demokritos.meetingscheduler.textfields.IntegerField;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class MeetingForm extends VerticalLayout {
    private TextField nameTf = new TextField("");
    private RadioButtonGroup<Boolean> completedRb = new RadioButtonGroup<>(VaadinElementUtils.COMPLETED);
    private IntegerField durationTf = new IntegerField("");
    private DateField dateTf = new DateField("");
    private ComboBox<String> startTimeCb = new ComboBox<>("");
    private ComboBox<String> endTimeCb = new ComboBox<>("");

    private final Binder<MeetingDto> binder = new Binder<>();
    private EnumUtils.Action action;

    public MeetingForm(EnumUtils.Action action) {
        super();
        this.action = action;
        setUpRadioButton();
        setupComboBoxes();
        addPlaceHolders();
        if (action.equals(EnumUtils.Action.ADD)) {
            addValidation();
        }
        nameTf.setRequiredIndicatorVisible(false);
        durationTf.setRequiredIndicatorVisible(false);
        addComponent(setUpFormLayout());
    }

    public void setReadOnly() {
        nameTf.setReadOnly(true);
        completedRb.setReadOnly(true);
        dateTf.setReadOnly(true);
        startTimeCb.setReadOnly(true);
        endTimeCb.setReadOnly(true);
        durationTf.setReadOnly(true);
    }

    private void setUpRadioButton() {
        completedRb.setItems(Boolean.TRUE, Boolean.FALSE);
        completedRb.setItemCaptionGenerator((ItemCaptionGenerator<Boolean>) item -> item ? VaadinElementUtils.YES_BUTTON : VaadinElementUtils.NO_BUTTON);
        completedRb.setSelectedItem(Boolean.FALSE);
        completedRb.setRequiredIndicatorVisible(true);
        completedRb.setStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
    }

    public void fillForm(MeetingDto meetingDto) {
        durationTf.setValue(meetingDto.getDuration().toString());
        if (!StringUtils.isBlank(meetingDto.getName())) {
            nameTf.setValue(meetingDto.getName());
            nameTf.setCaption(VaadinElementUtils.MEETING_NAME);
        }
        if (meetingDto.getCompleted() != null) {
            completedRb.setValue(meetingDto.getCompleted());
        }
        if (meetingDto.getDate() != null) {
            dateTf.setValue(meetingDto.getDate());
            dateTf.setCaption(VaadinElementUtils.MEETING_DATE);
        }
        if (meetingDto.getStartTime() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GeneralUtils.TIME24HFORMAT);
            startTimeCb.setValue(meetingDto.getStartTime().format(formatter));
            startTimeCb.setCaption(VaadinElementUtils.START_TIME);
        }
        if (meetingDto.getEndTime() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GeneralUtils.TIME24HFORMAT);
            endTimeCb.setValue(meetingDto.getEndTime().format(formatter));
            endTimeCb.setCaption(VaadinElementUtils.END_TIME);
        }
        removeAllComponents();
        addComponent(setUpFormLayout());
    }

    private void setupComboBoxes() {
        startTimeCb.setEmptySelectionAllowed(false);
        endTimeCb.setEmptySelectionAllowed(false);

        if (!action.equals(EnumUtils.Action.VIEW)) {
            List<TimezoneDto> timezones = MeetingUI.getMeetingUI().getTimezoneBean().getAllTimezones();
            Pair<List<String>, List<String>> timezonesForCb = GeneralUtils.getTimezonesForComboboxes(timezones);
            if (timezonesForCb != null) {
                startTimeCb.setItems(timezonesForCb.getLeft());
                endTimeCb.setItems(timezonesForCb.getRight());
            }
        }
    }

    private void addPlaceHolders() {
        nameTf.setPlaceholder(VaadinElementUtils.MEETING_NAME);
        dateTf.setPlaceholder(VaadinElementUtils.MEETING_DATE);
        startTimeCb.setPlaceholder(VaadinElementUtils.START_TIME);
        endTimeCb.setPlaceholder(VaadinElementUtils.END_TIME);
        durationTf.setPlaceholder(VaadinElementUtils.MEETING_DURATION);
        if(!action.equals(EnumUtils.Action.VIEW)) {
            nameTf.addBlurListener(event -> {
                if (StringUtils.isBlank(nameTf.getValue())) {
                    nameTf.setPlaceholder(VaadinElementUtils.MEETING_NAME);
                    nameTf.setCaption("");
                    nameTf.setRequiredIndicatorVisible(false);
                }
            });

            nameTf.addFocusListener(event -> {
                nameTf.setPlaceholder("");
                nameTf.setCaption(VaadinElementUtils.MEETING_NAME);
                nameTf.setRequiredIndicatorVisible(true);
            });

            dateTf.addBlurListener(event -> {
                if (dateTf.getValue() != null) {
                    dateTf.setPlaceholder(VaadinElementUtils.MEETING_DATE);
                    dateTf.setCaption("");
                    dateTf.setRequiredIndicatorVisible(false);
                }
            });

            dateTf.addFocusListener(event -> {
                dateTf.setPlaceholder("");
                dateTf.setCaption(VaadinElementUtils.MEETING_DATE);
                dateTf.setRequiredIndicatorVisible(true);
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

            durationTf.addBlurListener(event -> {
                if (StringUtils.isBlank(durationTf.getValue())) {
                    durationTf.setPlaceholder(VaadinElementUtils.MEETING_DURATION);
                    durationTf.setCaption("");
                    durationTf.setRequiredIndicatorVisible(false);
                }
            });

            durationTf.addFocusListener(event -> {
                durationTf.setPlaceholder("");
                durationTf.setCaption(VaadinElementUtils.MEETING_DURATION);
                durationTf.setRequiredIndicatorVisible(true);
            });

        } else {
            nameTf.setCaption(VaadinElementUtils.MEETING_NAME);
            dateTf.setCaption(VaadinElementUtils.MEETING_DATE);
            startTimeCb.setCaption(VaadinElementUtils.START_TIME);
            endTimeCb.setCaption(VaadinElementUtils.END_TIME);
            durationTf.setCaption(VaadinElementUtils.MEETING_DURATION);
        }
    }

    public void addValidation() {
        binder.forField(nameTf).asRequired(MessagesUtils.MANDATORY_FIELDS).bind(MeetingDto::getName, MeetingDto::setName);
        binder.forField(durationTf).asRequired(MessagesUtils.MANDATORY_FIELDS).bind(meetingDto -> meetingDto.getDuration().toString(),
                (meetingDto, s) -> meetingDto.setDuration(Integer.parseInt(s)));
    }

    private VerticalLayout setUpFormLayout() {
        VerticalLayout formLayout = new VerticalLayout();
        if (action.equals(EnumUtils.Action.ADD)) {
            formLayout.addComponents(nameTf, durationTf, completedRb);
        } else {
            HorizontalLayout textFieldsLayout = new HorizontalLayout();
            VerticalLayout leftFormFields = new VerticalLayout();
            VerticalLayout rightFormFields = new VerticalLayout();
            leftFormFields.addComponents(nameTf, dateTf, completedRb);
            rightFormFields.addComponents(durationTf, startTimeCb, endTimeCb);
            textFieldsLayout.addComponents(leftFormFields, rightFormFields);
            formLayout.addComponents(textFieldsLayout);
        }
        return formLayout;
    }

    public TextField getNameTf() {
        return nameTf;
    }

    public void setNameTf(TextField nameTf) {
        this.nameTf = nameTf;
    }

    public RadioButtonGroup<Boolean> getCompletedRb() {
        return completedRb;
    }

    public void setCompletedRb(RadioButtonGroup<Boolean> completedRb) {
        this.completedRb = completedRb;
    }

    public DateField getDateTf() {
        return dateTf;
    }

    public IntegerField getDurationTf() {
        return durationTf;
    }

    public void setDurationTf(IntegerField durationTf) {
        this.durationTf = durationTf;
    }

    public void setDateTf(DateField dateTf) {
        this.dateTf = dateTf;
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

    public Binder<MeetingDto> getBinder() {
        return binder;
    }

    public EnumUtils.Action getAction() {
        return action;
    }

    public void setAction(EnumUtils.Action action) {
        this.action = action;
    }
}
