package turmina.velho.sfgpetclinic.services.springdatajpa;

import turmina.velho.sfgpetclinic.model.Pet;
import turmina.velho.sfgpetclinic.model.PetType;
import turmina.velho.sfgpetclinic.repositories.PetTypeRepository;
import turmina.velho.sfgpetclinic.services.PetTypeService;

import java.util.HashSet;
import java.util.Set;

public class PetTypeSDJpaService implements PetTypeService{
    private final PetTypeRepository petTypeRepository;

    public PetTypeSDJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(Long aLong) {
        return petTypeRepository.findById(aLong).orElse(null);
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);

    }

    @Override
    public void deleteById(Long aLong) {
        petTypeRepository.deleteById(aLong);

    }
}
