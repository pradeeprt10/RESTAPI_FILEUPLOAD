package com.sts.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sts.file.logic.FileUploadLogic;

@RestController
public class FileController {

	@Autowired
	private FileUploadLogic fileUploadLogic;

	@PostMapping("/file-upload")
	public ResponseEntity<String> fileupload(@RequestParam("file") MultipartFile file) {

		if (file.isEmpty()) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FileCan't be Empty");
		} else if (file.getContentType().equals("jpeg/png/image")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only JPEG/PNG/IMAGE File upload");

		} else {

			try {

				boolean success = false;
				success = fileUploadLogic.upload(file);
				if (success == true) {
					//return ResponseEntity.ok("File uploaded Succesfully");
					System.out.println(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toString());
					return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toString());
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Issue in the file");
				}

			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception occur");
			}

		}

	}

}
