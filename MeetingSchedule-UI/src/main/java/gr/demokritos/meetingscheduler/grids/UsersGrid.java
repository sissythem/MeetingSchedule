package gr.demokritos.meetingscheduler.grids;

import com.vaadin.ui.Grid;
import gr.demokritos.meetingscheduler.business.dto.UserDto;
import gr.demokritos.meetingscheduler.layouts.UsersGridLayout;
import gr.demokritos.meetingscheduler.providers.UserProvider;

public class UsersGrid extends Grid<UserDto> {

    private UsersGridLayout usersGridLayout;
    private UserProvider userProvider = new UserProvider();
    private UserDto selectedAppUser;

    public UsersGrid(UsersGridLayout usersGridLayout) {
        super(UserDto.class);
        this.usersGridLayout = usersGridLayout;
        this.setDataProvider(userProvider);
        addItemClickListener(event -> setSelectedAppUser(event.getItem()));
    }

    public UsersGridLayout getUsersGridLayout() {
        return usersGridLayout;
    }

    public void setUsersGridLayout(UsersGridLayout usersGridLayout) {
        this.usersGridLayout = usersGridLayout;
    }

    public UserProvider getUserProvider() {
        return userProvider;
    }

    public void setUserProvider(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    public UserDto getSelectedAppUser() {
        return selectedAppUser;
    }

    public void setSelectedAppUser(UserDto selectedAppUser) {
        this.selectedAppUser = selectedAppUser;
    }
}
