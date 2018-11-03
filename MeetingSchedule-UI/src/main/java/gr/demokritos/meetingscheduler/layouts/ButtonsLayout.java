package gr.demokritos.meetingscheduler.layouts;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import gr.demokritos.meetingscheduler.applayouts.ContentLayout;
import gr.demokritos.meetingscheduler.utils.CssUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;

public class ButtonsLayout extends HorizontalLayout {
    private ContentLayout contentLayout;
    private MeetingGridLayout meetingGridLayout;
    private Button addBtn = new Button(VaadinElementUtils.ADD_BUTTON);
    private Button editBtn = new Button(VaadinElementUtils.EDIT_BUTTON);
    private Button deleteBtn = new Button(VaadinElementUtils.DELETE_BUTTON);
    private Button viewBtn = new Button(VaadinElementUtils.VIEW_BUTTON);

    public ButtonsLayout(MeetingGridLayout meetingGridLayout, ContentLayout contentLayout) {
        super();
        this.meetingGridLayout = meetingGridLayout;
        this.contentLayout = contentLayout;
        initButtons();
    }

    private void initButtons() {
        this.deleteBtn.setStyleName(MaterialTheme.BUTTON_DANGER);
        this.addBtn.addStyleName(CssUtils.SCSS_UPDATE_BUTTON);
        addComponents(addBtn, editBtn, viewBtn, deleteBtn);
    }

    public void disableButtons() {
        this.addBtn.setEnabled(false);
        this.editBtn.setEnabled(false);
        this.viewBtn.setEnabled(false);
        this.deleteBtn.setEnabled(false);
    }

    public void enableButtons() {
        this.addBtn.setEnabled(true);
        this.editBtn.setEnabled(true);
        this.viewBtn.setEnabled(true);
        this.deleteBtn.setEnabled(true);
    }

    public ContentLayout getContentLayout() {
        return contentLayout;
    }

    public void setContentLayout(ContentLayout contentLayout) {
        this.contentLayout = contentLayout;
    }

    public Button getAddBtn() {
        return addBtn;
    }

    public void setAddBtn(Button addBtn) {
        this.addBtn = addBtn;
    }

    public Button getEditBtn() {
        return editBtn;
    }

    public void setEditBtn(Button editBtn) {
        this.editBtn = editBtn;
    }

    public Button getDeleteBtn() {
        return deleteBtn;
    }

    public void setDeleteBtn(Button deleteBtn) {
        this.deleteBtn = deleteBtn;
    }

    public Button getViewBtn() {
        return viewBtn;
    }

    public void setViewBtn(Button viewBtn) {
        this.viewBtn = viewBtn;
    }

    public MeetingGridLayout getMeetingGridLayout() {
        return meetingGridLayout;
    }

    public void setMeetingGridLayout(MeetingGridLayout meetingGridLayout) {
        this.meetingGridLayout = meetingGridLayout;
    }

}
