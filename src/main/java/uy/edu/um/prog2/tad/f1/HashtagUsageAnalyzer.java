package uy.edu.um.prog2.tad.f1;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class HashtagUsageAnalyzer {
    private Reader reader;

    public HashtagUsageAnalyzer(Reader reader) {
        this.reader = reader;
    }

    public int getDistinctHashtagCount(String date) throws IOException {
        Set<String> hashtagSet = new HashSet<>();

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);

        for (CSVRecord record : records) {
            String tweetDate = record.get("date").split(" ")[0];
            if (tweetDate.equals(date)) {
                String hashtags = record.get("hashtags");
                if (!hashtags.isEmpty()) {
                    String[] hashtagArray = hashtags.split(",");
                    for (String hashtag : hashtagArray) {
                        String trimmedHashtag = hashtag.trim();
                        if (!trimmedHashtag.equalsIgnoreCase("#f1")) {
                            hashtagSet.add(trimmedHashtag);
                        }
                    }
                }
            }
        }

        return hashtagSet.size();
    }

    public String getMostUsedHashtag(String date) throws IOException {
        Map<String, Integer> hashtagCounts;
        hashtagCounts = new HashMap<>();

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);

        for (CSVRecord record : records) {
            String tweetDate = record.get("date").split(" ")[0];
            if (tweetDate.equals(date)) {
                String hashtags = record.get("hashtags");
                if (!hashtags.isEmpty()) {
                    String[] hashtagArray = hashtags.split(",");
                    for (String hashtag : hashtagArray) {
                        String trimmedHashtag = hashtag.trim();
                        if (!trimmedHashtag.equalsIgnoreCase("#f1")) {
                            hashtagCounts.put(trimmedHashtag, hashtagCounts.getOrDefault(trimmedHashtag, 0) + 1);
                        }
                    }
                }
            }
        }

        String mostUsedHashtag = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : hashtagCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostUsedHashtag = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return mostUsedHashtag;
    }
}