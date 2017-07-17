package com.monitise.alkin.core;

import javax.servlet.http.HttpSession;

public class SessionCheck {

    public static boolean check(HttpSession httpSession) {
        return httpSession.isNew() || httpSession.getAttribute(Constants.USER_ID) == null;
    }

}
