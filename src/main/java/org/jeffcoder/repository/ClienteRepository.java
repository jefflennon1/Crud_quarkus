package org.jeffcoder.repository;

import org.jeffcoder.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Transactional
@Repository 
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> { 
	 
}
