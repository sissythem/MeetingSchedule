package gr.demokritos.meetingscheduler.business.mappers;

import gr.demokritos.meetingscheduler.business.dto.*;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.*;
import org.apache.commons.collections4.CollectionUtils;

public class MemberMapper {

    public MemberDto convertMemberToMemberDto(Member member) {
        if(member==null) return null;
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setName(member.getName());
        memberDto.setLastName(member.getLastName());
        if(!CollectionUtils.isEmpty(member.getAvailabilities())) {
            member.getAvailabilities().forEach(availability -> memberDto.addAvailabilityDto(convertAvailabilityToAvailabilityDto(availability)));
        }
        if(!CollectionUtils.isEmpty(member.getMeetings())) {
            member.getMeetings().forEach(meetingMember -> memberDto.addMeetingMemberDto(convertMeetingMemberToMeetingMemberDto(meetingMember)));
        }
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
        member.setLastName(memberDto.getLastName());
        if(!CollectionUtils.isEmpty(memberDto.getAvailabilityDtos())) {
            memberDto.getAvailabilityDtos().forEach(availabilityDto -> member.addAvailability(convertAvailabilityDtoToAvailability(availabilityDto)));
        }
        if(!CollectionUtils.isEmpty(memberDto.getPossibleMeetingMemberDtos())) {
            memberDto.getPossibleMeetingMemberDtos().forEach(possibleMeetingMemberDto -> member.addPossibleMeetingMember(convertPossibleMeetingMemberDtoToPossibleMeetingMember(possibleMeetingMemberDto)));
        }
        if(!CollectionUtils.isEmpty(memberDto.getMeetingMemberDtos())) {
            memberDto.getMeetingMemberDtos().forEach(meetingMemberDto -> member.addMeetingMember(convertMeetingMemberDtoToMeetingMember(meetingMemberDto)));
        }
        return member;
    }

    private AvailabilityDto convertAvailabilityToAvailabilityDto(Availability availability) {
        if(availability == null) return null;
        AvailabilityDto availabilityDto = new AvailabilityDto();
        availabilityDto.setId(availability.getId());
        availabilityDto.setIsAvailabile(availability.getAvailability());
        availabilityDto.setDayDto(convertDayToDayDto(availability.getDay()));
        availabilityDto.setTimezoneDto(convertTimezoneToTimezoneDto(availability.getTimezone()));
        return availabilityDto;
    }

    private Availability convertAvailabilityDtoToAvailability(AvailabilityDto availabilityDto) {
        if(availabilityDto == null) return null;
        Availability availability = new Availability();
        availability.setId(availabilityDto.getId());
        availability.setAvailability(availabilityDto.getIsAvailable());
        availability.setDay(convertDayDtoToDay(availabilityDto.getDayDto()));
        availability.setTimezone(convertTimezoneDtoToTimezone(availabilityDto.getTimezoneDto()));
        return availability;
    }

    private MeetingMemberDto convertMeetingMemberToMeetingMemberDto(MeetingMember meetingMember) {
        if (meetingMember == null) return null;
        MeetingMemberDto meetingMemberDto = new MeetingMemberDto();
        meetingMemberDto.setId(meetingMember.getId());
        meetingMemberDto.setAttended(meetingMember.getAttended());
        meetingMemberDto.setMeetingDto(convertMeetingToMeetingDto(meetingMember.getMeeting()));
        return meetingMemberDto;
    }

    private MeetingMember convertMeetingMemberDtoToMeetingMember(MeetingMemberDto meetingMemberDto) {
        if (meetingMemberDto == null) return null;
        MeetingMember meetingMember = new MeetingMember();
        meetingMember.setId(meetingMemberDto.getId());
        meetingMember.setAttended(meetingMemberDto.getAttended());
        meetingMember.setMeeting(convertMeetingDtoToMeeting(meetingMemberDto.getMeetingDto()));
        return meetingMember;
    }

    private MeetingDto convertMeetingToMeetingDto(Meeting meeting) {
        if (meeting == null) return null;
        MeetingDto meetingDto = new MeetingDto();
        meetingDto.setId(meeting.getId());
        meetingDto.setDate(meeting.getDate());
        meetingDto.setStartTime(meeting.getStartTime());
        meetingDto.setEndTime(meeting.getEndTime());
        meetingDto.setCompleted(meeting.getCompleted());
        if (!CollectionUtils.isEmpty(meeting.getPossibleMeetings())) {
            meeting.getPossibleMeetings().forEach(possibleMeeting -> meetingDto.addPossibleMeetingDto(convertPossibleMeetingToPossibleMeetingDto(possibleMeeting)));
        }
        return meetingDto;
    }

    private Meeting convertMeetingDtoToMeeting(MeetingDto meetingDto) {
        if (meetingDto == null) return null;
        Meeting meeting = new Meeting();
        meeting.setId(meetingDto.getId());
        meeting.setDate(meetingDto.getDate());
        meeting.setStartTime(meetingDto.getStartTime());
        meeting.setEndTime(meetingDto.getEndTime());
        meeting.setCompleted(meetingDto.getCompleted());
        if (!CollectionUtils.isEmpty(meetingDto.getPossibleMeetingDtos())) {
            meetingDto.getPossibleMeetingDtos().forEach(possibleMeetingDto -> meeting.addPossibleMeeting(convertPossibleMeetingDtoToPossibleMeeting(possibleMeetingDto)));
        }
        return meeting;
    }

    private PossibleMeetingMemberDto convertPossibleMeetingMemberToPossibleMeetingMemberDto(PossibleMeetingMember possibleMeetingMember) {
        if (possibleMeetingMember == null) return null;
        PossibleMeetingMemberDto possibleMeetingMemberDto = new PossibleMeetingMemberDto();
        possibleMeetingMemberDto.setId(possibleMeetingMember.getId());
        possibleMeetingMemberDto.setAttending(possibleMeetingMember.getAttending());
        possibleMeetingMemberDto.setPossibleMeetingDto(convertPossibleMeetingToPossibleMeetingDto(possibleMeetingMember.getPossibleMeeting()));
        return possibleMeetingMemberDto;
    }

    private PossibleMeetingMember convertPossibleMeetingMemberDtoToPossibleMeetingMember(PossibleMeetingMemberDto possibleMeetingMemberDto) {
        if (possibleMeetingMemberDto == null) return null;
        PossibleMeetingMember possibleMeetingMember = new PossibleMeetingMember();
        possibleMeetingMember.setId(possibleMeetingMemberDto.getId());
        possibleMeetingMember.setAttending(possibleMeetingMemberDto.getAttending());
        possibleMeetingMember.setPossibleMeeting(convertPossibleMeetingDtoToPossibleMeeting(possibleMeetingMemberDto.getPossibleMeetingDto()));
        return possibleMeetingMember;
    }

    private PossibleMeetingDto convertPossibleMeetingToPossibleMeetingDto(PossibleMeeting possibleMeeting) {
        if (possibleMeeting == null) return null;
        PossibleMeetingDto possibleMeetingDto = new PossibleMeetingDto();
        possibleMeetingDto.setId(possibleMeeting.getId());
        possibleMeetingDto.setDayDto(convertDayToDayDto(possibleMeeting.getDay()));
        possibleMeetingDto.setTimezoneDto(convertTimezoneToTimezoneDto(possibleMeeting.getTimezone()));
        return possibleMeetingDto;
    }

    private PossibleMeeting convertPossibleMeetingDtoToPossibleMeeting(PossibleMeetingDto possibleMeetingDto) {
        if (possibleMeetingDto == null) return null;
        PossibleMeeting possibleMeeting = new PossibleMeeting();
        possibleMeeting.setId(possibleMeetingDto.getId());
        possibleMeeting.setDay(convertDayDtoToDay(possibleMeetingDto.getDayDto()));
        possibleMeeting.setTimezone(convertTimezoneDtoToTimezone(possibleMeetingDto.getTimezoneDto()));
        return possibleMeeting;
    }

    private DayDto convertDayToDayDto(Day day) {
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

    private Day convertDayDtoToDay(DayDto dayDto) {
        if (dayDto == null) return null;
        Day day = new Day();
        day.setId(dayDto.getId());
        day.setName(dayDto.getName());
        day.setDate(dayDto.getDate());
        return day;
    }

    private TimezoneDto convertTimezoneToTimezoneDto(Timezone timezone) {
        if (timezone == null) return null;
        TimezoneDto timezoneDto = new TimezoneDto();
        timezoneDto.setId(timezone.getId());
        timezoneDto.setStartTime(timezone.getStartTime());
        timezoneDto.setEndTime(timezone.getEndTime());
        return timezoneDto;
    }

    private Timezone convertTimezoneDtoToTimezone(TimezoneDto timezoneDto) {
        if (timezoneDto == null) return null;
        Timezone timezone = new Timezone();
        timezone.setId(timezoneDto.getId());
        timezone.setStartTime(timezoneDto.getStartTime());
        timezone.setEndTime(timezoneDto.getEndTime());
        return timezone;
    }

}
