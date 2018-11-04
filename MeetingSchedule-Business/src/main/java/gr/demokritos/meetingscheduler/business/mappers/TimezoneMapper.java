package gr.demokritos.meetingscheduler.business.mappers;

import gr.demokritos.meetingscheduler.business.dto.*;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.*;
import org.apache.commons.collections4.CollectionUtils;

public class TimezoneMapper {

    public TimezoneDto convertTimezoneToTimezoneDto(Timezone timezone) {
        if (timezone == null) return null;
        TimezoneDto timezoneDto = new TimezoneDto();
        timezoneDto.setId(timezone.getId());
        timezoneDto.setStartTime(timezone.getStartTime());
        timezoneDto.setEndTime(timezone.getEndTime());
        if(!CollectionUtils.isEmpty(timezone.getAvailabilities())) {
            timezone.getAvailabilities().forEach(availability -> timezoneDto.addAvailabilityDto(convertAvailabilityToAvailabilityDto(availability)));
        }
        if(!CollectionUtils.isEmpty(timezone.getPossibleMeetings())) {
            timezone.getPossibleMeetings().forEach(possibleMeeting -> timezoneDto.addPossibleMeetingDto(convertPossibleMeetingToPossibleMeetingDto(possibleMeeting)));
        }
        return timezoneDto;
    }

    public Timezone convertTimezoneDtoToTimezone(TimezoneDto timezoneDto) {
        if (timezoneDto == null) return null;
        Timezone timezone = new Timezone();
        timezone.setId(timezoneDto.getId());
        timezone.setStartTime(timezoneDto.getStartTime());
        timezone.setEndTime(timezoneDto.getEndTime());
        if(!CollectionUtils.isEmpty(timezoneDto.getAvailabilityDtos())) {
            timezoneDto.getAvailabilityDtos().forEach(availabilityDto -> timezone.addAvailability(convertAvailabilityDtoToAvailability(availabilityDto)));
        }
        if(!CollectionUtils.isEmpty(timezoneDto.getPossibleMeetingDtos())) {
            timezoneDto.getPossibleMeetingDtos().forEach(possibleMeetingDto -> timezone.addPossibleMeeting(convertPossibleMeetingDtoToPossibleMeeting(possibleMeetingDto)));
        }
        return timezone;
    }

    public PossibleMeetingDto convertPossibleMeetingToPossibleMeetingDto(PossibleMeeting possibleMeeting) {
        if (possibleMeeting == null) return null;
        PossibleMeetingDto possibleMeetingDto = new PossibleMeetingDto();
        possibleMeetingDto.setId(possibleMeeting.getId());
        possibleMeetingDto.setDayDto(convertDayToDayDto(possibleMeeting.getDay()));
        possibleMeetingDto.setTimezoneDto(convertTimezoneToTimezoneDto(possibleMeeting.getTimezone()));
        possibleMeetingDto.setMeetingDto(convertMeetingToMeetingDto(possibleMeeting.getMeeting()));
        if(!CollectionUtils.isEmpty(possibleMeeting.getPossibleMeetingMembers())) {
            possibleMeeting.getPossibleMeetingMembers().forEach(possibleMeetingMember -> possibleMeetingDto.addPossibleMeetingMemberDto(convertPossibleMeetingMemberToPossibleMeetingMemberDto(possibleMeetingMember)));
        }
        return possibleMeetingDto;
    }

    public PossibleMeeting convertPossibleMeetingDtoToPossibleMeeting(PossibleMeetingDto possibleMeetingDto) {
        if (possibleMeetingDto == null) return null;
        PossibleMeeting possibleMeeting = new PossibleMeeting();
        possibleMeeting.setId(possibleMeetingDto.getId());
        possibleMeeting.setDay(convertDayDtoToDay(possibleMeetingDto.getDayDto()));
        possibleMeeting.setTimezone(convertTimezoneDtoToTimezone(possibleMeetingDto.getTimezoneDto()));
        possibleMeeting.setMeeting(convertMeetingDtoToMeeting(possibleMeetingDto.getMeetingDto()));
        if(!CollectionUtils.isEmpty(possibleMeetingDto.getPossibleMeetingMemberDtos())) {
            possibleMeetingDto.getPossibleMeetingMemberDtos().forEach(possibleMeetingMemberDto -> possibleMeeting.addPossibleMeetingMember(convertPossibleMeetingMemberDtoToPossibleMeetingMember(possibleMeetingMemberDto)));
        }
        return possibleMeeting;
    }

    public MeetingDto convertMeetingToMeetingDto(Meeting meeting) {
        if (meeting == null) return null;
        MeetingDto meetingDto = new MeetingDto();
        meetingDto.setId(meeting.getId());
        meetingDto.setDate(meeting.getDate());
        meetingDto.setStartTime(meeting.getStartTime());
        meetingDto.setEndTime(meeting.getEndTime());
        meetingDto.setCompleted(meeting.getCompleted());
        meetingDto.setDuration(meeting.getDuration());
        if(!CollectionUtils.isEmpty(meeting.getMembers())) {
            meeting.getMembers().forEach(meetingMember -> meetingDto.addMeetingMemberDto(convertMeetingMemberToMeetingMemberDto(meetingMember)));
        }
        if (!CollectionUtils.isEmpty(meeting.getAvailabilities())) {
            meeting.getAvailabilities().forEach(availability -> meetingDto.addAvailabilityDto(convertAvailabilityToAvailabilityDto(availability)));
        }
        return meetingDto;
    }

    public Meeting convertMeetingDtoToMeeting(MeetingDto meetingDto) {
        if (meetingDto == null) return null;
        Meeting meeting = new Meeting();
        meeting.setId(meetingDto.getId());
        meeting.setDate(meetingDto.getDate());
        meeting.setDuration(meetingDto.getDuration());
        meeting.setStartTime(meetingDto.getStartTime());
        meeting.setEndTime(meetingDto.getEndTime());
        meeting.setCompleted(meetingDto.getCompleted());
        if(!CollectionUtils.isEmpty(meetingDto.getMeetingMemberDtos())) {
            meetingDto.getMeetingMemberDtos().forEach(meetingMemberDto -> meeting.addMeetingMember(convertMeetingMemberDtoToMeetingMember(meetingMemberDto)));
        }
        if(!CollectionUtils.isEmpty(meetingDto.getAvailabilityDtos())) {
            meetingDto.getAvailabilityDtos().forEach(availabilityDto -> meeting.addAvailability(convertAvailabilityDtoToAvailability(availabilityDto)));
        }
        return meeting;
    }

    public MeetingMemberDto convertMeetingMemberToMeetingMemberDto(MeetingMember meetingMember) {
        if (meetingMember == null) return null;
        MeetingMemberDto meetingMemberDto = new MeetingMemberDto();
        meetingMemberDto.setId(meetingMember.getId());
        meetingMemberDto.setAttended(meetingMember.getAttended());
        meetingMemberDto.setMemberDto(convertMemberToMemberDto(meetingMember.getMember()));
        return meetingMemberDto;
    }

    public MeetingMember convertMeetingMemberDtoToMeetingMember(MeetingMemberDto meetingMemberDto) {
        if (meetingMemberDto == null) return null;
        MeetingMember meetingMember = new MeetingMember();
        meetingMember.setId(meetingMemberDto.getId());
        meetingMember.setAttended(meetingMemberDto.getAttended());
        meetingMember.setMember(convertMemberDtoToMember(meetingMemberDto.getMemberDto()));
        return meetingMember;
    }

    public MemberDto convertMemberToMemberDto(Member member) {
        if(member==null) return null;
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setName(member.getName());
        memberDto.setLastName(member.getLastName());
        memberDto.setEmail(member.getEmail());
        if(!CollectionUtils.isEmpty(member.getPossibleMeetingMembers())) {
            member.getPossibleMeetingMembers().forEach(possibleMeetingMember -> memberDto.addPossibleMeetingMemberDto(convertPossibleMeetingMemberToPossibleMeetingMemberDto(possibleMeetingMember)));
        }
        return memberDto;
    }

    public Member convertMemberDtoToMember(MemberDto memberDto) {
        if(memberDto==null) return null;
        Member member = new Member();
        member.setId(memberDto.getId());
        member.setName(memberDto.getName());
        member.setEmail(memberDto.getEmail());
        member.setLastName(memberDto.getLastName());
        if(!CollectionUtils.isEmpty(memberDto.getPossibleMeetingMemberDtos())) {
            memberDto.getPossibleMeetingMemberDtos().forEach(possibleMeetingMemberDto -> member.addPossibleMeetingMember(convertPossibleMeetingMemberDtoToPossibleMeetingMember(possibleMeetingMemberDto)));
        }
        return member;
    }

    public PossibleMeetingMemberDto convertPossibleMeetingMemberToPossibleMeetingMemberDto(PossibleMeetingMember possibleMeetingMember) {
        if (possibleMeetingMember == null) return null;
        PossibleMeetingMemberDto possibleMeetingMemberDto = new PossibleMeetingMemberDto();
        possibleMeetingMemberDto.setId(possibleMeetingMember.getId());
        possibleMeetingMemberDto.setAttending(possibleMeetingMember.getAttending());
        return possibleMeetingMemberDto;
    }

    public PossibleMeetingMember convertPossibleMeetingMemberDtoToPossibleMeetingMember(PossibleMeetingMemberDto possibleMeetingMemberDto) {
        if (possibleMeetingMemberDto == null) return null;
        PossibleMeetingMember possibleMeetingMember = new PossibleMeetingMember();
        possibleMeetingMember.setId(possibleMeetingMemberDto.getId());
        possibleMeetingMember.setAttending(possibleMeetingMemberDto.getAttending());
        return possibleMeetingMember;
    }

    public AvailabilityDto convertAvailabilityToAvailabilityDto(Availability availability) {
        if(availability == null) return null;
        AvailabilityDto availabilityDto = new AvailabilityDto();
        availabilityDto.setId(availability.getId());
        availabilityDto.setIsAvailabile(availability.getAvailability());
        availabilityDto.setDayDto(convertDayToDayDto(availability.getDay()));
        return availabilityDto;
    }

    public Availability convertAvailabilityDtoToAvailability(AvailabilityDto availabilityDto) {
        if(availabilityDto == null) return null;
        Availability availability = new Availability();
        availability.setId(availabilityDto.getId());
        availability.setAvailability(availabilityDto.getIsAvailable());
        availability.setDay(convertDayDtoToDay(availabilityDto.getDayDto()));
        return availability;
    }

    public DayDto convertDayToDayDto(Day day) {
        if (day == null) return null;
        DayDto dayDto = new DayDto();
        dayDto.setId(day.getId());
        dayDto.setDate(day.getDate());
        if (day.getDate() != null) {
            dayDto.setDayOfWeek(day.getDate().getDayOfWeek());
            dayDto.setName(dayDto.getDayOfWeek().toString());
        }
        return dayDto;
    }

    public Day convertDayDtoToDay(DayDto dayDto) {
        if (dayDto == null) return null;
        Day day = new Day();
        day.setId(dayDto.getId());
        day.setName(dayDto.getName());
        day.setDate(dayDto.getDate());
        return day;
    }

}
