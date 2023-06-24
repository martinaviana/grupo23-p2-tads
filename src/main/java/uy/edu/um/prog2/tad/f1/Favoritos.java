package uy.edu.um.prog2.tad.f1;

import  org.apache.commons.csv.CSVRecord;
import uy.edu.um.prog2.tad.binaryTree.MyBinarySearchTreeImpl;
import uy.edu.um.prog2.tad.binaryTree.MySearchBinaryTreeVisitor;
import uy.edu.um.prog2.tad.binaryTree.Node;
import uy.edu.um.prog2.tad.exceptions.EmptyListException;
import uy.edu.um.prog2.tad.exceptions.KeyNotInTree;
import uy.edu.um.prog2.tad.exceptions.OutOfBoundsException;
import uy.edu.um.prog2.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.tad.linkedlist.MyList;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Favoritos implements TweetRecordCallback {

    private MyBinarySearchTreeImpl<String, UserFavourites> tree = new MyBinarySearchTreeImpl<>();
    private MyLinkedListImpl<UserFavourites> result = new MyLinkedListImpl<>();


    @Override
    public void execute(CSVRecord record) {
        try {
            String favourites = record.get("user_favourites");
            double parseFavourites = Double.parseDouble(favourites);
            int parseFavouritesInt = (int) Math.round(parseFavourites);
            try {
                UserFavourites userFavourites = tree.find(record.get("user_name"));
                userFavourites.add(parseFavouritesInt);
            }catch (KeyNotInTree e) {
                tree.add(record.get("user_name"), new UserFavourites(record.get("user_name"), parseFavouritesInt));
            }
        } catch (NumberFormatException e) {

        }
    }

    public MyList<UserFavourites> sortFavourites() throws OutOfBoundsException, EmptyListException {

        MyBinarySearchTreeImpl<Integer, MyList<UserFavourites>> favouriteUsersTree = new MyBinarySearchTreeImpl<>();

        tree.visit(new MySearchBinaryTreeVisitor<UserFavourites>() {
            @Override
            public void visit(UserFavourites userFavourites) {
                try {
                    MyList<UserFavourites> userFavouritesMyList = favouriteUsersTree.find(userFavourites.getFavouritesNum());
                    userFavouritesMyList.add(userFavourites);
                } catch (KeyNotInTree e) {
                    MyList<UserFavourites> userCounters = new MyLinkedListImpl<>();
                    userCounters.add(userFavourites);
                    favouriteUsersTree.add(userFavourites.getFavouritesNum(), userCounters);
                }
            }
        });

        calculate7Most(favouriteUsersTree.getRoot());

        return result;
    }

    private void calculate7Most(Node<Integer, MyList<UserFavourites>> node) {
        if (node.getRight() != null) {
            calculate7Most(node.getRight());
        }
        if (result.size() == 7)
            return;

        for (int i = 0; i < node.getValue().size(); i++) {
            if (result.size() < 7) {
                try {
                    result.add(node.getValue().get(i));
                } catch (EmptyListException e) {
                    e.printStackTrace();
                } catch (OutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        }

        if (node.getLeft() != null) {
            calculate7Most(node.getLeft());
        } else {
            return;
        }
    }
}





