package uy.edu.um.prog2.tad.f1;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import uy.edu.um.prog2.tad.binaryTree.MyBinarySearchTreeImpl;
import uy.edu.um.prog2.tad.binaryTree.MySearchBinaryTree;
import uy.edu.um.prog2.tad.binaryTree.MySearchBinaryTreeVisitor;
import uy.edu.um.prog2.tad.binaryTree.Node;
import uy.edu.um.prog2.tad.exceptions.EmptyListException;
import uy.edu.um.prog2.tad.exceptions.KeyNotInTree;
import uy.edu.um.prog2.tad.exceptions.OutOfBoundsException;
import uy.edu.um.prog2.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.tad.linkedlist.MyList;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UsersWithMoreTweets implements TweetRecordCallback{
    @NonNull private MySearchBinaryTree<String, UserCounter> usersTweets;
    @NonNull private int limit;
    MyList<UserCounter> result;


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

    public MyList<UserCounter> sortUserMoreTweets() {
        result = new MyLinkedListImpl<>();
        final MyBinarySearchTreeImpl<Long, MyList<UserCounter>> countTree = new MyBinarySearchTreeImpl<>();

        usersTweets.visit(new MySearchBinaryTreeVisitor<UserCounter>() {
            @Override
            public void visit(UserCounter userCounter) {
                try {
                    MyList<UserCounter> userCounters = countTree.find(userCounter.getCounter());
                    userCounters.add(userCounter);
                } catch (KeyNotInTree e) {
                    MyList<UserCounter> userCounters = new MyLinkedListImpl<>();
                    userCounters.add(userCounter);
                    countTree.add(userCounter.getCounter(), userCounters);
                }
            }
        });

        calculateFifteenMost(countTree.getRoot());
        return result;
    }

    private void calculateFifteenMost(Node<Long, MyList<UserCounter>> node) {
        if (node.getRight() != null) {
            calculateFifteenMost(node.getRight());
        }
        if (result.size() == limit)
            return;

        for (int i = 0; i < node.getValue().size(); i++) {
            if (result.size() < limit) {
                try {
                    result.add(node.getValue().get(i));
                } catch (EmptyListException e) {
                    e.printStackTrace();
                } catch (OutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        }
        /*for (UserCounter userCounter : node.getValue()) {
            if (result.size() < limit)
                result.add(userCounter);
        }*/

        if (node.getLeft() != null) {
            calculateFifteenMost(node.getLeft());
        } else {
            return;
        }
        return;
    }
}
