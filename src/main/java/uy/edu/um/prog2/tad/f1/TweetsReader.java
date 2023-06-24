package uy.edu.um.prog2.tad.f1;

import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@AllArgsConstructor
public class TweetsReader {
    private Reader reader;
    private TweetRecordCallback callback;

    public void read() throws IOException {
        String[] HEADERS = {"line_number", "user_name", "user_location", "user_description", "user_created", "user_followers", "user_friends", "user_favourites", "user_verified", "date", "text", "hashtags", "source", "is_retweet"};

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        Iterable<CSVRecord> records = csvFormat.parse(reader);

        for (CSVRecord record : records)
            callback.execute(record);
    }
}
