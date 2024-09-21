package org.example;



import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();
        int purchasedSeats = 0;
        float ticketsPercentage = 0;
        int currentIncome = 0;
        int totalIncome;

        char[][] seats = new char[rows + 1][seatsPerRow + 1];

        for (int i = 1; i <= rows; i++) {
            seats[i][0] = (char) ('0' + i);
        }
        for (int j = 1; j <= seatsPerRow; j++) {
            seats[0][j] = (char) ('0' + j);
            seats[0][0] = ' ';
        }

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= seatsPerRow; j++)
                seats[i][j] = 'S';
        }

        int maxSeats = rows * seatsPerRow;
        if (maxSeats <= 60) {
            totalIncome = maxSeats * 10;
        } else {
            int firstHalfRows = rows / 2;
            int secondHalfRows = rows - firstHalfRows;
            totalIncome = (firstHalfRows * seatsPerRow * 10) + (secondHalfRows * seatsPerRow * 8);
        }

        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int answer = scanner.nextInt();

            if (answer == 1) {
                System.out.println();
                System.out.println("Cinema:");
                for (int i = 0; i < seats.length; i++) {
                    for (int j = 0; j < seats[i].length; j++) {
                        System.out.print(seats[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
            } else if (answer == 2) {
                boolean validInput = false;
                while (!validInput) {
                    System.out.println("Enter a row number:");
                    int rowNumber = scanner.nextInt();

                    System.out.println("Enter a seat number in that row:");
                    int seatNumber = scanner.nextInt();

                    if (rowNumber <= 0 || rowNumber > rows || seatNumber <= 0 || seatNumber > seatsPerRow) {
                        System.out.println();
                        System.out.println("Wrong input!");
                        System.out.println();
                    } else if (seats[rowNumber][seatNumber] == 'B') {
                        System.out.println();
                        System.out.println("That ticket has already been purchased!");
                        System.out.println();
                    } else {
                        seats[rowNumber][seatNumber] = 'B';
                        purchasedSeats++;
                        int totalSeats = rows * seatsPerRow;
                        ticketsPercentage = (float) purchasedSeats / totalSeats * 100;

                        int ticketPrice = 0;
                        if (totalSeats <= 60) {
                            ticketPrice = 10;
                        } else {
                            int firstHalfRows = rows / 2;
                            if (rowNumber <= firstHalfRows) {
                                ticketPrice = 10;
                            } else {
                                ticketPrice = 8;
                            }
                        }
                        currentIncome += ticketPrice;
                        System.out.println();
                        System.out.println("Ticket price: $" + ticketPrice);
                        System.out.println();
                        validInput = true;
                    }
                }
            } else if (answer == 3) {
                System.out.println("Number of purchased tickets: " + purchasedSeats);
                System.out.println("Percentage: " + String.format("%.2f", ticketsPercentage) + "%");
                System.out.println("Current income: $" + currentIncome);
                System.out.println("Total Income: $" + totalIncome);
            } else if (answer == 0) {
                break;
            } else {
                System.out.println();
                System.out.println("Invalid option. Please try again.");
                System.out.println();
            }
        }
        scanner.close();
    }
}