package net.devmanuals.controller;


import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import net.devmanuals.web.vo.UploadedFile;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/cont")
public class RestController {

  UploadedFile ufile;
  public RestController(){
  	System.out.println("init RestController");
  	ufile = new UploadedFile();
  }
  
 
  @RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
  public void get(HttpServletResponse response,@PathVariable String value){
 		try {
 			
 		 	response.setContentType(ufile.type);
			response.setContentLength(ufile.length);
	 		FileCopyUtils.copy(ufile.bytes, response.getOutputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }

  
  @RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addArticle() {
		return new ModelAndView("addUpload");
	}
  
   @RequestMapping(value = "/upload", method = RequestMethod.POST)
   public @ResponseBody String upload(MultipartHttpServletRequest request, HttpServletResponse response) {                 
  	 
  	 //0. notice, we have used MultipartHttpServletRequest
  	 
  	 //1. get the file from the request object
	   System.out.println("----------------------------------------------------"+request.getClass());
	 Iterator<String> itr =  request.getFileNames();
	 
	 MultipartFile mpf = request.getFile(itr.next());
  	 System.out.println(mpf.getOriginalFilename() +" uploaded!");

  	 try {
		ufile.length = mpf.getBytes().length;
		ufile.bytes= mpf.getBytes();
	  	ufile.type = mpf.getContentType();
	  	ufile.name = mpf.getOriginalFilename();

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  	 
  	 //2. send it back to the client as <img> that calls get method
  	 //we are using getTimeInMillis to avoid server cached image 
  
	System.out.println("----------------------------------------------------"+request.getClass());
	   if((request instanceof MultipartHttpServletRequest)){
		   
	        System.out.println("++++++++++++++++++++++++++++++++++++++true-----------------------");
	    }
  	 return "<img src='http://localhost:8080/spring/cont/get/"+Calendar.getInstance().getTimeInMillis()+"' />";
  	
  }
  		
}

