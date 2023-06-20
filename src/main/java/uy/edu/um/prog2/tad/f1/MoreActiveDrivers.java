package uy.edu.um.prog2.tad.f1;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import uy.edu.um.prog2.tad.binaryTree.MyBinarySearchTreeImpl;
import uy.edu.um.prog2.tad.binaryTree.Node;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MoreActiveDrivers implements TweetRecordCallback {
    @NonNull List<DriverCounter> driverCounters;
    @NonNull int limit;
    List<DriverCounter> result;

    @Override
    public void execute(CSVRecord record) {
        for (DriverCounter driverCounter : driverCounters)
            driverCounter.readTweet(record.get("text"));
    }

    public List<DriverCounter> sortDriverCounters() {
        result = new ArrayList<>();
        MyBinarySearchTreeImpl<Long, DriverCounter> drivers = new MyBinarySearchTreeImpl<>();
        for (DriverCounter driverCounter : this.driverCounters)
            drivers.add(driverCounter.counter, driverCounter);

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
