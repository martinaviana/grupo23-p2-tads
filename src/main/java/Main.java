import uy.edu.um.prog2.tad.binaryTree.MyBinarySearchTreeImpl;
import uy.edu.um.prog2.tad.f1.*;
import uy.edu.um.prog2.tad.hash.ClosedHashImpl;
import uy.edu.um.prog2.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.tad.linkedlist.MyList;

import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner inputReader = new Scanner(System.in);

        boolean quit = false;
        while(!quit) {
            System.out.println("1. Listar los 10 pilotos activos en la temporada 2023 más mencionados en los tweets en un mes");
            System.out.println("2. Top 15 usuarios con más tweets");
            System.out.println("3. ");
            System.out.println("4. ");
            System.out.println("5. ");
            System.out.println("6. ");
            System.out.println("7. Salir del programa");

            int input = inputReader.nextInt();
            inputReader.nextLine();
            
            switch (input) {
                case 1:
                    int month = 0;
                    int year = 0;

                    try {
                        System.out.println("Ingrese el año en formato YYYY");
                        year = inputReader.nextInt();
                        System.out.println("Ingrese el mes en formato MM");
                        month = inputReader.nextInt();
                    }catch (InputMismatchException e) {
                        System.out.println("Revise la entrada");
                        break;
                    }

                    firstQuery(year, month);
                    break;
                case 2:
                    secondQuery();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    quit = true;
                    break;
            }
        }

    }


    private static void firstQuery(int year, int month)throws Exception {
        long time = System.currentTimeMillis();

        MyList<Driver> drivers = new DriversReader().read(new FileReader("drivers.txt"));

        Reader reader = new FileReader("f1_dataset.csv");

        MyList<DriverCounter> driverCounters = new MyLinkedListImpl<>();

        for (int i = 0; i < drivers.size(); i++) {
            driverCounters.add(new DriverCounter(drivers.get(i)));
        }

        MoreActiveDrivers tenMoreActiveDrivers = new MoreActiveDrivers(driverCounters, 10, year, month);

        TweetsReader tweetsReader = new TweetsReader(reader, tenMoreActiveDrivers);

        tweetsReader.read();

        MyList<DriverCounter> list = tenMoreActiveDrivers.sortDriverCounters();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        time = System.currentTimeMillis() - time;
        System.out.println(time);
    }

    private static void secondQuery() throws Exception {
        Reader reader = new FileReader("f1_dataset.csv");

        UsersWithMoreTweets fifteenUsersWithMoreTweets = new UsersWithMoreTweets(new MyBinarySearchTreeImpl<>(), 15);

        TweetsReader tweetReader = new TweetsReader(reader, fifteenUsersWithMoreTweets);

        tweetReader.read();

        MyList<UserCounter> list = fifteenUsersWithMoreTweets.sortUserMoreTweets();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }

}
