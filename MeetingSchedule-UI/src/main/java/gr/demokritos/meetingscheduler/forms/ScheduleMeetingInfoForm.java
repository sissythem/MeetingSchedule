package gr.demokritos.meetingscheduler.forms;

import com.vaadin.data.Binder;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import gr.demokritos.meetingscheduler.business.dto.WeekDto;
import gr.demokritos.meetingscheduler.textfields.IntegerField;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;
import gr.demokritos.meetingscheduler.windows.ScheduleMeetingWindow;

public class ScheduleMeetingInfoForm extends VerticalLayout {
    private ScheduleMeetingWindow window;
    private Label messageLbl = new Label("Give the week (start-end) you are interested in");
    private DateField weekStartDateTf = new DateField("");
    private DateField weekEndDateTf = new DateField("");
    private IntegerField thresholdTf = new IntegerField("");
    private Binder<WeekDto> binder = new Binder<>();

    public ScheduleMeetingInfoForm(ScheduleMeetingWindow window) {
        this.window = window;
        this.window.setWindowPage(1);
        addPlaceholders();
        addValidation();
        weekStartDateTf.setRequiredIndicatorVisible(false);
        weekEndDateTf.setRequiredIndicatorVisible(false);
        setUpFormLayout();
    }

    private void addPlaceholders() {
        weekStartDateTf.setPlaceholder(VaadinElementUtils.MEETING_DATE);
        weekEndDateTf.setPlaceholder(VaadinElementUtils.START_TIME);
        thresholdTf.setPlaceholder(VaadinElementUtils.MEMBERS_THRESHOLD);
        weekStartDateTf.addBlurListener(event -> {
            if (weekStartDateTf.getValue() != null) {
                weekStartDateTf.setPlaceholder(VaadinElementUtils.MEETING_START_DATE);
                weekStartDateTf.setCaption("");
                weekStartDateTf.setRequiredIndicatorVisible(false);
            }
        });

        weekStartDateTf.addFocusListener(event -> {
            weekStartDateTf.setPlaceholder("");
            weekStartDateTf.setCaption(VaadinElementUtils.MEETING_START_DATE);
            weekStartDateTf.setRequiredIndicatorVisible(true);
        });

        weekEndDateTf.addBlurListener(event -> {
            if (weekEndDateTf.getValue() != null) {
                weekEndDateTf.setPlaceholder(VaadinElementUtils.MEETING_END_DATE);
                weekEndDateTf.setCaption("");
                weekEndDateTf.setRequiredIndicatorVisible(false);
            }
        });

        weekEndDateTf.addFocusListener(event -> {
            weekEndDateTf.setPlaceholder("");
            weekEndDateTf.setCaption(VaadinElementUtils.MEETING_END_DATE);
            weekEndDateTf.setRequiredIndicatorVisible(true);
        });

        thresholdTf.addBlurListener(event -> {
            if (thresholdTf.getValue() != null) {
                thresholdTf.setPlaceholder(VaadinElementUtils.MEMBERS_THRESHOLD);
                thresholdTf.setCaption("");
            }
        });

        thresholdTf.addFocusListener(event -> {
            thresholdTf.setPlaceholder("");
            thresholdTf.setCaption(VaadinElementUtils.MEMBERS_THRESHOLD);
        });
    }

    private void addValidation() {
        binder.forField(weekStartDateTf).asRequired(MessagesUtils.MANDATORY_FIELDS).bind(WeekDto::getStartDate, WeekDto::setEndDate);
        binder.forField(weekEndDateTf).asRequired(MessagesUtils.MANDATORY_FIELDS).bind(WeekDto::getEndDate, WeekDto::setEndDate);
    }

    private void setUpFormLayout() {
        this.addComponents(messageLbl, weekStartDateTf, weekEndDateTf, thresholdTf);
    }

    public ScheduleMeetingWindow getWindow() {
        return window;
    }

    public void setWindow(ScheduleMeetingWindow window) {
        this.window = window;
    }

    public Label getMessageLbl() {
        return messageLbl;
    }

    public void setMessageLbl(Label messageLbl) {
        this.messageLbl = messageLbl;
    }

    public DateField getWeekStartDateTf() {
        return weekStartDateTf;
    }

    public void setWeekStartDateTf(DateField weekStartDateTf) {
        this.weekStartDateTf = weekStartDateTf;
    }

    public DateField getWeekEndDateTf() {
        return weekEndDateTf;
    }

    public void setWeekEndDateTf(DateField weekEndDateTf) {
        this.weekEndDateTf = weekEndDateTf;
    }

    public Binder<WeekDto> getBinder() {
        return binder;
    }

    public void setBinder(Binder<WeekDto> binder) {
        this.binder = binder;
    }

    public IntegerField getThresholdTf() {
        return thresholdTf;
    }

    public void setThresholdTf(IntegerField thresholdTf) {
        this.thresholdTf = thresholdTf;
    }
}
