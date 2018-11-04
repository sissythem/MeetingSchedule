package gr.demokritos.meetingscheduler.datalayer.utils;

public class DbConstants {

    public static final String YES = "Y";
    public static final String NO = "N";

    public static final String USER_TABLE = "users";
    public static final String MEMBER_TABLE = "members";
    public static final String AVAILABILITY_TABLE = "availabilities";
    public static final String DAY_TABLE = "days";
    public static final String TIMEZONE_TABLE = "timezones";
    public static final String MEETING_TABLE = "meetings";
    public static final String MEETING_MEMBER_TABLE = "meetings_members";
    public static final String POSSIBLE_MEETING_TABLE = "possible_meetings";
    public static final String POSSIBLE_MEETING_MEMBER_TABLE = "possible_meetings_members";

    public static final String USER_FIND_ALL = "User.findAll";
    public static final String USER_FIND_BY_ID = "User.findById";
    public static final String USER_FIND_BY_NAME = "User.findByName";
    public static final String USER_FIND_BY_LAST_NAME = "User.findByLastName";
    public static final String USER_FIND_BY_USERNAME = "User.findByUsername";
    public static final String USER_FIND_BY_EMAIL = "User.findByEmail";

    public static final String MEMBER_FIND_ALL = "Member.findAll";
    public static final String MEMBER_FIND_BY_ID = "Member.findById";
    public static final String MEMBER_FIND_BY_NAME = "Member.findByName";
    public static final String MEMBER_FIND_BY_LAST_NAME = "Member.findByLastName";
    public static final String MEMBER_FIND_BY_NAME_AND_LAST_NAME = "Member.findByNameAndLastName";

    public static final String DAY_FIND_ALL = "Day.findAll";
    public static final String DAY_FIND_BY_ID = "Day.findById";
    public static final String DAY_FIND_BY_NAME = "Day.findByName";
    public static final String DAY_FIND_BY_DATE = "Day.findByDate";
    public static final String DAY_FIND_BY_NAME_AND_DATE = "Day.findByNameAndDate";

    public static final String TIMEZONE_FIND_ALL = "Timezone.findAll";
    public static final String TIMEZONE_FIND_ID = "Timezone.findById";
    public static final String TIMEZONE_FIND_START_TIME = "Timezone.findByStartTime";
    public static final String TIMEZONE_FIND_END_TIME = "Timezone.findByEndTime";
    public static final String TIMEZONE_FIND_START_TIME_AND_END_TIME = "Timezone.findByStartTimeAndEndTime";

    public static final String MEETING_MEMBER_FIND_ALL = "MeetingMember.findAll";
    public static final String MEETING_MEMBER_FIND_BY_ID = "MeetingMember.findById";
    public static final String MEETING_MEMBER_FIND_BY_MEETING = "MeetingMember.findByMeeting";
    public static final String MEETING_MEMBER_FIND_BY_MEMBER = "MeetingMember.findByMember";
    public static final String MEETING_MEMBER_FIND_BY_ATTENDED = "MeetingMember.findByAttended";
    public static final String MEETING_MEMBER_FIND_BY_MEMBER_AND_ATTENDED = "MeetingMember.findByMemberAndAttended";
    public static final String MEETING_MEMBER_FIND_BY_MEETING_AND_ATTENDED = "MeetingMember.findByMeetingAndAttended";
    public static final String MEETING_MEMBER_FIND_BY_MEMBER_AND_MEETING = "MeetingMember.findByMemberAndMeeting";
    public static final String MEETING_MEMBER_FIND_BY_MEETING_MEMBER_AND_ATTENDED = "MeetingMember.findByMeetingMemberAndAttended";

    public static final String POSSIBLE_MEETING_MEMBER_FIND_ALL = "PossibleMeetingMember.findAll";
    public static final String POSSIBLE_MEETING_MEMBER_FIND_BY_ID = "PossibleMeetingMember.findById";
    public static final String POSSIBLE_MEETING_MEMBER_FIND_BY_POSSIBLE_MEETING = "PossibleMeetingMember.findByPossibleMeeting";
    public static final String POSSIBLE_MEETING_MEMBER_FIND_BY_MEMBER = "PossibleMeetingMember.findByMember";
    public static final String POSSIBLE_MEETING_MEMBER_FIND_BY_ATTENDING = "PossibleMeetingMember.findByAttending";
    public static final String POSSIBLE_MEETING_MEMBER_FIND_BY_MEMBER_AND_ATTENDING = "PossibleMeetingMember.findByMemberAndAttending";
    public static final String POSSIBLE_MEETING_MEMBER_FIND_BY_MEMBER_AND_POSSIBLE_MEETING = "PossibleMeetingMember.findByMemberAndMeeting";
    public static final String POSSIBLE_MEETING_MEMBER_FIND_BY_POSSIBLE_MEETING_AND_ATTENDING = "PossibleMeetingMember.findByPossibleMeetingAndAttending";
    public static final String POSSIBLE_MEETING_MEMBER_FIND_BY_POSSIBLE_MEETING_MEMBER_AND_ATTENDING = "PossibleMeetingMember.findByPossibleMeetingMemberAndAttendoing";

    public static final String MEETING_FIND_ALL = "Meeting.findAll";
    public static final String MEETING_FIND_BY_ID = "Meeting.findById";
    public static final String MEETING_FIND_BY_NAME = "Meeting.findByName";
    public static final String MEETING_FIND_BY_DATE = "Meeting.findByDate";
    public static final String MEETING_FIND_BY_START_TIME = "Meeting.findByStartTime";
    public static final String MEETING_FIND_BY_END_TIME = "Meeting.findByEndTime";
    public static final String MEETING_FIND_BY_COMPLETED = "Meeting.findByCompleted";

    public static final String POSSIBLE_MEETING_FIND_ALL = "PossibleMeeting.findAll";
    public static final String POSSIBLE_MEETING_FIND_BY_ID = "PossibleMeeting.findById";
    public static final String POSSIBLE_MEETING_FIND_BY_MEETING = "PossibleMeeting.findByMeeting";
    public static final String POSSIBLE_MEETING_FIND_BY_DAY = "PossibleMeeting.findByDay";
    public static final String POSSIBLE_MEETING_FIND_BY_TIMEZONE = "PossibleMeeting.findByTimezone";
    public static final String POSSIBLE_MEETING_FIND_BY_MEETING_AND_DAY = "PossibleMeeting.findByMeetingAndDay";
    public static final String POSSIBLE_MEETING_FIND_BY_DAY_AND_TIMEZONE = "PossibleMeeting.findByDayAndTimezone";
    public static final String POSSIBLE_MEETING_FIND_BY_MEETING_DAY_AND_TIMEZONE = "PossibleMeeting.findByMeetingDayAndTimezone";

    public static final String AVAILABILITY_FIND_ALL = "Availability.findAll";
    public static final String AVAILABILITY_FIND_BY_ID = "Availability.findById";
    public static final String AVAILABILITY_FIND_BY_MEMBER = "Availability.findByMember";
    public static final String AVAILABILITY_FIND_BY_DAY = "Availability.findByDay";
    public static final String AVAILABILITY_FIND_BY_TIMEZONE = "Availability.findByTimezone";
    public static final String AVAILABILITY_FIND_BY_MEETING = "Availability.findByMeeting";
    public static final String AVAILABILITY_FIND_BY_MEETING_AND_AVAILABILITY = "Availability.findByMeetingAndAvailability";
    public static final String AVAILABILITY_FIND_BY_DAY_AND_TIMEZONE = "Availability.findByDayAndTimezone";
    public static final String AVAILABILITY_FIND_BY_MEMBER_AND_DAY = "Availability.findByMemberAndDay";
    public static final String AVAILABILITY_FIND_BY_MEMBER_DAY_AND_TIMEZONE = "Availability.findByMemberDayAndTimezone";
}
