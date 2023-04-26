package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileReader {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(FileReader.class));

    public Profile getDataFromFile(File file) {

        String name = null;
        Integer age = null;
        String email = null;
        Long phone = null;

        try (java.io.FileReader fileReader = new java.io.FileReader(file);
             BufferedReader reader = new BufferedReader(fileReader)) {

            String line = reader.readLine();

            while (line != null) {

                switch(line.split(":", 2)[0]){
                    case "Name":
                        name = line.split(": ", 2)[1];
                        break;
                    case "Age":
                        age = Integer.parseInt(line.split(": ", 2)[1]);
                        break;
                    case "Email":
                        email = line.split(": ", 2)[1];
                        break;
                    case "Phone":
                        phone = Long.parseLong(line.split(": ", 2)[1]);
                        break;
                    default:
                        name = null;
                        age = null;
                        email = null;
                        phone = null;
                }

                line = reader.readLine();
            }
        } catch (IOException ex){
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }


        return new Profile(name, age, email, phone);
    }
}
