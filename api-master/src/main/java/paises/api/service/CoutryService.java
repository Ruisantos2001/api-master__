package paises.api.service;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import paises.api.domain.Pais;

public interface CoutryService
{
    Pais create(@Valid CoutryServiceImpl.CreateCountryData data);
    Page<CoutryServiceImpl.ListCountryData> listall(@PageableDefault(size=10,sort={"nome"}) Pageable pageable);
    Pais update(Long id, CoutryServiceImpl.ModifiyCountryData data);
    void delete(Long id);
    Pais view(Long id);
}
