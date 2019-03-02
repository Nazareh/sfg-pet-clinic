package turmina.velho.sfgpetclinic.services.springdatajpa;

import turmina.velho.sfgpetclinic.model.Specialty;
import turmina.velho.sfgpetclinic.repositories.SpecialtyRepository;
import turmina.velho.sfgpetclinic.services.SpecialtyService;

import java.util.HashSet;
import java.util.Set;

public class SpecialtySDJpaService implements SpecialtyService{

    private final SpecialtyRepository specialtyRepository;

    public SpecialtySDJpaService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Set<Specialty> findAll() {
        Set<Specialty> specialties = new HashSet<>();
        specialtyRepository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public Specialty findById(Long aLong) {
        return specialtyRepository.findById(aLong).orElse(null);
    }

    @Override
    public Specialty save(Specialty object) {
        return specialtyRepository.save(object);
    }

    @Override
    public void delete(Specialty object) {
        specialtyRepository.delete(object);

    }

    @Override
    public void deleteById(Long aLong) {
        specialtyRepository.deleteById(aLong);

    }
}
