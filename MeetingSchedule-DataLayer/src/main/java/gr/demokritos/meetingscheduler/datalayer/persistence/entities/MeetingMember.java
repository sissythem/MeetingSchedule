package gr.demokritos.meetingscheduler.datalayer.persistence.entities;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

@SuppressWarnings("serial")
@SessionScoped
@Entity
@Table(name="meetings_members")
@NamedQueries({
	@NamedQuery(name=DbConstants.MEETING_MEMBER_FIND_ALL, query="SELECT m From MeetingMember m"),
	@NamedQuery(name=DbConstants.MEETING_MEMBER_FIND_BY_ID, query="SELECT m From MeetingMember m WHERE m.id = :id"),
	@NamedQuery(name=DbConstants.MEETING_MEMBER_FIND_BY_MEETING, query="SELECT m From MeetingMember m WHERE m.meeting.id = :meetingId"),
	@NamedQuery(name=DbConstants.MEETING_MEMBER_FIND_BY_MEMBER, query="SELECT m From MeetingMember m WHERE m.member.id = :memberId"),
	@NamedQuery(name=DbConstants.MEETING_MEMBER_FIND_BY_ATTENDED, query="SELECT m From MeetingMember m WHERE m.attended = :attended"),
	@NamedQuery(name=DbConstants.MEETING_MEMBER_FIND_BY_MEMBER_AND_ATTENDED, query="SELECT m From MeetingMember m WHERE m.member.id = :memberId AND m.attended = :attended"),
	@NamedQuery(name=DbConstants.MEETING_MEMBER_FIND_BY_MEETING_AND_ATTENDED, query="SELECT m From MeetingMember m WHERE m.meeting.id = :meetingId AND m.attended = :attended"),
	@NamedQuery(name=DbConstants.MEETING_MEMBER_FIND_BY_MEMBER_AND_MEETING, query="SELECT m From MeetingMember m WHERE m.member.id = :memberId AND m.meeting.id = :meetingId"),
	@NamedQuery(name=DbConstants.MEETING_MEMBER_FIND_BY_MEETING_MEMBER_AND_ATTENDED, query="SELECT m From MeetingMember m WHERE m.meeting.id = :meetingId AND m.member.id = :memberId AND m.attended = :attended")
})
public class MeetingMember extends DBEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@ManyToOne
	@JoinColumn(name="meeting_id")
	private Meeting meeting;
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;
	@Column(name="attended")
	@Size(max=4)
	private String attended;
	
	public MeetingMember() {
		
	}

	public MeetingMember(Long id, Meeting meeting, Member member, @Size(max = 4) String attended) {
		super();
		this.id = id;
		this.meeting = meeting;
		this.member = member;
		this.attended = attended;
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
		this.meeting = meeting;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getAttended() {
		return attended;
	}

	public void setAttended(String attended) {
		this.attended = attended;
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
		MeetingMember other = (MeetingMember) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MeetingMember [id=" + id + ", meeting=" + meeting.toString() + ", member=" + member.toString() + ", attended=" + attended
				+ "]";
	}
	
	
}
