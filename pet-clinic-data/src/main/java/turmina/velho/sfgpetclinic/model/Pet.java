package turmina.velho.sfgpetclinic.model;


import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "pets")
public class Pet extends BaseEntity {

    @Builder
    public Pet(Long id, LocalDate birthDate, PetType petType, Owner owner, String name, Set<Visit> visits) {
        super(id);
        this.birthDate = birthDate;
        this.petType = petType;
        this.owner = owner;
        this.name = name;
        this.visits = visits;
    }

    @Column (name = "birth_date")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn (name = "owner_id")
    private Owner owner;

    @Column (name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

}
