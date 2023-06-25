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
import java.time.format.DateTimeParseException;
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


    public HashtagCounter getMaxHashtag() {
        HashtagCounter maxHashtag = null;
        long maxCount = 0;

        for (int i = 0; i < hashtagCounters.size(); i++) {
            try {
                if (hashtagCounters.get(i).counter > maxCount && !hashtagCounters.get(i).hashtag.equalsIgnoreCase("F1")) {
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
        try {
            LocalDateTime dateTime = LocalDateTime.parse(record.get("date"), formatter);// cambio formato fecha

            if (dateTime.getMonthValue() == month && dateTime.getYear() == year && dateTime.getDayOfMonth() == day) {
                String trimmedString = record.get("hashtags").replaceAll("\\[|\\]|'", "");
                String[] hashtags = trimmedString.split("\\s*,\\s*");


                for (String hashtag : hashtags) {
                    boolean found = false;
                    for (int i = 0; i < hashtagCounters.size(); i++) {

                        try {
                            if (hashtagCounters.get(i).hashtag.equals(hashtag)) {
                                hashtagCounters.get(i).counter++;
                                found = true;
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
        } catch (DateTimeParseException e) {
        }
    }
}



