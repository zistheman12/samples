package com.smartfridge.model;

import java.util.*;

/**
 * Created by sbokhari on 2/12/2018.
 */
public class SmartFridge {

    private Map<Long,List<SmartFridgeItem>> itemTypesToItems = new HashMap<>();
    private Map<String,SmartFridgeItem> itemUUIDToItem = new HashMap<>();
    private Map<Long,Double> itemsFillFactor = new HashMap<>();

    public Map<Long, List<SmartFridgeItem>> getItemTypesToItems() {
        return itemTypesToItems;
    }

    public void setItemTypesToItems(Map<Long, List<SmartFridgeItem>> itemTypesToItems) {
        this.itemTypesToItems = itemTypesToItems;
    }

    public Map<String, SmartFridgeItem> getItemUUIDToItem() {
        return itemUUIDToItem;
    }

    public void setItemUUIDToItem(Map<String, SmartFridgeItem> itemUUIDToItem) {
        this.itemUUIDToItem = itemUUIDToItem;
    }

    public Map<Long, Double> getItemsFillFactor() {
        return itemsFillFactor;
    }

    public void setItemsFillFactor(Map<Long, Double> itemsFillFactor) {
        this.itemsFillFactor = itemsFillFactor;
    }

}
