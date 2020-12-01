package ppkwu.weeiaCalendar;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class CalendarCreator {

    public void createCalendarICS(Document document, int month, HttpServletResponse response) throws URISyntaxException, IOException {
        Calendar calendar = new Calendar();
        calendar.getProperties().add(new ProdId("-//Kalendarz WEEIA//"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);
        calendar.getProperties().add(new Uid(   "WeeiaCalendarExample"));
        calendar = addEvents(calendar, document, month);

        File ics = generateICSFile(calendar,month);
        returnICSFile(response, ics);

        System.out.println(calendar);
    }

    private Calendar addEvents(Calendar calendar, Document document, int month) throws URISyntaxException {
        Elements event_data = document.select("a.active");
        Elements event_names = document.select("div.InnerBox");

        for(int i=0; i < event_data.size(); i++){
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(java.util.Calendar.MONTH, month);
            cal.set(java.util.Calendar.DAY_OF_MONTH, Integer.parseInt(event_data.get(i).text()));

            VEvent event = new VEvent(new Date(cal.getTime()), event_names.get(i).text());
            event.getProperties().add(new Uid(   "WeeiaCalendarExample" + i));
            URI uri = new URI(event_data.get(i).attr("href"));
            event.getProperties().add(new Url(uri));

            calendar.getComponents().add(event);
        }
        return calendar;
    }

    public File generateICSFile(Calendar calendar, int month) throws IOException {
        String name ="WEEIA";
        name += String.format("%02d", month) + ".ics";
        File ics = new File(name);
        FileWriter fileWriter = new FileWriter(name);
        fileWriter.write(String.valueOf(calendar));
        fileWriter.close();
        return ics;
    }

    private void returnICSFile(HttpServletResponse response, File ics) throws IOException {
        InputStream inputStream = new FileInputStream(ics);
        response.setContentType("text/calendar;charset=utf-8");
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }
}
