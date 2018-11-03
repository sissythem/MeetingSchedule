package gr.demokritos.meetingscheduler.applayouts;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.dto.UserDto;
import gr.demokritos.meetingscheduler.utils.CssUtils;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;
import gr.demokritos.meetingscheduler.windows.LogoutWindow;
import gr.demokritos.meetingscheduler.windows.ProfileWindow;

public class UserMenuBar extends MenuBar {
    private TopBarLayout topBarLayout;
    private MenuItem userItem;
    private MenuItem profileItem;
    private MenuItem helpItem;
    private MenuItem logoutItem;
    private UserDto userDto;

    public UserMenuBar(TopBarLayout topBarLayout) {
        super();
        this.topBarLayout = topBarLayout;
        this.addStyleName(CssUtils.SCSS_TOP_MENU_BAR);
        this.userDto = GeneralUtils.getUserFromSession();
        if (userDto != null) {
            addUserMenuItem();
            addProfileMenuItem();
            addHelpMenuItem();
            addLogoutMenuItem();
        }
    }

    private void addUserMenuItem() {
        userItem = this.addItem(userDto.getName(), VaadinIcons.USER, null);
        this.userItem.setStyleName(CssUtils.SCSS_USER_MENU_ITEM);
    }

    private void addProfileMenuItem() {
        profileItem = userItem.addItem(GeneralUtils.PROFILE, VaadinIcons.USER_CARD,
                command -> goToProfile());
    }

    private void addHelpMenuItem() {
        helpItem = userItem.addItem(GeneralUtils.HELP, VaadinIcons.QUESTION_CIRCLE,
                command -> goToHelpLayout());
    }

    private void addLogoutMenuItem() {
        logoutItem = userItem.addItem(GeneralUtils.LOGOUT, VaadinIcons.SIGN_OUT, command -> {
            selectTopMenuItem(EnumUtils.TopMenuSelection.LOGOUT);
            MeetingUI.getMeetingUI().addWindow(new LogoutWindow(GeneralUtils.LOGOUT));
        });
    }

    private void goToProfile() {
        unselectLeftMenuItem();
        selectTopMenuItem(EnumUtils.TopMenuSelection.PROFILE);
        MeetingUI.getMeetingUI().addWindow(
                new ProfileWindow(GeneralUtils.getUserFromSession(), GeneralUtils.PROFILE));
    }

    private void goToHelpLayout() {
        unselectLeftMenuItem();
        selectTopMenuItem(EnumUtils.TopMenuSelection.HELP);
        topBarLayout.getAppLayout().getAppPanel().getContentLayout()
                .addComponent(new HelpLayout(topBarLayout.getAppLayout().getAppPanel().getContentLayout()));
    }

    private void selectTopMenuItem(EnumUtils.TopMenuSelection topSelectedItem) {
        topBarLayout.getAppLayout().getAppPanel().getContentLayout().removeAllComponents();
        switch (topSelectedItem) {
            case HELP:
                topBarLayout.getAppLayout().getAppPanel().getContentLayout().setTopSelectedItem(EnumUtils.TopMenuSelection.HELP);
                break;
            case LOGOUT:
                topBarLayout.getAppLayout().getAppPanel().getContentLayout().setTopSelectedItem(EnumUtils.TopMenuSelection.LOGOUT);
                break;
            case PROFILE:
                topBarLayout.getAppLayout().getAppPanel().getContentLayout().setTopSelectedItem(EnumUtils.TopMenuSelection.PROFILE);
                break;
            default:
                break;
        }
    }

    private void unselectLeftMenuItem() {
        EnumUtils.LeftMenuSelection leftSelectedItem = topBarLayout.getAppLayout().getAppPanel().getContentLayout()
                .getLeftSelectedItem();
        if (leftSelectedItem != null) {
            topBarLayout.getAppLayout().getAppPanel().getMenuBarLayout().removeButtonSelectedStyle(leftSelectedItem);
            topBarLayout.getAppLayout().getAppPanel().getContentLayout().setLeftSelectedItem(null);
        }
    }

    public TopBarLayout getTopBar() {
        return topBarLayout;
    }

    public void setTopBar(TopBarLayout topBarLayout) {
        this.topBarLayout = topBarLayout;
    }

    public MenuItem getUserItem() {
        return userItem;
    }

    public void setUserItem(MenuItem userItem) {
        this.userItem = userItem;
    }

    public MenuItem getProfileItem() {
        return profileItem;
    }

    public void setProfileItem(MenuItem profileItem) {
        this.profileItem = profileItem;
    }

    public MenuItem getHelpItem() {
        return helpItem;
    }

    public void setHelpItem(MenuItem helpItem) {
        this.helpItem = helpItem;
    }

    public MenuItem getLogoutItem() {
        return logoutItem;
    }

    public void setLogoutItem(MenuItem logoutItem) {
        this.logoutItem = logoutItem;
    }

    public UserDto getAppUserDto() {
        return userDto;
    }

    public void setAppUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

}
