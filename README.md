# WEEIA ICS calendar

#### API generates ics file based on WEEIA calendar http://www.weeia.p.lodz.p for requested month.
  
First of all, it gets data from weeia calendar using Jsoup.  
Next step is to get information about events in current month: day of event, name and url (if exists).  
After that, API generates ical4j Calendar and adds all required properties.  
When calendar is ready, VEvents are created and added to previously generated calendar.  
Now ICS file is created based on calendar with name: "WEEIAmonth" where month is a number of requested month.  
As the last step, API returns generated ICS file containig WEEIA calendar for specific month.

#### How to run:
Run `WeeiaCalendarApplication.java`  


Type:
``` 
http://localhost:8080/getCalendar?month=MONTH&year=YEAR
```
where MONTH and YEAR are required values numbers of month and year.


### Example:
Input:
``` 
http://localhost:8080/getCalendar?month=10&year=2020
```
