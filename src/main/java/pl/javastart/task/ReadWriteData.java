package pl.javastart.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ReadWriteData {
    public static void saveQueue(Queue<Vehicle> vehicleQueue, File queueFile) throws IOException {
        FileWriter fileWriter = new FileWriter(queueFile);
        for (Vehicle vehicleInQueue : vehicleQueue) {
            fileWriter.write(vehicleInQueue.toStringForFile());
        }
        fileWriter.close();
    }

    public static Queue<Vehicle> readFile(File file) throws IOException {
        Queue<Vehicle> vehicleQueue = new LinkedList<>();

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String vehicleLine = sc.nextLine();
                vehicleQueue.offer(createVehicleRecordFromLine(vehicleLine));
            }
        }
        return vehicleQueue;
    }

    private static Vehicle createVehicleRecordFromLine(String vehicleLine) {
        String[] data = vehicleLine.split(";");
        String type = data[0];
        String brand = data[1];
        String model = data[2];
        int year = Integer.parseInt(data[3]);
        double mileage = Double.parseDouble(data[4]);
        String vin = data[5];

        return new Vehicle(type, brand, model, year, mileage, vin);
    }

    public static void clearFile(File queueFile) throws IOException {
        new FileWriter(queueFile, false).close();
    }
}
