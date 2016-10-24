package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replace(".", "/") + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() throws IOException {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        try {
            Document doc = getDocument();

            Elements template = doc.getElementsByAttributeValueContaining("class", "template").clone();
            template.removeAttr("style");
            template.removeClass("template");

            doc.getElementsByAttributeValue("class", "vacancy").remove();

            for (Vacancy vacancy : vacancies) {
                Elements copy = template.clone();
                copy.select("td.city").html(vacancy.getCity());
                copy.select("td.companyName").html(vacancy.getCompanyName());
                copy.select("td.salary").html(vacancy.getSalary());
                copy.select("td.title a[href]").html(vacancy.getTitle()).attr("href", vacancy.getUrl());
                doc.select("tr.vacancy").last().before(copy.outerHtml());
            }

            return doc.outerHtml();
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
    }

    private void updateFile(String body) throws IOException {
        if (body != null) {
            FileWriter writer = new FileWriter(filePath);
            writer.write(body);
            writer.close();
        }
    }

    protected Document getDocument() throws IOException {
        File input = new File(filePath);
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
}
