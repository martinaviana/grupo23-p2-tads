package uy.edu.um.prog2.tad.f1;

import uy.edu.um.prog2.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.tad.linkedlist.MyList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class DriversReader {

    public MyList<Driver> read(Reader textReader) throws IOException {
        NameTokenizer nameTokenizer = new NameTokenizer(new HashSet(Arrays.asList("de")));

        MyList<Driver> drivers = new MyLinkedListImpl<>();
        try (BufferedReader bufferedReader = new BufferedReader(textReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null)
                drivers.add(new Driver(line, nameTokenizer.tokenize(line)));

        }

        return drivers;
    }
}
