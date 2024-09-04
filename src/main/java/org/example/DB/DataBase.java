package org.example.DB;

import org.example.Interface.IDB;

import java.io.*;
import java.util.ArrayList;

public class DataBase implements IDB {
    private static final String fileName = "databaseUserText.txt";
    private static DataBase instance;

    private DataBase() {
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public void saveData(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data+"\n");
            System.out.println("Text has been written to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public ArrayList<String> loadData() {

        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return list;

    }
    public void deleteData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            writer.write("");
            System.out.println("Text has been cleared from " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }



    public void updateData(String oldWord, String newWord) {

        ArrayList<String> list = loadData();


        ArrayList<String> updatedList = new ArrayList<>();


        for (String line : list) {
            String updatedLine = line.replace(oldWord, newWord);
            updatedList.add(updatedLine);
        }


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            for (String line : updatedList) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Data updated successfully in " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


}
