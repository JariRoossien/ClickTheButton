import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class HLTVCountry {


    public void countCountries() throws IOException {
        System.setProperty("http.agent", "Chrome");
        Random r = new Random();
        List<String> countries = new ArrayList<>();
        int max = 4147;
        for (int i = 0; i < max; i++) {
            int random = r.nextInt(1200000);
            try {
                Document doc = Jsoup.connect("https://www.hltv.org/profile/" + random + "/X").get();

                Elements country = doc.select(".flag");
                for (Element headline : country) {
                    countries.add(headline.attr("title"));
                }
            } catch (HttpStatusException e) {
                continue;
            }
            if (i % 414 == 0) {
                System.out.println(i + " of the " + max + " done!");
            }
        }

        for (int i = 0; i < countries.size(); i++) {
            System.out.println(countries.get(i));
        }
    }


    public void speedTest() {
        long time1;
        long time2;
        System.out.println(time1 = System.nanoTime());

        for (int i = 0; i < 100; i++) {
            System.out.println(ThreadLocalRandom.current().nextInt(1000));
        }

        System.out.println(time2 = System.nanoTime());
        System.out.println(time2 - time1);
    }
}
