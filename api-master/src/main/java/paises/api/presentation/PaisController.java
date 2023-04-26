package paises.api.presentation;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paises.api.domain.Pais;
import paises.api.service.*;

@RestController
@RequestMapping("/paises")
public class PaisController
{
    @Autowired //para instanciar o repositorio sozinho
    private CoutryServiceImpl cS;

    @PostMapping()
    public ResponseEntity <Pais> create(@RequestBody@Valid CoutryServiceImpl.CreateCountryData data)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(cS.create(data));
    }

    @GetMapping()
    public ResponseEntity<?> listall(@PageableDefault(size=10,sort={"name"}) Pageable pageable)
    {
        return ResponseEntity.ok(cS.listall(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pais> modify(@RequestBody@Valid CoutryServiceImpl.ModifiyCountryData data, @PathVariable Long id)
    {
        return ResponseEntity.ok(cS.update(id,data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id )
    {
        cS.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity view(@PathVariable Long id)
    {
        return ResponseEntity.ok(cS.view(id));
    }
}