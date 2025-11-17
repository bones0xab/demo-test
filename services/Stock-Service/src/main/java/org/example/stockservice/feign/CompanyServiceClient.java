package org.example.stockservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "COMPANY-SERVICE")
public interface CompanyServiceClient {


    @PutMapping("/api/companies/{id}/price")
    void updateCompanyPrice(@PathVariable("id") Long id,
                            @RequestParam("newPrice") Double newPrice);

}