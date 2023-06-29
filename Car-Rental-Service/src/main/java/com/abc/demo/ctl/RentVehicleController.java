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

import com.abc.demo.dto.BookingDTO;
import com.abc.demo.dto.BrandDTO;
import com.abc.demo.dto.UserDTO;
import com.abc.demo.dto.VehicleDTO;
import com.abc.demo.exception.DuplicateRecordException;
import com.abc.demo.exception.RecordNotFoundException;
import com.abc.demo.form.BookingForm;
import com.abc.demo.service.BookingService;
import com.abc.demo.service.BrandService;
import com.abc.demo.service.VehicleService;

@Controller
public class RentVehicleController extends BaseCtl {

	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private BookingService bookingService;

	@GetMapping("/user/cars")
	public String viewVehicle(Model model) {
		model.addAttribute("brand", brandService.list());
		model.addAttribute("vehicles", vehicleService.list());
		return "viewCars";
	}

	@GetMapping("/user/brand/{id}")
	public String viewBrandById(Model model, @PathVariable("id") int id) throws RecordNotFoundException {
		model.addAttribute("brand", brandService.list());
		model.addAttribute("vehicles", vehicleService.showAllVehiclesByBrandId(id));
		return "viewCars";
	}

	@GetMapping("/user/view/vehicles/{id}")
	public String viewVehicleById(Model model, @PathVariable("id") long id, HttpSession session)
			throws RecordNotFoundException {
		model.addAttribute("vehicles", vehicleService.getVehicleById(id));
		return "viewCars";
	}

	@GetMapping("/user/book/vehicles/{id}")
	public String showBookVehicle(Model model, @PathVariable("id") Long id, HttpSession session,
			@ModelAttribute("form") BookingForm form) throws RecordNotFoundException {
		UserDTO dto = (UserDTO) session.getAttribute("user");
		session.setAttribute("vehicleId", id);
		model.addAttribute("fname", dto.getFirstName());
		model.addAttribute("login", dto.getLogin());
		model.addAttribute("vehicles", vehicleService.getVehicleById(id));
		return "bookingForm";
	}

	@PostMapping("/user/book/vehicles")
	public String bookVehicle(Model model, HttpSession session, @Valid @ModelAttribute("form") BookingForm form,
			BindingResult bindingResult, HttpServletRequest request) throws RecordNotFoundException {
		UserDTO dto = (UserDTO) session.getAttribute("user");
		model.addAttribute("fname", dto.getFirstName());
		model.addAttribute("login", dto.getLogin());
		System.out.println(session.getAttribute("vehicleId"));
		long vehicleDTO = (Long) session.getAttribute("vehicleId");
		model.addAttribute("vehicles", vehicleService.getVehicleById(vehicleDTO));
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			return "bookingForm";
		}
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
				BookingDTO entity = (BookingDTO) populateDTO(form.getDTO(), request);
				VehicleDTO vehicleDTO2 = vehicleService.getVehicleById(vehicleDTO);
				entity.setVehicleId(vehicleDTO2.getId());
				entity.setVehicle(vehicleService.getVehicleById(entity.getVehicleId()));
				UserDTO userId = (UserDTO) session.getAttribute("user");
				entity.setUser(userId);
				entity.setUserId(userId.getId());
				if (entity.getId() > 0) {
					bookingService.update(entity, entity.getId());
					model.addAttribute("message", "Booking Updated Successfully");
					return "bookingForm";
				}

				bookingService.register(entity);
				model.addAttribute("message",
						"Your Booking is Confirmed! Kindly Note your Booking Id " + entity.getBookingNo());
				return "bookingForm";
			}
		} catch (DuplicateRecordException e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "bookingForm";
	}

	@GetMapping("/admin/user/booking")
	public String home(Model m, HttpServletRequest request) {
		return getBookings(1, m, request);

	}

	@RequestMapping(value = "/user/booking", method = RequestMethod.GET)
	public String getBookingsById(Model model, HttpServletRequest request, HttpSession session) {
		String keyword = request.getParameter("vehicleName");
		System.out.println("keyword" + keyword);
		int pageSize = 3;
		UserDTO dto = (UserDTO) session.getAttribute("user");
		System.out.println(dto.getId());
		
		model.addAttribute("bookingList", bookingService.getAllBookingById(dto.getId()));
		return "userBookingList";

	}

	@RequestMapping(value = "/admin/user/booking/{pageNo}", method = RequestMethod.GET)
	public String getBookings(@PathVariable(value = "pageNo") int pageNo, Model model, HttpServletRequest request) {
		String keyword = request.getParameter("vehicleName");
		System.out.println("keyword" + keyword);
		int pageSize = 3;
		Page<BookingDTO> page = bookingService.findByPage(pageNo, pageSize);
		List<BookingDTO> list = page.getContent();
		model.addAttribute("bookingList", list);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		System.out.println("total pages"+page.getTotalPages());
		model.addAttribute("recordPerPage", page.getNumberOfElements());
		model.addAttribute("totalRecords", page.getTotalElements());
		model.addAttribute("index", 1);
		return "bookingList";

	}
}
