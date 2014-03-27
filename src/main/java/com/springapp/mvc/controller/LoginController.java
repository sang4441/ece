package com.springapp.mvc.controller;

import com.springapp.mvc.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.springapp.mvc.model.*;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

@Controller
public class LoginController {

    @Autowired PersonDAO personDAO;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "index";
	}

    @RequestMapping(value="/login_form", method = RequestMethod.GET)
    public String loginForm() {
        return "login_form";
    }

    @RequestMapping(value="/login_action", method = RequestMethod.POST)
    public String login(
            HttpServletRequest request,
            ModelMap model,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password) {

        String role;
            // find user/pass combo
            Person user = personDAO.getPersonWithUsernameAndPassword(username, password);

            // set user in session data
            if (user == null) {
                // failed login
                // error message required
                return "login_form";

            } else {
                // success
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                request.getSession().setAttribute("user", user);
                if (user.getRoleID() == 1) {
                    role = "patient";
                } else if (user.getRoleID() == 2) {
                    role = "doctor";
                } else {
                    role = "staff";
                }
                // get homepage
            }
        return "redirect:"+role+"/dashboard";
    }

    @RequestMapping(value="/log_out", method = RequestMethod.GET)
    public String logOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", new Person());
        return "redirect:";
    }

}