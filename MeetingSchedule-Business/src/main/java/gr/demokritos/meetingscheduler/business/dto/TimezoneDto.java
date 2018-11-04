package gr.demokritos.meetingscheduler.business.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimezoneDto extends ParentDto {
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<PossibleMeetingDto> possibleMeetingDtos = new ArrayList<>();
    private List<AvailabilityDto> availabilityDtos = new ArrayList<>();

    public TimezoneDto() {

    }

	public TimezoneDto(LocalTime startTime, LocalTime endTime) {
        super();
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TimezoneDto(Long id, LocalTime startTime, LocalTime endTime, List<PossibleMeetingDto> possibleMeetingDtos) {
        super();
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.possibleMeetingDtos = possibleMeetingDtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public List<PossibleMeetingDto> getPossibleMeetingDtos() {
        return possibleMeetingDtos;
    }

    public void setPossibleMeetingDtos(List<PossibleMeetingDto> possibleMeetingDtos) {
        this.possibleMeetingDtos = possibleMeetingDtos;
    }

    public List<AvailabilityDto> getAvailabilityDtos() {
        return availabilityDtos;
    }

    public void setAvailabilityDtos(List<AvailabilityDto> availabilityDtos) {
        this.availabilityDtos = availabilityDtos;
    }

    public void addAvailabilityDto(AvailabilityDto availabilityDto) {
        availabilityDto.setTimezoneDto(this);
    }

    public void removeAvailabilityDto(AvailabilityDto availabilityDto) {
        availabilityDto.setTimezoneDto(null);
    }

    public void addPossibleMeetingDto(PossibleMeetingDto possibleMeetingDto) {
        possibleMeetingDto.setTimezoneDto(this);
    }

    public void removePossibleMeetingDto(PossibleMeetingDto possibleMeetingDto) {
        possibleMeetingDto.setTimezoneDto(null);
    }

    public void internalAddAvailabilityDto(AvailabilityDto availabilityDto) {
        this.availabilityDtos.add(availabilityDto);
    }

    public void internalRemoveAvailabilityDto(AvailabilityDto availabilityDto) {
        this.availabilityDtos.remove(availabilityDto);
    }

    public void internalAddPossibleMeetingDto(PossibleMeetingDto possibleMeetingDto) {
        this.possibleMeetingDtos.add(possibleMeetingDto);
    }

    public void internalRemovePossibleMeetingDto(PossibleMeetingDto possibleMeetingDto) {
        this.possibleMeetingDtos.remove(possibleMeetingDto);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        TimezoneDto other = (TimezoneDto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TimezoneDto [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + "]";
    }


}
