package gr.demokritos.meetingscheduler.business.mappers;

import org.apache.commons.collections4.CollectionUtils;

import gr.demokritos.meetingscheduler.business.dto.AvailabilityDto;
import gr.demokritos.meetingscheduler.business.dto.DayDto;
import gr.demokritos.meetingscheduler.business.dto.MeetingMemberDto;
import gr.demokritos.meetingscheduler.business.dto.MemberDto;
import gr.demokritos.meetingscheduler.business.dto.PossibleMeetingDto;
import gr.demokritos.meetingscheduler.business.dto.PossibleMeetingMemberDto;
import gr.demokritos.meetingscheduler.business.dto.TimezoneDto;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Availability;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Day;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.MeetingMember;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Member;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.PossibleMeeting;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.PossibleMeetingMember;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Timezone;
import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

public class AvailabilityMapper {

	public AvailabilityDto convertAvailabilityToAvailabilityDto(Availability availability) {
		if (availability == null) {
			return null;
		}
		AvailabilityDto availabilityDto = new AvailabilityDto();
		availabilityDto.setId(availability.getId());
		availabilityDto.setDayDto(convertDayToDayDto(availability.getDay()));
		availabilityDto.setIsAvailable(isAvailable(availability.getAvailability()));
		availabilityDto.setMemberDto(convertMemberToMemberDto(availability.getMember()));
		availabilityDto.setTimezoneDto(convertTimezoneToTimezoneDto(availability.getTimezone()));
		return availabilityDto;
	}

	private Boolean isAvailable(String availability) {
		if (availability != null) {
			if (availability.equals(DbConstants.YES)) {
				return true;
			} else {
				return false;
			}
		}
		return null;
	}

	public Availability convertAvailabilityDtoToAvailability(AvailabilityDto availabilityDto) {
		if (availabilityDto == null) {
			return null;
		}
		Availability availability = new Availability();
		availability.setId(availabilityDto.getId());
		availability.setAvailability(availabilityDto.getIsAvailable());
		availability.setDay(convertDayDtoToDay(availabilityDto.getDayDto()));
		availability.setMember(convertMemberDtoToMember(availabilityDto.getMemberDto()));
		availability.setTimezone(convertTimezoneDtoToTimezone(availabilityDto.getTimezoneDto()));
		return availability;
	}

	public DayDto convertDayToDayDto(Day day) {
		if (day == null) {
			return null;
		}
		DayDto dayDto = new DayDto();
		dayDto.setId(day.getId());
		dayDto.setDate(day.getDate());
		dayDto.setName(day.getName());
		if (day.getDate() != null) {
			dayDto.setDayOfWeek(day.getDate().getDayOfWeek());
		}
		if (!CollectionUtils.isEmpty(day.getPossibleMeetings())) {
			day.getPossibleMeetings().forEach(possibleMeeting -> {
				PossibleMeetingDto possibleMeetingDto = convertPossibleMeetingToPossibleMeetingDto(possibleMeeting);
				dayDto.addPossibleMeetingDto(possibleMeetingDto);
			});
		}
		if (!CollectionUtils.isEmpty(day.getTimezones())) {
			day.getTimezones().forEach(timezone -> {
				TimezoneDto timezoneDto = convertTimezoneToTimezoneDto(timezone);
				dayDto.addTimezoneDto(timezoneDto);
			});
		}
		return dayDto;
	}

	public Day convertDayDtoToDay(DayDto dayDto) {
		if (dayDto == null) {
			return null;
		}
		Day day = new Day();
		day.setDate(dayDto.getDate());
		day.setId(dayDto.getId());
		day.setName(dayDto.getName());
		if (!CollectionUtils.isEmpty(dayDto.getPossibleMeetingsDto())) {
			dayDto.getPossibleMeetingsDto().forEach(possibleMeeting -> day
					.addPossibleMeeting(convertPossibleMeetingDtoToPossibleMeeting(possibleMeeting)));
		}

		if (!CollectionUtils.isEmpty(dayDto.getTimezones())) {
			dayDto.getTimezones().forEach(timezone -> day.addTimezone(convertTimezoneDtoToTimezone(timezone)));
		}
		return day;
	}

	public MemberDto convertMemberToMemberDto(Member member) {
		if (member == null) {
			return null;
		}
		MemberDto memberDto = new MemberDto();
		memberDto.setId(member.getId());
		memberDto.setName(member.getName());
		memberDto.setLastName(member.getLastName());
		if (!CollectionUtils.isEmpty(member.getMeetings())) {
			member.getMeetings()
					.forEach(meeting -> memberDto.addMeetingMemberDto(convertMeetingMemberToMeetingMemberDto(meeting)));
		}
		if (!CollectionUtils.isEmpty(member.getPossibleMeetingMembers())) {
			member.getPossibleMeetingMembers().forEach(possibleMeetingMember -> memberDto.addPossibleMeetingMemberDto(
					convertPossibleMeetingMemberToPossibleMeetingMemberDto(possibleMeetingMember)));
		}
		return memberDto;
	}

	public Member convertMemberDtoToMember(MemberDto memberDto) {
		if (memberDto == null) {
			return null;
		}
		Member member = new Member();
		member.setId(memberDto.getId());
		member.setName(memberDto.getName());
		member.setLastName(memberDto.getLastName());
		if (!CollectionUtils.isEmpty(memberDto.getMeetingMemberDtos())) {
			memberDto.getMeetingMemberDtos().forEach(meetingMemberDto -> member
					.addMeetingMember(convertMeetingMemberDtoToMeetingMember(meetingMemberDto)));
		}
		if (!CollectionUtils.isEmpty(memberDto.getPossibleMeetingMemberDtos())) {
			memberDto.getPossibleMeetingMemberDtos()
					.forEach(possibleMeetingMemberDto -> member.addPossibleMeetingMember(
							convertPossibleMeetingMemberDtoToPossibleMeetingMember(possibleMeetingMemberDto)));
		}
		return member;
	}

	public TimezoneDto convertTimezoneToTimezoneDto(Timezone timezone) {
		if (timezone == null) {
			return null;
		}
		TimezoneDto timezoneDto = new TimezoneDto();
		timezoneDto.setId(timezone.getId());
		timezoneDto.setStartTime(timezone.getStartTime());
		timezoneDto.setEndTime(timezone.getEndTime());
		if (!CollectionUtils.isEmpty(timezone.getPossibleMeetings())) {
			timezone.getPossibleMeetings().forEach(possibleMeeting -> timezoneDto
					.addPossibleMeetingDto(convertPossibleMeetingToPossibleMeetingDto(possibleMeeting)));
		}
		return timezoneDto;
	}

	public Timezone convertTimezoneDtoToTimezone(TimezoneDto timezoneDto) {
		if (timezoneDto == null) {
			return null;
		}
		Timezone timezone = new Timezone();
		timezone.setId(timezoneDto.getId());
		timezone.setStartTime(timezoneDto.getStartTime());
		timezone.setEndTime(timezoneDto.getEndTime());
		if (!CollectionUtils.isEmpty(timezoneDto.getPossibleMeetingDtos())) {
			timezoneDto.getPossibleMeetingDtos().forEach(possibleMeetingDto -> timezone
					.addPossibleMeeting(convertPossibleMeetingDtoToPossibleMeeting(possibleMeetingDto)));
		}
		return timezone;
	}

	public PossibleMeetingDto convertPossibleMeetingToPossibleMeetingDto(PossibleMeeting possibleMeeting) {
		if (possibleMeeting == null) {
			return null;
		}
		PossibleMeetingDto possibleMeetingDto = new PossibleMeetingDto();
		possibleMeetingDto.setId(possibleMeeting.getId());
		if (!CollectionUtils.isEmpty(possibleMeeting.getPossibleMeetingMembers())) {
			possibleMeeting.getPossibleMeetingMembers()
					.forEach(possibleMeetingMember -> possibleMeetingDto.addPossibleMeetingMemberDto(
							convertPossibleMeetingMemberToPossibleMeetingMemberDto(possibleMeetingMember)));
		}
		return possibleMeetingDto;
	}

	public PossibleMeeting convertPossibleMeetingDtoToPossibleMeeting(PossibleMeetingDto possibleMeetingDto) {
		if (possibleMeetingDto == null) {
			return null;
		}
		PossibleMeeting possibleMeeting = new PossibleMeeting();
		possibleMeeting.setId(possibleMeetingDto.getId());
		if (!CollectionUtils.isEmpty(possibleMeetingDto.getPossibleMeetingMemberDtos())) {
			possibleMeetingDto.getPossibleMeetingMemberDtos()
					.forEach(possibleMeetingMemberDto -> possibleMeeting.addPossibleMeetingMember(
							convertPossibleMeetingMemberDtoToPossibleMeetingMember(possibleMeetingMemberDto)));
		}
		return possibleMeeting;
	}

	public PossibleMeetingMember convertPossibleMeetingMemberDtoToPossibleMeetingMember(
			PossibleMeetingMemberDto possibleMeetingMemberDto) {
		if (possibleMeetingMemberDto == null) {
			return null;
		}
		PossibleMeetingMember possibleMeetingMember = new PossibleMeetingMember();
		possibleMeetingMember.setId(possibleMeetingMemberDto.getId());
		possibleMeetingMember.setAttending(possibleMeetingMemberDto.getAttending());
		return possibleMeetingMember;
	}

	public PossibleMeetingMemberDto convertPossibleMeetingMemberToPossibleMeetingMemberDto(
			PossibleMeetingMember possibleMeetingMember) {
		if (possibleMeetingMember == null) {
			return null;
		}
		PossibleMeetingMemberDto possibleMeetingMemberDto = new PossibleMeetingMemberDto();
		possibleMeetingMemberDto.setId(possibleMeetingMember.getId());
		possibleMeetingMemberDto.setAttending(possibleMeetingMember.getAttending());
		return possibleMeetingMemberDto;
	}

	public MeetingMemberDto convertMeetingMemberToMeetingMemberDto(MeetingMember meetingMember) {
		if (meetingMember == null) {
			return null;
		}
		MeetingMemberDto meetingMemberDto = new MeetingMemberDto();
		meetingMemberDto.setId(meetingMember.getId());
		meetingMemberDto.setAttended(meetingMember.getAttended());
		return meetingMemberDto;
	}

	public MeetingMember convertMeetingMemberDtoToMeetingMember(MeetingMemberDto meetingMemberDto) {
		if (meetingMemberDto == null) {
			return null;
		}
		MeetingMember meetingMember = new MeetingMember();
		meetingMember.setId(meetingMemberDto.getId());
		meetingMember.setAttended(meetingMemberDto.getAttended());
		return meetingMember;
	}
}
