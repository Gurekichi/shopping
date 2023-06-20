package com.group.shopping.controller;

import java.util.List;

import com.group.shopping.domain.ToBuyObject;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShoppingResponse {

        private List<ToBuyObject> toBuyObjects;

}
