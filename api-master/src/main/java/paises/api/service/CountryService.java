package paises.api.service;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import paises.api.domain.Country;

public interface CountryService
{
    Country create(@Valid CountryServiceImpl.CreateCountryData data);
    Page<CountryServiceImpl.ListCountryData> listall(@PageableDefault(size=10,sort={"name"}) Pageable pageable);
    Country update(Long id, CountryServiceImpl.ModifiyCountryData data);
    void delete(Long id);
    Country view(Long id);
}
