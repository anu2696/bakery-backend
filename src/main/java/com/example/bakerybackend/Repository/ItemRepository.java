package com.example.bakerybackend.Repository;

import com.example.bakerybackend.Entity.Items;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Items, Integer> {
    List<Items> findAll();
}
