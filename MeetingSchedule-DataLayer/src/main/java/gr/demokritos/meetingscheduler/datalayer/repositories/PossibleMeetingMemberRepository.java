package gr.demokritos.meetingscheduler.datalayer.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;

import org.apache.commons.collections4.CollectionUtils;

import gr.demokritos.meetingscheduler.datalayer.persistence.entities.PossibleMeetingMember;
import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

@JpaRepo
@Dependent
public class PossibleMeetingMemberRepository extends AbstractRepository<PossibleMeetingMember> {

	public PossibleMeetingMemberRepository() {
		super(DbConstants.POSSIBLE_MEETING_MEMBER_TABLE);
	}
	
	public List<PossibleMeetingMember> findAllPossibleMeetingMembers() {
		return namedQuery(DbConstants.POSSIBLE_MEETING_MEMBER_FIND_ALL, null);
	}

	public PossibleMeetingMember findPossibleMeetingMemberById(Long id) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		List<PossibleMeetingMember> meetings = namedQuery(DbConstants.POSSIBLE_MEETING_MEMBER_FIND_BY_ID, parameters);
		if (!CollectionUtils.isEmpty(meetings)) {
			return meetings.get(0);
		}
		return null;
	}
	
	public List<PossibleMeetingMember> findPossibleMeetingMemberByPossibleMeeting(Long possibleMeetingId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("possibleMeetingId", possibleMeetingId);
		return namedQuery(DbConstants.POSSIBLE_MEETING_MEMBER_FIND_BY_POSSIBLE_MEETING, parameters);
	}
	
	public List<PossibleMeetingMember> findPossibleMeetingMemberByMember(Long memberId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("memberId", memberId);
		return namedQuery(DbConstants.POSSIBLE_MEETING_MEMBER_FIND_BY_MEMBER, parameters);
	}
	
	public List<PossibleMeetingMember> findPossibleMeetingMemberByAttending(String attending) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("attending", attending);
		return namedQuery(DbConstants.POSSIBLE_MEETING_MEMBER_FIND_BY_ATTENDING, parameters);
	}
	
	public List<PossibleMeetingMember> findPossibleMeetingMemberByMemberAndAttending(Long memberId, String attending) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("memberId", memberId);
		parameters.put("attending", attending);
		return namedQuery(DbConstants.POSSIBLE_MEETING_MEMBER_FIND_BY_MEMBER_AND_ATTENDING, parameters);
	}
	
	public List<PossibleMeetingMember> findPossibleMeetingMemberByMemberAndPossibleMeeting(Long memberId, Long possibleMeetingId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("memberId", memberId);
		parameters.put("possibleMeetingId", possibleMeetingId);
		return namedQuery(DbConstants.POSSIBLE_MEETING_MEMBER_FIND_BY_MEMBER_AND_POSSIBLE_MEETING, parameters);
	}
	
	public List<PossibleMeetingMember> findPossibleMeetingMemberByPossibleMeetingAndAttending(Long possibleMeetingId, String attending) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("possibleMeetingId", possibleMeetingId);
		parameters.put("attending", attending);
		return namedQuery(DbConstants.POSSIBLE_MEETING_MEMBER_FIND_BY_POSSIBLE_MEETING_AND_ATTENDING, parameters);
	}
	
	public List<PossibleMeetingMember> findPossibleMeetingMemberByPossibleMeetingMemberAndAttending(Long possibleMeetingId, Long memberId, String attending) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("possibleMeetingId", possibleMeetingId);
		parameters.put("memberId", memberId);
		parameters.put("attending", attending);
		return namedQuery(DbConstants.POSSIBLE_MEETING_MEMBER_FIND_BY_POSSIBLE_MEETING_MEMBER_AND_ATTENDING, parameters);
	}

}
