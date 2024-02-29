package com.example.sanrio.Service.Impl;

import com.example.sanrio.Entity.ManageTable;
import com.example.sanrio.Pojo.ManageTablePojo;
import com.example.sanrio.Repo.ManageTableRepo;
import com.example.sanrio.Service.ManageTableService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManageTableServiceImpl implements ManageTableService {

    private final ManageTableRepo manageTableRepo;
    @Override
    public void saveManageTable(ManageTablePojo manageTablePojo){
        ManageTable manageTable=new ManageTable();

        if(manageTablePojo.getId()!=null){
            manageTable=manageTableRepo.findById(manageTablePojo.getId()).get();
        }

        manageTable.setTableName(manageTablePojo.getTableName());
        manageTable.setStatus(manageTablePojo.getStatus());

        manageTableRepo.save(manageTable); // insert query
        System.out.println(" This Table Saved Successfully");
    }


    @Override
    public List<ManageTable> findAll() {
        return manageTableRepo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        manageTableRepo.deleteById(id);
    }

    @Override
    public Optional<ManageTable> findById(Integer id) {
        return manageTableRepo.findById(id);
    }

    @Override
    public String update(Integer id, ManageTablePojo manageTablePojo){
        ManageTable manageTable = manageTableRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Table not found with ID : "+ id));
        manageTable.setStatus(manageTablePojo.getStatus());
        manageTableRepo.save(manageTable);
        return "Update successfully";
    }

    @Override
    public Boolean checkTable(String table){
        return manageTableRepo.existsByTableName(table);

    }
}
