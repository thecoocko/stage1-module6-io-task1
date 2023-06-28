package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


 public class FileReader {


    public Profile getDataFromFile(File file) {
        String profileData = "";
        String [] userData = new String[4];
        try(BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(file))) {
            String line = "";
            while((line = bufferedReader.readLine()) != null){
                profileData += line+"\n";
            }
            profileData = profileData.replace(" ","");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Pattern pattern = Pattern.compile("(?<=:)(\\s*)(.+)");
        Matcher matcher = pattern.matcher(profileData);

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
