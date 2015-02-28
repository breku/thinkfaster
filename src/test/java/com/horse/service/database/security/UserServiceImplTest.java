package com.horse.service.database.security;

import com.horse.dao.UserDao;
import com.horse.model.Registration;
import com.horse.model.User;
import com.horse.utils.TestBuilder;
import com.horse.utils.security.RoleName;
import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private UserServiceImpl uut;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        uut = new UserServiceImpl(passwordEncoder,userDao);

    }

    @Test
    public void shouldAddAuthority(){
        // given
        User user = TestBuilder.createUser(1, "user1");
        Assertions.assertThat(user.getUserRoles()).hasSize(1);
        Assertions.assertThat(user.getAuthorities()).hasSize(1);

        // when
        uut.grantAuthorityToUser(user, RoleName.ROLE_ADMIN);

        // then
        Assertions.assertThat(user.getUserRoles()).hasSize(2);
        Assertions.assertThat(user.getAuthorities()).hasSize(2);

    }


    @Test
    public void shouldNotAddAuthority(){
        // given
        User user = TestBuilder.createUser(1, "user1");
        Assertions.assertThat(user.getUserRoles()).hasSize(1);
        Assertions.assertThat(user.getAuthorities()).hasSize(1);

        // when
        uut.grantAuthorityToUser(user, RoleName.ROLE_USER);

        // then
        Assertions.assertThat(user.getUserRoles()).hasSize(1);
        Assertions.assertThat(user.getAuthorities()).hasSize(1);

    }



    @Test
    public void shouldRemoveAuthority(){
        // given
        User user = TestBuilder.createUser(1, "user1");
        Assertions.assertThat(user.getUserRoles()).hasSize(1);
        Assertions.assertThat(user.getAuthorities()).hasSize(1);

        // when
        uut.removeAuthorityFromUser(user, RoleName.ROLE_USER);

        // then
        Assertions.assertThat(user.getUserRoles()).hasSize(0);
        Assertions.assertThat(user.getAuthorities()).hasSize(0);

    }


    @Test
    public void shouldNotRemoveAuthority(){
        // given
        User user = TestBuilder.createUser(1, "user1");
        Assertions.assertThat(user.getUserRoles()).hasSize(1);
        Assertions.assertThat(user.getAuthorities()).hasSize(1);

        // when
        uut.removeAuthorityFromUser(user, RoleName.ROLE_ADMIN);

        // then
        Assertions.assertThat(user.getUserRoles()).hasSize(1);
        Assertions.assertThat(user.getAuthorities()).hasSize(1);

    }


    @Test
    public void shouldEncodePassword(){
        // given
        Registration registration= TestBuilder.createRegistrationObject("user","password");

        // when
        uut.registerNewUser(registration);

        // then
        Mockito.verify(passwordEncoder,Mockito.times(1)).encode("password");
    }

}