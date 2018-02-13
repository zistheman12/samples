package com.smartfridge.service.impl;

import com.smartfridge.service.SmartFridgeManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

import static org.junit.Assert.*;

/**
 * Created by sbokhari on 2/13/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmartFridgeManagerServiceTest {

    @Autowired
    private SmartFridgeManager fridgeManagerService;

    @Test
    public void addRemoveAndForgetSomeItems() throws Exception {
        //Add some items
        fridgeManagerService.handleItemAdded(1L, "A", "Horizon Milk", .25d);
        fridgeManagerService.handleItemAdded(1L, "B", "Giant Two Percent Milk", .5d);
        fridgeManagerService.handleItemAdded(2L, "C", "Whole Foods Eggs", .1d);
        fridgeManagerService.handleItemAdded(2L, "D", "Giant Eggs", .2d);
        fridgeManagerService.handleItemAdded(3L, "E", "Wegman's Bread", .5d);
        Object[] items = fridgeManagerService.getItems(.5d);
        Object[] expected = new Object[3];
        expected[0] = new Object[]{1L, .5d};
        expected[1] = new Object[]{2L, .2d};
        expected[2] = new Object[]{3L, .5d};
        assertThat("Results",
                Arrays.asList(items), containsInAnyOrder(expected));
        //Forget an item
        fridgeManagerService.forgetItem(2L);
        items = fridgeManagerService.getItems(.5d);
        expected = new Object[2];
        expected[0] = new Object[]{1L, .5d};
        expected[1] = new Object[]{3L, .5d};
        assertThat("Results",
                Arrays.asList(items), containsInAnyOrder(expected));
        //Remove an item
        fridgeManagerService.handleItemRemoved("B");
        expected = new Object[2];
        expected[0] = new Object[]{1L, fridgeManagerService.getFillFactor(1L)};
        expected[1] = new Object[]{3L, .5d};
        items = fridgeManagerService.getItems(.5d);
        assertThat("Results",
                Arrays.asList(items), containsInAnyOrder(expected));
        //Empty an item type
        fridgeManagerService.handleItemRemoved("A");
        expected = new Object[2];
        expected[0] = new Object[]{1L, 0d};
        expected[1] = new Object[]{3L, .5d};
        items = fridgeManagerService.getItems(.5d);
        assertThat("Results",
                Arrays.asList(items), containsInAnyOrder(expected));
    }

}