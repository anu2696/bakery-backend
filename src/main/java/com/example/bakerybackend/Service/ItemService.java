package com.example.bakerybackend.Service;

import com.example.bakerybackend.Entity.Items;
import com.example.bakerybackend.Repository.ItemRepository;
import com.example.bakerybackend.model.ItemResponse;
import com.example.bakerybackend.model.ItemResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepo;

    public ResponseEntity<ItemResponseWrapper> getAllItemService(){
        List<Items> allItems = new ArrayList<Items>();
        List<ItemResponse> itemList = new ArrayList<>();
        allItems = itemRepo.findAll();
        for(Items item: allItems){
            ItemResponse res= new ItemResponse();
            res.setId(item.getId());
            res.setName(item.getName());
            res.setPrice(item.getPrice());
            res.setCategory(item.getCategory());
            itemList.add(res);
        }
        ItemResponseWrapper wrapper = new ItemResponseWrapper();
        wrapper.setItemList(itemList);
        wrapper.setStatus("0");
        return new ResponseEntity<ItemResponseWrapper>(wrapper, HttpStatus.OK);
    }
}
