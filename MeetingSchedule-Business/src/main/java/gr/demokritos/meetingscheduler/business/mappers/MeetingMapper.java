package gr.demokritos.meetingscheduler.business.mappers;

import org.apache.commons.collections4.CollectionUtils;

import gr.demokritos.meetingscheduler.business.dto.DayDto;
import gr.demokritos.meetingscheduler.business.dto.MeetingDto;
import gr.demokritos.meetingscheduler.business.dto.MeetingMemberDto;
import gr.demokritos.meetingscheduler.business.dto.MemberDto;
import gr.demokritos.meetingscheduler.business.dto.PossibleMeetingDto;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Day;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Meeting;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.MeetingMember;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Member;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.PossibleMeeting;

public class MeetingMapper {

	public MeetingDto convertMeetingToMeetingDto(Meeting meeting) {
		MeetingDto meetingDto = new MeetingDto();
		meetingDto.setId(meeting.getId());
		meetingDto.setCompleted(meeting.getCompleted());
		meetingDto.setDate(meeting.getDate());
		meetingDto.setEndTime(meeting.getEndTime());
		meetingDto.setStartTime(meeting.getStartTime());
		meetingDto.setName(meeting.getName());
		if (!CollectionUtils.isEmpty(meeting.getMembers())) {
			meeting.getMembers().forEach(meetingMember -> meetingDto
					.addMeetingMemberDto(convertMeetingMemberToMeetingMemberDto(meetingMember)));
		}
		if (!CollectionUtils.isEmpty(meeting.getPossibleMeetings())) {
			meeting.getPossibleMeetings().forEach(possibleMeeting -> meetingDto
					.addPossibleMeetingDto(convertPossibleMeetingToPossibleMeetingDto(possibleMeeting)));
		}
		return meetingDto;
	}

	public Meeting convertMeetingDtoToMeeting(MeetingDto meetingDto) {
		Meeting meeting = new Meeting();
		meeting.setId(meetingDto.getId());
		meeting.setCompleted(meetingDto.getCompleted());
		meeting.setDate(meetingDto.getDate());
		meeting.setEndTime(meetingDto.getEndTime());
		meeting.setStartTime(meetingDto.getStartTime());
		meeting.setName(meetingDto.getName());
		if (!CollectionUtils.isEmpty(meetingDto.getMeetingMemberDtos())) {
			meetingDto.getMeetingMemberDtos().forEach(meetingMemberDto -> meeting
					.addMeetingMember(convertMeetingMemberDtoToMeetingMember(meetingMemberDto)));
		}
		if (!CollectionUtils.isEmpty(meetingDto.getPossibleMeetingDtos())) {
			meetingDto.getPossibleMeetingDtos().forEach(possibleMeetingDto -> meeting
					.addPossibleMeeting(convertPossibleMeetingDtoToPossibleMeeting(possibleMeetingDto)));
		}
		return meeting;
	}

	public MeetingMemberDto convertMeetingMemberToMeetingMemberDto(MeetingMember meetingMember) {
		MeetingMemberDto meetingMemberDto = new MeetingMemberDto();
		meetingMemberDto.setId(meetingMember.getId());
		meetingMemberDto.setAttended(meetingMember.getAttended());
		meetingMemberDto.setMemberDto(convertMemberToMemberDto(meetingMember.getMember()));
		return meetingMemberDto;
	}

	public MeetingMember convertMeetingMemberDtoToMeetingMember(MeetingMemberDto meetingMemberDto) {
		MeetingMember meetingMember = new MeetingMember();
		meetingMember.setId(meetingMemberDto.getId());
		meetingMember.setAttended(meetingMemberDto.getAttended());
		meetingMember.setMember(convertMemberDtoToMember(meetingMemberDto.getMemberDto()));
		return meetingMember;
	}

	public PossibleMeetingDto convertPossibleMeetingToPossibleMeetingDto(PossibleMeeting possibleMeeting) {
		PossibleMeetingDto possibleMeetingDto = new PossibleMeetingDto();
		possibleMeetingDto.setId(possibleMeeting.getId());
		
		return possibleMeetingDto;
	}

	public PossibleMeeting convertPossibleMeetingDtoToPossibleMeeting(PossibleMeetingDto possibleMeetingDto) {
		PossibleMeeting possibleMeeting = new PossibleMeeting();

		return possibleMeeting;
	}
	
	public MemberDto convertMemberToMemberDto(Member member) {
		MemberDto memberDto = new MemberDto();
		
		return memberDto;
	}
	
	public Member convertMemberDtoToMember(MemberDto memberDto) {
		Member member = new Member();
		
		return member;
	}
	
	public DayDto convertDayToDayDto(Day day) {
		DayDto dayDto = new DayDto();
		
		return dayDto;
	}
	
	public Day convertDayDtoToDay(DayDto dayDto) {
		Day day = new Day();
		
		return day;
	}
}
