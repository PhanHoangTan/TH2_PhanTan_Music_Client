package client;

import dao.AlbumDao;

import java.rmi.Naming;
import java.util.Scanner;

public class Client {
    private static final String URL = "rmi://PhanHoangTan:4591/";
    public static void main(String[] args) {
        try {
            AlbumDao albumDao = (AlbumDao) Naming.lookup(URL + "albumDao");
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("1. List albums by genre");
                System.out.println("2. Update price of album");
                System.out.println("3. Get album count by genre");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Enter genre: ");
                        String genre = scanner.next();
                        System.out.println("Enter year: ");
                        int year = scanner.nextInt();
                        albumDao.listAlbumByGenre("Pop", 2018).forEach(System.out::println);
                        break;
                    case 2:
                        System.out.println("Enter album id: ");
                        String id = scanner.next();
                        System.out.println("Enter new price: ");
                        int price = scanner.nextInt();
                        albumDao.updatePriceOfAlbum(id, price);
                        break;
                    case 3:
                        albumDao.getAlbumCountByGenre().forEach((k, v) -> System.out.println(k + ": " + v));
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}