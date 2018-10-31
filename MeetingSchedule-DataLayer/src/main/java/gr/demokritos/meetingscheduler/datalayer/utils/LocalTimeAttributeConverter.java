package gr.demokritos.meetingscheduler.datalayer.utils;

import java.sql.Time;
import java.time.LocalTime;

import javax.persistence.AttributeConverter;

public class LocalTimeAttributeConverter implements AttributeConverter<LocalTime, Time> {
	@Override
    public Time convertToDatabaseColumn(LocalTime localTime) {
    	return (localTime == null ? null : Time.valueOf(localTime));
    }

    @Override
    public LocalTime convertToEntityAttribute(Time sqlTime) {
    	return (sqlTime == null ? null : sqlTime.toLocalTime());
    }

}
