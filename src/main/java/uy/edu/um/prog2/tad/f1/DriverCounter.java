package uy.edu.um.prog2.tad.f1;

import lombok.*;

@RequiredArgsConstructor
@ToString
@Getter
public class DriverCounter {
    @NonNull private Driver driver;
    long counter;

    public void readTweet(String tweet) {
        for (String name : driver.getNames())
            if (tweet.contains(name)) {
                counter++;
                break;
            }
    }
}
