package paises.api.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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
    private PaisRepository repository;

    public CoutryServiceImpl(PaisRepository repository) {
        this.repository = repository;
    }
    public record DadoscriarPais(@NotBlank String name, @NotBlank String capital, @NotBlank String region, @NotBlank String subregion, @NotBlank String area)//uma classe imutavel , usada para enviar ou receber dados
    {

    }

    public record DadosListagemPais(long id, String name, String capital, String area, String region)
    {
        public DadosListagemPais(Pais pais )
        {
            this(pais.getId(),pais.getName(),pais.getCapital(),pais.getArea(),pais.getRegion());
        }
    }

    public record DadosmodificarPais(String capital, String area)
    {

    }

    public record DadosDetalhadosPais(Long id, String name, String capital, String region, String subregion, String area)
    {
        public DadosDetalhadosPais(Pais pais)
        {
            this(pais.getId(),pais.getName(),pais.getCapital(),pais.getRegion(),pais.getSubregion(),pais.getArea());
        }
    }

    public Page<DadosListagemPais>listall(@PageableDefault(size=10,sort={"nome"}) Pageable paginacao) //método para mostrar paises, e ordenar os países por qualquer uma das suas propriedades.
    {
         return repository.findAll(paginacao).map(DadosListagemPais::new);
    }

    public Pais create(@Valid DadoscriarPais dados)
    {
        Pais pais=new Pais(dados);
       return repository.save(pais);
    }

    public Pais update(Long id,DadosmodificarPais dados)
    {
        Pais pais =repository.getReferenceById(id);
        pais.modificardados(dados);
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
}
