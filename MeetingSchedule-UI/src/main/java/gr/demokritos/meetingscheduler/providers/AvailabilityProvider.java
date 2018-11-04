package gr.demokritos.meetingscheduler.providers;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import gr.demokritos.meetingscheduler.MeetingUI;
import gr.demokritos.meetingscheduler.business.dto.AvailabilityDto;
import gr.demokritos.meetingscheduler.layouts.AvailabilitiesGridLayout;
import gr.demokritos.meetingscheduler.utils.SortStringGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AvailabilityProvider extends AbstractBackEndDataProvider<AvailabilityDto,String> {
    private String filterText = "";
    private AvailabilitiesGridLayout.AvailabilityFields selectedField;

    @Override
    public boolean isInMemory() {
        return false;
    }

    public void setFilter(AvailabilitiesGridLayout.AvailabilityFields selectedField, String filterText) {
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
    protected Stream<AvailabilityDto> fetchFromBackEnd(Query<AvailabilityDto, String> query) {
        List<QuerySortOrder> sortOrders = query.getSortOrders();

        if (StringUtils.isBlank(filterText) && CollectionUtils.isEmpty(sortOrders)) { // empty filter, no sortOrders
            return addIndexToAvailabilities(MeetingUI.getMeetingUI().getAvailabilityBean().getAllAvailabilities()).stream();
        } else if (StringUtils.isBlank(filterText) && !CollectionUtils.isEmpty(sortOrders)) { // empty filter, have
            // sortOrders
            if (fetchAllAvailabilitiesSorted(sortOrders) != null) {
                return addIndexToAvailabilities(fetchAllAvailabilitiesSorted(sortOrders)).stream();
            } else {
                return addIndexToAvailabilities(MeetingUI.getMeetingUI().getAvailabilityBean().getAllAvailabilities()).stream();
            }
        }

        // if filterText not empty and have sortOrders, retrieve filtered
        if (fetchAllAvailabilitiesSorted(sortOrders) != null) {
            List<AvailabilityDto> availabilityDtos = fetchAllAvailabilitiesSorted(sortOrders);
            switch (selectedField) {
                case MemberName:
                    return addIndexToAvailabilities(availabilityDtos.stream().filter(availabilityDto -> passesFilter(availabilityDto.getMemberDto().getName(), filterText))
                            .collect(Collectors.toList())).stream();
                case Date:
                    return addIndexToAvailabilities(availabilityDtos.stream().filter(availabilityDto -> passesFilter(availabilityDto.getDayDto().getDate(), filterText))
                            .collect(Collectors.toList())).stream();
                case StartTime:
                    return addIndexToAvailabilities(availabilityDtos.stream().filter(availabilityDto -> passesFilter(availabilityDto.getTimezoneDto().getStartTime(), filterText))
                            .collect(Collectors.toList())).stream();
                case EndTime:
                    return addIndexToAvailabilities(availabilityDtos.stream().filter(availabilityDto -> passesFilter(availabilityDto.getTimezoneDto().getEndTime(), filterText))
                            .collect(Collectors.toList())).stream();
                case MeetingName:
                    return addIndexToAvailabilities(availabilityDtos.stream().filter(availabilityDto -> passesFilter(availabilityDto.getMeetingDto().getName(), filterText))
                            .collect(Collectors.toList())).stream();
                case Status:
                    return addIndexToAvailabilities(availabilityDtos.stream().filter(availabilityDto -> passesFilter(availabilityDto.getIsAvailable(), filterText))
                            .collect(Collectors.toList())).stream();
                default: // all
                    return addIndexToAvailabilities(
                            availabilityDtos.stream().filter(availabilityDto -> filterEveryField(availabilityDto)).collect(Collectors.toList())).stream();
            }
        } else { // if filterText not empty and ordering by empty column, retrieve filtered
            List<AvailabilityDto> availabilityDtos = MeetingUI.getMeetingUI().getAvailabilityBean().getAllAvailabilities();
            if (!CollectionUtils.isEmpty(availabilityDtos)) {
                return addIndexToAvailabilities(
                        availabilityDtos.stream().filter(availabilityDto -> filterEveryField(availabilityDto)).collect(Collectors.toList())).stream();
            } else {
                return null;
            }
        }

    }

    @Override
    protected int sizeInBackEnd(Query<AvailabilityDto, String> query) {
        if (fetchFromBackEnd(query) != null) {
            return (int) fetchFromBackEnd(query).count();
        } else {
            return 0;
        }
    }

    private List<AvailabilityDto> fetchAllAvailabilitiesSorted(List<QuerySortOrder> sortOrders) {
        List<AvailabilityDto> allAvailabilities = MeetingUI.getMeetingUI().getAvailabilityBean().getAllAvailabilities();
        List<AvailabilityDto> sortedAvailabilities = MeetingUI.getMeetingUI().getAvailabilityBean().getAllAvailabilities(SortStringGenerator.generate(sortOrders));

        // if the grid is sorted from a column which contains null values
        if (!CollectionUtils.isEmpty(sortedAvailabilities) && !CollectionUtils.isEqualCollection(allAvailabilities, sortedAvailabilities)) {
            for (QuerySortOrder sortOrder : sortOrders) { // sorted nulls last
                if (sortOrder.getDirection() == SortDirection.DESCENDING) {
                    allAvailabilities.removeAll(sortedAvailabilities);
                    sortedAvailabilities.addAll(allAvailabilities);
                } else { // nulls first
                    allAvailabilities.removeAll(sortedAvailabilities);
                    sortedAvailabilities.addAll(allAvailabilities);
                    List<AvailabilityDto> sortedAvailabilitiesNullsFirst = new ArrayList<>();
                    sortedAvailabilitiesNullsFirst.addAll(allAvailabilities);
                    sortedAvailabilitiesNullsFirst.addAll(sortedAvailabilities);
                    return sortedAvailabilitiesNullsFirst;
                }
            }
        }

        return sortedAvailabilities;
    }

    private boolean filterEveryField(AvailabilityDto availabilityDto) {
        return passesFilter(availabilityDto.getMemberDto().getName(), filterText) || passesFilter(availabilityDto.getDayDto().getDate(), filterText)
                || passesFilter(availabilityDto.getTimezoneDto().getStartTime(), filterText) || passesFilter(availabilityDto.getTimezoneDto().getEndTime(), filterText)
                || passesFilter(availabilityDto.getMeetingDto().getName(), filterText) || passesFilter(availabilityDto.getIsAvailable(), filterText);
    }

    private List<AvailabilityDto> addIndexToAvailabilities(List<AvailabilityDto> availabilityDtos) {
        int index = 1;
        if (!CollectionUtils.isEmpty(availabilityDtos)) {
            for (int i = 0; i < availabilityDtos.size(); i++) {
                availabilityDtos.get(i).setIndex(index++);
            }
            return availabilityDtos;
        }
        return new ArrayList<>();
    }
}
