package ppkwu.weeiaCalendar;

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
    public String reverseString(@RequestParam(value = "year") int year,
            @RequestParam(value = "month") int month) throws IOException {

        String url = "http://www.weeia.p.lodz.pl/pliki_strony_kontroler/kalendarz.php?rok=" + year + "&miesiac=" + String.format("%02d", month);

        Document document = Jsoup.connect(url).get();

        System.out.println(document);

        return null;

    }

}
