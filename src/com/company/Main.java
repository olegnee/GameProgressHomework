package com.company;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        GameProgress player1 = new GameProgress(100500, 13, 80,555.23);
        GameProgress player2 = new GameProgress(100600, 7, 80,556.17);
        GameProgress player3 = new GameProgress(100400, 17, 80,545.99);
        saveGame("C:\\Games\\savegames/save.dat", player1);
        saveGame("C:\\Games\\savegames/save.dat1", player2);
        saveGame("C:\\Games\\savegames/save.dat2", player3);
        List<String> pathes= new ArrayList<>(
                List.of("C:\\Games\\savegames/save.dat", "C:\\Games\\savegames/save.dat1", "C:\\Games\\savegames/save.dat2"));
        zipFiles("C:\\Games\\savegames/", pathes);
        File dir = new File("C:\\Games\\savegames/");
        for (File someFile : dir.listFiles()) {
            if(!someFile.getName().equals("zip_output.zip")) {
                someFile.delete();
            }
        }
    }
     static void saveGame(String path, GameProgress somePlayer) {
        try (FileOutputStream fos = new FileOutputStream(path); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(somePlayer);
        } catch (IOException e) {
            e.getMessage();
        }
    }
    static void zipFiles(String path, List<String> filesPath) throws FileNotFoundException {
        try(ZipOutputStream zot = new ZipOutputStream(new FileOutputStream(path + "zip_output.zip"))) {
            for (String filePath : filesPath) {
                try (FileInputStream fis = new FileInputStream(filePath))
                {
                    String name = fis.toString();
                    ZipEntry entry = new ZipEntry(name);
                    zot.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zot.write(buffer);
                } catch (IOException ex) {
                    ex.getMessage();
                }
            }
            zot.closeEntry();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
}