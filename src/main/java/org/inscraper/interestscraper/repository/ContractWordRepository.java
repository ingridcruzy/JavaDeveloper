package org.inscraper.interestscraper.repository;

import org.inscraper.interestscraper.entity.ContractWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractWordRepository extends JpaRepository<ContractWord, Integer> {
    @Query(value = "SELECT * FROM contract_word WHERE tipo = :tipo", nativeQuery = true)
    ContractWord findContratoByTypeContract(@Param("tipo") String tipo);
}
