package com.hoaxify.ws.File;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileService {

    @Value("${upload-path}")
    String uploadPath;

    public String writeBase64EncodedStringToFile(String image) throws IOException {
        String fileName = generateRandomName();
        File target = new File(uploadPath + "/" + fileName);
        OutputStream outputStream = new FileOutputStream(target);

        byte[] base64encoded = Base64.getDecoder().decode(image);
        outputStream.write(base64encoded);
        outputStream.close();
        return fileName;
    }
// kullanıcı resimleri için ramdom isim üreten method
    public String generateRandomName(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
