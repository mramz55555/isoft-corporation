package com.isoft.repositories;

import com.isoft.models.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@RepositoryRestResource
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    //jpql
//    @Query(value = "select * from `contact_message` `c`  where `c`.`status`= : status",nativeQuery = true)
//    @Query(value = "select c from Contact c where c.status=:status")
    List<Contact> getAllByStatus(String status);

    @Query(nativeQuery = true)
    @RestResource(path = "getAllByStatusEquals", rel = "getAllByStatusEquals", description = @Description("hellojgjg"))
    Page<Contact> getAllByStatus(String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery = true)
//    @Query("delete from Contact c where c.status = ?1 and c.id= ?2")
    int deleteById(int id);

    List<Contact> getAllByCreatedBy(String createdBy);
}
