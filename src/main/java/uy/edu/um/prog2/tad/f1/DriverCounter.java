package uy.edu.um.prog2.tad.f1;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class DriverCounter {
    @NonNull Driver driver;
    long counter;

    public void readTweet(String tweet) {
        for (String name : driver.getNames())
            if (tweet.contains(name)) {
                counter++;
                break;
            }
    }
}
