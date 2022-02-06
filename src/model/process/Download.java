package model.process;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Observable;

public class Download extends Observable implements  Runnable{
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
    private long readSinceStart;
    private float avgSpeed=0; //average download speed in KB/s
    private long remainingTime=-1; //time remaining to finish download

    private final long prevElapsedTime=0; // time elapsed before resuming download
    private float speed=0; //download speed in KB/s

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

    public Download(String urlPath, String destPath){
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

        long initTime = System.nanoTime();

        InputStream is = null;
        OutputStream os = null;

        File urlFilePath;
        File urlDestFilePath;

        if (getFileOrigin().exists() && getFileDestination().exists()) {
            urlFilePath = getFileOrigin();
            urlDestFilePath = getFileDestination();

            System.out.println("File Open: " + urlFilePath.getName());
            System.out.println("File Path: " + urlFilePath.getAbsolutePath());
            System.out.println("File Size:" + urlFilePath.length());
            System.out.println("Destination: " + urlDestFilePath);

            if (size == -1) {
                size = urlFilePath.length();
                stateChanged();
            }

            int i = 0;
            while (status == DOWNLOADING) {
                if (i == 0) {
                    startTime = System.nanoTime();
                    readSinceStart = 0;
                }

                byte[] buffer;
                //byte[] buffer = new byte[1024];

                if (size - downloaded > MAX_BUFFER_SIZE) {
                    buffer = new byte[MAX_BUFFER_SIZE];
                } else {
                    buffer = new byte[(int) (size - downloaded)];
                }

                int read = 0;
                try {
                    assert false;
                    read = is.read(buffer);
                    if (read == -1)
                        break;

                    os.write(buffer, 0, read);
                    downloaded += read;
                    readSinceStart += read;

                    if (i >= 50) {
                        speed = (readSinceStart * 976562.5f) / (System.nanoTime() - startTime);
                        if (speed > 0) remainingTime = (long) ((size - downloaded) / (speed * 1024));
                        else remainingTime = -1;
                        elapsedTime = prevElapsedTime + (System.nanoTime() - initTime);
                        avgSpeed = (downloaded * 976562.5f) / elapsedTime;
                        i = 0;
                    }
                    stateChanged();
                    i++;

                    if (status == DOWNLOADING) {
                        status = COMPLETE;
                        stateChanged();
                    }

                } catch (IOException e) {
                    //e.printStackTrace();
                    System.out.println(e);
                    error();
                }

                try {
                    is.close();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            /*try {
                is = new FileInputStream(urlFilePath);
                os = new FileOutputStream(urlDestFilePath);
                byte[] buffer = new byte[1024];
                int length;

                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }

                is.close();
                os.close();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }*/
            }
            //System.out.println("Total Time: "+System.nanoTime()-initTime);
        }
    }
}
