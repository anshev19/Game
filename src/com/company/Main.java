package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
		GameInstaller installer = new GameInstaller();
		installer.install("C:\\Users\\aleksandr.shevchenko\\Desktop\\Games");

		GameSaver saver = new GameSaver();
	    GameProgress gameProgress1 = new GameProgress(80,15,2,10.5);
	    GameProgress gameProgress2 = new GameProgress(65,20,4,50.8);
	    GameProgress gameProgress3 = new GameProgress(8,4,2,16.1);

		List<String> pathList = new ArrayList<>();
		pathList.add("C:\\Users\\aleksandr.shevchenko\\Desktop\\Games\\savegames\\game1.dat");
		pathList.add("C:\\Users\\aleksandr.shevchenko\\Desktop\\Games\\savegames\\game2.dat");
		pathList.add("C:\\Users\\aleksandr.shevchenko\\Desktop\\Games\\savegames\\game3.dat");

		saver.saveGame(pathList.get(0), gameProgress1);
		saver.saveGame(pathList.get(1), gameProgress2);
		saver.saveGame(pathList.get(2), gameProgress3);

		saver.zipFiles("C:\\Users\\aleksandr.shevchenko\\Desktop\\Games\\savegames\\game_progress.zip", pathList);

		File file1 = new File(pathList.get(0));
		File file2 = new File(pathList.get(1));
		File file3 = new File(pathList.get(2));

		file1.delete();
		file2.delete();
		file3.delete();

		GameLoader loader = new GameLoader();
		loader.openZip("C:\\Users\\aleksandr.shevchenko\\Desktop\\Games\\savegames\\game_progress.zip",
				"C:\\Users\\aleksandr.shevchenko\\Desktop\\Games\\savegames");
		GameProgress gProgress1 = loader.openProgress(pathList.get(0));
		GameProgress gProgress2 = loader.openProgress(pathList.get(1));
		GameProgress gProgress3 = loader.openProgress(pathList.get(2));

		System.out.println(gProgress1);
		System.out.println(gProgress2);
		System.out.println(gProgress3);
    }
}
