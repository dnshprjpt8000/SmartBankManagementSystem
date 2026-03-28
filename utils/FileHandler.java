package com.bankapp.utils;

import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    public static void write(String filePath, String data) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(data + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}
