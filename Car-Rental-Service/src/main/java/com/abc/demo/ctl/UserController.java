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
import org.springframework.web.bind.annotation.RequestParam;

import com.abc.demo.dao.UserRepository;
import com.abc.demo.dto.UserDTO;
import com.abc.demo.dto.VehicleDTO;
import com.abc.demo.exception.DuplicateRecordException;
import com.abc.demo.exception.RecordNotFoundException;
import com.abc.demo.form.ChangePasswordForm;
import com.abc.demo.form.LoginForm;
import com.abc.demo.form.MyProfileForm;
import com.abc.demo.form.UserForm;
import com.abc.demo.form.VehicleForm;
import com.abc.demo.service.UserService;

@Controller
public class UserController extends BaseCtl {
	protected static final String OP_SIGNIN = "SignIn";
	protected static final String OP_SIGNUP = "SignUp";
	protected static final String OP_LOGOUT = "Logout";
	protected static final String CUSTOMER_ROLEID = "2";
	@Autowired
	private UserService service;

	@GetMapping("/login")
	public String displayLogin(@ModelAttribute("form") LoginForm form, HttpSession session, Model model) {
		if (session.getAttribute("user") != null) {
			session.invalidate();
			model.addAttribute("message", "You have logout Successfully!!!");
		}
		return "login";
	}

	@GetMapping("/register")
	public String displayRegForm(@ModelAttribute("form") UserForm form) {
		return "register";
	}

	@PostMapping("/register")
	public String submitRegForm(@Valid @ModelAttribute("form") UserForm form, BindingResult bindingResult, Model model,
			HttpServletRequest request) {
		System.out.println("in::::" + form.getPassword() + form.getGender() + form.getDob() + form.getFirstName()
				+ form.getLastName() + form.getLogin() + form.getContactNo());
		if (bindingResult.hasErrors()) {
			System.out.println("ikmkn" + bindingResult.getAllErrors());
			return "register";
		}
		System.out.println("in");
		try {
			if (OP_SIGNUP.equalsIgnoreCase(form.getOperation())) {
				UserDTO entity = (UserDTO) populateDTO(form.getDTO(), request);
				entity.setRoleId(CUSTOMER_ROLEID); // for customer
				service.register(entity);
				model.addAttribute("message", "Register Successfully");
				return "register";
			}
		} catch (DuplicateRecordException e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		System.out.println("out");
		return "register";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("form") LoginForm form, BindingResult bindingResult, Model model,
			HttpSession session) {
		// check
		System.out.println("form :  " + form.getLogin() + "password   :" + form.getPassword());
		if (bindingResult.hasErrors()) {
			return "login";
		}

		UserDTO bean = service.authenticate(form.getLogin(), form.getPassword());
		System.out.println("beannnnn  " + bean);
		if (bean != null) {
			System.out.println("here" + bean.toString());
			session.setAttribute("user", bean);
			return "redirect:/";
		}
		model.addAttribute("error", "Login and Password not matched!!");

		return "login";

	}

	@GetMapping("/admin/users")
	public String home(Model m, HttpServletRequest request) {
		return getAllUsers(1, m, request);

	}

	@RequestMapping(value = "/admin/users/{pageNo}", method = RequestMethod.GET)
	public String getAllUsers(@PathVariable(value = "pageNo") int pageNo, Model model, HttpServletRequest request) {
		String keyword = request.getParameter("firstName");
		System.out.println("keyword" + keyword);
		int pageSize = 3;
		Page<UserDTO> page = service.findByPage(pageNo, pageSize);
		List<UserDTO> list = page.getContent();
		if (keyword != null) {
			System.out.println("in::::::");
			List<UserDTO> list1 = service.getByKeyword(keyword);
			model.addAttribute("userList", list1);
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("recordPerPage", page.getNumberOfElements());
			model.addAttribute("totalRecords", page.getTotalElements());
			model.addAttribute("index", 1);
			return "userList";
		}

		model.addAttribute("userList", list);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("recordPerPage", page.getNumberOfElements());
		model.addAttribute("totalRecords", page.getTotalElements());
		model.addAttribute("index", 1);
		return "userList";

	}

	@GetMapping("/admin/users/delete/{id}")
	public String deleteUser(@PathVariable long id, Model m) {
		System.out.println("hello");
		service.deleteUserById(id);
		m.addAttribute("message", "Successfully Deleted");
		return "redirect:/admin/users";
	}

	@GetMapping("/admin/users/view/{id}")
	public String display(@PathVariable("id") Long id, @ModelAttribute("form") UserForm form, HttpSession session,
			Model model) throws RecordNotFoundException {
		System.out.println("inside display");
		if (form.getId() > 0) {
			UserDTO bean = service.getUserById(id);
			form.populate(bean);
		}
		return "userView";
	}

	@RequestMapping(value = "/login/changepassword", method = RequestMethod.GET)
	public String displayChangePassword(@ModelAttribute("form") ChangePasswordForm form, Model model) {
		return "changePassword";
	}

	@RequestMapping(value = "/login/changepassword", method = RequestMethod.POST)
	public String submitChangePassword(HttpSession session, @ModelAttribute("form") @Valid ChangePasswordForm form,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "changePassword";
		}
		if (form.getNewPassword().equalsIgnoreCase(form.getConfirmPassword())) {

			UserDTO dto = (UserDTO) session.getAttribute("user");
			dto = service.findByPk(dto.getId());

			if (service.changePassword(dto.getId(), form.getOldPassword(), form.getNewPassword())) {
				model.addAttribute("message", "Password changed Successfully");
			} else {
				model.addAttribute("error", "Old Passowors Does not Matched");
			}
		} else {
			model.addAttribute("error", "New Password and confirm password does not matched");
		}
		return "changePassword";
	}

	@RequestMapping(value = "/login/profile", method = RequestMethod.GET)
	public String displayProfile(HttpSession session, @ModelAttribute("form") MyProfileForm form, Model model) {
		UserDTO entity = (UserDTO) session.getAttribute("user");
		System.out.println("form:   "+form.getLastName());
		form.populate(entity);

		System.out.println("/Myprofile" + entity.getLastName());
		return "myprofile";
	}

	@RequestMapping(value = "/login/profile", method = RequestMethod.POST)
	public String submitProfile(HttpSession session, @ModelAttribute("form") @Valid MyProfileForm form,
			BindingResult bindingResult, @RequestParam(required = false) String operation, Model model,
			HttpServletRequest request) throws DuplicateRecordException {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/login/profile";
		}

		if (bindingResult.hasErrors()) {
			return "myprofile";
		}
		UserDTO entity = (UserDTO) session.getAttribute("user");
		entity = service.findByPk(entity.getId());
		entity.setFirstName(form.getFirstName());
		entity.setLastName(form.getLastName());
		entity.setLogin(form.getLogin());
		entity.setContactNo(form.getContactNo());
		entity.setDob(form.getDob());
		service.update(entity, entity.getId());
		model.addAttribute("message", "Profile Update successfully");
		return "myprofile";
	}
}
