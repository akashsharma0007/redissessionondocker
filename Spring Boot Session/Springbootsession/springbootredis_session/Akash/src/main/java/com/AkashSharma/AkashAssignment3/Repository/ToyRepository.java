package com.AkashSharma.AkashAssignment3.Repository;

import com.AkashSharma.AkashAssignment3.Pojo.ToyPojo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToyRepository extends CrudRepository<ToyPojo,Long> {
    @Query(value = "select max(id) from toys", nativeQuery = true)
    Long findLargestId();
}
