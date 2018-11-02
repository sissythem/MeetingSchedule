package gr.demokritos.meetingscheduler.business.dto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DayDto extends ParentDto {
	private Long id;
	private String name;
	private LocalDate date;
	private DayOfWeek dayOfWeek;
	private Set<TimezoneDto> timezones;
	private List<PossibleMeetingDto> possibleMeetingsDto = new ArrayList<>();
	private List<AvailabilityDto> availabilityDtos = new ArrayList<>();
	
	public DayDto() {
		
	}

	public DayDto(Long id, String name, LocalDate date, Set<TimezoneDto> timezones,
			List<PossibleMeetingDto> possibleMeetings) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.timezones = timezones;
		this.possibleMeetingsDto = possibleMeetings;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Set<TimezoneDto> getTimezones() {
		return timezones;
	}

	public void setTimezones(Set<TimezoneDto> timezones) {
		this.timezones = timezones;
	}
	
	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public List<PossibleMeetingDto> getPossibleMeetingsDto() {
		return possibleMeetingsDto;
	}

	public void setPossibleMeetingsDto(List<PossibleMeetingDto> possibleMeetingsDto) {
		this.possibleMeetingsDto = possibleMeetingsDto;
	}

	public List<AvailabilityDto> getAvailabilityDtos() {
		return availabilityDtos;
	}

	public void setAvailabilityDtos(List<AvailabilityDto> availabilityDtos) {
		this.availabilityDtos = availabilityDtos;
	}
	
	public void addAvailabilityDto(AvailabilityDto availabilityDto) {
		availabilityDto.setDayDto(this);
	}
	
	public void removeAvailabilityDto(AvailabilityDto availabilityDto) {
		availabilityDto.setDayDto(null);
	}
	
	public void addPossibleMeetingDto(PossibleMeetingDto possibleMeetingDto) {
		possibleMeetingDto.setDayDto(this);
	}
	
	public void removePossibleMeetingDto(PossibleMeetingDto possibleMeetingDto) {
		possibleMeetingDto.setDayDto(null);
	}
	
	public void internalAddAvailabilityDto(AvailabilityDto availabilityDto) {
		this.availabilityDtos.add(availabilityDto);
	}
	
	public void internalRemoveAvailabilityDto(AvailabilityDto availabilityDto) {
		this.availabilityDtos.remove(availabilityDto);
	}
	
	public void internalAddPossibleMeetingDto(PossibleMeetingDto possibleMeetingDto) {
		this.possibleMeetingsDto.add(possibleMeetingDto);
	}
	
	public void internalRemovePossibleMeetingDto(PossibleMeetingDto possibleMeetingDto) {
		this.possibleMeetingsDto.remove(possibleMeetingDto);
	}
	
	public void addTimezoneDto(TimezoneDto timezoneDto) {
		this.timezones.add(timezoneDto);
	}
	
	public void removeTimeZoneDto(TimezoneDto timezoneDto) {
		this.timezones.remove(timezoneDto);
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
		DayDto other = (DayDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DayDto [id=" + id + ", name=" + name + ", date=" + date + "]";
	}
	
}
