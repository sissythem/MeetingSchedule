package gr.demokritos.meetingscheduler.business.dto;

import org.apache.commons.lang3.StringUtils;

import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

public class PossibleMeetingMemberDto extends ParentDto {
	private Long id;
	private PossibleMeetingDto possibleMeetingDto;
	private MemberDto memberDto;
	private Boolean attending;
	
	public PossibleMeetingMemberDto() {
		
	}

	public PossibleMeetingMemberDto(Long id, PossibleMeetingDto possibleMeetingDto, MemberDto memberDto,
			Boolean attending) {
		super();
		this.id = id;
		this.possibleMeetingDto = possibleMeetingDto;
		this.memberDto = memberDto;
		this.attending = attending;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PossibleMeetingDto getPossibleMeetingDto() {
		return possibleMeetingDto;
	}

	public void setPossibleMeetingDto(PossibleMeetingDto possibleMeetingDto) {
		if(this.possibleMeetingDto!=null) {
			this.possibleMeetingDto.internalRemovePossibleMeetingMemberDto(this);
		}
		this.possibleMeetingDto = possibleMeetingDto;
		if(possibleMeetingDto!=null) {
			possibleMeetingDto.internalAddPossibleMeetingMemberDto(this);
		}
	}

	public MemberDto getMemberDto() {
		return memberDto;
	}

	public void setMemberDto(MemberDto memberDto) {
		if(this.memberDto!=null) {
			this.memberDto.internalAddPossibleMeetingMemberDto(this);
		}
		this.memberDto = memberDto;
		if(memberDto!=null) {
			memberDto.internalAddPossibleMeetingMemberDto(this);
		}
	}

	public Boolean getAttending() {
		return attending;
	}

	public void setAttending(Boolean attending) {
		this.attending = attending;
	}
	
	public void setAttending(String attending) {
		if(!StringUtils.isBlank(attending)) {
			if(attending.equalsIgnoreCase(DbConstants.YES)) {
				this.attending = true;
			} else if(attending.equalsIgnoreCase(DbConstants.NO)) {
				this.attending = false;
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
		PossibleMeetingMemberDto other = (PossibleMeetingMemberDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PossibleMeetingMemberDto [id=" + id + ", possibleMeetingDto=" + possibleMeetingDto.toString() + ", memberDto="
				+ memberDto.toString() + ", attending=" + attending + "]";
	}
	
}
