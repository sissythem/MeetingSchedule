package gr.demokritos.meetingscheduler.providers;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.dto.MeetingDto;
import gr.demokritos.meetingscheduler.layouts.MeetingsGridLayout;
import gr.demokritos.meetingscheduler.utils.SortStringGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MeetingProvider extends AbstractBackEndDataProvider<MeetingDto,String> {
    private String filterText = "";
    private MeetingsGridLayout.MeetingFields selectedField;

    @Override
    public boolean isInMemory() {
        return false;
    }

    public void setFilter(MeetingsGridLayout.MeetingFields selectedField, String filterText) {
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
    protected Stream<MeetingDto> fetchFromBackEnd(Query<MeetingDto, String> query) {
        List<QuerySortOrder> sortOrders = query.getSortOrders();

        if (StringUtils.isBlank(filterText) && CollectionUtils.isEmpty(sortOrders)) { // empty filter, no sortOrders
            return addIndexToMeetings(MeetingUI.getMeetingUI().getMeetingBean().getAllMeetings()).stream();
        } else if (StringUtils.isBlank(filterText) && !CollectionUtils.isEmpty(sortOrders)) { // empty filter, have
            // sortOrders
            if (fetchAllMeetingsSorted(sortOrders) != null) {
                return addIndexToMeetings(fetchAllMeetingsSorted(sortOrders)).stream();
            } else {
                return addIndexToMeetings(MeetingUI.getMeetingUI().getMeetingBean().getAllMeetings()).stream();
            }
        }

        // if filterText not empty and have sortOrders, retrieve filtered
        if (fetchAllMeetingsSorted(sortOrders) != null) {
            List<MeetingDto> meetingDtos = fetchAllMeetingsSorted(sortOrders);
            switch (selectedField) {
                case Name:
                    return addIndexToMeetings(meetingDtos.stream().filter(meeting -> passesFilter(meeting.getName(), filterText))
                            .collect(Collectors.toList())).stream();
                case Date:
                    return addIndexToMeetings(meetingDtos.stream().filter(meeting -> passesFilter(meeting.getDate(), filterText))
                            .collect(Collectors.toList())).stream();
                case StartTime:
                    return addIndexToMeetings(meetingDtos.stream().filter(meeting -> passesFilter(meeting.getStartTime(), filterText))
                            .collect(Collectors.toList())).stream();
                case EndTime:
                    return addIndexToMeetings(meetingDtos.stream().filter(meeting -> passesFilter(meeting.getEndTime(), filterText))
                            .collect(Collectors.toList())).stream();
                case Status:
                    return addIndexToMeetings(meetingDtos.stream().filter(meeting -> passesFilter(meeting.getCompleted(), filterText))
                            .collect(Collectors.toList())).stream();
                default: // all
                    return addIndexToMeetings(
                            meetingDtos.stream().filter(meeting -> filterEveryField(meeting)).collect(Collectors.toList())).stream();
            }
        } else { // if filterText not empty and ordering by empty column, retrieve filtered
            List<MeetingDto> meetingDtos = MeetingUI.getMeetingUI().getMeetingBean().getAllMeetings();
            if (!CollectionUtils.isEmpty(meetingDtos)) {
                return addIndexToMeetings(
                        meetingDtos.stream().filter(meeting -> filterEveryField(meeting)).collect(Collectors.toList())).stream();
            } else {
                return null;
            }
        }
    }

    @Override
    protected int sizeInBackEnd(Query<MeetingDto, String> query) {
        if (fetchFromBackEnd(query) != null) {
            return (int) fetchFromBackEnd(query).count();
        } else {
            return 0;
        }
    }

    private List<MeetingDto> fetchAllMeetingsSorted(List<QuerySortOrder> sortOrders) {
        List<MeetingDto> allMeetings = MeetingUI.getMeetingUI().getMeetingBean().getAllMeetings();
        List<MeetingDto> sortedMeetings = MeetingUI.getMeetingUI().getMeetingBean().getAllMeetings(SortStringGenerator.generate(sortOrders));

        // if the grid is sorted from a column which contains null values
        if (!CollectionUtils.isEmpty(sortedMeetings) && !CollectionUtils.isEqualCollection(allMeetings, sortedMeetings)) {
            for (QuerySortOrder sortOrder : sortOrders) { // sorted nulls last
                if (sortOrder.getDirection() == SortDirection.DESCENDING) {
                    allMeetings.removeAll(sortedMeetings);
                    sortedMeetings.addAll(allMeetings);
                } else { // nulls first
                    allMeetings.removeAll(sortedMeetings);
                    sortedMeetings.addAll(allMeetings);
                    List<MeetingDto> sortedMeetingsNullsFirst = new ArrayList<>();
                    sortedMeetingsNullsFirst.addAll(allMeetings);
                    sortedMeetingsNullsFirst.addAll(sortedMeetings);
                    return sortedMeetingsNullsFirst;
                }
            }
        }

        return sortedMeetings;
    }

    private boolean filterEveryField(MeetingDto meetingDto) {
        return passesFilter(meetingDto.getName(), filterText) || passesFilter(meetingDto.getDate(), filterText)
                || passesFilter(meetingDto.getStartTime(), filterText) || passesFilter(meetingDto.getEndTime(), filterText)
                || passesFilter(meetingDto.getCompleted(), filterText);
    }

    private List<MeetingDto> addIndexToMeetings(List<MeetingDto> meetingDtos) {
        int index = 1;
        if (!CollectionUtils.isEmpty(meetingDtos)) {
            for (int i = 0; i < meetingDtos.size(); i++) {
                meetingDtos.get(i).setIndex(index++);
            }
            return meetingDtos;
        }
        return new ArrayList<>();
    }

}
