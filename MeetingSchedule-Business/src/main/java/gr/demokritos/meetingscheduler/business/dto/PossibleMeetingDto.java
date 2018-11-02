package gr.demokritos.meetingscheduler.business.dto;

import java.util.List;

public class PossibleMeetingDto extends ParentDto {
	private Long id;
	private MeetingDto meetingDto;
	private DayDto dayDto;
	private TimezoneDto timezoneDto;
	private List<PossibleMeetingMemberDto> possibleMeetingMemberDtos;
	
	public PossibleMeetingDto() {
		
	}

	public PossibleMeetingDto(Long id, MeetingDto meetingDto, DayDto dayDto, TimezoneDto timezoneDto,
			List<PossibleMeetingMemberDto> possibleMeetingMemberDtos) {
		super();
		this.id = id;
		this.meetingDto = meetingDto;
		this.dayDto = dayDto;
		this.timezoneDto = timezoneDto;
		this.possibleMeetingMemberDtos = possibleMeetingMemberDtos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MeetingDto getMeetingDto() {
		return meetingDto;
	}

	public void setMeetingDto(MeetingDto meetingDto) {
		if(this.meetingDto!=null) {
			this.meetingDto.internalRemovePossibleMeetingDto(this);
		}
		this.meetingDto = meetingDto;
		if(meetingDto!=null) {
			meetingDto.internalAddPossibleMeetingDto(this);
		}
	}

	public DayDto getDayDto() {
		return dayDto;
	}

	public void setDayDto(DayDto dayDto) {
		if(this.dayDto!=null) {
			this.dayDto.internalRemovePossibleMeetingDto(this);
		}
		this.dayDto = dayDto;
		if(dayDto!=null) {
			dayDto.internalAddPossibleMeetingDto(this);
		}
	}

	public TimezoneDto getTimezoneDto() {
		return timezoneDto;
	}

	public void setTimezoneDto(TimezoneDto timezoneDto) {
		if(this.timezoneDto!=null) {
			this.timezoneDto.internalRemovePossibleMeetingDto(this);
		}
		this.timezoneDto = timezoneDto;
		if(timezoneDto!=null) {
			timezoneDto.internalAddPossibleMeetingDto(this);
		}
	}

	public List<PossibleMeetingMemberDto> getPossibleMeetingMemberDtos() {
		return possibleMeetingMemberDtos;
	}

	public void setPossibleMeetingMemberDtos(List<PossibleMeetingMemberDto> possibleMeetingMemberDtos) {
		this.possibleMeetingMemberDtos = possibleMeetingMemberDtos;
	}
	
	public void addPossibleMeetingMemberDto(PossibleMeetingMemberDto possibleMeetingMemberDto) {
		possibleMeetingMemberDto.setPossibleMeetingDto(this);
	}
	
	public void removePossibleMeetingMemberDto(PossibleMeetingMemberDto possibleMeetingMemberDto) {
		possibleMeetingMemberDto.setPossibleMeetingDto(null);
	}
	
	public void internalAddPossibleMeetingMemberDto(PossibleMeetingMemberDto possibleMeetingMemberDto) {
		this.possibleMeetingMemberDtos.add(possibleMeetingMemberDto);
	}
	
	public void internalRemovePossibleMeetingMemberDto(PossibleMeetingMemberDto possibleMeetingMemberDto) {
		this.possibleMeetingMemberDtos.remove(possibleMeetingMemberDto);
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
		PossibleMeetingDto other = (PossibleMeetingDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PossibleMeetingDto [id=" + id + ", meetingDto=" + meetingDto.toString() + ", dayDto=" + dayDto.toString() + ", timezoneDto="
				+ timezoneDto.toString() + "]";
	}
	
}
