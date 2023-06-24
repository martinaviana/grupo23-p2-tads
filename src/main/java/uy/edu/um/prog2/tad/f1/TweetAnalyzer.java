package uy.edu.um.prog2.tad.f1;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

public class TweetAnalyzer {
    private Reader reader;

    public TweetAnalyzer(Reader reader) {
        this.reader = reader;
    }

    public int getDistinctHashtagsCount(String date) throws IOException {
        Set<String> distinctHashtags = new HashSet<>();

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);

        for (CSVRecord record : records) {
            String tweetDate = record.get("date").split(" ")[0];
            if (tweetDate.equals(date)) {
                String hashtags = record.get("hashtags");
                if (!hashtags.isEmpty()) {
                    String[] hashtagArray = hashtags.split(",");
                    for (String hashtag : hashtagArray) {
                        distinctHashtags.add(hashtag.trim());
                    }
                }
            }
        }

        return distinctHashtags.size();
    }

    // Otros métodos y funcionalidades para el análisis de tweets...

    public static void main(String[] args) throws IOException {
        // Ejemplo de uso
        Reader reader = new FileReader("tweets.csv");
        TweetAnalyzer tweetAnalyzer = new TweetAnalyzer(reader);
        int distinctHashtagsCount = tweetAnalyzer.getDistinctHashtagsCount("2023-06-24");
        System.out.println("Cantidad de hashtags distintos para el día dado: " + distinctHashtagsCount);
        reader.close();
    }
}