package com.horse.service.database.security;

import com.google.common.collect.Sets;
import com.horse.dao.UserDao;
import com.horse.model.Registration;
import com.horse.model.User;
import com.horse.model.UserRole;
import com.horse.service.database.AbstractRootService;
import com.horse.utils.security.RoleName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;


/**
 * User: Breku
 * Date: 2014-09-15
 */
@Service
public class UserServiceImpl extends AbstractRootService<User, UserDao> implements UserService, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private PasswordEncoder passwordEncoder;



    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserDao userDao) {
        this.passwordEncoder = passwordEncoder;
        this.dao = userDao;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return dao.findByUsername(s);
    }

    public void registerNewUser(Registration registration) {
        String encodedPassword = passwordEncoder.encode(registration.getPassword());
        registration.setPassword(encodedPassword);
        registration.setConfirmPassword(encodedPassword);
        User user = new User(registration.getUsername(), registration.getEmail(), registration.getPassword(), true, true, true, true,
                Sets.<UserRole>newHashSet(new UserRole(RoleName.ROLE_USER.name())));
        save(user);
    }

    @Override
    public User getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return get(user.getId());
    }

    public void grantAuthorityToUser(User user, RoleName roleName) {
        Set<UserRole> userRoles = user.getUserRoles();
        for (UserRole userRole : userRoles) {
            if(userRole.getRoleName().equals(roleName.name())){
                LOGGER.warn("User {} already has this authority: {}", user, roleName);
                return;
            }
        }

        userRoles.add(new UserRole(roleName.name()));
        save(user);
    }

    public void removeAuthorityFromUser(User user, RoleName roleName) {
        Set<UserRole> userRoles = user.getUserRoles();

        Iterator<UserRole> iterator = userRoles.iterator();
        while (iterator.hasNext()){
            UserRole userRole = iterator.next();
            if(userRole.getRoleName().equals(roleName.name())){
                iterator.remove();
                save(user);
                return;
            }
        }
        LOGGER.warn("Trying to remove not existing authority user={} roleName={}", user, roleName);
    }

    @Override
    public User findByName(String name) {
        return dao.findByUsername(name);
    }
}
