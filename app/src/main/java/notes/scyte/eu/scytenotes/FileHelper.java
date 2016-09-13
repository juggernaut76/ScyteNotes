package notes.scyte.eu.scytenotes;

import android.content.Context;
import android.os.Environment;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by gogic on 09.09.16.
 */
public class FileHelper {

    public File saveTextFile(Context context, String subject, String content,
                             File oldFile) throws IOException {
        File file;
        String rootPath;
        File externalStorage = Environment.getExternalStorageDirectory();
        String externalPath = externalStorage.getAbsolutePath()
                .replace("/mnt", "");
        externalStorage = new File(externalPath);
        File textDir = null;
        if (externalStorage.canWrite()) {
            rootPath = externalStorage.getPath();
            textDir = new File(rootPath + "/ScyteNotes/txt");
        } else if (context.getFilesDir().canWrite()) {
            rootPath = context.getFilesDir().toString();
            textDir = new File(rootPath + "/txt");
        }
        String fileName = subject + ".txt";
        if (!textDir.exists()) {
            textDir.mkdirs();
        }
        textDir.setWritable(true);
        textDir.setReadable(true);
        file = new File(textDir, fileName);
        if (oldFile == null) {
            if (file.exists()) {
                file = new File(checkFilePath(
                        file.getAbsolutePath() ,0
                        , ".txt"));
            }
        } else if (!file.getAbsolutePath()
                .equals(oldFile.getAbsolutePath())) {
            if (file.exists()) {
                file = new File(checkFilePath(file.getAbsolutePath(), 0, ".txt"));
            }
            oldFile.renameTo(file);
        }
        try {

            FileOutputStream fileOutStr = new FileOutputStream(file);
            OutputStreamWriter outputStrWr = new OutputStreamWriter(
                    fileOutStr,
                    Charset.defaultCharset());
            BufferedWriter buffWriter = new BufferedWriter(outputStrWr);
            buffWriter.write(content);
            buffWriter.close();

        } catch (Throwable t) {
            t.printStackTrace();
        }
        return file;
    }

    // Prüft, ob die Datei schon vorhanden ist.
    private boolean fileExits(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    // Diese Methode liefert den Datei-Pfad ohne Erweiterung zurück.
    public String deleteFileExtension(String filePath) {
        filePath = filePath.substring(0, filePath.lastIndexOf("."));
        return filePath;
    }

    // Diese Methode liefert den Datei-Pfad ohne Datei-Zähler zurück.
    public String deleteFileNameCounter(String filePath) {
        if (filePath.lastIndexOf((char) 32) > 1) {
            filePath = filePath.substring(0,
                    filePath.lastIndexOf((char) 40) - 1);
        }
        return filePath;
    }

    private String checkFilePath(String filePath
            , int count
            , String fileExtension) {
        if (count > 0) {
            filePath = deleteFileExtension(filePath);
            filePath = deleteFileNameCounter(filePath);
            filePath = filePath
                    + String.format(" (%d)", count)
                    + fileExtension;
        }
        if (fileExits(filePath)) {
            count++;
            return checkFilePath(filePath, count, fileExtension);
        } else {
            return filePath;
        }
    }

}
