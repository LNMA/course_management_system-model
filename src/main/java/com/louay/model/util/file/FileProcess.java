package com.louay.model.util.file;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileProcess {
    public byte[] readFile(String path) throws IOException {
        byte[] bytes;
        InputStream in;
        File file = new File(path);

        if (file.exists()) {
            if (file.canExecute()) {
                if (file.canRead()) {
                    bytes = new byte[(int) file.length()];
                    in = new BufferedInputStream(new FileInputStream(path));
                    int i = 0;
                    while (in.available() != 0) {
                        bytes[i] = (byte) in.read();
                        i++;
                    }
                    in.close();
                } else {
                    throw new IOException("file can not Read.");
                }
            } else {
                throw new IOException("file can not execute.");
            }
        } else {
            throw new FileNotFoundException("file not found.");
        }
        return bytes;
    }

    public void writeFile(String path, byte[] bytes) throws IOException {
        OutputStream out = new BufferedOutputStream(new FileOutputStream(path));
        out.write(bytes);
        out.flush();
        out.close();
    }
}
