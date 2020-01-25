package com.evolent.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.evolent.dao.User;
import com.evolent.service.UserServiceImpl;
import com.evolent.validation.UserValConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.evolent.controllers.UserRestController;

public class UserRestControllerTest {

	private MockMvc mockMvc;
	
	@InjectMocks
    UserRestController userRestController;
     
    @Mock
    UserServiceImpl userserviceimpl;
    
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userRestController)
                .build();
    }

    /****** Test getAllUsers() request *******/
    @Test
    public void test_get_all_users() throws Exception {

        List<User> users = Arrays.asList(
        		new User(1, "ABC", "XYZ", "abc@xyz.com","1234567890","Active"),
        		new User(2, "cab", "zyx", "xyz@abc.com","1234567890","Active"));
        when(userserviceimpl.getAllUsers()).thenReturn(users);
        mockMvc.perform(get(UserURIConstants.GET_REST_ALL_USER))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                 .andExpect(jsonPath("$[0].id", is(1)))
                 .andExpect(jsonPath("$[0].firstName", is("ABC")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstName", is("cab")));
        verify(userserviceimpl, times(1)).getAllUsers();
        verifyNoMoreInteractions(userserviceimpl);
    }
    
    /****** Test user fields validations *******/
    @Test
    public void test_get_user_field_validations() throws Exception {
    			User user1 = new User(1, "ABC1", "XYZ2", "abcxyz","1234","Active");
        		User user2 =new User(2, "CAB&", "ZYX@", "xyz","123456789013131","Active");
        List<User> users = Arrays.asList(user1,user2);
        when(userserviceimpl.getAllUsers()).thenReturn(users);
        mockMvc.perform(get(UserURIConstants.GET_REST_ALL_USER))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        		.andExpect(jsonPath("$[0].firstName",is(validate(user1.getFirstName(),UserValConstants.NAME_REGEX))))
        		.andExpect(jsonPath("$[0].lastName", is(validate(user1.getLastName(),UserValConstants.NAME_REGEX))))
        		.andExpect(jsonPath("$[0].email", is(validate(user1.getEmail(),UserValConstants.EMAIL_REGEX))))
        		.andExpect(jsonPath("$[0].phoneNumber", is(validate(user1.getPhoneNumber(),UserValConstants.PHNUMBER_REGEX))))
        		.andExpect(jsonPath("$[1].firstName",is(validate(user2.getFirstName(),UserValConstants.NAME_REGEX))))
        		.andExpect(jsonPath("$[1].lastName", is(validate(user2.getLastName(),UserValConstants.NAME_REGEX))))
        		.andExpect(jsonPath("$[1].email", is(validate(user2.getEmail(),UserValConstants.EMAIL_REGEX))))
        		.andExpect(jsonPath("$[1].phoneNumber", is(validate(user2.getPhoneNumber(),UserValConstants.PHNUMBER_REGEX))));
        verify(userserviceimpl, times(1)).getAllUsers();
        verifyNoMoreInteractions(userserviceimpl);
    }
    
    public String validate(String name, String regex){
    	if(!name.matches(regex)){
    		return name;
    	}
    	return "";
    }

    /****** Test createUser() request *******/
    @Test
    public void test_create_user() throws Exception {
    	User user = new User(1, "ABC", "XYZ", "abc@xyz.com","1234567890","Active");
        mockMvc.perform(
                post(UserURIConstants.CREATE_REST_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
        		.andExpect(status().isCreated());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
   
	/****** Test getUser() request *******/
    @Test
    public void test_get_user() throws Exception {
    	User user = new User(1, "ABC", "XYZ", "abc@xyz.com","1234567890","Active");
        when(userserviceimpl.getUser(1)).thenReturn(user);
        mockMvc.perform(get(UserURIConstants.GET_REST_USER, 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("ABC")));
        verify(userserviceimpl, times(1)).getUser(1);
        verifyNoMoreInteractions(userserviceimpl);
    }
    
    /****** Test updateUser() request *******/
    @Test
    public void test_update_user() throws Exception {
    	User user = new User(1, "ABC", "XYZ", "abc@xyz.com","1234567890","Active");
    	
    	mockMvc.perform(
    			put(UserURIConstants.UPDATE_REST_USER, user.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(user)))
            .andExpect(status().isOk());
    }
    
    /****** Test deleteUser() request *******/
    @Test
    public void test_delete_user() throws Exception {
    	User user = new User(1, "ABC", "XYZ", "abc@xyz.com","1234567890","Active");
        mockMvc.perform(
                delete(UserURIConstants.DELETE_REST_USER, user.getId())
        		.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());	
    }
}
