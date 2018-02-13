package com.smartfridge.service.impl;

import com.smartfridge.model.SmartFridge;
import com.smartfridge.model.SmartFridgeItem;
import com.smartfridge.service.SmartFridgeManager;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by sbokhari on 2/12/2018.
 */
@Service
public class SmartFridgeManagerService implements SmartFridgeManager {

    private SmartFridge fridge = new SmartFridge();

    public void handleItemAdded(long itemType, String itemUUID, String name, Double fillFactor) {
        SmartFridgeItem item = new SmartFridgeItem();
        item.setItemUUID(itemUUID);
        item.setType(itemType);
        item.setName(name);
        fridge.getItemUUIDToItem().put(itemUUID, item);
        if(!fridge.getItemTypesToItems().containsKey(item.getType())) {
            fridge.getItemTypesToItems().put(item.getType(), new ArrayList<>());
        }
        fridge.getItemTypesToItems().get(item.getType()).add(item);
        fridge.getItemsFillFactor().put(item.getType(), fillFactor);
    }

    public void handleItemRemoved(String itemUUID) {
        SmartFridgeItem item = fridge.getItemUUIDToItem().get(itemUUID);
        List<SmartFridgeItem> items = fridge.getItemTypesToItems().get(item.getType());
        if(items.contains(item)) {
            /**************************************************************************************************************************
            *Assume here that fill factor passed in by the SmartFridge hardware is telling you the maximum capacity for the item type.
            *So leave it up to the hardware to determine capacity, and use that capacity calculation when removing items.
            **************************************************************************************************************************/
            Double existingFillFactor = fridge.getItemsFillFactor().get(item.getType());
            Double currentMaxItems = BigDecimal.valueOf(items.size() / existingFillFactor).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
            items.remove(item);
            fridge.getItemsFillFactor().put(item.getType(), BigDecimal.valueOf(items.size() / currentMaxItems).setScale(2, RoundingMode.HALF_EVEN).doubleValue());
        }
    }

    public void forgetItem(long itemType) {
        fridge.getItemsFillFactor().remove(itemType);
    }

    public Object[] getItems(Double fillFactor)  {
        List<Object[]> results = new ArrayList<>();
        for(Map.Entry<Long, Double> entry : fridge.getItemsFillFactor().entrySet())
        {
            if(entry.getValue() <= fillFactor) {
                Object[] arrayEntry = new Object[2];
                arrayEntry[0] = entry.getKey();
                arrayEntry[1] = entry.getValue();
                results.add(arrayEntry);
            }
        }
        return results.toArray(new Object[results.size()]);
    }

    public Double getFillFactor(long itemType) {
        return fridge.getItemsFillFactor().get(itemType);
    }

}