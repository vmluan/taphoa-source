package com.luanvm.coffee.web.controller.admin;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.luanvm.coffee.domain.User;
import com.luanvm.coffee.service.UserService;
import com.luanvm.coffee.web.form.GenericGrid;
import com.luanvm.coffee.web.form.Message;
import com.luanvm.coffee.web.util.UrlUtil;

@RequestMapping("/admin/users")
@Controller
public class UserController {

	final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	MessageSource messageSource;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel) {
		logger.info("Listing users");

		List<User> users = userService.findAll();
		uiModel.addAttribute("users", users);

		logger.info("No. of users: " + users.size());

		return "users/list";
	}

	@RequestMapping(value = "/listgrid", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public GenericGrid<User> listGrid(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sortBy,
			@RequestParam(value = "sord", required = false) String order) {

		logger.info("Listing users for grid with page: {}, rows: {}", page,
				rows);
		logger.info("Listing users for grid with sort: {}, order: {}", sortBy,
				order);

		// Process order by
		Sort sort = null;
		String orderBy = sortBy;
		

		if (orderBy != null && order != null) {
			if (order.equals("desc")) {
				sort = new Sort(Sort.Direction.DESC, orderBy);
			} else
				sort = new Sort(Sort.Direction.ASC, orderBy);
		}

		// Constructs page request for current page
		// Note: page number for Spring Data JPA starts with 0, while jqGrid
		// starts with 1
		PageRequest pageRequest = null;

		if (sort != null) {
			pageRequest = new PageRequest(page - 1, rows, sort);
		} else {
			pageRequest = new PageRequest(page - 1, rows);
		}

		Page<User> userPage = userService.findAllByPage(pageRequest);

		// Construct the grid data that will return as JSON data
		GenericGrid<User> userGrid = new GenericGrid<User>();

		userGrid.setCurrentPage(userPage.getNumber() + 1);
		userGrid.setTotalPages(userPage.getTotalPages());
		userGrid.setTotalRecords(userPage.getTotalElements());
		userGrid.setEntityData(Lists.newArrayList(userPage.iterator()));

		return userGrid;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer id, Model uiModel) {
        User user = userService.findById(id);
		uiModel.addAttribute("user", user);
        return "users/show";
    }
	
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
        uiModel.addAttribute("user", userService.findById(id));
        return "users/update";
	}
	
	@RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model uiModel) {
        uiModel.addAttribute("user", new User());
        return "users/create";
	}
	
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
	@Transactional
    public String update(@ModelAttribute("user") User user, @PathVariable Integer id, Model uiModel, 
    		HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, @RequestParam(value="password", required=true) String password ) {
		logger.info("Updating user");
        uiModel.asMap().clear();
        user.setUserid(id);
        
        String oldPassword = userService.findById(id).getPassword();
        if (!oldPassword.equals(password))
        {
        	user.setPassword(DigestUtils.md5Hex(password));	
        }
        
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("user_save_success", new Object[]{}, locale)));        

        userService.save(user);
        
        return "redirect:/admin/users/" + UrlUtil.encodeUrlPathSegment(user.getUserid().toString(), httpServletRequest);
    }
	
	@RequestMapping(params = "form", method = RequestMethod.POST)
    public String create(@Valid User user, BindingResult bindingResult, Model uiModel, 
    		HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale,
    		@RequestParam(value="password", required=true) String password) {
		logger.info("Creating user");
        if (bindingResult.hasErrors()) {
			uiModel.addAttribute("message", new Message("error", messageSource.getMessage("user_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("user", user);
            return "users/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("user_save_success", new Object[]{}, locale)));

        user.setPassword(DigestUtils.md5Hex(password));
        logger.info("User id: " + user.getUserid());
        
        userService.save(user);
        return "redirect:/admin/users/" + UrlUtil.encodeUrlPathSegment(user.getUserid().toString(), httpServletRequest);
    }	
}
