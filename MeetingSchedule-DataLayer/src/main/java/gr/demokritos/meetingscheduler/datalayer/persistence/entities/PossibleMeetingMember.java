package gr.demokritos.meetingscheduler.datalayer.persistence.entities;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.persistence.*;
import javax.validation.constraints.Size;

import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

@SuppressWarnings("serial")
@SessionScoped
@Entity
@Table(name="possible_meetings_members")
@NamedQueries({
	@NamedQuery(name=DbConstants.POSSIBLE_MEETING_MEMBER_FIND_ALL,query="SELECT p FROM PossibleMeetingMember p"),
	@NamedQuery(name=DbConstants.POSSIBLE_MEETING_MEMBER_FIND_BY_ID,query="SELECT p FROM PossibleMeetingMember p WHERE p.id = :id"),
	@NamedQuery(name=DbConstants.POSSIBLE_MEETING_MEMBER_FIND_BY_POSSIBLE_MEETING,query="SELECT p FROM PossibleMeetingMember p WHERE p.possibleMeeting.id= :possibleMeetingId"),
	@NamedQuery(name=DbConstants.POSSIBLE_MEETING_MEMBER_FIND_BY_MEMBER,query="SELECT p FROM PossibleMeetingMember p WHERE p.member.id = :memberId"),
	@NamedQuery(name=DbConstants.POSSIBLE_MEETING_MEMBER_FIND_BY_ATTENDING,query="SELECT p FROM PossibleMeetingMember p WHERE p.attending = :attending"),
	@NamedQuery(name=DbConstants.POSSIBLE_MEETING_MEMBER_FIND_BY_MEMBER_AND_ATTENDING,query="SELECT p FROM PossibleMeetingMember p WHERE p.member.id = :memberId AND p.attending = :attending"),
	@NamedQuery(name=DbConstants.POSSIBLE_MEETING_MEMBER_FIND_BY_MEMBER_AND_POSSIBLE_MEETING,query="SELECT p FROM PossibleMeetingMember p WHERE p.possibleMeeting.id = :possibleMeetingId AND p.member.id = :memberId"),
	@NamedQuery(name=DbConstants.POSSIBLE_MEETING_MEMBER_FIND_BY_POSSIBLE_MEETING_AND_ATTENDING,query="SELECT p FROM PossibleMeetingMember p WHERE p.possibleMeeting.id = :possibleMeetingId AND p.attending = :attending"),
	@NamedQuery(name=DbConstants.POSSIBLE_MEETING_MEMBER_FIND_BY_POSSIBLE_MEETING_MEMBER_AND_ATTENDING,query="SELECT p FROM PossibleMeetingMember p WHERE p.possibleMeeting.id = :possibleMeetingId AND p.member.id = :memberId AND p.attending = :attending")
})
public class PossibleMeetingMember extends DBEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@ManyToOne
	@JoinColumn(name="possible_meeting_id")
	private PossibleMeeting possibleMeeting;
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;
	@Size(max=4)
	@Column(name="attending")
	private String attending;
	
	public PossibleMeetingMember() {
		
	}

	public PossibleMeetingMember(Long id, PossibleMeeting possibleMeeting, Member member) {
		super();
		this.id = id;
		this.possibleMeeting = possibleMeeting;
		this.member = member;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PossibleMeeting getPossibleMeeting() {
		return possibleMeeting;
	}

	public void setPossibleMeeting(PossibleMeeting possibleMeeting) {
		this.possibleMeeting = possibleMeeting;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getAttending() {
		return attending;
	}

	public void setAttending(String attending) {
		this.attending = attending;
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
		PossibleMeetingMember other = (PossibleMeetingMember) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PossibleMeetingMember [id=" + id + ", possibleMeeting=" + possibleMeeting.toString() + ", member=" + member.toString() + ", attending=" + attending + "]";
	}
	
}
