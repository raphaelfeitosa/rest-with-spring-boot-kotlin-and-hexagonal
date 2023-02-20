package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.repository

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.entity.AddressEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AddressRepository : JpaRepository<AddressEntity, UUID>