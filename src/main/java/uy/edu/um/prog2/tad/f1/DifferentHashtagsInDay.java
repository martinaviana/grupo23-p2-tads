package uy.edu.um.prog2.tad.f1;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import uy.edu.um.prog2.tad.linkedlist.MyList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RequiredArgsConstructor
public class DifferentHashtagsInDay implements TweetRecordCallback{
    @NonNull
    private MyList<String> hashTagCounter;
    @NonNull private int year;
    @NonNull private int month;
    @NonNull private int day;
    private long hashTags = 0;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void execute(CSVRecord record) {
        String trimmedString = record.get("hashtags").replaceAll("\\[|\\]|'", "");
        String[] hashtags = trimmedString.split("\\s*,\\s*");

        try {
            LocalDateTime dateTime = LocalDateTime.parse(record.get("date"), formatter);
            for (String hashtag : hashtags)
                if (!hashTagCounter.contains(hashtag) && dateTime.getMonthValue() == month && dateTime.getYear() == year && dateTime.getDayOfMonth() == day)
                    addHashtag(hashtag);
        }catch (DateTimeParseException e) {
        }
    }

    private void addHashtag(String hashTag) {
        hashTagCounter.add(hashTag);
        hashTags++;
    }

    public MyList<String> getHashTagCounter() {
        return this.hashTagCounter;
    }

    public long getHashTags() {
        return this.hashTags;
    }
}
