package paises.api.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import paises.api.domain.Pais;
import paises.api.persistence.PaisRepository;

@Service
@NoArgsConstructor
public class CoutryServiceImpl implements CoutryService
{
    @Autowired //para instanciar o repositorio sozinho
    public PaisRepository repository;


    public Page<ListCountryData>listall(@PageableDefault(size=10,sort={"nome"}) Pageable pageable) //método para mostrar paises, e ordenar os países por qualquer uma das suas propriedades.
    {
        return repository.findAll(pageable).map(ListCountryData::new);
    }

    public Pais create(@Valid CreateCountryData data)
    {
        Pais pais=new Pais(data);
        repository.save(pais);
        return pais;
    }

    public Pais update(Long id,ModifiyCountryData data)
    {
        Pais pais =repository.getReferenceById(id);
        pais.modifiycoutrydata(data);
        Pais saved=repository.save(pais);
        return saved;
    }

    public void delete(Long id)
    {
        repository.deleteById(id);
    }

    public Pais view(Long id)
    {
        return repository.getReferenceById(id);
    }
    public record CreateCountryData(@NotBlank String name, @NotBlank String capital, @NotBlank String region, @NotBlank String subregion, @NotBlank String area)//uma classe imutavel , usada para enviar ou receber dados
    {

    }

    public record ListCountryData(long id, String name, String capital, String area, String region)
    {
        public ListCountryData(Pais pais )
        {
            this(pais.getId(),pais.getName(),pais.getCapital(),pais.getArea(),pais.getRegion());
        }
    }

    public record ModifiyCountryData(String capital, String area)
    {

    }

    public record ViewCountry(Long id, String name, String capital, String region, String subregion, String area)
    {
        public ViewCountry(Pais pais)
        {
            this(pais.getId(),pais.getName(),pais.getCapital(),pais.getRegion(),pais.getSubregion(),pais.getArea());
        }
    }
}
