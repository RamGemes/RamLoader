package net.ramgames.modloader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ModrinthManager {
    public List<DisplayMod> getSearchResults(String searchTitle, String version) {
        try {
            URL url = new URL(version != null ? "https://modrinth.com/mods?q=" + searchTitle + "&v=" + version : "https://modrinth.com/mods?q=" + searchTitle);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String html = response.toString();
            Document doc = Jsoup.parse(html);
            Element modList = doc.getElementById("search-results");

            List<DisplayMod> displayMods = new ArrayList<>();
            if (modList == null) {
                return new ArrayList<>();
            } else {
                for (Element element : modList.children()) {
                    Elements environment = element.getElementsByClass("environment");
                    List<String> tags = new ArrayList<>();
                    for(Element attr : environment) {
                        if(attr.hasText()) {
                            if(attr.text().equals("Fabric"))
                            tags.add(attr.text());
                        }
                    }
                    DisplayMod displayMod = new DisplayMod(
                            element.getElementsByClass("name").text(),
                            element.getElementsByClass("description").text(),
                            element.getElementsByClass("author").text(),
                            environment.text().equals(" Client "),
                            tags.contains()
                    );
                    System.out.println("----");
                    System.out.println("----");
                }
            }
            return displayMods;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

}
