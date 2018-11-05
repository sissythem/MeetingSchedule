package gr.demokritos.meetingscheduler.business.beans;

import gr.demokritos.meetingscheduler.business.dto.*;
import gr.demokritos.meetingscheduler.business.mappers.PossibleMeetingMapper;
import gr.demokritos.meetingscheduler.datalayer.persistence.entities.PossibleMeeting;
import gr.demokritos.meetingscheduler.datalayer.repositories.JpaRepo;
import gr.demokritos.meetingscheduler.datalayer.repositories.PossibleMeetingRepository;
import org.apache.commons.collections4.CollectionUtils;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Session Bean implementation class PossibleMeetingBean
 */
@Stateless
@LocalBean
public class PossibleMeetingBean {

    private PossibleMeetingMapper possibleMeetingMapper = new PossibleMeetingMapper();

    @EJB
    private AvailabilityBean availabilityBean;

    @Inject
    @JpaRepo
    private PossibleMeetingRepository possibleMeetingRepository;

    public PossibleMeetingBean() {

    }

    public List<MeetingDto> getHierarchicalMeetings(Map<DayDto, List<PossibleMeetingDto>> possibleMeetingsAndDays) {
        List<MeetingDto> roots = new ArrayList<>();
        for (DayDto day : possibleMeetingsAndDays.keySet()) {
            if (!CollectionUtils.isEmpty(possibleMeetingsAndDays.get(day))) {
                PossibleMeetingDto possibleMeetingDto = possibleMeetingsAndDays.get(day).get(0);
                MeetingDto meetingDto = generateMeetingFromPossibleMeeting(day, possibleMeetingDto);
                List<PossibleMeetingDto> lessPossibleMeetings = possibleMeetingsAndDays.get(day);
                lessPossibleMeetings.remove(0);
                List<MeetingDto> lessPossMeetings = new ArrayList<>();
                lessPossibleMeetings.forEach(possibleMeetingDto1 -> {
                    MeetingDto mdto = generateMeetingFromPossibleMeeting(day, possibleMeetingDto);
                    lessPossMeetings.add(mdto);
                    meetingDto.setLessPossibleMeetings(lessPossMeetings);
                });
                roots.add(meetingDto);
            }
        }
        roots.sort(Comparator.comparingInt(MeetingDto::getCanAttend).reversed());
        return roots;
    }

    private MeetingDto generateMeetingFromPossibleMeeting(DayDto day, PossibleMeetingDto possibleMeetingDto) {
        MeetingDto meetingDto = new MeetingDto();
        meetingDto.setId(possibleMeetingDto.getMeetingDto().getId());
        meetingDto.setName(possibleMeetingDto.getMeetingDto().getName());
        meetingDto.setDuration(possibleMeetingDto.getMeetingDto().getDuration());
        meetingDto.setCompleted(possibleMeetingDto.getMeetingDto().getCompleted());
        meetingDto.setStartTime(possibleMeetingDto.getTimezoneDto().getStartTime());
        meetingDto.setEndTime(possibleMeetingDto.getTimezoneDto().getEndTime());
        meetingDto.setDate(day.getDate());
        meetingDto.setAvailabilityDtos(possibleMeetingDto.getMeetingDto().getAvailabilityDtos());
        meetingDto.setMeetingMemberDtos(possibleMeetingDto.getMeetingDto().getMeetingMemberDtos());
        meetingDto.setCanAttend(possibleMeetingDto.getCanAttend());
        meetingDto.setCannotAttend(possibleMeetingDto.getCannotAttend());
        meetingDto.setCanAttendList(possibleMeetingDto.getCanAttendList());
        meetingDto.setCannotAttendList(possibleMeetingDto.getCannotAttendList());
        return meetingDto;
    }

    public Map<DayDto, List<PossibleMeetingDto>> getWeekPossibleMeetings(MeetingDto meetingDto, WeekDto weekDto, Integer threshold) {
        Map<DayDto, List<PossibleMeetingDto>> possibleMeetingsPerWeek = new HashMap<>();

        List<AvailabilityDto> availabilities = availabilityBean.getAvailabilitiesByMeeting(meetingDto.getId()).stream()
                .filter(availabilityDto -> isAvailabilityInsideTimePeriod(weekDto, availabilityDto)).collect(Collectors.toList());

        Set<DayDto> daysInWeek = availabilities.stream().map(AvailabilityDto::getDayDto).collect(Collectors.toSet());

        for (DayDto day : daysInWeek) {
            List<PossibleMeetingDto> possibleMeetingsPerDay = getPossibleMeetingsPerDay(meetingDto, threshold, availabilities, day);
            possibleMeetingsPerWeek.put(day, possibleMeetingsPerDay);
        }

        return possibleMeetingsPerWeek;
    }

    private List<PossibleMeetingDto> getPossibleMeetingsPerDay(MeetingDto meetingDto, Integer threshold, List<AvailabilityDto> availabilities, DayDto day) {
        List<PossibleMeetingDto> possibleMeetingsPerDay = new ArrayList<>();
        List<AvailabilityDto> availabilitiesPerDay = availabilities.stream().filter(availabilityDto ->
                availabilityDto.getDayDto().getDate().isEqual(day.getDate())).collect(Collectors.toList());
        Set<TimezoneDto> timezonesPerDay = availabilitiesPerDay.stream().map(AvailabilityDto::getTimezoneDto).collect(Collectors.toSet());
        timezonesPerDay.forEach(timezone -> {
            List<AvailabilityDto> canAttend = availabilitiesPerDay.stream().filter(availabilityDto -> availabilityDto.getTimezoneDto().equals(timezone) &&
                    availabilityDto.getIsAvailable()).collect(Collectors.toList());
            List<AvailabilityDto> cannotAttend = availabilitiesPerDay.stream().filter(availabilityDto -> availabilityDto.getTimezoneDto().equals(timezone) &&
                    !availabilityDto.getIsAvailable()).collect(Collectors.toList());
            PossibleMeetingDto possibleMeetingDto = generatePossibleMeetingPerDayAndTimezone(meetingDto, day, timezone, canAttend, cannotAttend);
            if (threshold != null) {
                if (cannotAttend.size() < threshold) {
                    possibleMeetingsPerDay.add(possibleMeetingDto);
                }
            } else {
                possibleMeetingsPerDay.add(possibleMeetingDto);
            }
        });
        possibleMeetingsPerDay.sort(Comparator.comparingInt(PossibleMeetingDto::getCanAttend).reversed());
        return possibleMeetingsPerDay;
    }

    private PossibleMeetingDto generatePossibleMeetingPerDayAndTimezone(MeetingDto meetingDto, DayDto day, TimezoneDto timezone, List<AvailabilityDto> canAttend,
                                                                        List<AvailabilityDto> cannotAttend) {
        PossibleMeetingDto possibleMeeting = new PossibleMeetingDto();
        possibleMeeting.setDayDto(day);
        possibleMeeting.setTimezoneDto(timezone);
        possibleMeeting.setMeetingDto(meetingDto);
        possibleMeeting.setCanAttendList(canAttend);
        possibleMeeting.setCanAttend(canAttend.size());
        possibleMeeting.setCannotAttendList(cannotAttend);
        possibleMeeting.setCannotAttend(cannotAttend.size());
        return possibleMeeting;
    }

    private boolean isAvailabilityInsideTimePeriod(WeekDto weekDto, AvailabilityDto availabilityDto) {
        return (availabilityDto.getDayDto().getDate().isAfter(weekDto.getStartDate()) ||
                availabilityDto.getDayDto().getDate().isEqual(weekDto.getStartDate())) &&
                (availabilityDto.getDayDto().getDate().isBefore(weekDto.getEndDate())) || availabilityDto.getDayDto().getDate().isEqual(weekDto.getEndDate());
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

    public List<PossibleMeetingDto> getPossibleMeetingsByMeetingDayAndTimezone(Long meetingId, Long dayId, Long timezoneId) {
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
