package paises.api.domain;
import jakarta.persistence.*;
import lombok.*;
import paises.api.service.CoutryServiceImpl;


//JPA representação do obj Pais como tabela na bd

@Table(name="paises")
@Entity(name="Pais")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of="id")

public class Pais
{
     @Id @GeneratedValue (strategy=GenerationType.IDENTITY)
     private long id;
     private String name,capital,region,subregion,area;

     public long getId(){return id;}
     public void setId(long id){ this.id=id;}

     public Pais(CoutryServiceImpl.DadoscriarPais dados)
     {
         this.name=dados.name();
         this.capital=dados.capital();
         this.region=dados.region();
         this.subregion=dados.subregion();
         this.area=dados.area();
     }

     public void modificardados(CoutryServiceImpl.DadosmodificarPais dados)
     {
         if(dados.capital()!=null)
         this.capital=dados.capital();
         if(dados.area()!=null)
         this.area=dados.area();
     }
}