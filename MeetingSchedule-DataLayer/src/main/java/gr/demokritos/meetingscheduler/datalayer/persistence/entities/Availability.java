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

import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

@SuppressWarnings("serial")
@SessionScoped
@Entity
@Table(name = "availabilities")
@NamedQueries({
        @NamedQuery(name = DbConstants.AVAILABILITY_FIND_ALL, query = "SELECT a FROM Availability a"),
        @NamedQuery(name = DbConstants.AVAILABILITY_FIND_BY_ID, query = "SELECT a FROM Availability a WHERE a.id = :id"),
        @NamedQuery(name = DbConstants.AVAILABILITY_FIND_BY_MEMBER, query = "SELECT a FROM Availability a WHERE a.member.id = :memberId"),
        @NamedQuery(name = DbConstants.AVAILABILITY_FIND_BY_DAY, query = "SELECT a FROM Availability a WHERE a.day.id = :dayId"),
        @NamedQuery(name = DbConstants.AVAILABILITY_FIND_BY_TIMEZONE, query = "SELECT a FROM Availability a WHERE a.timezone.id = :timezoneId"),
        @NamedQuery(name = DbConstants.AVAILABILITY_FIND_BY_DAY_AND_TIMEZONE, query = "SELECT a FROM Availability a WHERE a.day.id = :dayId AND a.timezone.id = :timezoneId"),
        @NamedQuery(name = DbConstants.AVAILABILITY_FIND_BY_MEMBER_AND_DAY, query = "SELECT a FROM Availability a WHERE a.member.id = :memberId AND a.day.id = :dayId"),
        @NamedQuery(name = DbConstants.AVAILABILITY_FIND_BY_MEMBER_DAY_AND_TIMEZONE, query = "SELECT a FROM Availability a WHERE a.member.id = :memberId AND a.day.id = :dayId AND a.timezone.id = :timezoneId"),
        @NamedQuery(name = DbConstants.AVAILABILITY_FIND_BY_MEETING, query = "SELECT a FROM Availability a WHERE a.meeting.id = :meetingId"),
        @NamedQuery(name = DbConstants.AVAILABILITY_FIND_BY_MEETING_AND_AVAILABILITY, query = "SELECT a FROM Availability a WHERE a.meeting.id = :meetingId AND a.availability = :availability")
})
public class Availability extends DBEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;
    @ManyToOne
    @JoinColumn(name = "day_id")
    private Day day;
    @ManyToOne
    @JoinColumn(name = "timezone_id")
    private Timezone timezone;
    @Column(name = "availability")
    private String availability;

    public Availability() {

    }

    public Availability(Long id, Member member, Day day, Timezone timezone, Meeting meeting, String availability) {
        super();
        this.id = id;
        this.member = member;
        this.day = day;
        this.meeting = meeting;
        this.timezone = timezone;
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.internalRemoveAvailability(this);
        }
        this.member = member;
        if (member != null) {
            member.internalAddAvailability(this);
        }
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        if (this.day != null) {
            this.day.internalRemoveAvailability(this);
        }
        this.day = day;
        if (day != null) {
            day.internalAddAvailability(this);
        }
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(Timezone timezone) {
        if (this.timezone != null) {
            this.timezone.internalRemoveAvailability(this);
        }
        this.timezone = timezone;
        if (timezone != null) {
            timezone.internalAddAvailability(this);
        }
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void setAvailability(Boolean isAvailable) {
        if (isAvailable != null) {
            if (isAvailable) {
                this.availability = DbConstants.YES;
            } else {
                this.availability = DbConstants.NO;
            }
        }
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        if(this.meeting!=null) {
            this.meeting.internalRemoveAvailability(this);
        }
        this.meeting = meeting;
        if(meeting!=null) {
            meeting.internalAddAvailability(this);
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
        Availability other = (Availability) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Availability [id=" + id + ", member=" + member + ", day=" + day + ", timezone=" + timezone
                + ", availability=" + availability + "]";
    }

}
