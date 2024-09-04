package org.example.Interface;

import java.util.ArrayList;

public interface IDB {
    void saveData(String data);
    ArrayList<String> loadData();
    void deleteData();
    void updateData(String what,String to);

}