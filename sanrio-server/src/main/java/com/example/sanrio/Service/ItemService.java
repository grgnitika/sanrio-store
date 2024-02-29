package com.example.sanrio.Service;


import com.example.sanrio.Entity.Item;
import com.example.sanrio.Pojo.ItemPojo;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ItemService {

    void saveItem(ItemPojo itemPojo)  throws IOException;
    List<Item> findAll();

    Optional<Item> getItemById(Integer id);

    void deleteItemById(Integer id);
}
