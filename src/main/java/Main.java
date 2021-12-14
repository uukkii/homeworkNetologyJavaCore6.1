import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static final List<String> savesList = new ArrayList<>();

    public void main(String[] args) {
//
//        GameProgress gameProgress1 = new GameProgress(100, 2, 1, 1.0);
//        GameProgress gameProgress2 = new GameProgress(80, 5, 4, 112.5);
//        GameProgress gameProgress3 = new GameProgress(65, 9, 9, 323.6);
//
//        saveGame("Games/BestGame/savegames/save1.dat", gameProgress1);
//        saveGame("Games/BestGame/savegames/save2.dat", gameProgress2);
//        saveGame("Games/BestGame/savegames/save3.dat", gameProgress3);
//
//        zipFiles("Games/BestGame/savegames/saves.zip", savesList);
//
//        delete("Games/BestGame/savegames/save1.dat");
//        delete("Games/BestGame/savegames/save2.dat");
//        delete("Games/BestGame/savegames/save4.dat");
    }

    public void saveGame(String dir, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(dir);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
            savesList.add(dir);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void zipFiles(String dir, List<String> savesList) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dir))) {
            for (String save : savesList) {
                FileInputStream fis = new FileInputStream(save);
                ZipEntry entry = new ZipEntry(save.substring(25));
                zos.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zos.write(buffer);
                fis.close();
                zos.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String dir) throws IOException {
        try {
            Files.delete(Paths.get(dir));
        } catch (IOException e) {
            throw e;
        }
    }
}