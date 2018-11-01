package gr.demokritos.meetingscheduler.datalayer.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;

import org.apache.commons.collections4.CollectionUtils;

import gr.demokritos.meetingscheduler.datalayer.persistence.entities.MeetingMember;
import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

@JpaRepo
@Dependent
public class MeetingMemberRepository extends AbstractRepository<MeetingMember> {

	public MeetingMemberRepository() {
		super(DbConstants.MEETING_MEMBER_TABLE);
	}
	
	public List<MeetingMember> findAllMeetingMembers() {
		return namedQuery(DbConstants.MEETING_MEMBER_FIND_ALL, null);
	}

	public MeetingMember findMeetingMemberById(Long id) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		List<MeetingMember> meetings = namedQuery(DbConstants.MEETING_MEMBER_FIND_BY_ID, parameters);
		if (!CollectionUtils.isEmpty(meetings)) {
			return meetings.get(0);
		}
		return null;
	}
	
	public List<MeetingMember> findMeetingMemberByMeeting(Long meetingId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("meetingId", meetingId);
		return namedQuery(DbConstants.MEETING_MEMBER_FIND_BY_MEETING, parameters);
	}
	
	public List<MeetingMember> findMeetingMemberByMember(Long memberId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("memberId", memberId);
		return namedQuery(DbConstants.MEETING_MEMBER_FIND_BY_MEMBER, parameters);
	}
	
	public List<MeetingMember> findMeetingMemberByAttended(String attended) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("attended", attended);
		return namedQuery(DbConstants.MEETING_MEMBER_FIND_BY_ATTENDED, parameters);
	}
	
	public List<MeetingMember> findMeetingMemberByMemberAndAttended(Long memberId, String attended) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("memberId", memberId);
		parameters.put("attended", attended);
		return namedQuery(DbConstants.MEETING_MEMBER_FIND_BY_MEMBER_AND_ATTENDED, parameters);
	}
	
	public List<MeetingMember> findMeetingMemberByMeetingAndAttended(Long meetingId, String attended) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("meetingId", meetingId);
		parameters.put("attended", attended);
		return namedQuery(DbConstants.MEETING_MEMBER_FIND_BY_MEETING_AND_ATTENDED, parameters);
	}
	
	public List<MeetingMember> findMeetingMemberByMemberAndMeeting(Long memberId, Long meetingId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("memberId", memberId);
		parameters.put("meetingId", meetingId);
		return namedQuery(DbConstants.MEETING_MEMBER_FIND_BY_MEMBER_AND_MEETING, parameters);
	}
	
	public List<MeetingMember> findMeetingMemberByMeetingMemberAndAttended(Long meetingId, Long memberId, String attended) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("meetingId", meetingId);
		parameters.put("memberId", memberId);
		parameters.put("attended", attended);
		return namedQuery(DbConstants.MEETING_MEMBER_FIND_BY_MEETING_MEMBER_AND_ATTENDED, parameters);
	}

}
