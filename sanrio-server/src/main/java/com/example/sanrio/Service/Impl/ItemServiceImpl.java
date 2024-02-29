package com.example.sanrio.Service.Impl;

import com.example.sanrio.Entity.Category;
import com.example.sanrio.Entity.Item;
import com.example.sanrio.Pojo.ItemPojo;
import com.example.sanrio.Repo.CategoryRepo;
import com.example.sanrio.Repo.ItemRepo;
import com.example.sanrio.Service.ItemService;
import com.example.sanrio.util.ImageToBase64;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepo itemRepo;
    private final CategoryRepo categoryRepository;

    private final String UPLOAD_DIRECTORY = new StringBuilder().append(System.getProperty("user.dir")).append("/Feast-Images/Items-images").toString();
    ImageToBase64 imageToBase64 = new ImageToBase64();

    @Override
    public void saveItem(ItemPojo itemPojo) throws IOException {
        Item item;
        if (itemPojo.getId() != null) {
            item = itemRepo.findById(itemPojo.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Item not found with ID: " + itemPojo.getId()));
        } else {
            item = new Item();
        }

        item.setItemName(itemPojo.getItemName());
        item.setItemPrice(itemPojo.getItemPrice());

        Category category = categoryRepository.findById(itemPojo.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + itemPojo.getCategoryId()));


        if (itemPojo.getItemImage() != null) {
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, itemPojo.getItemImage().getOriginalFilename());
            Files.write(fileNameAndPath, itemPojo.getItemImage().getBytes());
        }
        item.setItemImage(itemPojo.getItemImage().getOriginalFilename());


        item.setCategory(category);
        itemRepo.save(item);
    }

    @Override
    public List<Item> findAll(){
        List<Item> items = itemRepo.findAll();
        items = items.stream().map(item -> {
            item.setItemImage(imageToBase64.getImageBase64("/Items-images/" + item.getItemImage()));
            return item;
        }).collect(Collectors.toList());
        return items;
    }

    @Override
    public Optional<Item> getItemById(Integer id) {
        return itemRepo.findById(id);
    }

    @Override
    public void deleteItemById(Integer id) {
        itemRepo.deleteById(id);
    }
}

