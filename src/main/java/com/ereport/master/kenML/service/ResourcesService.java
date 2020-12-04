package com.ereport.master.kenML.service;

import com.ereport.master.kenML.repository.ResourcesRepo;
import org.springframework.stereotype.Service;

@Service
public class ResourcesService {
    private final ResourcesRepo resourcesRepo;

    public ResourcesService(ResourcesRepo resourcesRepo) {
        this.resourcesRepo = resourcesRepo;
    }


}
