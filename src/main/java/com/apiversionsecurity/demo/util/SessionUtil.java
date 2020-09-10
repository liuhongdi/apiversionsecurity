package com.apiversionsecurity.demo.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    //得到security所保存的用户
    public static String getCurrentUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();
            //System.out.println(principal);
            if (principal instanceof String) {
                return (String)principal;
            } else if (principal instanceof UserDetails) {
                String currentuser = ((UserDetails) principal).getUsername();
                return currentuser;
            } else {
                //System.out.println("not instanceof UserDetails");
            }
            return null;
        }
        return null;
    }
}
