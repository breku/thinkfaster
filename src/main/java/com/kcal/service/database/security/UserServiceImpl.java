package com.kcal.service.database.security;

import com.google.common.collect.Sets;
import com.kcal.annotation.Loggable;
import com.kcal.dao.UserDao;
import com.kcal.model.Registration;
import com.kcal.model.User;
import com.kcal.model.UserProfile;
import com.kcal.model.json.UserProfileSliderJson;
import com.kcal.model.json.XEditableForm;
import com.kcal.service.database.AbstractRootService;
import com.kcal.service.database.UserProfileService;
import com.kcal.utils.security.RoleName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * User: Breku
 * Date: 2014-09-15
 */
@Service
public class UserServiceImpl extends AbstractRootService<User, UserDao> implements UserService, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private PasswordEncoder passwordEncoder;

    private UserProfileService userProfileService;

    @Autowired
    public UserServiceImpl(UserDao dao, PasswordEncoder passwordEncoder, UserProfileService userProfileService) {
        super(dao);
        this.passwordEncoder = passwordEncoder;
        this.userProfileService = userProfileService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return dao.findByUsername(s);
    }

    @Loggable
    public void registerNewUser(Registration registration) {
        String encodedPassword = passwordEncoder.encode(registration.getPassword());
        registration.setPassword(encodedPassword);
        registration.setConfirmPassword(encodedPassword);
        User user = new User(registration.getUsername(), registration.getEmail(), registration.getPassword(), true, true, true, true,
                Sets.<GrantedAuthority>newHashSet(new SimpleGrantedAuthority(RoleName.ROLE_USER.name())));
        save(user);
    }

    public User getUser(long userId) {
        return dao.get(userId);
    }

    @Override
    public User getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return get(user.getId());
    }

    @Loggable
    public void grantAuthorityToUser(User user, RoleName roleName) {
        Set<GrantedAuthority> authorities = user.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(roleName.name())) {
                LOGGER.warn(String.format("User %s already has this authority: %s", user, roleName));
                return;
            }
        }
        SimpleGrantedAuthority newAuthority = new SimpleGrantedAuthority(roleName.name());
        authorities.add(newAuthority);
        update(user, "authorities", authorities);
    }

    @Loggable
    public void removeAuthorityFromUser(User user, RoleName roleName) {
        Set<GrantedAuthority> authorities = user.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(roleName.name())) {
                authorities.remove(grantedAuthority);
                update(user, "authorities", authorities);
                return;
            }
        }
    }

    @Override
    public void updateProfile(UserProfileSliderJson json) {
        User user = getCurrentUser();
        userProfileService.updateProfile(user, json);
        update(user, "userProfile", user.getUserProfile());
    }

    @Override
    public void updateProfile(XEditableForm form) {
        User user = getCurrentUser();
        userProfileService.updateProfile(user, form);
        update(user, "userProfile", user.getUserProfile());

    }

    @Override
    public UserProfile getUserProfile() {
        return getCurrentUser().getUserProfile();
    }


    @Override
    public User findByName(String name) {
        return dao.findByUsername(name);
    }
}
