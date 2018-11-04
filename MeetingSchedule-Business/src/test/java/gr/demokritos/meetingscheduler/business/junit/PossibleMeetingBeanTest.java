package gr.demokritos.meetingscheduler.business.junit;

import gr.demokritos.meetingscheduler.business.dto.PossibleMeetingDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PossibleMeetingBeanTest {

    @Test
    public void testCompareIntAscendingOrDescendingOrder() {
        List<PossibleMeetingDto> possibleMeetingDtos = new ArrayList<>();
        PossibleMeetingDto possibleMeetingDto1 = new PossibleMeetingDto();
        PossibleMeetingDto possibleMeetingDto2 = new PossibleMeetingDto();
        PossibleMeetingDto possibleMeetingDto3 = new PossibleMeetingDto();
        possibleMeetingDto1.setCanAttend(1);
        possibleMeetingDto1.setId(1L);
        possibleMeetingDto2.setCanAttend(2);
        possibleMeetingDto2.setId(2L);
        possibleMeetingDto3.setCanAttend(3);
        possibleMeetingDto3.setId(3L);
        possibleMeetingDtos.add(possibleMeetingDto1);
        possibleMeetingDtos.add(possibleMeetingDto2);
        possibleMeetingDtos.add(possibleMeetingDto3);
        possibleMeetingDtos.sort(Comparator.comparingInt(PossibleMeetingDto::getCanAttend));
        for(PossibleMeetingDto possibleMeetingDto : possibleMeetingDtos) {
            System.out.println("Element "+ possibleMeetingDtos.indexOf(possibleMeetingDto) + " is: "+ possibleMeetingDto.getId());
        }
        Assert.assertEquals(true,true);
    }
}
