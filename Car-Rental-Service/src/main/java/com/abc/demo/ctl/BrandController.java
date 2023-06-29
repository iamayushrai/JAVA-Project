package com.abc.demo.ctl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abc.demo.dto.BrandDTO;
import com.abc.demo.dto.UserDTO;
import com.abc.demo.exception.DuplicateRecordException;
import com.abc.demo.exception.RecordNotFoundException;
import com.abc.demo.form.BrandForm;
import com.abc.demo.service.BrandService;

@Controller
public class BrandController extends BaseCtl {
	
	@Autowired
	private BrandService brandService;

	@GetMapping("/admin/brand/add")
	public String displayBrandForm(@ModelAttribute("form") BrandForm form) {
		return "brand";
	}
	@PostMapping("/admin/brand/add")
	public String addBrandForm(@Valid @ModelAttribute("form") BrandForm form, BindingResult bindingResult,Model model,HttpServletRequest request) throws RecordNotFoundException {
		System.out.println("here");
		if(bindingResult.hasErrors()) {
			return "brand";
		}try {
		if(OP_SAVE.equalsIgnoreCase(form.getOperation())) {
			  BrandDTO entity = (BrandDTO)populateDTO(form.getDTO(), request);
			  if(entity.getId() > 0) {
				  brandService.update(entity,entity.getId());
				  model.addAttribute("message", "Brand Updated Successfully");
				  return "brand"; 
			  }
			  brandService.register(entity);
			  model.addAttribute("message", "Brand Added Successfully");
			  return "brand";
			}
		}catch (DuplicateRecordException e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		System.out.println("out");
		return "brand";
	}
	@GetMapping("/admin/brands")
	 public String home(Model m,HttpServletRequest request) {
	  return getBrands(1, m,request);
	  
	 }
	@RequestMapping(value = "/admin/brands/{pageNo}",method = RequestMethod.GET)
	public String getBrands(@PathVariable(value = "pageNo") int pageNo, Model model,HttpServletRequest request) {
			String keyword = request.getParameter("carBrand");
			System.out.println("keyword"+keyword);
			int pageSize = 3;
			Page<BrandDTO> page  = brandService.findByPage(pageNo, pageSize);
			List<BrandDTO> list = page.getContent();
			if(keyword!=null) {
			   List<BrandDTO> list1 = brandService.getByKeyword(keyword);
			   model.addAttribute("brandList", list1);
			 //  model.addAttribute("brandList", list);
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("totalPages", page.getTotalPages());
				model.addAttribute("recordPerPage", page.getNumberOfElements());
				model.addAttribute("totalRecords", page.getTotalElements());
				model.addAttribute("index",1);	
			   return "brandList";
			  }
		
		
		model.addAttribute("brandList", list);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("recordPerPage", page.getNumberOfElements());
		model.addAttribute("totalRecords", page.getTotalElements());
		model.addAttribute("index",1);		
		return "brandList";
			
		
	}
	@GetMapping("/admin/brands/delete/{id}")
	 public String deleteBrand(@PathVariable long id, Model m) {
		System.out.println("hello");
	   brandService.deletecBrandById(id);	
	   m.addAttribute("message", "Successfully Deleted");
	   return "redirect:/admin/brands";
	 }
	@GetMapping("/admin/brands/edit/{id}")
	public String display(@PathVariable("id") Long id, @ModelAttribute("form") BrandForm form,
			HttpSession session, Model model) throws RecordNotFoundException {
		System.out.println("inside display");
		if (form.getId() > 0) {
			BrandDTO bean = brandService.getBrandById(id);
			form.populate(bean);
		}
		return "brand";
	}
}
