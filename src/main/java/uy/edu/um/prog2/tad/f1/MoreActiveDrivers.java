package uy.edu.um.prog2.tad.f1;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import uy.edu.um.prog2.tad.binaryTree.MyBinarySearchTreeImpl;
import uy.edu.um.prog2.tad.binaryTree.Node;
import uy.edu.um.prog2.tad.exceptions.EmptyListException;
import uy.edu.um.prog2.tad.exceptions.OutOfBoundsException;
import uy.edu.um.prog2.tad.linkedlist.*;
import uy.edu.um.prog2.tad.linkedlist.MyList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

@RequiredArgsConstructor
public class MoreActiveDrivers implements TweetRecordCallback {
    @NonNull private MyList<DriverCounter> driverCounters;
    @NonNull private int limit;
    @NonNull private int year;
    @NonNull private int month;
    private MyList<DriverCounter> result;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void execute(CSVRecord record) {
        for (int i = 0; i < driverCounters.size(); i++) {
            try {
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(record.get("date"), formatter);
                    if (dateTime.getMonthValue() == month && dateTime.getYear() == year) {
                        driverCounters.get(i).readTweet(record.get("text"));
                    }
                }catch (DateTimeParseException e) {
                    System.out.println("Exception");
                    break;
                }
            }catch (OutOfBoundsException | EmptyListException e) {
                System.out.println("Morimos en " + record.get("line_number"));
                e.printStackTrace();
            }
        }
    }

    public MyList<DriverCounter> sortDriverCounters() {
        result = new MyLinkedListImpl<DriverCounter>();
        MyBinarySearchTreeImpl<Long, DriverCounter> drivers = new MyBinarySearchTreeImpl<>();

        for (int i = 0; i < driverCounters.size(); i++) {
            try {
                drivers.add(driverCounters.get(i).counter, driverCounters.get(i));
            }catch (OutOfBoundsException | EmptyListException e) {
                e.printStackTrace();
            }
        }

        calculateTenMost(drivers.getRoot());

        return result;
    }

    private void calculateTenMost(Node<Long, DriverCounter> node) {
        if (node.getRight() != null) {
            calculateTenMost(node.getRight());
        }
        if (result.size() == limit)
            return;
        result.add(node.getValue());

        if (node.getLeft() != null) {
            calculateTenMost(node.getLeft());
        } else {
            return;
        }
    }
}
