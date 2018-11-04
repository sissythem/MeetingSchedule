package gr.demokritos.meetingscheduler.windows;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import gr.demokritos.meetingscheduler.interfaces.FormButtonsListener;
import gr.demokritos.meetingscheduler.utils.CssUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;

public abstract class ParentWindow extends Window implements FormButtonsListener {

    protected VerticalLayout windowLayout = new VerticalLayout();
    protected VerticalLayout formLayout = new VerticalLayout();
    private HorizontalLayout buttonsLayout = new HorizontalLayout();
    protected Button saveBtn = new Button(VaadinElementUtils.SAVE_BUTTON);
    protected Button cancelBtn = new Button(VaadinElementUtils.CANCEL_BUTTON);

    public ParentWindow() {
        super();
        this.setCaptionAsHtml(true);
        setModal(true);
        setResizable(false);
        setSizeUndefined();
    }

    public void setUpWindow() {
        windowLayout.removeAllComponents();
        initButtons();
        windowLayout.addComponents(formLayout, buttonsLayout);
        windowLayout.setComponentAlignment(buttonsLayout, Alignment.BOTTOM_CENTER);
        setContent(windowLayout);
    }

    public void setUpDrawerWindow() {
        initButtons();
        windowLayout.addComponents(formLayout, buttonsLayout);
        windowLayout.setComponentAlignment(buttonsLayout, Alignment.BOTTOM_CENTER);
        windowLayout.setExpandRatio(formLayout, 5);
        windowLayout.setExpandRatio(buttonsLayout, 1);
        setContent(windowLayout);
    }

    private void initButtons() {
        buttonsLayout.setMargin(true);
        saveBtn.setStyleName(CssUtils.SCSS_UPDATE_BUTTON);
        saveBtn.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        cancelBtn.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
        saveBtn.addClickListener(e -> onSave());
        cancelBtn.addClickListener(e -> onCancel());
        buttonsLayout.addComponents(saveBtn, cancelBtn);
    }

    public void show() {
        UI.getCurrent().addWindow(this);
    }

    @Override
    public void onSave() {

    }

    @Override
    public void onCancel() {
        this.close();
    }

    public VerticalLayout getFormLayout() {
        return formLayout;
    }

    public void setFormLayout(VerticalLayout formLayout) {
        this.formLayout = formLayout;
    }

    public HorizontalLayout getButtonsLayout() {
        return buttonsLayout;
    }

    public void setButtonsLayout(HorizontalLayout buttonsLayout) {
        this.buttonsLayout = buttonsLayout;
    }

    public VerticalLayout getWindowLayout() {
        return windowLayout;
    }

    public void setWindowLayout(VerticalLayout windowLayout) {
        this.windowLayout = windowLayout;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public void setSaveBtn(Button saveBtn) {
        this.saveBtn = saveBtn;
    }

    public Button getCancelBtn() {
        return cancelBtn;
    }

    public void setCancelBtn(Button cancelBtn) {
        this.cancelBtn = cancelBtn;
    }


}
