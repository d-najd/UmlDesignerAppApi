package com.umldesigner.schema.foreign_key.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umldesigner.infrastructure.domain.identities.BaseMIdentity;
import com.umldesigner.schema.foreign_key.domain.SFK;

@Repository
public interface SFKRepository extends JpaRepository<SFK, BaseMIdentity> {
    public Optional<SFK> findById(BaseMIdentity identity);
}
