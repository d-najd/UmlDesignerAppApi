package com.umldesigner.schema.item_reference.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umldesigner.infrastructure.domain.identities.BaseMTMIdentity;
import com.umldesigner.schema.item_reference.domain.SchemaItemReference;

@Repository
public interface SchemaItemReferenceRepository extends JpaRepository<SchemaItemReference, BaseMTMIdentity> {
    public Optional<SchemaItemReference> findById(BaseMTMIdentity identity);
}
