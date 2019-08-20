package turmina.velho.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import turmina.velho.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner,Long>{
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
