package paises.api.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import paises.api.domain.Country;
import paises.api.persistence.CoutryRepository;

@Service
@NoArgsConstructor
public class CountryServiceImpl implements CountryService
{
    @Autowired //para instanciar o repositorio sozinho
    public CoutryRepository repository;


    public Page<ListCountryData>listall(@PageableDefault(size=10,sort={"name"}) Pageable pageable) //método para mostrar paises, e ordenar os países por qualquer uma das suas propriedades.
    {
        return repository.findAll(pageable).map(ListCountryData::new);
    }

    public Country create(@Valid CreateCountryData data)
    {
        Country country=new Country(data);
        repository.save(country);
        return country;
    }

    public Country update(Long id,ModifiyCountryData data)
    {
        Country country =repository.getReferenceById(id);
        country.modifiycoutrydata(data);
        Country saved=repository.save(country);
        return saved;
    }

    public void delete(Long id)
    {
        repository.deleteById(id);
    }

    public Country view(Long id) { return repository.getReferenceById(id);}

    //records
    public record CreateCountryData(@NotBlank String name, @NotBlank String capital, @NotBlank String region, @NotBlank String subregion, @NotBlank String area)//uma classe imutavel , usada para enviar ou receber dados
    {

    }

    public record ListCountryData(long id, String name, String capital, String area, String region)
    {
        public ListCountryData(Country country )
        {
            this(country.getId(),country.getName(),country.getCapital(),country.getArea(),country.getRegion());
        }
    }

    public record ModifiyCountryData(String capital, String area)
    {

    }

    public record ViewCountry(Long id, String name, String capital, String region, String subregion, String area)
    {
        public ViewCountry(Country country)
        {
            this(country.getId(),country.getName(),country.getCapital(),country.getRegion(),country.getSubregion(),country.getArea());
        }
    }
}