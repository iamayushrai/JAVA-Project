package com.abc.demo.ctl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.abc.demo.dto.BookingDTO;
import com.abc.demo.dto.UserDTO;
import com.abc.demo.form.ContactForm;

import com.abc.demo.service.BookingService;
import com.abc.demo.utility.PDFGeneratorVehicle;
import com.lowagie.text.DocumentException;

@Controller
public class TestController {
	@Autowired
	private BookingService service;

	@GetMapping("/")
	public String home(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("here11");
		/*
		 * HttpSession session = request.getSession(false);
		 * if(session.getAttribute("user")==null) {
		 * response.sendRedirect(request.getContextPath()+"/"); return "index"; }
		 */
		return "index";
	}
	@GetMapping("/contact")
	public String contactForm(@ModelAttribute("form") ContactForm form) {
		return "contact";
	}
	@GetMapping("/about")
	public String aboutForm() {
		return "about";
	}
	@GetMapping("/pdf/user/{id}")
	public void generator(HttpServletResponse response,HttpSession session,HttpServletRequest request,@PathVariable("id") long id) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_"+currentDateTime+".pdf";
		response.setHeader(headerkey, headervalue);
		BookingDTO bookList = service.findByPk(id);
		PDFGeneratorVehicle generetorUser = new PDFGeneratorVehicle();
		generetorUser.setB(bookList);
		generetorUser.generate(response);
	}
}
