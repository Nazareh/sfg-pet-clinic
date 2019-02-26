package turmina.velho.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import turmina.velho.sfgpetclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet,Long>{
}
