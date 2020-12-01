package ppkwu.weeiaCalendar;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;
import org.jsoup.select.Elements;

public class CalendarCreator {

    public void createCalendarICS(Elements event_names, Elements event_days, int month){
        Calendar calendar = new Calendar();
        calendar.getProperties().add(new ProdId("-//Kalendarz WEEIA//"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);
        calendar.getProperties().add(new Uid(   "WeeiaCalendarExample"));
        calendar = addEvents(calendar, event_names, event_days, month);

        System.out.println(calendar);
    }

    private Calendar addEvents(Calendar calendar, Elements event_names, Elements event_days, int month){
        for(int i=0; i < event_days.size(); i++){
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(java.util.Calendar.MONTH, month);
            cal.set(java.util.Calendar.DAY_OF_MONTH, Integer.parseInt(event_days.get(i).text()));

            VEvent event = new VEvent(new Date(cal.getTime()), event_names.get(i).text());
            event.getProperties().add(new Uid(   "WeeiaCalendarExample" + i));

            calendar.getComponents().add(event);
        }
        return calendar;
    }
}
