package com.springapp.mvc.controller;

import com.springapp.mvc.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.springapp.mvc.model.*;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BasicController {

    @Autowired PersonDAO personDAO;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

    @RequestMapping(value="/Login", method = RequestMethod.GET)
    public String login(
            HttpServletRequest request,
            ModelMap model,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password) {



        model.addAttribute("message", "Hello world!");

        String url;
        boolean success = false;


            // find user/pass combo
            Person user = personDAO.getPersonWithUsernameAndPassword(username, password);

            // set user in session data
            if (user == null) {
                // failed login
                url = "login";

            } else {
                // success
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                request.getSession().setAttribute("user", user);
                // get homepage
                url = "index";
            }


        return url;
    }

}