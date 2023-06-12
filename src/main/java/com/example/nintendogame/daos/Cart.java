package com.example.nintendogame.daos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Cart {
    private List<Item> cartItems = new ArrayList<>();
    public void addItems(Item item) {
        boolean isExist = cartItems.stream()
                .filter(i -> Objects.equals(i.getNtdId(),
                        item.getNtdId()))
                .findFirst()
                .map(i -> {
                    i.setQuantity(i.getQuantity() +
                            item.getQuantity());
                    return true;
                })
                .orElse(false);
        if (!isExist) {
            cartItems.add(item);
        }
    }
    public void removeItems(Long ntdId) {
        cartItems.removeIf(item -> Objects.equals(item.getNtdId(),
                ntdId));
    }
    public void updateItems(int ntdId, int quantity) {
        cartItems.stream()
                .filter(item -> Objects.equals(item
                        .getNtdId(), ntdId))
                .forEach(item -> item.setQuantity(quantity));
    }

}
