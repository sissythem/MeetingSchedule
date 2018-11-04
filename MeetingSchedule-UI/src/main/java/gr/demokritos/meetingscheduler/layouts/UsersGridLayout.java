package gr.demokritos.meetingscheduler.layouts;

import gr.demokritos.meetingscheduler.applayouts.ContentLayout;
import gr.demokritos.meetingscheduler.business.dto.UserDto;
import gr.demokritos.meetingscheduler.comboboxes.SearchCombobox;
import gr.demokritos.meetingscheduler.grids.UsersGrid;
import gr.demokritos.meetingscheduler.providers.UserProvider;
import gr.demokritos.meetingscheduler.textfields.SearchField;
import gr.demokritos.meetingscheduler.utils.EnumUtils;
import gr.demokritos.meetingscheduler.utils.MessagesUtils;
import gr.demokritos.meetingscheduler.utils.VaadinElementUtils;
import gr.demokritos.meetingscheduler.windows.UserWindow;

import java.util.HashMap;
import java.util.Map;

public class UsersGridLayout extends MeetingGridLayout {
    public enum UserFields {
        All, Name, LastName, Username, Email
    }

    private ContentLayout contentLayout;
    private UsersGrid usersGrid = new UsersGrid(this);
    private Map<String, UserFields> userFields = new HashMap<>();
    private UserFields selectedField = UserFields.All;

    public UsersGridLayout(ContentLayout contentLayout) {
        super(contentLayout);
        this.contentLayout = contentLayout;
        setUpColumns();
        addValueChangeListenerToSearchComboBox();
        setUpSearchField();
        setUpLayout(usersGrid);
    }

    @Override
    public void setUpColumns() {
        usersGrid.setColumns("name", "lastName", "username", "email");
//        usersGrid.getColumn(VaadinElementUtils.INDEX).setSortable(false)
//                .setCaption(VaadinElementUtils.INDEX).setExpandRatio(1);
        usersGrid.getColumn("name").setCaption(VaadinElementUtils.NAME)
                .setSortProperty("u.name").setExpandRatio(7);
        usersGrid.getColumn("lastName").setCaption(VaadinElementUtils.LAST_NAME)
                .setSortProperty("u.lastName").setExpandRatio(7);
        usersGrid.getColumn("username").setCaption(VaadinElementUtils.USER_NAME)
                .setSortProperty("u.username").setExpandRatio(7);
        usersGrid.getColumn("email").setCaption(VaadinElementUtils.EMAIL)
                .setSortProperty("u.email").setExpandRatio(7);

        putFieldsInMap();
    }

    private void putFieldsInMap() {
        userFields.put(VaadinElementUtils.NAME, UserFields.Name);
        userFields.put(VaadinElementUtils.LAST_NAME, UserFields.LastName);
        userFields.put(VaadinElementUtils.USER_NAME, UserFields.Username);
        userFields.put(VaadinElementUtils.EMAIL, UserFields.Email);
    }

    @Override
    public void addValueChangeListenerToSearchComboBox() {
        SearchCombobox<String> searchComboBox = getSearchLayout().getSearchComboBox();
        searchComboBox.addValueChangeListener(e -> {
            String selectedValue = searchComboBox.getValue();
            if (userFields.containsKey(selectedValue)) {
                selectedField = userFields.get(selectedValue);
            } else {
                selectedField = UserFields.All;
            }
        });
    }

    @Override
    public void setUpSearchField() {
        SearchField searchField = getSearchLayout().getSearchField();
        searchField.addValueChangeListener(event -> {
            UserProvider userProvider = usersGrid.getUserProvider();
            userProvider.setFilter(selectedField, searchField.getValue());
        });
    }

    @Override
    public void onAdd() {
        new UserWindow(EnumUtils.Action.ADD, MessagesUtils.NEW_USER, usersGrid).show();
    }

    @Override
    public void onDelete() {
        UserDto selectedUser = usersGrid.getSelectedAppUser();
        if (selectedUser != null) {
            new UserWindow(EnumUtils.Action.REMOVE, MessagesUtils.REMOVE_USER, usersGrid).show();
        }
    }

    @Override
    public void onEdit() {
        UserDto selectedUser = usersGrid.getSelectedAppUser();
        if (selectedUser != null) {
            new UserWindow(EnumUtils.Action.EDIT, MessagesUtils.EDIT_USER + " " + "<b>" + selectedUser.getUsername() + "</b>",
                    usersGrid).show();
        }
    }

    @Override
    public void onView() {
        UserDto selectedUser = usersGrid.getSelectedAppUser();
        if (selectedUser != null) {
            new UserWindow(EnumUtils.Action.VIEW, MessagesUtils.VIEW_USER + " " + "<b>" + selectedUser.getUsername() + "</b>",
                    usersGrid).show();
        }
    }

    @Override
    public ContentLayout getContentLayout() {
        return contentLayout;
    }

    @Override
    public void setContentLayout(ContentLayout contentLayout) {
        this.contentLayout = contentLayout;
    }

    public UsersGrid getUsersGrid() {
        return usersGrid;
    }

    public void setUsersGrid(UsersGrid usersGrid) {
        this.usersGrid = usersGrid;
    }

    public Map<String, UserFields> getUserFields() {
        return userFields;
    }

    public void setUserFields(Map<String, UserFields> userFields) {
        this.userFields = userFields;
    }

    public UserFields getSelectedField() {
        return selectedField;
    }

    public void setSelectedField(UserFields selectedField) {
        this.selectedField = selectedField;
    }
}
