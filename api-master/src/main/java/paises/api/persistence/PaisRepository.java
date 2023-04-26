package paises.api.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import paises.api.domain.Pais;

public interface PaisRepository extends JpaRepository<Pais,Long>
{

}
