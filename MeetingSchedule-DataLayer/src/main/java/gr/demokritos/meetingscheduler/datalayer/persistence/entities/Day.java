package gr.demokritos.meetingscheduler.datalayer.persistence.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@Table(name="days")
@NamedQueries({
	@NamedQuery(name=DbConstants.DAY_FIND_ALL, query="SELECT d FROM Day d"),
	@NamedQuery(name=DbConstants.DAY_FIND_BY_ID, query="SELECT d FROM Day d WHERE d.id = :id"),
	@NamedQuery(name=DbConstants.DAY_FIND_BY_NAME, query="SELECT d FROM Day d WHERE d.name = :name"),
	@NamedQuery(name=DbConstants.DAY_FIND_BY_DATE, query="SELECT d FROM Day d WHERE d.date = :date"),
	@NamedQuery(name=DbConstants.DAY_FIND_BY_NAME_AND_DATE, query="SELECT d FROM Day d WHERE d.name = :name AND d.date = :date")
})
public class Day extends DBEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="name")
	@Size(max=255)
	private String name;
	@Column(name="date")
	private LocalDate date;
	private transient Set<Timezone> timezones = new HashSet<>();
	@OneToMany(mappedBy="day", cascade=CascadeType.ALL)
	private List<PossibleMeeting> possibleMeetings = new ArrayList<>();
	@OneToMany(mappedBy="day", cascade=CascadeType.ALL)
	private List<Availability> availabilities = new ArrayList<>();
	
	public Day() {
		
	}

	public Day(Long id, String name, LocalDate date) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
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

	public Set<Timezone> getTimezones() {
		return timezones;
	}

	public void setTimezones(Set<Timezone> timezones) {
		this.timezones = timezones;
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
	
	public void addPossibleMeeting(PossibleMeeting possibleMeeting) {
		possibleMeeting.setDay(this);
	}
	
	public void removePossibleMeeting(PossibleMeeting possibleMeeting) {
		possibleMeeting.setDay(null);
	}
	
	public void addAvailability(Availability availability) {
		availability.setDay(this);
	}
	
	public void removeAvailability(Availability availability) {
		availability.setDay(null);
	}
	
	public void addTimezone(Timezone timezone) {
		this.timezones.add(timezone);
	}
	
	public void removeTimezone(Timezone timezone) {
		this.timezones.remove(timezone);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Day other = (Day) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Day [id=" + id + ", name=" + name + ", date=" + date + "]";
	}
	
}
