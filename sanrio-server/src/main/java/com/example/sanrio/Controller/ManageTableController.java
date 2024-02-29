package com.example.sanrio.Controller;

import com.example.sanrio.Entity.ManageTable;
import com.example.sanrio.Pojo.ManageTablePojo;
import com.example.sanrio.Service.ManageTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("manageTable")
@RequiredArgsConstructor
@Component
public class ManageTableController implements CommandLineRunner {
    private final ManageTableService manageTableService;

    @Override
    public void run(String... args) throws Exception {
        initializeTables();
    }

    @PostMapping("/save")
    public void saveManageTable(@RequestBody ManageTablePojo manageTablePojo) {
        manageTableService.saveManageTable(manageTablePojo);
    }

    private void initializeTables() {
        if(!manageTableService.checkTable("Table 12")){
            for (int i = 1; i <= 12; i++) {

                ManageTablePojo manageTablePojo = new ManageTablePojo();
                manageTablePojo.setTableName("Table " + i);
                manageTablePojo.setStatus("Available");

                saveManageTable(manageTablePojo);

            }
        }

    }

    @GetMapping("/findAll")
    public List<ManageTable> getAll() {
        return this.manageTableService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Optional<ManageTable> getById(@PathVariable("id") Integer id) {
        return this.manageTableService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        this.manageTableService.deleteById(id);
    }
}
