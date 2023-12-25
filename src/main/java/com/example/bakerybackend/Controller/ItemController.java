package com.example.bakerybackend.Controller;

import com.example.bakerybackend.Service.ItemService;
import com.example.bakerybackend.model.ItemResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value="/getAllItems")
    public ResponseEntity<ItemResponseWrapper> getAllItem(){
        return itemService.getAllItemService();
    }
}
