package com.smartfridge.model;

/**
 * Created by sbokhari on 2/12/2018.
 */
public class SmartFridgeItem {

    private String itemUUID;
    private Long type;
    private String name;

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getItemUUID() {
        return itemUUID;
    }

    public void setItemUUID(String itemUUID) {
        this.itemUUID = itemUUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmartFridgeItem that = (SmartFridgeItem) o;

        return itemUUID != null ? itemUUID.equals(that.itemUUID) : that.itemUUID == null;

    }

    @Override
    public int hashCode() {
        return itemUUID != null ? itemUUID.hashCode() : 0;
    }

}