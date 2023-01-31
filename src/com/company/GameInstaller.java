package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GameInstaller {
    private StringBuilder builder = new StringBuilder();
    private final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private final static String SRC_DIR = "src";
    private final static String MAIN_DIR = "main";
    private final static String TEST_DIR = "test";
    private final static String RES_DIR = "res";
    private final static String DRAWABLES_DIR = "drawables";
    private final static String VECTORS_DIR = "vectors";
    private final static String ICONS_DIR = "icons";
    private final static String SAVE_GAMES_DIR = "savegames";
    private final static String TEMP_DIR = "temp";
    private final static String TEMP_FILE = "temp.txt";
    private final static String MAIN_FILE = "Main.java";
    private final static String UTILS_FILE = "Utils.java";

    public void install(String installPath) {

        List<String> folders = new ArrayList<>();
        List<String> files = new ArrayList<>();

        folders.add(installPath + "\\" + SRC_DIR);
        folders.add(installPath + "\\" + SRC_DIR + "\\" + MAIN_DIR);
        folders.add(installPath + "\\" + SRC_DIR + "\\" + TEST_DIR);
        folders.add(installPath + "\\" + RES_DIR);
        folders.add(installPath + "\\" + RES_DIR + "\\" + DRAWABLES_DIR);
        folders.add(installPath + "\\" + RES_DIR + "\\" + VECTORS_DIR);
        folders.add(installPath + "\\" + RES_DIR + "\\" + ICONS_DIR);
        folders.add(installPath + "\\" + SAVE_GAMES_DIR);
        folders.add(installPath + "\\" + TEMP_DIR);

        files.add(installPath + "\\" + SRC_DIR + "\\" + MAIN_DIR + "\\" + MAIN_FILE);
        files.add(installPath + "\\" + SRC_DIR + "\\" + MAIN_DIR + "\\" + UTILS_FILE);
        files.add(installPath + "\\" + TEMP_DIR + "\\" + TEMP_FILE);

        createFolders(folders);
        createFiles(files);

        Log(installPath + "\\" + TEMP_DIR + "\\" + TEMP_FILE);
    }

    private void createFolders(List<String> folders) {
        for (String folder : folders) {
            File dir = new File(folder);
            if (dir.mkdir()) {
                builder.append("INFO>> " + DTF.format(LocalDateTime.now()) + ", папка '" + folder + "' успешно создана\n");
            }
            else {
                builder.append("WARNING>> " + DTF.format(LocalDateTime.now()) + ", папка '" + folder + "' не создана\n");
            }
        }
    }

    private void createFiles(List<String> files) {
        try {
            for (String fileName : files) {
                File file = new File(fileName);
                if (file.createNewFile()) {
                    builder.append("INFO>> " + DTF.format(LocalDateTime.now()) + ", файл '" + fileName + "' успешно создан\n");
                }
                else {
                    builder.append("WARNING>> " + DTF.format(LocalDateTime.now()) + ", файл '" + fileName + "' не создан\n");
                }
            }
        } catch(IOException e) {
            builder.append(e.getMessage());
        }
    }

    private void Log(String tempFile) {
        try {
            FileWriter fw = new FileWriter(tempFile);
            fw.write(builder.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
