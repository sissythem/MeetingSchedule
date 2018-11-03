package gr.demokritos.meetingscheduler.business.beans;

import gr.demokritos.meetingscheduler.business.dto.PossibleMeetingDto;
import gr.demokritos.meetingscheduler.business.mappers.PossibleMeetingMapper;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.PossibleMeeting;
import gr.demokritos.meetingscheduler.datalayer.repositories.JpaRepo;
import gr.demokritos.meetingscheduler.datalayer.repositories.PossibleMeetingRepository;
import org.apache.commons.collections4.CollectionUtils;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Session Bean implementation class PossibleMeetingBean
 */
@Stateless
@LocalBean
public class PossibleMeetingBean {

    private PossibleMeetingMapper possibleMeetingMapper = new PossibleMeetingMapper();

    @Inject
    @JpaRepo
    private PossibleMeetingRepository possibleMeetingRepository;

    public PossibleMeetingBean() {

    }

    public void addPossibleMeeting(PossibleMeetingDto possibleMeetingDto) {
        PossibleMeeting possibleMeeting = possibleMeetingMapper.convertPossibleMeetingDtoToPossibleMeeting(possibleMeetingDto);
        possibleMeetingRepository.add(possibleMeeting);
    }

    public void updatePossibleMeeting(PossibleMeetingDto possibleMeetingDto) {
        PossibleMeeting possibleMeeting = possibleMeetingMapper.convertPossibleMeetingDtoToPossibleMeeting(possibleMeetingDto);
        possibleMeetingRepository.update(possibleMeeting);
    }

    public void removePossibleMeeting(PossibleMeetingDto possibleMeetingDto) {
        PossibleMeeting possibleMeeting = possibleMeetingMapper.convertPossibleMeetingDtoToPossibleMeeting(possibleMeetingDto);
        possibleMeetingRepository.remove(possibleMeeting);
    }

    public List<PossibleMeetingDto> getAllPossibleMeetings() {
        return getPossibleMeetingDtos(possibleMeetingRepository.findAllPossibleMeetings());
    }

    public PossibleMeetingDto getPossibleMeetingById(Long id) {
        return possibleMeetingMapper.convertPossibleMeetingToPossibleMeetingDto(possibleMeetingRepository.findPossibleMeetingById(id));
    }

    public List<PossibleMeetingDto> getPossibleMeetingsByMeeting(Long meetingId) {
        return getPossibleMeetingDtos(possibleMeetingRepository.findPossibleMeetingByMeeting(meetingId));
    }

    public List<PossibleMeetingDto> getPossibleMeetingsByDay(Long dayId) {
        return getPossibleMeetingDtos(possibleMeetingRepository.findPossibleMeetingByDay(dayId));
    }

    public List<PossibleMeetingDto> getPossibleMeetingsByTimezone(Long timezoneId) {
        return getPossibleMeetingDtos(possibleMeetingRepository.findPossibleMeetingByTimezone(timezoneId));
    }

    public List<PossibleMeetingDto> getPossibleMeetingsByMeetingAndDay(Long meetingId, Long dayId) {
        return getPossibleMeetingDtos(possibleMeetingRepository.findPossibleMeetingByMeetingAndDay(meetingId, dayId));
    }

    public List<PossibleMeetingDto> getPossibleMeetingsByDayAndTimezone(Long dayId, Long timezoneId) {
        return getPossibleMeetingDtos(possibleMeetingRepository.findPossibleMeetingByDayAndTimezone(dayId, timezoneId));
    }

    public List<PossibleMeetingDto> getPossibleMeetingsByMeetingDayAndTimezone(Long meetingId, Long dayId, Long timezoneId){
        return getPossibleMeetingDtos(possibleMeetingRepository.findPossibleMeetingByMeetingDayAndTimezone(meetingId, dayId, timezoneId));
    }

    private List<PossibleMeetingDto> getPossibleMeetingDtos(List<PossibleMeeting> possibleMeetings) {
        List<PossibleMeetingDto> possibleMeetingDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(possibleMeetings)) {
            possibleMeetings.forEach(possibleMeeting -> {
                PossibleMeetingDto possibleMeetingDto = possibleMeetingMapper.convertPossibleMeetingToPossibleMeetingDto(possibleMeeting);
                possibleMeetingDtos.add(possibleMeetingDto);
            });
        }
        return possibleMeetingDtos;
    }

}
