package paises.api.service;

import jakarta.validation.Valid;
import paises.api.domain.Pais;

public interface CoutryService
{
    Pais create(@Valid CoutryServiceImpl.DadoscriarPais dados);
}
