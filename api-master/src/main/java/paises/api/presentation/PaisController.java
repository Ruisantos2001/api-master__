package paises.api.presentation;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paises.api.domain.Pais;
import paises.api.service.CoutryServiceImpl;

@RestController //para identificar  que esta classe e um controller
@RequestMapping("/paises") //sempre que chegar uma requesição do tipo paises ele vai chamar esse PaisController
public class PaisController
{
    //@Autowired //para instanciar o repositorio sozinho
   // private PaisRepository repository;
    private CoutryServiceImpl cS=new CoutryServiceImpl();

    @PostMapping()
    public ResponseEntity <Pais> create(@RequestBody@Valid CoutryServiceImpl.DadoscriarPais dados)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(cS.create(dados));
    }

    @GetMapping()
    public ResponseEntity<?> listall(@PageableDefault(size=10,sort={"nome"}) Pageable paginacao)
    {
        return ResponseEntity.ok(cS.listall(paginacao));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Pais> modificar(@RequestBody@Valid CoutryServiceImpl.DadosmodificarPais dados, @PathVariable Long id)
    {
       /* Pais pais=repository.getReferenceById(id);
        pais.modificardados(dados);
        Pais saved=repository.save(pais);
        return ResponseEntity.ok(new CoutryServiceImpl.DadosDetalhadosPais(saved));
        */
        return ResponseEntity.ok(cS.update(id,dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id )
    {
       /*repository.deleteById(id);
       return ResponseEntity.noContent().build();*/
        cS.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity visualizar(@PathVariable Long id)
    {
        /*Pais pais = repository.getReferenceById(id);
        return ResponseEntity.ok(new CoutryServiceImpl.DadosDetalhadosPais(pais));*/
        return ResponseEntity.ok(cS.view(id));

    }
}