package com.java.coco.utils.image;

import java.util.Base64;

public class ImageUtil {

    /**
     * @param base64ImgData
     * @return
     */
    public static String checkImageBase64Format(String base64ImgData) {
        byte[] b = Base64.getDecoder().decode(base64ImgData);
        String type = "";

        if (b.length >= 12 && b[0] == 'R' && b[1] == 'I' && b[2] == 'F' && b[3] == 'F' && b[8] == 'W' && b[9] == 'E' && b[10] == 'B' && b[11] == 'P') {
            type = "webp";
        } else if (0x424D == ((b[0] & 0xff) << 8 | (b[1] & 0xff))) {
            type = "bmp";
        } else if (0x8950 == ((b[0] & 0xff) << 8 | (b[1] & 0xff))) {
            type = "png";
        } else if (0xFFD8 == ((b[0] & 0xff) << 8 | (b[1] & 0xff))) {
            type = "jpg";
        }
        return type;
    }

}
