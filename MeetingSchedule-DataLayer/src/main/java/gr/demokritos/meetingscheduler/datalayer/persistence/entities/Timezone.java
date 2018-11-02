package gr.demokritos.meetingscheduler.datalayer.persistence.entities;

import java.io.Serializable;
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

import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

@SuppressWarnings("serial")
@SessionScoped
@Entity
@Table(name="timezones")
@NamedQueries({
	@NamedQuery(name=DbConstants.TIMEZONE_FIND_ALL, query = "SELECT t FROM Timezone t"),
	@NamedQuery(name=DbConstants.TIMEZONE_FIND_ID, query = "SELECT t FROM Timezone t WHERE t.id = :id"),
	@NamedQuery(name=DbConstants.TIMEZONE_FIND_START_TIME, query = "SELECT t FROM Timezone t WHERE t.startTime = :startTime"),
	@NamedQuery(name=DbConstants.TIMEZONE_FIND_END_TIME, query = "SELECT t FROM Timezone t WHERE t.endTime = :endTime"),
	@NamedQuery(name=DbConstants.TIMEZONE_FIND_START_TIME_AND_END_TIME, query = "SELECT t FROM Timezone t WHERE t.startTime = :startTime AND t.endTime = :endTime")
})
public class Timezone extends DBEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="start_time")
	private LocalTime startTime;
	@Column(name="end_time")
	private LocalTime endTime;
	@OneToMany(mappedBy="timezone", cascade=CascadeType.ALL)
	private List<PossibleMeeting> possibleMeetings = new ArrayList<>();
	@OneToMany(mappedBy="timezone", cascade=CascadeType.ALL)
	private List<Availability> availabilities = new ArrayList<>();

	public Timezone() {
		
	}

	public Timezone(Long id, LocalTime startTime, LocalTime endTime) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
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

	public List<PossibleMeeting> getPossibleMeetings() {
		return possibleMeetings;
	}

	public void setPossibleMeetings(List<PossibleMeeting> possibleMeetings) {
		this.possibleMeetings = possibleMeetings;
	}

	public List<Availability> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(List<Availability> availabilities) {
		this.availabilities = availabilities;
	}
	
	public void internalAddPossibleMeeting(PossibleMeeting possibleMeeting) {
		this.possibleMeetings.add(possibleMeeting);
	}
	
	public void internalRemovePossibleMeeting(PossibleMeeting possibleMeeting) {
		this.possibleMeetings.remove(possibleMeeting);
	}
	
	public void internalAddAvailability(Availability availability) {
		this.availabilities.add(availability);
	}
	
	public void internalRemoveAvailability(Availability availability) {
		this.availabilities.remove(availability);
	}
	
	public void addAvailability(Availability availability) {
		availability.setTimezone(this);
	}
	
	public void removeAvailability(Availability availability) {
		availability.setTimezone(null);
	}
	
	public void addPossibleMeeting(PossibleMeeting possibleMeeting) {
		possibleMeeting.setTimezone(this);
	}
	
	public void removePossibleMeeting(PossibleMeeting possibleMeeting) {
		possibleMeeting.setTimezone(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
		Timezone other = (Timezone) obj;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Timezone [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
}
