package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


 public class FileReader {
     private static final Logger logger = Logger.getLogger(FileReader.class.getName());
    public Profile getDataFromFile(File file) {
        StringBuilder profileData = new StringBuilder((int)file.length());
        String [] userData = new String[4];
        try(BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(file))) {
            String line = "";
            while((line = bufferedReader.readLine()) != null){
                profileData.append(line.replace(" ","")+"\n");
            }
        } catch (FileNotFoundException e) {
            logger.severe(e.getMessage());
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }

        Pattern pattern = Pattern.compile("(?<=:)(\\s*)(.+)");
        Matcher matcher = pattern.matcher(profileData.toString());

        int i = 0;
        while (matcher.find()) {
            userData[i] = matcher.group();
            i++;
        }

        Profile profile = new Profile();
        profile.setAge(Integer.valueOf(userData[1]));
        profile.setName(userData[0]);
        profile.setEmail(userData[2]);
        profile.setPhone(Long.valueOf(userData[3]));

        return profile;
    }


 }
