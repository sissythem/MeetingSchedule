package gr.demokritos.meetingscheduler.business.dto;

import org.apache.commons.lang3.StringUtils;

import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

public class MeetingMemberDto extends ParentDto {
	private Long id;
	private MeetingDto meetingDto;
	private MemberDto memberDto;
	private Boolean attended;
	
	public MeetingMemberDto() {
		
	}

	public MeetingMemberDto(Long id, MeetingDto meetingDto, MemberDto memberDto, Boolean attended) {
		super();
		this.id = id;
		this.meetingDto = meetingDto;
		this.memberDto = memberDto;
		this.attended = attended;
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
			this.meetingDto.internalRemoveMeetingMemberDto(this);
		}
		this.meetingDto = meetingDto;
		if(meetingDto!=null) {
			meetingDto.internalAddMeetingMemberDto(this);
		}
	}

	public MemberDto getMemberDto() {
		return memberDto;
	}

	public void setMemberDto(MemberDto memberDto) {
		if(this.memberDto!=null) {
			this.memberDto.internalRemoveMeetingMemberDto(this);
		}
		this.memberDto = memberDto;
		if(memberDto!=null) {
			memberDto.internalAddMeetingMemberDto(this);
		}
	}

	public Boolean getAttended() {
		return attended;
	}

	public void setAttended(Boolean attended) {
		this.attended = attended;
	}
	
	public void setAttended(String attended) {
		if(!StringUtils.isBlank(attended)) {
			if(attended.equalsIgnoreCase(DbConstants.YES)) {
				this.attended = true;
			} else if(attended.equalsIgnoreCase(DbConstants.NO)) {
				this.attended = false;
			}
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
		MeetingMemberDto other = (MeetingMemberDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MeetingMemberDto [id=" + id + ", meetingDto=" + meetingDto.toString() + ", memberDto=" + memberDto.toString() + ", attended="
				+ attended + "]";
	}
	
}
