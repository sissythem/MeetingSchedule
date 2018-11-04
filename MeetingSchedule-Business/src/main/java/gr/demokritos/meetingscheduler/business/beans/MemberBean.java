package gr.demokritos.meetingscheduler.business.beans;

import gr.demokritos.meetingscheduler.business.dto.MeetingDto;
import gr.demokritos.meetingscheduler.business.dto.MemberDto;
import gr.demokritos.meetingscheduler.business.mappers.MemberMapper;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Meeting;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Member;
import gr.demokritos.meetingscheduler.datalayer.repositories.JpaRepo;
import gr.demokritos.meetingscheduler.datalayer.repositories.MemberRepository;
import org.apache.commons.collections4.CollectionUtils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Session Bean implementation class MemberBean
 */
@Stateless
@LocalBean
public class MemberBean {

    private MemberMapper memberMapper = new MemberMapper();

    @Inject
    @JpaRepo
    private MemberRepository memberRepository;

    public MemberBean() {
    
    }

    public void addMember(MemberDto memberDto) {
        Member member = memberMapper.convertMemberDtoToMember(memberDto);
        memberRepository.add(member);
    }

    public void updateMember(MemberDto memberDto) {
        Member member = memberMapper.convertMemberDtoToMember(memberDto);
        memberRepository.update(member);
    }

    public void removeMember(MemberDto memberDto) {
        Member member = memberMapper.convertMemberDtoToMember(memberDto);
        memberRepository.remove(member);
    }

    public List<MemberDto> getAllMembers(String sortString) {
        return getMemberDtos(memberRepository.findAllMembers(sortString));
    }

    public List<MemberDto> getAllMembers() {
        return getMemberDtos(memberRepository.findAllMembers());
    }

    public MemberDto getMemberById(Long id) {
        return memberMapper.convertMemberToMemberDto(memberRepository.findMemberById(id));
    }

    public List<MemberDto> getMembersByName(String name) {
        return getMemberDtos(memberRepository.findMemberByName(name));
    }

    public List<MemberDto> getMembersByLastName(String lastName) {
        return getMemberDtos(memberRepository.findMemberByLastName(lastName));
    }

    public MemberDto getMemberByNameAndLastName(String name, String lastName) {
        return memberMapper.convertMemberToMemberDto(memberRepository.findMemberByNameAndLastName(name, lastName));
    }

    private List<MemberDto> getMemberDtos(List<Member> members) {
        List<MemberDto> memberDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(members)) {
            members.forEach(member -> {
                MemberDto memberDto = memberMapper.convertMemberToMemberDto(member);
                memberDtos.add(memberDto);
            });
        }
        return memberDtos;
    }

}
