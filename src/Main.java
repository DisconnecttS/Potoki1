import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        GameProgress gameProgress = new GameProgress(100, 2, 1, 1.1);
        GameProgress gameProgress2 = new GameProgress(70, 15, 99, 565.89);
        GameProgress gameProgress3 = new GameProgress(25, 7, 19, 213.54);

        gameProgress.saveGame("C:\\Users\\User\\Desktop\\Games\\gameProgress.dat");
        gameProgress2.saveGame("C:\\Users\\User\\Desktop\\Games\\gameProgress2.dat");
        gameProgress3.saveGame("C:\\Users\\User\\Desktop\\Games\\gameProgress3.dat");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\User\\Desktop\\Games\\gameProgress.dat"))) {
            oos.writeObject(gameProgress);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream("C:\\Users\\User\\Desktop\\Games\\gameProgress2.dat"))) {
            oos2.writeObject(gameProgress2);
            oos2.close();
        } catch (IOException ex2) {
            ex2.printStackTrace();
        }

        try (ObjectOutputStream oos3 = new ObjectOutputStream(new FileOutputStream("C:\\Users\\User\\Desktop\\Games\\gameProgress3.dat"))) {
            oos3.writeObject(gameProgress3);
        } catch (IOException ex3) {
            ex3.printStackTrace();
        }

        List<String> gameProgressList = new ArrayList<>();
        gameProgressList.add("C:\\Users\\User\\Desktop\\Games\\gameProgress.dat");
        gameProgressList.add("C:\\Users\\User\\Desktop\\Games\\gameProgress2.dat");
        gameProgressList.add("C:\\Users\\User\\Desktop\\Games\\gameProgress3.dat");

        zipFiles("C:\\Users\\User\\Desktop\\Games\\zipTest.zip", gameProgressList);

    }

    public static void zipFiles(String namePath, List<String> nameFile) {
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(namePath))) {
            for (String list : nameFile) {
                FileInputStream fis = new FileInputStream(list);
                ZipEntry entry = new ZipEntry(list);
                zip.putNextEntry(entry);
                for(int c = fis.read(); c != -1; c = fis.read()) {
                    zip.write(c);
                }
                zip.closeEntry();
                File file = new File(list);
                file.delete();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }







}
