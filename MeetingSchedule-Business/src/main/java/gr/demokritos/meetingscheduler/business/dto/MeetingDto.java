package gr.demokritos.meetingscheduler.business.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

public class MeetingDto extends ParentDto {
	private Long id;
	private String name;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private Boolean completed;
	private List<MeetingMemberDto> meetingMemberDtos = new ArrayList<>();
	private List<PossibleMeetingDto> possibleMeetingDtos = new ArrayList<>();
	
	public MeetingDto() {
		
	}

	public MeetingDto(Long id, String name, LocalDate date, LocalTime startTime, LocalTime endTime, Boolean completed) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.completed = completed;
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

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	
	public void setCompleted(String completed) {
		if(!StringUtils.isBlank(completed)) {
			if(completed.equalsIgnoreCase(DbConstants.YES)) {
				this.completed = true;
			} else if(completed.equalsIgnoreCase(DbConstants.NO)) {
				this.completed = false;
			}
		}
	}

	public List<MeetingMemberDto> getMeetingMemberDtos() {
		return meetingMemberDtos;
	}

	public void setMeetingMemberDtos(List<MeetingMemberDto> meetingMemberDtos) {
		this.meetingMemberDtos = meetingMemberDtos;
	}

	public List<PossibleMeetingDto> getPossibleMeetingDtos() {
		return possibleMeetingDtos;
	}

	public void setPossibleMeetingDtos(List<PossibleMeetingDto> possibleMeetingDtos) {
		this.possibleMeetingDtos = possibleMeetingDtos;
	}
	
	public void addPossibleMeetingDto(PossibleMeetingDto possibleMeetingDto) {
		possibleMeetingDto.setMeetingDto(this);
	}
	
	public void removePossibleMeetingDto(PossibleMeetingDto possibleMeetingDto) {
		possibleMeetingDto.setMeetingDto(null);
	}
	
	public void addMeetingMemberDto(MeetingMemberDto meetingMemberDto) {
		meetingMemberDto.setMeetingDto(this);
	}
	
	public void removeMeetingMemberDto(MeetingMemberDto meetingMemberDto) {
		meetingMemberDto.setMeetingDto(null);
	}
	
	public void internalAddPossibleMeetingDto(PossibleMeetingDto possibleMeetingDto) {
		this.possibleMeetingDtos.add(possibleMeetingDto);
	}
	
	public void internalRemovePossibleMeetingDto(PossibleMeetingDto possibleMeetingDto) {
		this.possibleMeetingDtos.remove(possibleMeetingDto);
	}
	
	public void internalAddMeetingMemberDto(MeetingMemberDto meetingMemberDto) {
		this.meetingMemberDtos.add(meetingMemberDto);
	}
	
	public void internalRemoveMeetingMemberDto(MeetingMemberDto meetingMemberDto) {
		this.meetingMemberDtos.remove(meetingMemberDto);
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
		MeetingDto other = (MeetingDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MeetingDto [id=" + id + ", name=" + name + ", date=" + date + ", startTime=" + startTime + ", endTime="
				+ endTime + ", completed=" + completed + "]";
	}
	
}
