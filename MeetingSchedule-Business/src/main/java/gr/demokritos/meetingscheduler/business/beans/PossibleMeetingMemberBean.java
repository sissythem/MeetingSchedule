package gr.demokritos.meetingscheduler.business.beans;

import gr.demokritos.meetingscheduler.business.dto.PossibleMeetingDto;
import gr.demokritos.meetingscheduler.business.dto.PossibleMeetingMemberDto;
import gr.demokritos.meetingscheduler.business.mappers.PossibleMeetingMemberMapper;
import gr.demokritos.meetingscheduler.business.utils.Utilities;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.PossibleMeeting;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.PossibleMeetingMember;
import gr.demokritos.meetingscheduler.datalayer.repositories.JpaRepo;
import gr.demokritos.meetingscheduler.datalayer.repositories.PossibleMeetingMemberRepository;
import org.apache.commons.collections4.CollectionUtils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Session Bean implementation class PossibleMeetingMemberBean
 */
@Stateless
@LocalBean
public class PossibleMeetingMemberBean {

    private PossibleMeetingMemberMapper possibleMeetingMemberMapper = new PossibleMeetingMemberMapper();

    @Inject
    @JpaRepo
    private PossibleMeetingMemberRepository possibleMeetingMemberRepository;

    public PossibleMeetingMemberBean() {
    
    }

    public void addPossibleMeetingMember(PossibleMeetingMemberDto possibleMeetingMemberDto) {
        PossibleMeetingMember possibleMeetingMember = possibleMeetingMemberMapper.convertPossibleMeetingMemberDtoToPossibleMeetingMember(possibleMeetingMemberDto);
        possibleMeetingMemberRepository.add(possibleMeetingMember);
    }

    public void updatePossibleMeetingMember(PossibleMeetingMemberDto possibleMeetingMemberDto) {
        PossibleMeetingMember possibleMeetingMember = possibleMeetingMemberMapper.convertPossibleMeetingMemberDtoToPossibleMeetingMember(possibleMeetingMemberDto);
        possibleMeetingMemberRepository.update(possibleMeetingMember);
    }

    public void removePossibleMeetingMember(PossibleMeetingMemberDto possibleMeetingMemberDto) {
        PossibleMeetingMember possibleMeetingMember = possibleMeetingMemberMapper.convertPossibleMeetingMemberDtoToPossibleMeetingMember(possibleMeetingMemberDto);
        possibleMeetingMemberRepository.remove(possibleMeetingMember);
    }

    public List<PossibleMeetingMemberDto> getAllPossibleMeetingMembers() {
        return getPossibleMeetingMemberDtos(possibleMeetingMemberRepository.findAllPossibleMeetingMembers());
    }

    public PossibleMeetingMemberDto getPossibleMeetingMemberById(Long id) {
        return  possibleMeetingMemberMapper.convertPossibleMeetingMemberToPossibleMeetingMemberDto(possibleMeetingMemberRepository.findPossibleMeetingMemberById(id));
    }

    public List<PossibleMeetingMemberDto> getPossibleMeetingMembersByPossibleMeeting(Long possibleMeetingId) {
        return getPossibleMeetingMemberDtos(possibleMeetingMemberRepository.findPossibleMeetingMemberByPossibleMeeting(possibleMeetingId));
    }

    public List<PossibleMeetingMemberDto> getPossibleMeetingMembersByMember(Long memberId) {
        return getPossibleMeetingMemberDtos(possibleMeetingMemberRepository.findPossibleMeetingMemberByMember(memberId));
    }

    public List<PossibleMeetingMemberDto> getPossibleMeetingMembersByAttending(Boolean attending) {
        return getPossibleMeetingMemberDtos(possibleMeetingMemberRepository.findPossibleMeetingMemberByAttending(Utilities.booleanToString(attending)));
    }

    public List<PossibleMeetingMemberDto> getPossibleMeetingMembersByMemberAndAttending(Long memberId, Boolean attending) {
        return getPossibleMeetingMemberDtos(possibleMeetingMemberRepository.findPossibleMeetingMemberByMemberAndAttending(memberId, Utilities.booleanToString(attending)));
    }

    public List<PossibleMeetingMemberDto> getPossibleMeetingMembersByMemberAndPossibleMeeting(Long memberId, Long possibleMeetingId) {
        return getPossibleMeetingMemberDtos(possibleMeetingMemberRepository.findPossibleMeetingMemberByMemberAndPossibleMeeting(memberId, possibleMeetingId));
    }

    public List<PossibleMeetingMemberDto> getPossibleMeetingMembersByPossibleMeetingAndAttending(Long possibleMeetingId, Boolean attending) {
        return getPossibleMeetingMemberDtos(possibleMeetingMemberRepository.findPossibleMeetingMemberByPossibleMeetingAndAttending(possibleMeetingId, Utilities.booleanToString(attending)));
    }

    public List<PossibleMeetingMemberDto> getPossibleMeetingMembersByPossibleMeetingMemberAndAttending(Long possibleMeetingId, Long memberId, Boolean attending) {
        return getPossibleMeetingMemberDtos(possibleMeetingMemberRepository.findPossibleMeetingMemberByPossibleMeetingMemberAndAttending(possibleMeetingId, memberId, Utilities.booleanToString(attending)));
    }

    private List<PossibleMeetingMemberDto> getPossibleMeetingMemberDtos(List<PossibleMeetingMember> possibleMeetingMembers) {
        List<PossibleMeetingMemberDto> possibleMeetingMemberDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(possibleMeetingMembers)) {
            possibleMeetingMembers.forEach(possibleMeetingMember -> {
                PossibleMeetingMemberDto possibleMeetingMemberDto = possibleMeetingMemberMapper.convertPossibleMeetingMemberToPossibleMeetingMemberDto(possibleMeetingMember);
                possibleMeetingMemberDtos.add(possibleMeetingMemberDto);
            });
        }
        return possibleMeetingMemberDtos;
    }

}
