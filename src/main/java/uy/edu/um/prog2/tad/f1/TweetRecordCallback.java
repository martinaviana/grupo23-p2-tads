package uy.edu.um.prog2.tad.f1;

import org.apache.commons.csv.CSVRecord;

public interface TweetRecordCallback {

    void execute(CSVRecord record);

}
