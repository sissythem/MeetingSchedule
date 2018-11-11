# Meeting scheduler

## MySQL

* Import the database inside folder databaseBackup
* Add with queries some possible timezones (include the earliest and latest times)
	- Combinations of the timezones will be inserted if user creates an Availability using a non existing timezone
	- However, these combinations will be created from the times existing in timezone table, so make sure to insert many possible timezones

## Wildfly 14.0.1-Final

* Add MySql driver in wildfly's modules
* Add jndi datasource and driver in standalone.xml
