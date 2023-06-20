package uy.edu.um.prog2.tad.f1;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import uy.edu.um.prog2.tad.binaryTree.MyBinarySearchTreeImpl;
import uy.edu.um.prog2.tad.binaryTree.MySearchBinaryTree;
import uy.edu.um.prog2.tad.binaryTree.MySearchBinaryTreeVisitor;
import uy.edu.um.prog2.tad.binaryTree.Node;
import uy.edu.um.prog2.tad.exceptions.KeyNotInTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
public class UsersWithMoreTweets implements TweetRecordCallback{
    @NonNull private MySearchBinaryTree<String, UserCounter> usersTweets;
    @NonNull private int limit;
    List<UserCounter> result;


    @Override
    public void execute(CSVRecord record) {
        UserCounter userCounter = null;
        try {
            userCounter = usersTweets.find(record.get("user_name"));
            userCounter.increment();
        } catch (KeyNotInTree e) {
            usersTweets.add(record.get("user_name"), new UserCounter(record.get("user_name"), 1, Boolean.parseBoolean(record.get("user_verified").toLowerCase())));
        }
    }

    public List<UserCounter> sortUserMoreTweets() {
        result = new ArrayList<>();
        final MyBinarySearchTreeImpl<Long, List<UserCounter>> countTree = new MyBinarySearchTreeImpl<>();

        usersTweets.visit(new MySearchBinaryTreeVisitor<UserCounter>() {
            @Override
            public void visit(UserCounter userCounter) {
                try {
                    List<UserCounter> userCounters = countTree.find(userCounter.getCounter());
                    userCounters.add(userCounter);
                } catch (KeyNotInTree e) {
                    ArrayList<UserCounter> userCounters = new ArrayList<>();
                    userCounters.add(userCounter);
                    countTree.add(userCounter.getCounter(), userCounters);
                }
            }
        });

        calculateFifteenMost(countTree.getRoot());
        return result;
    }

    private void calculateFifteenMost(Node<Long, List<UserCounter>> node) {
        if (node.getRight() != null) {
            calculateFifteenMost(node.getRight());
        }
        if (result.size() == limit)
            return;
        for (UserCounter userCounter : node.getValue()) {
            if (result.size() < limit)
                result.add(userCounter);
        }

        if (node.getLeft() != null) {
            calculateFifteenMost(node.getLeft());
        } else {
            return;
        }
        return;
    }
}
