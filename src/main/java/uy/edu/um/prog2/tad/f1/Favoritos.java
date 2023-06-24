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


    @Override
    public void execute(CSVRecord record) {
        try {
            String favourites = record.get("user_favourites");
            double parseFavourites = Double.parseDouble(favourites);
            int parseFavouritesInt = (int) Math.round(parseFavourites);
            Node<Integer, String> node = new Node(parseFavouritesInt, record.get("user_name"));
            result.add(node);
            calculate7Most();
        } catch (NumberFormatException e) {

        }
    }

    private void calculate7Most() {
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.size() - i - 1; j++) {
                try {
                    if (result.get(j).getKey() < result.get(j + 1).getKey()) {
                        Node<Integer, String> temp = result.get(j);
                        Node<Integer, String> temp2 = result.get(j + 1);
                        result.get(j).setKey(temp2.getKey());
                        result.get(j).setValue(temp2.getValue());
                        result.get(j + 1).setKey(temp.getKey());
                        result.get(j + 1).setValue(temp.getValue());

                    }
                } catch (EmptyListException e) {
                    throw new RuntimeException(e);
                } catch (OutOfBoundsException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public MyLinkedListImpl<Node<Integer, String>> getFavoritos() {
        return result;
    }
}





