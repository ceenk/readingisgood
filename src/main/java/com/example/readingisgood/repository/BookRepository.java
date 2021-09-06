package com.example.readingisgood.repository;

import com.example.readingisgood.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Modifying
    @Query("update BookEntity b set b.stock = :stock where b.id = :id")
    void updateStock(@Param(value = "id") long id, @Param(value = "stock") int stock);
}
