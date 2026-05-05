package io.github.lighting.domain;

import io.github.lighting.enums.Mode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lighting")
public class Lighting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Mode mode;

    @Column(nullable = true)
    private Integer luminosity;

    @Column(nullable = false)
    private Boolean ledOn;

    @Column(nullable = false)
    private Boolean switchOn;

    @Column(nullable = false)
    private Integer threshold;

}
