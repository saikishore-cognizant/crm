package com.crm.service;

import java.util.List;

import com.crm.entity.CustomerProfile;

public interface CustomerProfileService {
	public List<CustomerProfile> getAllProfiles();
	public CustomerProfile getProfileById(Long id);
	public CustomerProfile addOrUpdateProfile(CustomerProfile profile);
	public CustomerProfile updateProfile(Long id, CustomerProfile profile);
	public void deleteProfile(Long id);
	

}
