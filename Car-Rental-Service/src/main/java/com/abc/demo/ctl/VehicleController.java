package com.abc.demo.ctl;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.event.spi.PreLoadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.abc.demo.dto.BrandDTO;
import com.abc.demo.dto.VehicleDTO;
import com.abc.demo.exception.DuplicateRecordException;
import com.abc.demo.exception.RecordNotFoundException;
import com.abc.demo.form.BrandForm;
import com.abc.demo.form.VehicleForm;
import com.abc.demo.service.BrandService;
import com.abc.demo.service.VehicleService;
import com.abc.demo.utility.DataUtility;

@Controller
public class VehicleController extends BaseCtl {

	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private BrandService brandService;
	@ModelAttribute
	public void preLoad(Model model) {
		List<String> seatList = Arrays.asList("Four Seater","Seven Seater","Twelve Seater");
		model.addAttribute("seatList", seatList);
		
		List<BrandDTO> brandList = brandService.list();
		model.addAttribute("brandList", brandList);
	
	}

	@GetMapping("/admin/vehicle/add")
	public String displayVehicleForm(@ModelAttribute("form") VehicleForm form) {
		return "vehicle";
	}
	@PostMapping("/admin/vehicle/add")
	public String addVehicleForm(@Valid @ModelAttribute("form") VehicleForm form, BindingResult bindingResult,Model model,HttpServletRequest request,@RequestParam("vehicleImage") MultipartFile file) throws RecordNotFoundException, IOException {
		System.out.println("here515");
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			return "vehicle";
		}try {
		if(OP_SAVE.equalsIgnoreCase(form.getOperation())) {
			  VehicleDTO entity = (VehicleDTO)populateDTO(form.getDTO(), request);
			  BrandDTO brandDTO = brandService.getBrandById(entity.getBrandId());
			  
			  entity.setBrandId(brandDTO.getId());
			  entity.setBrand(brandService.getBrandById(entity.getBrandId()));
			  entity.setPrice(DataUtility.getLong(form.getPrice()));
			  if(entity.getId() > 0) {
				  entity.setVehicleImage(form.getVehicleImage().getBytes());
				  vehicleService.update(entity,entity.getId());
				  model.addAttribute("message", "Vehicle Updated Successfully");
				  return "vehicle"; 
			  }
			  entity.setVehicleImage(form.getVehicleImage().getBytes());
			  vehicleService.register(entity);
			  model.addAttribute("message", "Vehicle Added Successfully");
			  return "vehicle";
			}
		}catch (DuplicateRecordException e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		System.out.println("out");
		return "vehicle";
	}
	@GetMapping("/admin/vehicles")
	 public String home(Model m,HttpServletRequest request) {
	  return getBrands(1, m,request);
	  
	 }
	@RequestMapping(value = "/admin/vehicles/{pageNo}",method = RequestMethod.GET)
	public String getBrands(@PathVariable(value = "pageNo") int pageNo, Model model,HttpServletRequest request) {
			String keyword = request.getParameter("vehicleName");
			System.out.println("keyword"+keyword);
			int pageSize = 3;
			Page<VehicleDTO> page  = vehicleService.findByPage(pageNo, pageSize);
			List<VehicleDTO> list = page.getContent();
			if(keyword!=null) {
				System.out.println("in::::::");
			   List<VehicleDTO> list1 = vehicleService.getByKeyword(keyword);
			   model.addAttribute("vehicleList", list1);
				model.addAttribute("currentPage", pageNo);
				model.addAttribute("totalPages", page.getTotalPages());
				model.addAttribute("recordPerPage", page.getNumberOfElements());
				model.addAttribute("totalRecords", page.getTotalElements());
				model.addAttribute("index",1);	
			   return "vehicleList";
			  }
		model.addAttribute("vehicleList", list);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("recordPerPage", page.getNumberOfElements());
		model.addAttribute("totalRecords", page.getTotalElements());
		model.addAttribute("index",1);		
		return "vehicleList";
			
		
	}
	@GetMapping("/admin/vehicles/delete/{id}")
	 public String deleteBrand(@PathVariable long id, Model m) {
		System.out.println("hello");
	   vehicleService.deleteVehicleById(id);	
	   m.addAttribute("message", "Successfully Deleted");
	   return "redirect:/admin/vehicles";
	 }
	@GetMapping("/admin/vehicles/edit/{id}")
	public String display(@PathVariable("id") Long id, @ModelAttribute("form") VehicleForm form,
			HttpSession session, Model model) throws RecordNotFoundException {
		System.out.println("inside display");
		if (form.getId() > 0) {
			VehicleDTO bean = vehicleService.getVehicleById(id);
			form.populate(bean);
		}
		return "vehicle";
	}
	@GetMapping("/image")
	 public void showImage(@Param("id") Long id, HttpServletResponse response, VehicleDTO v)
	   throws ServletException, IOException, RecordNotFoundException {
		v = vehicleService.getVehicleById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(v.getVehicleImage());
		response.getOutputStream().close();
	 }
}
