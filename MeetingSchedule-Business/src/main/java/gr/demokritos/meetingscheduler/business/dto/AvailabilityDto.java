package gr.demokritos.meetingscheduler.business.dto;

import org.apache.commons.lang3.StringUtils;

import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

public class AvailabilityDto extends ParentDto {
	private Long id;
	private MemberDto memberDto;
	private DayDto dayDto;
	private TimezoneDto timezoneDto;
	private Boolean isAvailable;
	
	public AvailabilityDto() {
		
	}

	public AvailabilityDto(Long id, MemberDto memberDto, DayDto dayDto, TimezoneDto timezoneDto, Boolean isAvailable) {
		super();
		this.id = id;
		this.memberDto = memberDto;
		this.dayDto = dayDto;
		this.timezoneDto = timezoneDto;
		this.isAvailable = isAvailable;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MemberDto getMemberDto() {
		return memberDto;
	}

	public void setMemberDto(MemberDto memberDto) {
		if(this.memberDto!=null) {
			this.memberDto.internalRemoveAvailabilityDto(this);
		}
		this.memberDto = memberDto;
		if(memberDto!=null) {
			memberDto.internalAddAvailabilityDto(this);
		}
	}

	public DayDto getDayDto() {
		return dayDto;
	}

	public void setDayDto(DayDto dayDto) {
		if(this.dayDto!=null) {
			this.dayDto.internalRemoveAvailabilityDto(this);
		}
		this.dayDto = dayDto;
		if(dayDto!=null) {
			dayDto.internalAddAvailabilityDto(this);
		}
	}

	public TimezoneDto getTimezoneDto() {
		return timezoneDto;
	}

	public void setTimezoneDto(TimezoneDto timezoneDto) {
		if(this.timezoneDto!=null) {
			this.timezoneDto.internalRemoveAvailabilityDto(this);
		}
		this.timezoneDto = timezoneDto;
		if(timezoneDto!=null) {
			timezoneDto.internalAddAvailabilityDto(this);
		}
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public void setIsAvailable(String isAvailable) {
		if(!StringUtils.isBlank(isAvailable)) {
			if(isAvailable.equalsIgnoreCase(DbConstants.YES)) {
				this.isAvailable = true;
			} else if(isAvailable.equalsIgnoreCase(DbConstants.NO)) {
				this.isAvailable = false;
			}
		}
	}
	
	public void setIsAvailabile(String availability) {
		if(availability.equalsIgnoreCase(DbConstants.YES)) {
			this.isAvailable = true;
		} else if(availability.equalsIgnoreCase(DbConstants.NO)) {
			this.isAvailable = false;
		}
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
		AvailabilityDto other = (AvailabilityDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AvailabilityDto [id=" + id + ", memberDto=" + memberDto.toString() + ", dayDto=" + dayDto.toString() + ", timezoneDto="
				+ timezoneDto.toString() + ", isAvailable=" + isAvailable + "]";
	}
	
	
}
