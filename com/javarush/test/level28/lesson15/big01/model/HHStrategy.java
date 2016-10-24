package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by barbudos on 09.09.2016.
 */
public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) throws IOException {
        List<Vacancy> vacancies = new ArrayList<>();

        for (int i = 0; ; i++) {
            Document doc = getDocument(searchString, i);
            if (doc == null) break;
            Elements serps = doc.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
            if (serps.size() == 0) break;

            for (Element serp : serps) {
                Vacancy vacancy = new Vacancy();
                vacancy.setTitle(serp.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
                vacancy.setSalary(serp.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text());

                Elements city = serp.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address");
                if (city.text().contains(","))
                    vacancy.setCity(city.text().substring(0, city.text().indexOf(",")));
                else
                    vacancy.setCity(city.text());

                vacancy.setCompanyName(serp.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text().trim());
                vacancy.setSiteName(doc.title());
                vacancy.setUrl(serp.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"));
                vacancies.add(vacancy);
            }
        }

        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page)).userAgent("Chrome/52.0.2743.116").referrer("").get();
    }

}

/*
2. Реализуй следующую логику метода getVacancies в классе HHStrategy:
2.1. Приконнекться к закешированной страничке ХэдХантера используя метод getDocument, нумерация начинается с 0.
2.2. Получи список элементов с атрибутом "vacancy-serp__vacancy". Должно быть до 20 вакансий на странице.
2.3. Если данные в списке из п.2.2. есть, то для каждого элемента:
2.3.1. создать вакансию и заполнить все ее данные, получив данные из текущего элемента.
Если тег с зарплатой присутствует, то заполнить и поле salary, иначе инициализировать поле пустой строкой.
2.4. Выполнить п.2.1-2.3 для следующей страницы ХэдХантера.
2.5. Если закончились страницы с вакансиями, то выйти из цикла.
 */

/*

    public static void main(String[] args) throws IOException {
        HHStrategy strategy = new HHStrategy();
        List<Vacancy> vac = strategy.getVacancies("Одесса");
        for (Vacancy vacancy : vac) {
            System.out.println(vacancy);
        }
    }
 */