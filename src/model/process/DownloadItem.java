package model.process;

import java.io.*;
import java.util.Observable;

public class DownloadItem extends Observable implements  Runnable{
    private static final int MAX_BUFFER_SIZE = 1024;
    public static final String[] STATUS_DOWNLOAD = {"Downloading", "Paused", "Complete", "Cancelled", "Error"};
    public static final int DOWNLOADING = 0;
    public static final int PAUSED = 1;
    public static final int COMPLETE = 2;
    public static final int CANCELLED = 3;
    public static final int ERROR = 4;

    private final File urlFilePath;
    private final File urlDestFilePath;
    private long size;
    private long downloaded;
    private int status;
    private long startTime = 0;
    private long elapsedTime = 0;

    public File getFileOrigin(){
        return this.urlFilePath;
    }

    public File getFileDestination(){
        return this.urlDestFilePath;
    }

    public long getSize() {
        return size;
    }

    public long getDownload() {
        return downloaded;
    }

    public long getStartTime() {
        return startTime;
    }

    public String getElapsedTime() {
        return formatTime(elapsedTime / 1000000000);
    }

    public float getProgress() {
        return ((float) downloaded / size) * 100;
    }

    public int getStatus() {
        return status;
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

    public DownloadItem(String urlPath, String destPath){
            this.urlFilePath = new File(urlPath);
            this.urlDestFilePath = new File(destPath);
            this.size = -1;
            this.downloaded = 0;
            this.status = DOWNLOADING;
            createNewDownload();
    }

    private void createNewDownload(){
        Thread thread = new Thread(this);
        thread.start();
    }

    public void cancel() {
        status = CANCELLED;
        stateChanged();
    }

    private void error() {
        status = ERROR;
        stateChanged();
    }

    private void stateChanged() {
        setChanged();
        notifyObservers();
    }

    public File validationFilePath(String urlPath){
        File urlTem = new File(urlPath);
        if(urlTem.exists()){
            return urlTem;
        }else{
            return null;
        }
    }

    @Override
    public void run() {
        int i = 0;
        long initTime = System.nanoTime();

        InputStream is = null;
        OutputStream os = null;

        File urlFilePath;
        File urlDestFilePath;

        if(getFileOrigin().exists() && getFileDestination().exists()){
            urlFilePath = getFileOrigin();
            urlDestFilePath = getFileDestination();

            System.out.println("File Open: " + urlFilePath.getName());
            System.out.println("File Path: " + urlFilePath.getAbsolutePath());
            System.out.println("File Size:"+urlFilePath.length());
            System.out.println("Destination: " + urlDestFilePath);

            if (size == -1) {
                size = urlFilePath.length();
                stateChanged();
            }

            try {
                is = new FileInputStream(urlFilePath);
                os = new FileOutputStream(urlDestFilePath);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        //System.out.println("Total Time: "+System.nanoTime()-initTime);
    }
}
