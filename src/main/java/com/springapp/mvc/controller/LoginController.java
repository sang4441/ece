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
	public String printWelcome(ModelMap model, HttpServletRequest request) {
        model.addAttribute("login", request.getAttribute("login"));
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
            String status = (personDAO.checkLogin(username, password) == 1? "success" : "fail");

            HttpSession session = request.getSession();

            // set user in session data
            if (status == "fail") {
                // failed login
                // error message required

                String err_msg = "Please check again for your login credentials";
                session.setAttribute("login", err_msg);
                request.getSession().setAttribute("login", err_msg);
                return "redirect:/";

            } else {
                // success
                Person user = personDAO.getPersonWithUsernameAndPassword(username, password);
                session.setAttribute("user", user);
                session.setAttribute("login", "");
                request.getSession().setAttribute("login", "");
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