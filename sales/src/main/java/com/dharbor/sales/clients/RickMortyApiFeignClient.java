package com.dharbor.sales.clients;

import com.dharbor.sales.model.rest.Character;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "rickmorty-api", url = "${service.external.api.rickmorty.url}")
public interface RickMortyApiFeignClient {

    @GetMapping("/character/{id}")
    Character findById(@PathVariable Integer id);

}
