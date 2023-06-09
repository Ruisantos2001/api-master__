package paises.api.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import paises.api.service.CountryServiceImpl;

//JPA representação do obj Pais como tabela na bd

@Table(name="paises")
@Entity(name="Pais")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Country
{
     @Id @GeneratedValue (strategy=GenerationType.IDENTITY)
     private long id;
     private String name,capital,region,subregion,area;

     public long getId(){return id;}
     public void setId(long id){ this.id=id;}

     public Country(CountryServiceImpl.CreateCountryData data)
     {
         this.name=data.name();
         this.capital=data.capital();
         this.region=data.region();
         this.subregion=data.subregion();
         this.area=data.area();
     }

     public void modifiycoutrydata(CountryServiceImpl.ModifiyCountryData data)
     {
         if(data.capital()!=null)
         this.capital=data.capital();
         if(data.area()!=null)
         this.area=data.area();
     }
}