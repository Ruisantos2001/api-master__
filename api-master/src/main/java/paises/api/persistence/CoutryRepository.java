package paises.api.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import paises.api.domain.Country;

public interface CoutryRepository extends JpaRepository<Country,Long>
{

}
