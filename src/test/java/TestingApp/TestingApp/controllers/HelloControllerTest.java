package TestingApp.TestingApp.controllers;

import TestingApp.TestingApp.Repositories.UserRepo;
import TestingApp.TestingApp.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

       @Autowired
       private MockMvc mockMvc;

       @MockitoBean
       private UserRepo userRepo;

       @Test
       void hello_returnsHelloWorld() throws Exception {
              mockMvc.perform(get("/hello"))
                            .andExpect(status().isOk())
                            .andExpect(content().string("Hello World"));
              System.out.println("Test hello_returnsHelloWorld passed.");
       }

       @Test
       void helloFirstName_returnsGreeting_whenUserExists() throws Exception {
              // Arrange
              User user = new User();
              user.setFirstName("Dustin");
              user.setLastName("van de Veerdonk");

              Mockito.when(userRepo.findByFirstName(eq("Dustin")))
                            .thenReturn(Optional.of(user));

              // Act & Assert
              mockMvc.perform(get("/hello/Dustin"))
                            .andExpect(status().isOk())
                            .andExpect(content().string("Hello, Dustin van de Veerdonk!"));
              System.out.println("Test helloFirstName_returnsGreeting_whenUserExists passed.");
       }

       @Test
       void helloFirstName_returnsNotFound_whenUserMissing() throws Exception {
              Mockito.when(userRepo.findByFirstName(eq("NonExistentUser")))
                            .thenReturn(Optional.empty());

              mockMvc.perform(get("/hello/NonExistentUser"))
                            .andExpect(status().isOk())
                            .andExpect(content().string("No User Found"));
              System.out.println("Test helloFirstName_returnsNotFound_whenUserMissing passed.");
       }
}
