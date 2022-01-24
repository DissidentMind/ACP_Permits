package model.process;

import javax.swing.*;
import java.io.*;
import java.util.Observable;

public class Download extends Observable implements Runnable {

    // Max size of download buffer.
    private static final int MAX_BUFFER_SIZE = 1024;
    // These are the status names.
    public static final String[] STATUS_DOWNLOAD = {"Downloading", "Paused", "Complete", "Cancelled", "Error"};

    // These are the status codes.
    public static final int DOWNLOADING = 0;
    public static final int PAUSED = 1;
    public static final int COMPLETE = 2;
    public static final int CANCELLED = 3;
    public static final int ERROR = 4;

    private final String url; // download URL
    private long size; // size of download in bytes
    private long downloaded; // number of bytes downloaded
    private int status; // current status of download
    private long startTime; // start time for current bytes
    private long readSinceStart; // number of bytes downloaded since startTime
    private long elapsedTime = 0; // elapsed time till now
    private long prevElapsedTime = 0; // time elapsed before resuming download
    //private long remainingTime = -1; //time remaining to finish download
    //private float avgSpeed = 0; //average download speed in KB/s
    private float speed = 0; //download speed in KB/s

    // Constructor for Download.
    public Download(String url) {
        this.url = url;
        size = -1;
        downloaded = 0;
        status = DOWNLOADING;
        // Begin the download.
        download();
    }

    // Get this download's URL.
    public String getUrl() {
        return url;
    }

    // Get this download's size.
    public long getSize() {
        return size;
    }

    // Get download speed.
    public float getSpeed() {
        return speed;
    }

    // Get elapsed time
    public String getElapsedTime() {
        return formatTime(elapsedTime / 1000000000);
    }

    // Format time
    public String formatTime(long time) { //time in seconds
        String s = "";
        s += (String.format("%02d", time / 3600)) + ":";
        time %= 3600;
        s += (String.format("%02d", time / 60)) + ":";
        time %= 60;
        s += String.format("%02d", time);
        return s;
    }

    // Get this download's progress.
    public float getProgress() {
        return ((float) downloaded / size) * 100;
    }

    // Get this download's status.
    public int getStatus() {
        return status;
    }

    // Pause this download.
    /*public void pause() {
        prevElapsedTime = elapsedTime;
        status = PAUSED;
        stateChanged();
    }*/

    // Cancel this download.
    public void cancel() {
        prevElapsedTime = elapsedTime;
        status = CANCELLED;
        stateChanged();
    }

    // Mark this download as having an error.
    private void error() {
        prevElapsedTime = elapsedTime;
        status = ERROR;
        stateChanged();
    }

    // Start or resume downloading.
    private void download() {
        Thread thread = new Thread(this);
        thread.start();
    }

    // Get file name portion of URL.
//    private String getFileName(URL url) {
//        String fileName = url.getFile();
//        return fileName.substring(fileName.lastIndexOf('/') + 1);
//    }

    private String getFileName(String url) {
        //return fileName.substring(fileName.lastIndexOf('/') + 1);
        return new File(url).getName();
    }


    // Download file.
    public void run() {
        RandomAccessFile file = null;
        InputStream stream = null;
        OutputStream os = null;

        int i = 0;
        long initTime = System.nanoTime();

        try {
            /*if(new File(url).exists() && new File(url).canWrite()){
                System.out.println("File exists and it is read only, making it writable");
                //url.setWritable(true);
            }*/

            file = new RandomAccessFile(getFileName(url), "rw");
            file.seek(downloaded);

            //stream = connection.getInputStream();
            //inital time when download started or resumed

            stream = new FileInputStream(url);
            os = new FileOutputStream("C:\\Users\\evanf\\Downloads");

            /*
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
             */

            /*FilesCopier_Utility.fileCopier(
            		new File(url),
            		new File("D:\\Src\\"));*/

            while (status == DOWNLOADING) {

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
                //file.write(buffer, 0, read);

                os.write(buffer, 0, read);

                downloaded += read;
                readSinceStart += read;
                //update speed
                i++;
                if (i >= 50) {
                    speed = (readSinceStart * 976562.5f) / (System.nanoTime() - startTime);
                    /*
                    if (speed > 0) remainingTime = (long) ((size - downloaded) / (speed * 1024));
                    else remainingTime = -1;
                    */
                    elapsedTime = prevElapsedTime + (System.nanoTime() - initTime);
                    //avgSpeed = (downloaded * 976562.5f) / elapsedTime;
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
            //if (file != null) {
                if (os != null) {
                try {
                    os.close();
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

    // Notify observers that this download's status has changed.
    private void stateChanged() {
        setChanged();
        notifyObservers();
    }
}