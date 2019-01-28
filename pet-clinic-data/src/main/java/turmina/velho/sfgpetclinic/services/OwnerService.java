package turmina.velho.sfgpetclinic.services;

import turmina.velho.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner,Long> {

    Owner findByLastName(String lastName);

}
