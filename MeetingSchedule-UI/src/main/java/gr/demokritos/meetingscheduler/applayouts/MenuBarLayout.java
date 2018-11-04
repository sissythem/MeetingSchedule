package gr.demokritos.meetingscheduler.applayouts;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import gr.demokritos.meetingscheduler.business.dto.UserDto;
import gr.demokritos.meetingscheduler.layouts.MeetingsGridLayout;
import gr.demokritos.meetingscheduler.layouts.MembersGridLayout;
import gr.demokritos.meetingscheduler.layouts.UsersGridLayout;
import gr.demokritos.meetingscheduler.panels.AppPanel;
import gr.demokritos.meetingscheduler.utils.CssUtils;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;
import kaesdingeling.hybridmenu.HybridMenu;
import kaesdingeling.hybridmenu.builder.HybridMenuBuilder;
import kaesdingeling.hybridmenu.builder.left.LeftMenuButtonBuilder;
import kaesdingeling.hybridmenu.data.DesignItem;
import kaesdingeling.hybridmenu.data.MenuConfig;
import kaesdingeling.hybridmenu.data.enums.EMenuComponents;
import kaesdingeling.hybridmenu.data.leftmenu.MenuButton;

import java.util.HashMap;
import java.util.Map;

public class MenuBarLayout extends VerticalLayout {
    private AppPanel appPanel;
    private ContentLayout contentLayout;
    private HybridMenu menuBar;
    private UserDto appUserDto = GeneralUtils.getUserFromSession();
    private Map<EnumUtils.LeftMenuSelection, MenuButton> menuItems = new HashMap<>();

    public MenuBarLayout(AppPanel appPanel, ContentLayout contentLayout) {
        super();
        setSizeFull();
        this.appPanel = appPanel;
        this.contentLayout = contentLayout;
        if (appUserDto != null) {
            buildAppMenu();
        }
    }

    private void buildAppMenu() {
        MenuConfig menuConfig = new MenuConfig();
        menuConfig.setDesignItem(DesignItem.getDarkDesign());
        menuBar = HybridMenuBuilder.get().setContent(new VerticalLayout()).setMenuComponent(EMenuComponents.ONLY_LEFT)
                .setConfig(menuConfig).build();
        menuBar.setSizeFull();
        buildLeftMenu();
        addComponent(menuBar);
        VaadinSession.getCurrent().setAttribute(HybridMenu.class, menuBar);
    }

    private void buildLeftMenu() {
        addUsersMenu();
        addMembersMenu();
        addMeetingsMenu();
        addAvailabilitiesMenu();
    }

    private void addUsersMenu() {
        MenuButton usersBtn = LeftMenuButtonBuilder.get().withCaption(GeneralUtils.USERS).withIcon(VaadinIcons.USERS).withClickListener(e -> {
                    makeButtonSelected(e);
                    contentLayout.removeAllComponents();
                    contentLayout.setLeftSelectedItem(EnumUtils.LeftMenuSelection.USERS);
                    contentLayout.addComponent(new UsersGridLayout(contentLayout));
                }).build();
        menuBar.addLeftMenuButton(usersBtn);
        menuItems.put(EnumUtils.LeftMenuSelection.USERS, usersBtn);
    }

    private void addMembersMenu() {
        MenuButton membersBtn = LeftMenuButtonBuilder.get().withCaption(GeneralUtils.MEMBERS)
                .withIcon(VaadinIcons.GROUP).withClickListener(e -> {
                    makeButtonSelected(e);
                    contentLayout.removeAllComponents();
                    contentLayout.setLeftSelectedItem(EnumUtils.LeftMenuSelection.MEMBERS);
                    contentLayout.addComponent(new MembersGridLayout(contentLayout));
                }).build();
        menuBar.addLeftMenuButton(membersBtn);
        menuItems.put(EnumUtils.LeftMenuSelection.MEMBERS, membersBtn);
    }

    private void addMeetingsMenu() {
        MenuButton meetingBtn = LeftMenuButtonBuilder.get().withCaption(GeneralUtils.MEETINGS)
                .withIcon(VaadinIcons.ACADEMY_CAP).withClickListener(e -> {
                    makeButtonSelected(e);
                    contentLayout.removeAllComponents();
                    contentLayout.setLeftSelectedItem(EnumUtils.LeftMenuSelection.MEETINGS);
                    contentLayout.addComponent(new MeetingsGridLayout(contentLayout));
                }).build();
        menuBar.addLeftMenuButton(meetingBtn);
        menuItems.put(EnumUtils.LeftMenuSelection.MEETINGS, meetingBtn);
    }

    private void addAvailabilitiesMenu() {
        MenuButton routesBtn = LeftMenuButtonBuilder.get().withCaption(GeneralUtils.AVAILABILITIES)
                .withIcon(VaadinIcons.CALENDAR).withClickListener(e -> {
                    makeButtonSelected(e);
                    contentLayout.removeAllComponents();
                    contentLayout.setLeftSelectedItem(EnumUtils.LeftMenuSelection.AVAILABILITIES);
//                    contentLayout.addComponent(new AvailabilitiesGridLayout(contentLayout));
                }).build();
        menuBar.addLeftMenuButton(routesBtn);
        menuItems.put(EnumUtils.LeftMenuSelection.AVAILABILITIES, routesBtn);
    }

    public void makeButtonSelected(Button.ClickEvent e) {
        if (e != null) {
            for (Map.Entry<EnumUtils.LeftMenuSelection, MenuButton> entry : menuItems.entrySet()) {
                entry.getValue().setStyleName(CssUtils.SCSS_SELECTED_MENU_ITEM,
                        e.getButton().getCaption().equals(entry.getValue().getCaption()));
            }
        }
    }

    public void removeButtonSelectedStyle(EnumUtils.LeftMenuSelection leftSelectedItem) {
        switch (leftSelectedItem) {
            case USERS:
                menuItems.get(EnumUtils.LeftMenuSelection.USERS).removeStyleName(CssUtils.SCSS_SELECTED_MENU_ITEM);
                break;
            case MEMBERS:
                menuItems.get(EnumUtils.LeftMenuSelection.MEMBERS).removeStyleName(CssUtils.SCSS_SELECTED_MENU_ITEM);
                break;
            case MEETINGS:
                menuItems.get(EnumUtils.LeftMenuSelection.MEETINGS).removeStyleName(CssUtils.SCSS_SELECTED_MENU_ITEM);
                break;
            case AVAILABILITIES:
                menuItems.get(EnumUtils.LeftMenuSelection.AVAILABILITIES).removeStyleName(CssUtils.SCSS_SELECTED_MENU_ITEM);
                break;
            default:
                break;
        }
    }

    public AppPanel getHomeLayout() {
        return appPanel;
    }

    public void setHomeLayout(AppPanel appPanel) {
        this.appPanel = appPanel;
    }

    public ContentLayout getContentLayout() {
        return contentLayout;
    }

    public void setContentLayout(ContentLayout contentLayout) {
        this.contentLayout = contentLayout;
    }

    public AppPanel getAppPanel() {
        return appPanel;
    }

    public void setAppPanel(AppPanel appPanel) {
        this.appPanel = appPanel;
    }

    public HybridMenu getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(HybridMenu menuBar) {
        this.menuBar = menuBar;
    }

    public Map<EnumUtils.LeftMenuSelection, MenuButton> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(Map<EnumUtils.LeftMenuSelection, MenuButton> menuItems) {
        this.menuItems = menuItems;
    }

    public UserDto getAppUserDto() {
        return appUserDto;
    }

    public void setAppUserDto(UserDto appUserDto) {
        this.appUserDto = appUserDto;
    }

}
