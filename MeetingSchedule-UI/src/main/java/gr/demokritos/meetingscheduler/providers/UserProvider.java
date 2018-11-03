package gr.demokritos.meetingscheduler.providers;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.dto.UserDto;
import gr.demokritos.meetingscheduler.layouts.UsersGridLayout;
import gr.demokritos.meetingscheduler.utils.GeneralUtils;
import gr.demokritos.meetingscheduler.utils.SortStringGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserProvider extends AbstractBackEndDataProvider<UserDto, String> {
    private String filterText = "";
    private UsersGridLayout.UserFields selectedField;

    @Override
    public boolean isInMemory() {
        return false;
    }

    public void setFilter(UsersGridLayout.UserFields selectedField, String filterText) {
        Objects.requireNonNull(filterText, "Filter text cannot be null");
        if (Objects.equals(this.filterText, filterText.trim())) {
            return;
        }
        this.filterText = filterText.trim();
        this.selectedField = selectedField;
        refreshAll();
    }

    private boolean passesFilter(Object object, String filterText) {
        return object != null && object.toString().toLowerCase(Locale.ENGLISH).contains(filterText.toLowerCase());
    }

    @Override
    protected Stream<UserDto> fetchFromBackEnd(Query<UserDto, String> query) {
        UserDto loggedInUser = GeneralUtils.getUserFromSession();
        List<QuerySortOrder> sortOrders = query.getSortOrders();

        if (StringUtils.isBlank(filterText) && CollectionUtils.isEmpty(sortOrders)) { // empty filter, no sortOrders
            return addIndexToUsers(MeetingUI.getMeetingUI().getUserBean().getAllUsers()).stream();
        } else if (StringUtils.isBlank(filterText) && !CollectionUtils.isEmpty(sortOrders)) { // empty filter, have
            // sortOrders
            if (fetchAllAppUsersSorted(sortOrders) != null) {
                return addIndexToUsers(fetchAllAppUsersSorted(sortOrders)).stream();
            } else {
                return addIndexToUsers(MeetingUI.getMeetingUI().getUserBean().getAllUsers()).stream();
            }
        }

        // if filterText not empty and have sortOrders, retrieve filtered
        if (fetchAllAppUsersSorted(sortOrders) != null) {
            List<UserDto> appUsers = fetchAllAppUsersSorted(sortOrders);
            switch (selectedField) {
                case Name:
                    return addIndexToUsers(appUsers.stream().filter(user -> passesFilter(user.getName(), filterText))
                            .collect(Collectors.toList())).stream();
                case LastName:
                    return addIndexToUsers(appUsers.stream().filter(user -> passesFilter(user.getLastName(), filterText))
                            .collect(Collectors.toList())).stream();
                case Username:
                    return addIndexToUsers(appUsers.stream().filter(user -> passesFilter(user.getUsername(), filterText))
                            .collect(Collectors.toList())).stream();
                case Email:
                    return addIndexToUsers(appUsers.stream().filter(user -> passesFilter(user.getEmail(), filterText))
                            .collect(Collectors.toList())).stream();
                default: // all
                    return addIndexToUsers(
                            appUsers.stream().filter(user -> filterEveryField(user)).collect(Collectors.toList())).stream();
            }
        } else { // if filterText not empty and ordering by empty column, retrieve filtered
            List<UserDto> appUsers = MeetingUI.getMeetingUI().getUserBean().getAllUsers();
            if (!CollectionUtils.isEmpty(appUsers)) {
                return addIndexToUsers(
                        appUsers.stream().filter(user -> filterEveryField(user)).collect(Collectors.toList())).stream();
            } else {
                return null;
            }
        }

    }

    @Override
    protected int sizeInBackEnd(Query<UserDto, String> query) {
        if (fetchFromBackEnd(query) != null) {
            return (int) fetchFromBackEnd(query).count();
        } else {
            return 0;
        }
    }

    private List<UserDto> fetchAllAppUsersSorted(List<QuerySortOrder> sortOrders) {
        UserDto loggedInUser = GeneralUtils.getUserFromSession();
        List<UserDto> allUsers = MeetingUI.getMeetingUI().getUserBean().getAllUsers();
        List<UserDto> sortedUsers = MeetingUI.getMeetingUI().getUserBean().getAllUsers(SortStringGenerator.generate(sortOrders));

        // if the grid is sorted from a column which contains null values
        if (!CollectionUtils.isEmpty(sortedUsers) && !CollectionUtils.isEqualCollection(allUsers, sortedUsers)) {
            for (QuerySortOrder sortOrder : sortOrders) { // sorted nulls last
                if (sortOrder.getDirection() == SortDirection.DESCENDING) {
                    allUsers.removeAll(sortedUsers);
                    sortedUsers.addAll(allUsers);
                } else { // nulls first
                    allUsers.removeAll(sortedUsers);
                    sortedUsers.addAll(allUsers);
                    List<UserDto> sortedUsersNullsFirst = new ArrayList<>();
                    sortedUsersNullsFirst.addAll(allUsers);
                    sortedUsersNullsFirst.addAll(sortedUsers);
                    return sortedUsersNullsFirst;
                }
            }
        }

        return sortedUsers;
    }

    private boolean filterEveryField(UserDto user) {
        return passesFilter(user.getName(), filterText) || passesFilter(user.getUsername(), filterText)
                || passesFilter(user.getLastName(), filterText) || passesFilter(user.getEmail(), filterText);
    }

    private List<UserDto> addIndexToUsers(List<UserDto> usersDto) {
        int index = 1;
        if (!CollectionUtils.isEmpty(usersDto)) {
            for (int i = 0; i < usersDto.size(); i++) {
                usersDto.get(i).setIndex(index++);
            }
            return usersDto;
        }
        return new ArrayList<>();
    }
}
