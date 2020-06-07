package br.com.farmacia.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author vinicius.montouro
 */

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="pharmacy")
public class Pharmacy implements AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "The field 'name' is mandatory")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "The field 'tel' is mandatory")
    @Column(nullable = false)
    private String tel;

    @NotNull(message = "The field 'address' is mandatory")
    @Column(nullable = false)
    private String address;

}
