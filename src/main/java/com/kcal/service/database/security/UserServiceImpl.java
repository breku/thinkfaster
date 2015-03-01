package com.kcal.service.database.security;

import com.google.common.collect.Sets;
import com.kcal.dao.UserDao;
import com.kcal.model.Registration;
import com.kcal.model.User;
import com.kcal.model.UserAuthority;
import com.kcal.service.database.AbstractRootService;
import com.kcal.utils.security.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.logging.Logger;

/**
 * User: Breku
 * Date: 2014-09-15
 */
@Service
public class UserServiceImpl extends AbstractRootService<User, UserDao> implements UserService, UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserDao dao, PasswordEncoder passwordEncoder) {
        super(dao);
        this.passwordEncoder = passwordEncoder;
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
                Sets.newHashSet(new UserAuthority(RoleName.ROLE_USER.name())));
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

    public void grantAuthorityToUser(User user, RoleName roleName) {
        Set<UserAuthority> authorities = user.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(roleName.name())) {
                LOGGER.warning(String.format("User %s already has this authority: %s", user, roleName));
                return;
            }
        }
        UserAuthority newAuthority = new UserAuthority(roleName.name());
        authorities.add(newAuthority);
        update(user, "authorities", authorities);
    }

    public void removeAuthorityFromUser(User user, RoleName roleName) {
        Set<UserAuthority> authorities = user.getAuthorities();
        for (UserAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(roleName.name())) {
                authorities.remove(grantedAuthority);
                update(user, "authorities", authorities);
                return;
            }
        }
    }

    @Override
    public User findByName(String name) {
        return dao.findByUsername(name);
    }
}
