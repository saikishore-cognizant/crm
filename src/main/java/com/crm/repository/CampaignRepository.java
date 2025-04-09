package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entity.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {


}