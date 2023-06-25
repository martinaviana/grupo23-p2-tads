package uy.edu.um.prog2.tad.f1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;

@RequiredArgsConstructor
@Getter
public class TweetsWithSpecificString implements TweetRecordCallback {
    private long stringCounter = 0;
    @NonNull private String stringToCount;

    @Override
    public void execute(CSVRecord record) {
        String loweredString = stringToCount.toLowerCase();
        String loweredRecordString = record.get("text").toLowerCase();
        if (loweredRecordString.contains(loweredString))
            stringCounter++;
    }
}
