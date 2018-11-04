package gr.demokritos.meetingscheduler.providers;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.dto.MemberDto;
import gr.demokritos.meetingscheduler.business.dto.UserDto;
import gr.demokritos.meetingscheduler.layouts.MembersGridLayout;
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

public class MemberProvider extends AbstractBackEndDataProvider<MemberDto, String> {
    private String filterText = "";
    private MembersGridLayout.MemberFields selectedField;

    @Override
    public boolean isInMemory() {
        return false;
    }

    public void setFilter(MembersGridLayout.MemberFields selectedField, String filterText) {
        Objects.requireNonNull(filterText, "Filter text cannot be null");
        if(Objects.equals(this.filterText, filterText.trim())) {
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
    protected Stream<MemberDto> fetchFromBackEnd(Query<MemberDto, String> query) {
        List<QuerySortOrder> sortOrders = query.getSortOrders();

        if (StringUtils.isBlank(filterText) && CollectionUtils.isEmpty(sortOrders)) { // empty filter, no sortOrders
            return addIndexToMembers(MeetingUI.getMeetingUI().getMemberBean().getAllMembers()).stream();
        } else if (StringUtils.isBlank(filterText) && !CollectionUtils.isEmpty(sortOrders)) { // empty filter, have
            // sortOrders
            if (fetchAllMembersSorted(sortOrders) != null) {
                return addIndexToMembers(fetchAllMembersSorted(sortOrders)).stream();
            } else {
                return addIndexToMembers(MeetingUI.getMeetingUI().getMemberBean().getAllMembers()).stream();
            }
        }

        // if filterText not empty and have sortOrders, retrieve filtered
        if (fetchAllMembersSorted(sortOrders) != null) {
            List<MemberDto> members = fetchAllMembersSorted(sortOrders);
            switch (selectedField) {
                case Name:
                    return addIndexToMembers(members.stream().filter(member -> passesFilter(member.getName(), filterText))
                            .collect(Collectors.toList())).stream();
                case LastName:
                    return addIndexToMembers(members.stream().filter(member -> passesFilter(member.getLastName(), filterText))
                            .collect(Collectors.toList())).stream();
                case Email:
                    return addIndexToMembers(members.stream().filter(member -> passesFilter(member.getEmail(), filterText))
                            .collect(Collectors.toList())).stream();
                default: // all
                    return addIndexToMembers(
                            members.stream().filter(member -> filterEveryField(member)).collect(Collectors.toList())).stream();
            }
        } else { // if filterText not empty and ordering by empty column, retrieve filtered
            List<MemberDto> members = MeetingUI.getMeetingUI().getMemberBean().getAllMembers();
            if (!CollectionUtils.isEmpty(members)) {
                return addIndexToMembers(
                        members.stream().filter(member -> filterEveryField(member)).collect(Collectors.toList())).stream();
            } else {
                return null;
            }
        }

    }

    @Override
    protected int sizeInBackEnd(Query<MemberDto, String> query) {
        if (fetchFromBackEnd(query) != null) {
            return (int) fetchFromBackEnd(query).count();
        } else {
            return 0;
        }
    }

    private List<MemberDto> fetchAllMembersSorted(List<QuerySortOrder> sortOrders) {
        List<MemberDto> allMembers = MeetingUI.getMeetingUI().getMemberBean().getAllMembers();
        List<MemberDto> sortedMembers = MeetingUI.getMeetingUI().getMemberBean().getAllMembers(SortStringGenerator.generate(sortOrders));

        // if the grid is sorted from a column which contains null values
        if (!CollectionUtils.isEmpty(sortedMembers) && !CollectionUtils.isEqualCollection(allMembers, sortedMembers)) {
            for (QuerySortOrder sortOrder : sortOrders) { // sorted nulls last
                if (sortOrder.getDirection() == SortDirection.DESCENDING) {
                    allMembers.removeAll(sortedMembers);
                    sortedMembers.addAll(allMembers);
                } else { // nulls first
                    allMembers.removeAll(sortedMembers);
                    sortedMembers.addAll(allMembers);
                    List<MemberDto> sortedMembersNullsFirst = new ArrayList<>();
                    sortedMembersNullsFirst.addAll(allMembers);
                    sortedMembersNullsFirst.addAll(sortedMembers);
                    return sortedMembersNullsFirst;
                }
            }
        }

        return sortedMembers;
    }

    private boolean filterEveryField(MemberDto memberDto) {
        return passesFilter(memberDto.getName(), filterText) || passesFilter(memberDto.getLastName(), filterText);
    }

    private List<MemberDto> addIndexToMembers(List<MemberDto> membersDto) {
        int index = 1;
        if (!CollectionUtils.isEmpty(membersDto)) {
            for (int i = 0; i < membersDto.size(); i++) {
                membersDto.get(i).setIndex(index++);
            }
            return membersDto;
        }
        return new ArrayList<>();
    }
}
