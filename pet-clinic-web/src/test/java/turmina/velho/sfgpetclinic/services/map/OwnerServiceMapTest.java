package turmina.velho.sfgpetclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import turmina.velho.sfgpetclinic.model.Owner;
import turmina.velho.sfgpetclinic.model.PetType;
import turmina.velho.sfgpetclinic.services.PetService;
import turmina.velho.sfgpetclinic.services.PetTypeService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest{
    OwnerServiceMap ownerServiceMap;

    final Long ownerId = 1L;
    final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap (new PetTypeServiceMap(),new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1,ownerSet.size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void saveExistingID() {
        Long id = 2L;
        Owner savedOwner = ownerServiceMap.save(Owner.builder().id(id).build());
        assertEquals(id,savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findById() {
        assertEquals(ownerId,ownerServiceMap.findById(ownerId).getId());
    }

    @Test
    void findByLastName() {
        Owner smith = ownerServiceMap.findByLastName(lastName);
        assertNotNull(smith);
        assertEquals(ownerId, smith.getId());

    }

    @Test
    void findByLastNameNotFound() {
        Owner smith = ownerServiceMap.findByLastName("Doesn't exist");
        assertNull(smith);

    }
}