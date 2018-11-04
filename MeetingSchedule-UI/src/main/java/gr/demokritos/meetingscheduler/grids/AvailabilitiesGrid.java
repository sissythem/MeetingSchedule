package gr.demokritos.meetingscheduler.grids;

import com.vaadin.ui.Grid;
import gr.demokritos.meetingscheduler.business.dto.AvailabilityDto;
import gr.demokritos.meetingscheduler.layouts.AvailabilitiesGridLayout;
import gr.demokritos.meetingscheduler.providers.AvailabilityProvider;

public class AvailabilitiesGrid extends Grid<AvailabilityDto> {
    private AvailabilitiesGridLayout availabilitiesGridLayout;
    private AvailabilityProvider availabilityProvider = new AvailabilityProvider();
    private AvailabilityDto selectedAvailability;

    public AvailabilitiesGrid(AvailabilitiesGridLayout availabilitiesGridLayout) {
        super(AvailabilityDto.class);
        this.availabilitiesGridLayout = availabilitiesGridLayout;
        this.setDataProvider(availabilityProvider);
        addItemClickListener(event -> setSelectedAvailability(event.getItem()));
    }

    public AvailabilitiesGridLayout getAvailabilitiesGridLayout() {
        return availabilitiesGridLayout;
    }

    public void setAvailabilitiesGridLayout(AvailabilitiesGridLayout availabilitiesGridLayout) {
        this.availabilitiesGridLayout = availabilitiesGridLayout;
    }

    public AvailabilityProvider getAvailabilityProvider() {
        return availabilityProvider;
    }

    public void setAvailabilityProvider(AvailabilityProvider availabilityProvider) {
        this.availabilityProvider = availabilityProvider;
    }

    public AvailabilityDto getSelectedAvailability() {
        return selectedAvailability;
    }

    public void setSelectedAvailability(AvailabilityDto selectedAvailability) {
        this.selectedAvailability = selectedAvailability;
    }
}
