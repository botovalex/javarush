package com.javarush.test.level18.lesson08.task03;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* AmigoOutputStream
1 Измените класс AmigoOutputStream так, чтобы он стал Wrapper-ом для класса FileOutputStream. Используйте наследование.
2 При вызове метода close() должны выполняться следующая последовательность действий:
2.1 вызвать метод flush()
2.2 дописать следующий текст [JavaRush © 2012-2013 All rights reserved.], используйте метод getBytes()
2.3 закрыть поток методом close()
*/

public class AmigoOutputStream extends FileOutputStream {
    public static String fileName = "C:/tmp/result.txt";
    private FileOutputStream stream;

    public AmigoOutputStream(FileOutputStream stream) throws IOException {
        super(fileName);
        this.stream = stream;
    }

    @Override
    public FileChannel getChannel() {
        return stream.getChannel();
    }

    @Override
    public void write(int b) throws IOException {
        stream.write(b);
    }

    @Override
    public void write(byte b[]) throws IOException {
        stream.write(b);
    }

    @Override
    public void write(byte b[], int off, int len) throws IOException {
        stream.write(b, off, len);
    }

    @Override
    public void close() throws IOException{
        stream.flush();
        stream.write("JavaRush © 2012-2013 All rights reserved.".getBytes());
        stream.close();
    }



    public static void main(String[] args) throws IOException {
        new AmigoOutputStream(new FileOutputStream(fileName));
    }
}
