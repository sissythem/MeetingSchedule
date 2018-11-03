package gr.demokritos.meetingscheduler.windows;

import com.vaadin.data.ValidationException;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.dto.UserDto;
import gr.demokritos.meetingscheduler.forms.UserForm;
import gr.demokritos.meetingscheduler.grids.UsersGrid;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;

public class UserWindow extends ParentWindow {
    private static final ThemeResource WARNING_ICON = new ThemeResource(GeneralUtils.WARN_ICON_PATH);
    private UsersGrid usersGrid;
    private EnumUtils.Action action;

    public UserWindow(EnumUtils.Action action, String caption, UsersGrid usersGrid) {
        super();
        this.setCaption(caption);
        this.action = action;
        this.usersGrid = usersGrid;
        setUpFormLayout();
        setUpWindow();
    }

    private void setUpFormLayout() {
        switch (action) {
            case ADD:
                formLayout = new UserForm(action);
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
        UserDto appUserDtoEdit = MeetingUI.getMeetingUI().getUserBean()
                .getUserById(usersGrid.getSelectedAppUser().getId());
        formLayout = new UserForm(action);
        getUserFormLayout().fillForm(appUserDtoEdit);
        ((UserForm) formLayout).addValidation(appUserDtoEdit);
    }

    private void onRemoveLayout() {
        formLayout = new VerticalLayout();
        HorizontalLayout hl = new HorizontalLayout();
        Image icon = new Image();
        icon.setIcon(WARNING_ICON);
        Label message = new Label(MessagesUtils.REMOVE_USER_MSG + " " + usersGrid.getSelectedAppUser().getUsername());
        hl.addComponents(icon, message);
        formLayout.addComponents(hl);
        getSaveBtn().setCaption(VaadinElementUtils.DELETE_BUTTON);
    }

    private void onViewLayout() {
        UserDto appUserDtoView = MeetingUI.getMeetingUI().getUserBean().getUserById(usersGrid.getSelectedAppUser().getId());
        formLayout = new UserForm(action);
        getSaveBtn().setCaption(VaadinElementUtils.OK_BUTTON);
        getCancelBtn().setVisible(false);
        getUserFormLayout().fillForm(appUserDtoView);
        getUserFormLayout().setReadOnly();
    }

    @Override
    public void onSave() {
        switch (action) {
            case ADD:
                addNewUser();
                break;
            case EDIT:
                saveEditedUser();
                break;
            case REMOVE:
                deleteUser();
                break;
            case VIEW:
                this.close();
                break;
        }

    }

    private void addNewUser() {
        UserDto appUserDto = setNewUserFields();
        try {
            ((UserForm) formLayout).getBinder().writeBean(appUserDto);
            MeetingUI.getMeetingUI().getUserBean().addUser(appUserDto);
            updateUserGrid();
        } catch (ValidationException e) {
            Message.show(MessagesUtils.ERROR, MessagesUtils.VALIDATION_MESSAGE, EnumUtils.MessageType.ERROR);
        }
    }

    private UserDto setNewUserFields() {
        UserDto appUserDto = new UserDto();
        appUserDto.setName(getUserFormLayout().getUserFirstNameTf().getValue());
        appUserDto.setLastName(getUserFormLayout().getUserLastNameTf().getValue());
        appUserDto.setUsername(getUserFormLayout().getUserNameTf().getValue());
        appUserDto.setPassword(getUserFormLayout().getUserPasswordTf().getValue());
        appUserDto.setEmail(getUserFormLayout().getEmailTf().getValue());
        return appUserDto;
    }

    private void saveEditedUser() {
        UserForm userFormLayout = (UserForm) formLayout;
        UserDto selectedAppUserDto = MeetingUI.getMeetingUI().getUserBean().getUserById(usersGrid.getSelectedAppUser().getId());
        try {
            userFormLayout.getBinder().writeBean(selectedAppUserDto);
            updateSelectedUser(selectedAppUserDto, userFormLayout);
            updateUserGrid();
        } catch (ValidationException e) {
            Message.show(MessagesUtils.ERROR, MessagesUtils.VALIDATION_MESSAGE, EnumUtils.MessageType.ERROR);
        }

    }

    private void updateSelectedUser(UserDto selectedAppUserDto, UserForm userFormLayout) {
        selectedAppUserDto.setName(getUserFormLayout().getUserFirstNameTf().getValue());
        selectedAppUserDto.setLastName(getUserFormLayout().getUserLastNameTf().getValue());
        selectedAppUserDto.setUsername(getUserFormLayout().getUserNameTf().getValue());
        selectedAppUserDto.setPassword(userFormLayout.getChangePasswordWindow().getChangedPassword());
        selectedAppUserDto.setEmail(getUserFormLayout().getEmailTf().getValue());
        MeetingUI.getMeetingUI().getUserBean().updateUser(selectedAppUserDto);
    }

    private void deleteUser() {
        MeetingUI.getMeetingUI().getUserBean().removeUser(
                MeetingUI.getMeetingUI().getUserBean().getUserById(usersGrid.getSelectedAppUser().getId()));
        updateUserGrid();
    }

    private void updateUserGrid() {
        usersGrid.getDataProvider().refreshAll();
        usersGrid.deselectAll();
        usersGrid.setSelectedAppUser(null);
        this.close();
    }

    private UserForm getUserFormLayout() {
        return (UserForm) formLayout;
    }

    public VerticalLayout getFormLayout() {
        return formLayout;
    }

    public void setFormLayout(VerticalLayout formLayout) {
        this.formLayout = formLayout;
    }

    public EnumUtils.Action getAction() {
        return action;
    }

    public void setAction(EnumUtils.Action action) {
        this.action = action;
    }

}
