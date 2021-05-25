package it.uniroma3.siw.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryService(final Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
    
    public String salvaImmagine(MultipartFile file) {
        try {
            return (String) this.cloudinary.uploader().upload(convert(file), ObjectUtils.emptyMap()).get("secure_url");
        } catch (IOException e) {
            throw new RuntimeException("messaggino random"); //TODO: Aggiornare
        }
    }

    private File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }
}
