package gr.demokritos.meetingscheduler.datalayer.persistence.entities;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@SessionScoped
@Entity
@Table(name="days")
@NamedQueries({
	
})
public class Day extends DBEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="name")
	@Size(max=255)
	private String name;
	private transient DayOfWeek dayOfWeek;
	@Column(name="date")
	private LocalDate date;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "days_timezones", joinColumns = {
			@JoinColumn(name = "day_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "timezone_id", referencedColumnName = "id") })
	private Set<Timezone> timezones;
	
	public Day() {
		
	}

	public Day(Long id, String name, DayOfWeek dayOfWeek, LocalDate date) {
		super();
		this.id = id;
		this.name = name;
		this.dayOfWeek = dayOfWeek;
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

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
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
	
	
}
