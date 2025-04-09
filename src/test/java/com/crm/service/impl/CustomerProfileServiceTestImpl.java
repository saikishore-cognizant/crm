package com.crm.service.impl;

import com.crm.entity.CustomerProfile;
import com.crm.repository.CustomerProfileRepository;
import com.crm.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CustomerProfileServiceTestImpl {

    @Mock
    private CustomerProfileRepository repository;

    @InjectMocks
    private CustomerProfileServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProfiles() {
        List<CustomerProfile> profiles = Arrays.asList(new CustomerProfile(), new CustomerProfile());
        when(repository.findAll()).thenReturn(profiles);

        List<CustomerProfile> result = service.getAllProfiles();
        assertEquals(profiles, result);
    }

    @Test
    public void testGetProfileById() {
        CustomerProfile profile = new CustomerProfile();
        when(repository.findById(1L)).thenReturn(Optional.of(profile));

        CustomerProfile result = service.getProfileById(1L);
        assertEquals(profile, result);
    }

    @Test
    public void testGetProfileByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getProfileById(1L));
    }

    @Test
    public void testAddOrUpdateProfile() {
        CustomerProfile profile = new CustomerProfile();
        when(repository.save(profile)).thenReturn(profile);

        CustomerProfile result = service.addOrUpdateProfile(profile);
        assertEquals(profile, result);
    }

    @Test
    public void testDeleteProfile() {
        CustomerProfile profile = new CustomerProfile();
        when(repository.findById(1L)).thenReturn(Optional.of(profile));
        doNothing().when(repository).deleteById(1L);

        service.deleteProfile(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}