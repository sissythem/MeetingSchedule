package gr.demokritos.meetingscheduler.datalayer.persistence.entities;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

@SuppressWarnings("serial")
@SessionScoped
@Entity
@Table(name="possible_meetings")
@NamedQueries({
	@NamedQuery(name=DbConstants.POSSIBLE_MEETING_FIND_ALL, query = "SELECT p FROM PossibleMeeting p"),
	@NamedQuery(name=DbConstants.POSSIBLE_MEETING_FIND_BY_ID, query = "SELECT p FROM PossibleMeeting p WHERE p.id = :id"),
	@NamedQuery(name=DbConstants.POSSIBLE_MEETING_FIND_BY_MEETING, query = "SELECT p FROM PossibleMeeting p WHERE p.meeting.id= :meetingId"),
	@NamedQuery(name=DbConstants.POSSIBLE_MEETING_FIND_BY_DAY, query = "SELECT p FROM PossibleMeeting p WHERE p.day.id= :dayId"),
	@NamedQuery(name=DbConstants.POSSIBLE_MEETING_FIND_BY_TIMEZONE, query = "SELECT p FROM PossibleMeeting p WHERE p.timezone.id= :timezoneId"),
	@NamedQuery(name=DbConstants.POSSIBLE_MEETING_FIND_BY_MEETING_AND_DAY, query = "SELECT p FROM PossibleMeeting p WHERE p.meeting.id= :meetingId AND p.day.id = :dayId"),
	@NamedQuery(name=DbConstants.POSSIBLE_MEETING_FIND_BY_DAY_AND_TIMEZONE, query = "SELECT p FROM PossibleMeeting p WHERE p.day.id= :dayId AND p.timezone.id = :timezoneId"),
	@NamedQuery(name=DbConstants.POSSIBLE_MEETING_FIND_BY_MEETING_DAY_AND_TIMEZONE, query = "SELECT p FROM PossibleMeeting p WHERE p.meeting.id= :meetingId AND p.day.id = :dayId AND p.timezone.id = :timezoneId")
})
public class PossibleMeeting extends DBEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@ManyToOne
	@JoinColumn(name="meeting_id")
	private Meeting meeting;
	@ManyToOne
	@JoinColumn(name="day_id")
	private Day day;
	@ManyToOne
	@JoinColumn(name="timezone_id")
	private Timezone timezone;
	@OneToMany(mappedBy="possibleMeeting", cascade=CascadeType.ALL)
	List<PossibleMeetingMember> possibleMeetingMembers;
	
	public PossibleMeeting() {
		
	}

	public PossibleMeeting(Long id, Meeting meeting, Day day, Timezone timezone) {
		super();
		this.id = id;
		this.meeting = meeting;
		this.day = day;
		this.timezone = timezone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		if(this.meeting!=null) {
			this.meeting.internalRemovePossibleMeeting(this);
		}
		this.meeting = meeting;
		if(meeting!=null) {
			meeting.internalAddPossibleMeeting(this);
		}
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		if(this.day!=null) {
			this.day.internalRemovePossibleMeeting(this);
		}
		this.day = day;
		if(day!=null) {
			day.internalAddPossibleMeeting(this);
		}
	}

	public Timezone getTimezone() {
		return timezone;
	}

	public void setTimezone(Timezone timezone) {
		if(this.timezone!=null) {
			this.timezone.internalRemovePossibleMeeting(this);
		}
		this.timezone = timezone;
		if(timezone!=null) {
			timezone.internalAddPossibleMeeting(this);
		}
	}

	public List<PossibleMeetingMember> getPossibleMeetingMembers() {
		return possibleMeetingMembers;
	}

	public void setPossibleMeetingMembers(List<PossibleMeetingMember> possibleMeetingMembers) {
		this.possibleMeetingMembers = possibleMeetingMembers;
	}
	
	public void addPossibleMeetingMember(PossibleMeetingMember possibleMeetingMember) {
		possibleMeetingMember.setPossibleMeeting(this);
	}
	
	public void removePossibleMeetingMember(PossibleMeetingMember possibleMeetingMember) {
		possibleMeetingMember.setPossibleMeeting(null);
	}
	
	public void internalAddPossibleMeetingMember(PossibleMeetingMember possibleMeetingMember) {
		this.possibleMeetingMembers.add(possibleMeetingMember);
	}
	
	public void internalRemovePossibleMeetingMember(PossibleMeetingMember possibleMeetingMember) {
		this.possibleMeetingMembers.remove(possibleMeetingMember);
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
		PossibleMeeting other = (PossibleMeeting) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PossibleMeeting [id=" + id + ", meeting=" + meeting.toString() + ", day=" + day.toString() + ", timezone=" + timezone.toString() + "]";
	}
	
	
}
