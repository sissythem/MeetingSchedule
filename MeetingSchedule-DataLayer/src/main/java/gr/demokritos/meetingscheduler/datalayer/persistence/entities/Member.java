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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

@SuppressWarnings("serial")
@SessionScoped
@Entity
@Table(name="memebers")
@NamedQueries({
	@NamedQuery(name=DbConstants.MEMBER_FIND_ALL, query="SELECT m From Member m"),
	@NamedQuery(name=DbConstants.MEMBER_FIND_BY_ID, query="SELECT m From Member m WHERE m.id = :id"),
	@NamedQuery(name=DbConstants.MEMBER_FIND_BY_NAME, query="SELECT m From Member m WHERE m.name = :name"),
	@NamedQuery(name=DbConstants.MEMBER_FIND_BY_LAST_NAME, query="SELECT m From Member m WHERE m.lastName= :lastName")
})
public class Member extends DBEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Size(max=255)
	@Column(name="name")
	private String name;
	@Size(max=255)
	@Column(name="last_name")
	private String lastName;
	
	@OneToMany(mappedBy="member", cascade=CascadeType.ALL)
	List<Availability> availabilities;
	@OneToMany(mappedBy="member", cascade=CascadeType.ALL)
	List<MeetingMember> meetings;
	@OneToMany(mappedBy="member", cascade=CascadeType.ALL)
	List<PossibleMeetingMember> possibleMeetingMembers;
	
	public Member() {
		
	}

	public Member(Long id, @Size(max = 255) String name, @Size(max = 255) String lastName) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Availability> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(List<Availability> availabilities) {
		this.availabilities = availabilities;
	}

	public List<MeetingMember> getMeetings() {
		return meetings;
	}

	public void setMeetings(List<MeetingMember> meetings) {
		this.meetings = meetings;
	}

	public List<PossibleMeetingMember> getPossibleMeetingMembers() {
		return possibleMeetingMembers;
	}

	public void setPossibleMeetingMembers(List<PossibleMeetingMember> possibleMeetingMembers) {
		this.possibleMeetingMembers = possibleMeetingMembers;
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
		Member other = (Member) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", lastName=" + lastName + "]";
	}
	
	
}
