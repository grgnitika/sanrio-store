package com.example.sanrio.Controller;

import com.example.sanrio.Entity.Item;
import com.example.sanrio.Pojo.ItemPojo;
import com.example.sanrio.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/save")
    public String savePItem(@RequestBody @ModelAttribute ItemPojo itemPojo) throws IOException {
        itemService.saveItem(itemPojo);
        return "Saved successfully";
    }

    @GetMapping("/findAll")
    public List<Item> findAll() {
        return itemService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Optional<Item> getItemById(@PathVariable("id") Integer id) {
        return itemService.getItemById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteItemById(@PathVariable("id") Integer id) {
        itemService.deleteItemById(id);
    }
}
