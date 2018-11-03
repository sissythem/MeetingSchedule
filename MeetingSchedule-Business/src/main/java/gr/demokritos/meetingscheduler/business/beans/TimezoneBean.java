package gr.demokritos.meetingscheduler.business.beans;

import gr.demokritos.meetingscheduler.business.mappers.TimezoneMapper;
import gr.demokritos.meetingscheduler.datalayer.repositories.JpaRepo;
import gr.demokritos.meetingscheduler.datalayer.repositories.TimezoneRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Session Bean implementation class TimezoneBean
 */
@Stateless
@LocalBean
public class TimezoneBean {

    private TimezoneMapper timezoneMapper = new TimezoneMapper();

    @Inject
    @JpaRepo
    private TimezoneRepository timezoneRepository;

    public TimezoneBean() {
    
    }

}
