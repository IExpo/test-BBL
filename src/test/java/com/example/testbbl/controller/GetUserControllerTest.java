package com.example.testbbl.controller;

import com.example.testbbl.entity.UserEntity;
import com.example.testbbl.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserControllerTest {

    @InjectMocks
    private GetUserController controller;

    @Mock
    private UserRepository userRepository;

    @Test
    void get_success() {
        UserEntity userEntity = new UserEntity();
        List<UserEntity> entityList = new ArrayList<>();

        userEntity.setId(1L);
        userEntity.setName("test name");
        userEntity.setWebsite("name.com");
        entityList.add(userEntity);

        userEntity = new UserEntity();
        userEntity.setId(2L);
        userEntity.setName("2 name");
        userEntity.setWebsite("2.com");
        entityList.add(userEntity);

        when(userRepository.findAll()).thenReturn(entityList);

        ResponseEntity<List<UserEntity>> response = controller.getListUser();

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getFirst().getId());
        assertEquals("test name", response.getBody().getFirst().getName());
        assertEquals("name.com", response.getBody().getFirst().getWebsite());

        assertEquals(2, response.getBody().get(1).getId());
        assertEquals("2 name", response.getBody().get(1).getName());
        assertEquals("2.com", response.getBody().get(1).getWebsite());
    }
}
