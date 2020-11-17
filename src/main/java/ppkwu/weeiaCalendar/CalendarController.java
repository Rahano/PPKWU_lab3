package ppkwu.weeiaCalendar;

import ppkwu.weeiaCalendar.CalendarCreator;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CalendarController {

    @RequestMapping(path = "/getEvents")
    public String GetWEEIACalendar(@RequestParam(value = "year") int year,
            @RequestParam(value = "month") int month) throws IOException {

        String url = "http://www.weeia.p.lodz.pl/pliki_strony_kontroler/kalendarz.php?rok=" + year + "&miesiac=" + String.format("%02d", month);

        Document document = Jsoup.connect(url).get();

        //System.out.println(document);
        Elements event_days = document.select("a.active");
        Elements event_names = document.select("div.InnerBox");

        for(int i = 0; i < event_days.size(); i++){
            System.out.println(event_days.get(i).text());
            System.out.println(event_names.get(i).text());
        }

        CalendarCreator calendarCreator = new CalendarCreator();
        calendarCreator.createCalendarICS(event_names, event_days, month);

        return null;

    }

}
