import uy.edu.um.prog2.tad.binaryTree.MyBinarySearchTreeImpl;
import uy.edu.um.prog2.tad.f1.*;
import uy.edu.um.prog2.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.tad.linkedlist.MyList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner inputReader = new Scanner(System.in);

        boolean quit = false;
        while (!quit) {
            System.out.println("1. Listar los 10 pilotos activos en la temporada 2023 más mencionados en los tweets en un mes");
            System.out.println("2. Top 15 usuarios con más tweets");
            System.out.println("3. Cantidad de hashtags distintos para un día dado");
            System.out.println("4. Hashtag más usado para un día dado");
            System.out.println("5. Top 7 cuentas con más favoritos");
            System.out.println("6. Cantidad de tweets con una palabra o frase específicos");
            System.out.println("7. Salir del programa");

            int input = inputReader.nextInt();
            inputReader.nextLine();
            int month = 0;
            int year = 0;
            int day = 0;

            switch (input) {
                case 1:
                    try {
                        System.out.println("Ingrese el año en formato YYYY");
                        year = inputReader.nextInt();
                        System.out.println("Ingrese el mes en formato MM");
                        month = inputReader.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Revise la entrada");
                        break;
                    }

                    firstQuery(year, month);
                    break;
                case 2:
                    secondQuery();
                    break;
                case 3:
                    try {
                        System.out.println("Ingrese el año en formato YYYY");
                        year = inputReader.nextInt();
                        System.out.println("Ingrese el mes en formato MM");
                        month = inputReader.nextInt();
                        System.out.println("Ingrese el dia en formato DD");
                        day = inputReader.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Revise la entrada");
                        break;
                    }
                    thirdQuery(year, month , day);
                    break;
                case 4:
                    try {
                        System.out.println("Ingrese el año en formato YYYY");
                        year = inputReader.nextInt();
                        System.out.println("Ingrese el mes en formato MM");
                        month = inputReader.nextInt();
                        System.out.println("Ingrese el dia en formato DD");
                        day = inputReader.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Revise la entrada");
                        break;
                    }
                    fourthQuery(year, month, day);
                    break;
                case 5:
                    fifthQuery();
                    break;
                case 6:
                    String toSearch = null;
                    try {
                        System.out.println("Ingrese la palabra o frase a contar: ");
                        toSearch = inputReader.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Revise la entrada");
                        break;
                    }
                    sixthQuery(toSearch);
                    break;
                case 7:
                    quit = true;
                    break;
            }
        }

    }


    private static void firstQuery(int year, int month) throws Exception {
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

    private static void thirdQuery(int year, int month, int day) throws Exception {
        Reader reader = new FileReader("f1_dataset.csv"); // paso archivo que va a leer
        DifferentHashtagsInDay temp = new DifferentHashtagsInDay(new MyLinkedListImpl<>(), year, month, day);
        TweetsReader tweetReader = new TweetsReader(reader, temp);
        tweetReader.read();

        for (int i = 0; i < temp.getHashTagCounter().size(); i++) {
            System.out.println(temp.getHashTagCounter().get(i));
        }
        System.out.println(temp.getHashTags());
    }

    private static void fourthQuery(int year, int month, int day) throws Exception {
        Reader reader = new FileReader("f1_dataset.csv"); // paso archivo que va a leer
        HashtagUsageAnalyzer temp = new HashtagUsageAnalyzer(year, month, day);
        TweetsReader tweetReader = new TweetsReader(reader, temp);
        tweetReader.read();
        System.out.println(temp.getMaxHashtag().hashtag);


    }
    private static void fifthQuery() throws Exception {
        Reader reader = new FileReader("f1_dataset.csv"); // paso archivo que va a leer
        Favoritos favoritos= new Favoritos();
        TweetsReader tweetReader = new TweetsReader(reader, favoritos);
        tweetReader.read();
        MyList<UserFavourites> list = favoritos.sortFavourites();


        for (int i=0; i<list.size(); i++){
            System.out.println(i+1 + ". " + list.get(i));
        }

        System.out.println("\n");
    }

    private static void sixthQuery(String toSearch) throws Exception {
        Reader reader = new FileReader("f1_dataset.csv"); // paso archivo que va a leer
        TweetsWithSpecificString tweetsWithSpecificString = new TweetsWithSpecificString(toSearch);
        TweetsReader tweetReader = new TweetsReader(reader, tweetsWithSpecificString);
        tweetReader.read();
        long counter =  tweetsWithSpecificString.getStringCounter();

        System.out.println("La palabra o frase " + toSearch + " ha aparecido " + counter + " veces \n");
    }
}

