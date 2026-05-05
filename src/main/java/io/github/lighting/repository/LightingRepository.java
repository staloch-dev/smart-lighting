package io.github.lighting.repository;

import io.github.lighting.domain.Lighting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LightingRepository extends JpaRepository<Lighting, Long> {

}
