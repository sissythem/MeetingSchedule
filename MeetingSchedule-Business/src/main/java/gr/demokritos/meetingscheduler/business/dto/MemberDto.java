package gr.demokritos.meetingscheduler.business.dto;

import java.util.ArrayList;
import java.util.List;

public class MemberDto extends ParentDto {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private List<AvailabilityDto> availabilityDtos = new ArrayList<>();
    private List<MeetingMemberDto> meetingMemberDtos = new ArrayList<>();
    private List<PossibleMeetingMemberDto> possibleMeetingMemberDtos = new ArrayList<>();

    @NotForMapping
    private Integer index;

    public MemberDto() {

    }

    public MemberDto(Long id, String name, String lastName, String email) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastName = lastName;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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

    public List<AvailabilityDto> getAvailabilityDtos() {
        return availabilityDtos;
    }

    public void setAvailabilityDtos(List<AvailabilityDto> availabilityDtos) {
        this.availabilityDtos = availabilityDtos;
    }

    public List<MeetingMemberDto> getMeetingMemberDtos() {
        return meetingMemberDtos;
    }

    public void setMeetingMemberDtos(List<MeetingMemberDto> meetingMemberDtos) {
        this.meetingMemberDtos = meetingMemberDtos;
    }

    public List<PossibleMeetingMemberDto> getPossibleMeetingMemberDtos() {
        return possibleMeetingMemberDtos;
    }

    public void setPossibleMeetingMemberDtos(List<PossibleMeetingMemberDto> possibleMeetingMemberDtos) {
        this.possibleMeetingMemberDtos = possibleMeetingMemberDtos;
    }

    public void addAvailabilityDto(AvailabilityDto availabilityDto) {
        availabilityDto.setMemberDto(this);
    }

    public void removeAvailabilityDto(AvailabilityDto availabilityDto) {
        availabilityDto.setMemberDto(null);
    }

    public void addPossibleMeetingMemberDto(PossibleMeetingMemberDto possibleMeetingMemberDto) {
        possibleMeetingMemberDto.setMemberDto(this);
    }

    public void removePossibleMeetingDto(PossibleMeetingMemberDto possibleMeetingMemberDto) {
        possibleMeetingMemberDto.setMemberDto(null);
    }

    public void addMeetingMemberDto(MeetingMemberDto meetingMemberDto) {
        meetingMemberDto.setMemberDto(this);
    }

    public void removeMeetingMemberDto(MeetingMemberDto meetingMemberDto) {
        meetingMemberDto.setMemberDto(null);
    }

    public void internalAddAvailabilityDto(AvailabilityDto availabilityDto) {
        this.availabilityDtos.add(availabilityDto);
    }

    public void internalRemoveAvailabilityDto(AvailabilityDto availabilityDto) {
        this.availabilityDtos.remove(availabilityDto);
    }

    public void internalAddPossibleMeetingMemberDto(PossibleMeetingMemberDto possibleMeetingMemberDto) {
        this.possibleMeetingMemberDtos.add(possibleMeetingMemberDto);
    }

    public void internalRemovePossibleMeetingMemberDto(PossibleMeetingMemberDto possibleMeetingMemberDto) {
        this.possibleMeetingMemberDtos.remove(possibleMeetingMemberDto);
    }

    public void internalAddMeetingMemberDto(MeetingMemberDto meetingMemberDto) {
        this.meetingMemberDtos.add(meetingMemberDto);
    }

    public void internalRemoveMeetingMemberDto(MeetingMemberDto meetingMemberDto) {
        this.meetingMemberDtos.remove(meetingMemberDto);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        MemberDto other = (MemberDto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MemberDto [id=" + id + ", name=" + name + ", lastName=" + lastName + "]";
    }


}
