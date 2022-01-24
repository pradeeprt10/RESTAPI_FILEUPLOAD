package com.sts.file.logic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadLogic {
	
	//Static URL
	//private final String uploadpath="C:\\Users\\prade\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\RESTAPI-FileUpload\\src\\main\\resources\\static\\image";
	private final String uploadpath= new ClassPathResource("static/image/").getFile().getAbsolutePath();
	
	// This is for ClassPathResource need Default constructor need to be handle thought Default constructor
	FileUploadLogic()throws IOException{
		
	}
	
	public boolean upload(MultipartFile file) {
		boolean success=false;
		try {
			// reading input stram file and storing in byte array
			InputStream inputstr = file.getInputStream();
			byte inputsize[] = new byte[inputstr.available()];
			inputstr.read(inputsize);
			
			System.out.println(file.getSize());
			
			//Writting data to output file
			FileOutputStream fileoutput = new FileOutputStream(uploadpath+"\\"+file.getOriginalFilename());
			fileoutput.write(inputsize);
		    System.out.println(uploadpath+"\\"+file.getOriginalFilename());
		    success=true;
		    fileoutput.flush();
		    fileoutput.close();
		    return success;
		}
		catch(Exception e) {		
			e.printStackTrace();
			return success;
		}
	
	}

}
