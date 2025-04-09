package com.crm.controller;

import com.crm.entity.CustomerProfile;
import com.crm.service.impl.CustomerProfileServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerProfileControllerTest {

    @Mock
    private CustomerProfileServiceImpl service;

    @InjectMocks
    private CustomerProfileController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProfiles() {
        List<CustomerProfile> profiles = Arrays.asList(new CustomerProfile(), new CustomerProfile());
        when(service.getAllProfiles()).thenReturn(profiles);

        ResponseEntity<List<CustomerProfile>> response = controller.getAllProfiles();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(profiles, response.getBody());
    }

    @Test
    public void testGetProfileById() {
        CustomerProfile profile = new CustomerProfile();
        when(service.getProfileById(1L)).thenReturn(profile);

        ResponseEntity<CustomerProfile> response = controller.getProfileById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(profile, response.getBody());
    }

    @Test
    public void testAddOrUpdateProfile() {
        CustomerProfile profile = new CustomerProfile();
        when(service.addOrUpdateProfile(profile)).thenReturn(profile);

        ResponseEntity<CustomerProfile> response = controller.addOrUpdateProfile(profile);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(profile, response.getBody());
    }

    @Test
    public void testUpdateProfile() {
        CustomerProfile profile = new CustomerProfile();
        when(service.getProfileById(1L)).thenReturn(profile);
        when(service.addOrUpdateProfile(profile)).thenReturn(profile);

        ResponseEntity<CustomerProfile> response = controller.updateProfile(1L, profile);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(profile, response.getBody());
    }

    @Test
    public void testDeleteProfile() {
        CustomerProfile profile = new CustomerProfile();
        when(service.getProfileById(1L)).thenReturn(profile);
        doNothing().when(service).deleteProfile(1L);

        ResponseEntity<Void> response = controller.deleteProfile(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}