package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?page=%d&q=java+%s";

    @Override
    public List<Vacancy> getVacancies(String searchString) throws IOException {
        List<Vacancy> vacancies = new ArrayList<>();

        for (int i = 1; ; i++) {
            Document doc = getDocument(searchString, i);
            if (doc == null) break;
            Elements serps = doc.getElementsByClass("job");
            if (serps.size() == 0) break;

            for (Element serp : serps) {
                Vacancy vacancy = new Vacancy();
                vacancy.setTitle(serp.getElementsByClass("title").text());
                vacancy.setSalary(serp.getElementsByClass("salary").text());
                vacancy.setCity(serp.getElementsByClass("location").text());
                vacancy.setCompanyName(serp.getElementsByClass("company_name").text());
                vacancy.setSiteName(doc.title());
                vacancy.setUrl(serp.getElementsByClass("title").select("a").attr("abs:href"));
                vacancies.add(vacancy);
            }
        }

        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, page, searchString)).userAgent("Chrome/52.0.2743.116").referrer("").get();
    }
}
