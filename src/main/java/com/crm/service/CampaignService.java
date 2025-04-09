package com.crm.service;

import java.util.List;

import com.crm.entity.Campaign;

public interface CampaignService {
	public List<Campaign> getAllCampaigns();
	public Campaign getCampaignById(Integer id);
	public Campaign addOrUpdateCampaign(Campaign campaign);
	public Campaign updateCampaign(Integer id, Campaign campaign);
	public void deleteCampaign(Integer id);

}
