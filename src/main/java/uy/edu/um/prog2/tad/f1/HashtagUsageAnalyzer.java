package uy.edu.um.prog2.tad.f1;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import uy.edu.um.prog2.tad.exceptions.EmptyListException;
import uy.edu.um.prog2.tad.exceptions.OutOfBoundsException;
import uy.edu.um.prog2.tad.linkedlist.MyLinkedListImpl;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
@RequiredArgsConstructor

public class HashtagUsageAnalyzer implements TweetRecordCallback {


    @NonNull
    private int year;
    @NonNull
    private int month;
    @NonNull
    private int day;
    @NonNull
    private MyLinkedListImpl<HashtagCounter> hashtagCounters = new MyLinkedListImpl<>();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    /* public String getMostUsedHashtag(String date) throws IOException {
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
     }*/
    public HashtagCounter getMaxHashtag() {
        HashtagCounter maxHashtag = null;
        long maxCount = 0;

        for (int i = 0; i < hashtagCounters.size(); i++) {
            try {
                if (hashtagCounters.get(i).counter > maxCount) {
                    maxHashtag = hashtagCounters.get(i);
                    maxCount = hashtagCounters.get(i).counter;
                }
            } catch (EmptyListException e) {
                throw new RuntimeException(e);
            } catch (OutOfBoundsException e) {
                throw new RuntimeException(e);
            }
        }

        return maxHashtag;
    }


    public MyLinkedListImpl<HashtagCounter> getHashtagCounters() {
        return hashtagCounters;
    }

    @Override
    public void execute(CSVRecord record) {
        System.out.println("entro al execute");

        LocalDateTime dateTime = LocalDateTime.parse(record.get("date"), formatter);// cambio formato fecha

        if (dateTime.getMonthValue() == month && dateTime.getYear() == year && dateTime.getDayOfMonth() == day) {
            System.out.println("entro al if");
            String trimmedString = record.get("hashtags").replaceAll("\\[|\\]|'", "");
            String[] hashtags = trimmedString.split("\\s*,\\s*");


            for (String hashtag : hashtags) {
                boolean found = false;
                System.out.println("imprimo hashtag" + hashtag);
                for (int i = 0; i < hashtagCounters.size(); i++) {
                    System.out.println("entro al for");

                    try {
                        if (hashtagCounters.get(i).hashtag.equals(hashtag)) {
                            hashtagCounters.get(i).counter++;
                            found = true;
                            System.out.println("entro al if");
                            break;

                        }
                    } catch (EmptyListException e) {
                        throw new RuntimeException(e);
                    } catch (OutOfBoundsException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (!found) {
                    HashtagCounter hashtagCounter = new HashtagCounter(hashtag);
                    hashtagCounter.counter++;
                    hashtagCounters.add(hashtagCounter);
                }


            }
        }
    }
}



