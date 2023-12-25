package com.example.bakerybackend.model;

import java.util.List;

public class ItemResponseWrapper {
    public String status;
    public List<ItemResponse>itemList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ItemResponse> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemResponse> itemList) {
        this.itemList = itemList;
    }
}
