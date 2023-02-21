package pl.javastart.task;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class QueueManager {
    private Queue<Vehicle> vehicleQueue = new LinkedList<>();
    private Scanner sc = new Scanner(System.in);
    private File queueFile = new File("src/main/resources/queue.txt");

    void mainLoop() throws IOException {
        vehicleQueue = ReadWriteData.readFile(queueFile);
        ReadWriteData.clearFile(queueFile);

        Option option;
        do {
            printOptions();
            System.out.println("Wybierz opcję: ");
            option = Option.createFromInt(sc.nextInt());
            sc.nextLine();
            switch (option) {
                case ADD:
                    addVehicle();
                    break;
                case TAKE:
                    takeVehicle();
                    break;
                case EXIT:
                    checkQueue();
                    System.out.println("Koniec pracy na dziś.");
                    break;
            }

        } while (option != Option.EXIT);
    }

    private void checkQueue() throws IOException {
        if (!vehicleQueue.isEmpty()) {
            System.out.printf("Liczba pojazdów do sprawdzenia na jutro: %d\n", vehicleQueue.size());
            ReadWriteData.saveQueue(vehicleQueue, queueFile);
        }
    }

    private void addVehicle() {
        Vehicle vehicle;
        do {
            vehicle = readAndAddVehicle();
        } while (vehicle == null);

        vehicleQueue.offer(vehicle);
        System.out.println("Pojazd dodany do kolejki");
    }

    private Vehicle readAndAddVehicle() {
        System.out.println("Podaj typ pojazdu:");
        String type = sc.nextLine();
        System.out.println("Podaj markę pojazdu:");
        String brand = sc.nextLine();
        System.out.println("Podaj model pojazdu:");
        String model = sc.nextLine();
        System.out.println("Podaj rocznik pojazdu:");
        int year = sc.nextInt();
        sc.nextLine();
        System.out.println("Podaj przebieg pojazdu:");
        double mileage = sc.nextInt();
        sc.nextLine();
        System.out.println("Podaj nr VIN pojazdu:");
        String vin = sc.nextLine();

        Vehicle vehicle = new Vehicle(type, brand, model, year, mileage, vin);
        if (!vehicleInQueue(vehicle)) {
            return vehicle;
        } else {
            System.out.println("Wprowadzony pojazd jest już w kolejce. Podaj dane ponownie");
        }

        return null;
    }

    private boolean vehicleInQueue(Vehicle vehicle) {
        for (Vehicle vehicleInQueue : vehicleQueue) {
            if (vehicleInQueue.equals(vehicle)) {
                return true;
            }
        }
        return false;

    }

    private void takeVehicle() {
        if (vehicleQueue.isEmpty()) {
            System.out.println("Wszystkie pojazdy przebadane.");
        } else {
            Vehicle nextVehicle = vehicleQueue.poll();
            System.out.println("Pojazd do diagnostyki: ");
            System.out.println(nextVehicle);
        }
    }

    private void printOptions() {
        Option[] values = Option.values();
        for (Option value : values) {
            System.out.printf("%s", value);
        }
    }

    private enum Option {
        EXIT(0, "Zakończ program"),
        ADD(1, "Dodaj pojazd"),
        TAKE(2, "Zbadaj pojazd");

        int option;
        String description;

        Option(int option, String description) {
            this.option = option;
            this.description = description;
        }

        static Option createFromInt(int option) {
            return values()[option];
        }

        @Override
        public String toString() {
            return option + " - " + description + '\n';
        }
    }
}
