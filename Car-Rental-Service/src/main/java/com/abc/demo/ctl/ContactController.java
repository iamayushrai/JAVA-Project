package com.abc.demo.ctl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.abc.demo.dto.BrandDTO;
import com.abc.demo.dto.ContactDTO;
import com.abc.demo.exception.DuplicateRecordException;
import com.abc.demo.exception.RecordNotFoundException;
import com.abc.demo.form.BrandForm;
import com.abc.demo.form.ContactForm;
import com.abc.demo.service.ContactService;

@Controller
public class ContactController extends BaseCtl {

	@Autowired
	private ContactService service;
	
	@PostMapping("/contact")
	public String sendMessage(@Valid @ModelAttribute("form") ContactForm form, BindingResult bindingResult,Model model,HttpServletRequest request) throws RecordNotFoundException {
		System.out.println("here");
		if(bindingResult.hasErrors()) {
			return "contact";
		}if(OP_SAVE.equalsIgnoreCase(form.getOperation())) {
			  ContactDTO entity = (ContactDTO)populateDTO(form.getDTO(), request);
			  service.register(entity);
			  model.addAttribute("message", "Message Send Successfully");
			  return "contact";
			}
		System.out.println("out");
		return "contact";
	}
}
