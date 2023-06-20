import uy.edu.um.prog2.tad.binaryTree.MyBinarySearchTreeImpl;
import uy.edu.um.prog2.tad.f1.*;
import uy.edu.um.prog2.tad.hash.ClosedHashImpl;

import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {

        Reader reader = new FileReader("f1_dataset.csv");

        UsersWithMoreTweets fifteenUsersWithMoreTweets = new UsersWithMoreTweets(new MyBinarySearchTreeImpl<>(), 15);

        TweetsReader tweetReader = new TweetsReader(reader, fifteenUsersWithMoreTweets);

        tweetReader.read();

        fifteenUsersWithMoreTweets.sortUserMoreTweets().forEach(System.out::println);



        /*long time = System.currentTimeMillis();

        List<Driver> drivers = new DriversReader().read(new FileReader("drivers.txt"));

        Reader reader = new FileReader("f1_dataset.csv");

        MoreActiveDrivers tenMoreActiveDrivers = new MoreActiveDrivers(
                drivers.stream()
                        .map(driver -> new DriverCounter(driver))
                        .collect(Collectors.toList()), 10);

        TweetsReader tweetsReader = new TweetsReader(reader, tenMoreActiveDrivers);

        tweetsReader.read();

        tenMoreActiveDrivers.sortDriverCounters().forEach(System.out::println);

        time = System.currentTimeMillis() - time;
        System.out.println(time);*/
    }

}
