package uy.edu.um.prog2.tad.f1;

import org.apache.commons.csv.CSVRecord;
import uy.edu.um.prog2.tad.binaryTree.MyBinarySearchTreeImpl;
import uy.edu.um.prog2.tad.binaryTree.Node;
import uy.edu.um.prog2.tad.exceptions.EmptyListException;
import uy.edu.um.prog2.tad.exceptions.OutOfBoundsException;
import uy.edu.um.prog2.tad.linkedlist.MyLinkedListImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Favoritos implements TweetRecordCallback {

    private MyLinkedListImpl<Node<Integer, String>> result = new MyLinkedListImpl<>();

    private MyBinarySearchTreeImpl<Integer ,String> arbolFavoritos= new MyBinarySearchTreeImpl<>();

    @Override
    public void execute(CSVRecord record) {
        try {
            String favourites = record.get("user_favourites");
            double parseFavourites = Double.parseDouble(favourites);
            int parseFavouritesInt = (int) Math.round(parseFavourites);
            arbolFavoritos.add(parseFavouritesInt, record.get("user_name"));
            aux();
        }catch (NumberFormatException e) {

        }
    }
    private void calculate7Most(Node<Integer, String> node) {

        if (node.getRight() != null) {
            calculate7Most(node.getRight());
        }
        if (result.size() == 7)
            return;
        if (!result.contains(node))
            result.add(node);

        if (node.getLeft() != null) {
            calculate7Most(node.getLeft());
        } else {
            return;
        }
    }
    public void aux(){
        calculate7Most(arbolFavoritos.getRoot());
    }
    public MyLinkedListImpl<Node<Integer, String>> getFavoritos(){
        return result;
    }
}

