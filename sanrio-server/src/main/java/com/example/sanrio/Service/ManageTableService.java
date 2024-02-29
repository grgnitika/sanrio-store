package com.example.sanrio.Service;

import com.example.sanrio.Entity.ManageTable;
import com.example.sanrio.Pojo.ManageTablePojo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ManageTableService {
    void saveManageTable(ManageTablePojo manageTablePojo);


    List<ManageTable> findAll();

    Optional<ManageTable> findById(Integer id);

    void deleteById(Integer id);

    String update(Integer id, ManageTablePojo manageTablePojo);
    public Boolean checkTable(String table);

}

