package model.app.custom.download;

import javax.swing.*;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;

public class DownloadItems extends Observable implements Runnable {
    public static final int DOWNLOADING = 0;
    public static final int PAUSED = 1;
    public static final int COMPLETE = 2;
    public static final int CANCELLED = 3;
    public static final int ERROR = 4;

    private static final int MAX_BUFFER_SIZE = 1024;
    private final URL url; // download URL
    private long size; // size of download in bytes
    private long downloaded; // number of bytes downloaded
    private int status; // current status of download
    private long startTime; // start time for current bytes
    private long readSinceStart; // number of bytes downloaded since startTime
    private long elapsedTime = 0; // elapsed time till now
    private long prevElapsedTime = 0; // time elapsed before resuming download
    private long remainingTime = -1; //time remaining to finish download
    private float avgSpeed = 0; //average download speed in KB/s
    private float speed = 0; //download speed in KB/s

    private String idFile;
    private String fileName;
    private String filePath;

    public String getIdFile() {
        return idFile;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    DownloadItems(URL url){
        this.url = url;
        size = -1;
        downloaded = 0;
        //Download
        //download();
    }

    // Start or resume downloading.
    private void download() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public String getUrl() {
        return url.toString();
    }
    public long getSize() {
        return size;
    }
    public float getSpeed() { return speed; }
    public float getAvgSpeed() {
        return avgSpeed;
    }
    public String getElapsedTime() {
        return formatTime(elapsedTime / 1000000000);
    }
    public String getRemainingTime() {
        if (remainingTime < 0) return "Unknown";
        else return formatTime(remainingTime);
    }
    public String formatTime(long time) { //time in seconds
        String s = "";
        s += (String.format("%02d", time / 3600)) + ":";
        time %= 3600;
        s += (String.format("%02d", time / 60)) + ":";
        time %= 60;
        s += String.format("%02d", time);
        return s;
    }
    public float getProgress() {
        return ((float) downloaded / size) * 100;
    }
    public int getStatus() {
        return status;
    }

    /*public void pause() {
        prevElapsedTime = elapsedTime;
        status = PAUSED;
        stateChanged();
    }*/
    /*public void resume() {
        status = DOWNLOADING;
        stateChanged();
        download();
    }*/
    public void cancel() {
        prevElapsedTime = elapsedTime;
        status = CANCELLED;
        stateChanged();
    }
    private void error() {
        prevElapsedTime = elapsedTime;
        status = ERROR;
        stateChanged();
    }

    private void stateChanged() {
        setChanged();
        notifyObservers();
    }

    // Get file name portion of URL.
    private String getFileName(URL url) {
        String fileName = url.getFile();
        return fileName.substring(fileName.lastIndexOf('/') + 1);
    }

    public void run() {
        RandomAccessFile file = null;
        InputStream stream = null;

        try {
            // Open connection to URL.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Specify what portion of file to download.
            connection.setRequestProperty("Range","bytes=" + downloaded + "-");
            // Connect to server.
            connection.connect();
            // Make sure response code is in the 200 range.
            if (connection.getResponseCode() / 100 != 2) {
                error();
            }
            // Check for valid content length.
            int contentLength = connection.getContentLength();
            if (contentLength < 1) {
                error();
            }
            /* Set the size for this download if it hasn't been already set. */
            if (size == -1) {
                size = contentLength;
                stateChanged();
            }
            // used to update speed at regular intervals
            int i = 0;
            // Open file and seek to the end of it.
            file = new RandomAccessFile(getFileName(url), "rw");
            file.seek(downloaded);
            stream = connection.getInputStream();
            //inital time when download started or resumed
            long initTime = System.nanoTime();
            while (status == DOWNLOADING) {
				/* Size buffer according to how much of the
           file is left to download. */
                if (i == 0) {
                    startTime = System.nanoTime();
                    readSinceStart = 0;
                }
                byte[] buffer;
                if (size - downloaded > MAX_BUFFER_SIZE) {
                    buffer = new byte[MAX_BUFFER_SIZE];
                } else {
                    buffer = new byte[(int) (size - downloaded)];
                }
                // Read from server into buffer.
                int read = stream.read(buffer);
                if (read == -1)
                    break;
                // Write buffer to file.
                file.write(buffer, 0, read);
                downloaded += read;
                readSinceStart += read;
                //update speed
                i++;
                if (i >= 50) {
                    speed = (readSinceStart * 976562.5f) / (System.nanoTime() - startTime);
                    if (speed > 0) remainingTime = (long) ((size - downloaded) / (speed * 1024));
                    else remainingTime = -1;
                    elapsedTime = prevElapsedTime + (System.nanoTime() - initTime);
                    avgSpeed = (downloaded * 976562.5f) / elapsedTime;
                    i = 0;
                }
                stateChanged();
            }
            /* Change status to complete if this point was reached because downloading has finished. */
            if (status == DOWNLOADING) {
                status = COMPLETE;
                stateChanged();
            }
        } catch (Exception e) {
            System.out.println(e);
            error();
        } finally {
            // Close file.
            if (file != null) {
                try {
                    file.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error to Close File", "File Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
            // Close connection to server.
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error to Close Connection", "Connection Error", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Error to Close Connection: "+e);
                }
            }
        }
    }

}
