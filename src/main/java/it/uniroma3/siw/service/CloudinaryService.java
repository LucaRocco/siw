package it.uniroma3.siw.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class CloudinaryService {
    private static final Logger log = LogManager.getLogger();
    private final Cloudinary cloudinary;

    public CloudinaryService(final Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
    
    public String salvaImmagine(final MultipartFile file) {
        log.debug("Start - salvaImmagine()");
        try {
            return (String) this.cloudinary.uploader().upload(convert(file), ObjectUtils.emptyMap()).get("secure_url");
        } catch (IOException e) {
            throw new RuntimeException("Errore durante l'upload della foto");
        }
    }

    private File convert(final MultipartFile file) throws IOException {
        log.debug("Start - convert()");
        File convFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        log.debug("End - convert()");
        return convFile;
    }
}
