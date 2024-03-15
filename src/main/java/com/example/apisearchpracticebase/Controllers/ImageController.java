package com.example.apisearchpracticebase.Controllers;

import com.example.apisearchpracticebase.Models.PracticeBase;
import com.example.apisearchpracticebase.Models.Student;
import com.example.apisearchpracticebase.Repositories.PracticeBaseRepos;
import com.example.apisearchpracticebase.Repositories.StudentRepos;
import com.example.apisearchpracticebase.Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ImageController {
    @Autowired
    PracticeBaseRepos practiceBaseRepos;

    @Autowired
    StudentRepos studentRepos;

    @Autowired
    ImageService imageService;

    @Value("${data.images.path.base}")
    private String pathImageBase;

    @Value("${data.images.path.profile}")
    private String pathImageProfile;
    @GetMapping("upload")
    public String getPage(){
        return "uploadImageTest";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
                                              @RequestParam("isPracticeBase") boolean isBase,
                                              @RequestParam("uploadingID") long id) {
        try {
            String pathImages = isBase ? pathImageBase : pathImageProfile;
            String fileName = imageService.saveImage(file, pathImages);
            if(isBase){
                PracticeBase practiceBase = practiceBaseRepos.findById(id).get();
                practiceBase.setPhotoPlace(fileName);
                practiceBaseRepos.save(practiceBase);
            }else{
                Student student = studentRepos.findById(id).get();
                student.getResume().setPhotoStudent(fileName);
                studentRepos.save(student);
            }
            return ResponseEntity.ok("Image uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload image");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<byte[]> getImage(@RequestParam("isPracticeBase") boolean isBase, @RequestParam("filename") String filename) {
        try {
            String pathImages = isBase ? pathImageBase : pathImageProfile;
            Path imagePath = Paths.get(pathImages, filename);

            byte[] imageBytes = Files.readAllBytes(imagePath);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
