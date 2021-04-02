package com.lab4.demo.frontoffice;

import com.lab4.demo.TestCreationFactory;
import com.lab4.demo.frontoffice.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.when;

class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        itemService = new ItemService(itemRepository);
    }

    @Test
    void findAll() {
        List<Item> items = TestCreationFactory.listOf(Item.class);
        when(itemRepository.findAll()).thenReturn(items);

        List<Item> all = itemService.findAll();

        Assertions.assertEquals(items.size(), all.size());
    }
}