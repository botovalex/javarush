package com.javarush.test.level16.lesson13.bonus01;

import com.javarush.test.level16.lesson13.bonus01.common.*;


/**
 * Created by barbudos on 21.06.2016.
 */
public class ImageReaderFactory {
    static ImageReader getReader(ImageTypes type) {
        if (type == ImageTypes.BMP)
            return new BmpReader();
        else if (type == ImageTypes.JPG)
            return new JpgReader();
        else if (type == ImageTypes.PNG)
            return new PngReader();
        else throw new IllegalArgumentException("Неизвестный тип картинки");
    }
}
