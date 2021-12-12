package com.shopping.repository;

import java.util.*;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;

import com.shopping.model.ItemDetails;
import org.springframework.data.repository.query.Param;

public interface ItemDetailsRepo extends JpaRepository<ItemDetails,Long> {

    List<ItemDetails> findAll();

    public Optional<ItemDetails> findByItemName();

    public Optional<ItemDetails> findByItemId(Long itemId);

    public Optional<ItemDetails> findByItemName(String itemName);

    @Query("SELECT * FROM ITEM_DETAILS WHERE ITEM_NAME LIKE '%name'")
    public Optional<ItemDetails> findItemByItemName(@Param("name") String name);
}
