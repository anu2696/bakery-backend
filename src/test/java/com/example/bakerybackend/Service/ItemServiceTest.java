package com.example.bakerybackend.Service;

import com.example.bakerybackend.Entity.Items;
import com.example.bakerybackend.Repository.ItemRepository;
import com.example.bakerybackend.model.ItemResponse;
import com.example.bakerybackend.model.ItemResponseWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ItemServiceTest {

    @Mock
    public ItemRepository itemRepository;

    @InjectMocks
    public ItemService itemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Items item1 = new Items();
        item1.setId(1);
        item1.setName("Item1");
        item1.setPrice(10.0);
        item1.setCategory("Category1");

        Items item2 = new Items();
        item2.setId(2);
        item2.setName("Item2");
        item2.setPrice(20.0);
        item2.setCategory("Category2");

        when(itemRepository.findAll()).thenReturn(Arrays.asList(item1, item2));
    }

    @Test
    public void getAllItemService() {
        ResponseEntity<ItemResponseWrapper> responseEntity = itemService.getAllItemService();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ItemResponseWrapper wrapper = responseEntity.getBody();
        assertEquals("0", wrapper.getStatus());

        assertEquals(2, wrapper.getItemList().size());

        ItemResponse itemResponse1 = wrapper.getItemList().get(0);
        assertEquals(Integer.valueOf(1), itemResponse1.getId());
        assertEquals("Item1", itemResponse1.getName());
        assertEquals(10.0, itemResponse1.getPrice());
        assertEquals("Category1", itemResponse1.getCategory());

        ItemResponse itemResponse2 = wrapper.getItemList().get(1);
        assertEquals(Integer.valueOf(2), itemResponse2.getId());
        assertEquals("Item2", itemResponse2.getName());
        assertEquals(20.0, itemResponse2.getPrice());
        assertEquals("Category2", itemResponse2.getCategory());
    }
}