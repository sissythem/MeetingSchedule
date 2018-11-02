package gr.demokritos.meetingscheduler.datalayer.persistence.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

@SuppressWarnings("serial")
@SessionScoped
@Entity
@Table(name="meetings")
@NamedQueries({
	@NamedQuery(name=DbConstants.MEETING_FIND_ALL, query="SELECT m FROM Meeting m"),
	@NamedQuery(name=DbConstants.MEETING_FIND_BY_ID, query="SELECT m FROM Meeting m WHERE m.id = :id"),
	@NamedQuery(name=DbConstants.MEETING_FIND_BY_NAME, query="SELECT m FROM Meeting m WHERE m.name = :name"),
	@NamedQuery(name=DbConstants.MEETING_FIND_BY_DATE, query="SELECT m FROM Meeting m WHERE m.date = :date"),
	@NamedQuery(name=DbConstants.MEETING_FIND_BY_START_TIME, query="SELECT m FROM Meeting m WHERE m.startTime = :startTime"),
	@NamedQuery(name=DbConstants.MEETING_FIND_BY_END_TIME, query="SELECT m FROM Meeting m WHERE m.endTime = :endTime"),
	@NamedQuery(name=DbConstants.MEETING_FIND_BY_COMPLETED, query="SELECT m FROM Meeting m WHERE m.completed = :completed")
})
public class Meeting extends DBEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Size(max=255)
	@Column(name="name")
	private String name;
	@Column(name="date")
	private LocalDate date;
	@Column(name="start_time")
	private LocalTime startTime;
	@Column(name="end_time")
	private LocalTime endTime;
	@Column(name="completed")
	@Size(max=4)
	private String completed;
	@OneToMany(mappedBy="meeting", cascade=CascadeType.ALL)
	private List<MeetingMember> members = new ArrayList<>();
	@OneToMany(mappedBy="meeting", cascade=CascadeType.ALL)
	private List<PossibleMeeting> possibleMeetings = new ArrayList<>();
	
	public Meeting() {
		
	}

	public Meeting(Long id, @Size(max = 255) String name, LocalDate date, LocalTime startTime, LocalTime endTime,
			@Size(max = 4) String completed, List<MeetingMember> members) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.completed = completed;
		this.members = members;
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

	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}
	
	public void setCompleted(Boolean completed) {
		if(completed!=null) {
			if(completed) {
				this.completed = DbConstants.YES;
			} else {
				this.completed = DbConstants.NO;
			}
		}
	}

	public List<MeetingMember> getMembers() {
		return members;
	}

	public void setMembers(List<MeetingMember> members) {
		this.members = members;
	}

	public List<PossibleMeeting> getPossibleMeetings() {
		return possibleMeetings;
	}

	public void setPossibleMeetings(List<PossibleMeeting> possibleMeetings) {
		this.possibleMeetings = possibleMeetings;
	}
	
	public void addPossibleMeeting(PossibleMeeting possibleMeeting) {
		possibleMeeting.setMeeting(this);
	}
	
	public void removePossibleMeeting(PossibleMeeting possibleMeeting) {
		possibleMeeting.setMeeting(null);
	}
	
	public void addMeetingMember(MeetingMember meetingMember) {
		meetingMember.setMeeting(this);
	}
	
	public void removeMeetingMember(MeetingMember meetingMember) {
		meetingMember.setMeeting(null);
	}
	
	public void internalAddPossibleMeeting(PossibleMeeting possibleMeeting) {
		this.possibleMeetings.add(possibleMeeting);
	}
	
	public void internalRemovePossibleMeeting(PossibleMeeting possibleMeeting) {
		this.possibleMeetings.remove(possibleMeeting);
	}
	
	public void internalAddMeetingMember(MeetingMember meetingMember) {
		this.members.add(meetingMember);
	}
	
	public void internalRemoveMeetingMember(MeetingMember meetingMember) {
		this.members.remove(meetingMember);
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
		Meeting other = (Meeting) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Meeting [id=" + id + ", name=" + name + ", date=" + date + ", startTime=" + startTime + ", endTime="
				+ endTime + ", completed=" + completed + "]";
	}
	
}
