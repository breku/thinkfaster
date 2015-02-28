package com.kcal.validator;

import com.kcal.controller.security.RegistrationController;
import com.kcal.model.Registration;
import com.kcal.service.database.security.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class RegisterUserValidatorTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    private RegistrationController controller;

    @Before
    public void init() {
        controller = new RegistrationController(userService,new RegisterUserValidator(userService));
        mockMvc = MockMvcBuilders.<StandaloneMockMvcBuilder>standaloneSetup(controller).build();
        Mockito.doNothing().when(userService).registerNewUser(Mockito.any(Registration.class));
    }

    @Test
    public void givenCorrectInput_shouldRedirect() throws Exception {
        Registration registration = createRegistrationModel("Jakub11", "aaa@aaa.com", "Password123", "Password123");
        performRequest(registration, status().is3xxRedirection());
        Mockito.verify(userService, Mockito.times(1)).registerNewUser(Mockito.any(Registration.class));
    }

    /**
     * Status should be 200, the user won't be saved in DB. There website will be shown again with errors attribute
     *
     * @throws Exception
     */
    @Test
    public void givenIncorrectInputs_shouldBeOk() throws Exception {
        List<String[]> incorrectInputList = Arrays.asList(
                new String[]{"", "aaa@aaa.com", "Password123", "Password123"},
                new String[]{"12345", "aaa@aaa.com", "Password123", "Password123"},
                new String[]{"123456", "", "Password123", "Password123"},
                new String[]{"Jakub11", "aaaaaa", "Password123", "Password123"},
                new String[]{"Jakub11", "aaa@aaa", "Password1234", "Password123"},
                new String[]{"Jakub11", "aaa@aaa", "asdf", "Password123"},
                new String[]{"Jakub11", "aaa@aaa", "Password", "Password"},
                new String[]{"Jakub11", "aaa@aaa", "password123", "password123"},
                new String[]{"Jakub11", "aaa@aaa", "", "Password123"},
                new String[]{"Jakub11", "aaa@aaa", "Password123", ""},
                new String[]{"Jakub11", "aaa@aaa", "Password123", "Password123"});


        for (String[] table : incorrectInputList) {
            Registration registration = createRegistrationModel(table[0], table[1], table[2], table[3]);
            performRequest(registration, status().isOk());
            Mockito.verify(userService, Mockito.never()).registerNewUser(Mockito.any(Registration.class));
        }


    }

    private void performRequest(Registration registration, ResultMatcher expectedStatus) throws Exception {
        this.mockMvc.perform(post("/register")
                .sessionAttr("registrationModelAttribute", registration)
                .param("username", registration.getUsername())
                .param("email", registration.getEmail())
                .param("password", registration.getPassword())
                .param("confirmPassword", registration.getConfirmPassword()))
                .andDo(print())
                .andExpect(expectedStatus);
    }

    private Registration createRegistrationModel(String username, String email, String password, String confirmPassword) {
        Registration registration = new Registration();
        registration.setUsername(username);
        registration.setEmail(email);
        registration.setPassword(password);
        registration.setConfirmPassword(confirmPassword);
        return registration;
    }

}