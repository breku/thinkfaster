package com.kcal.configuration.security;

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * User: Breku
 * Date: 2014-09-26
 */

public class SuccessLoginHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    public SuccessLoginHandler() {
        setAlwaysUseDefaultTargetUrl(true);
    }


}
