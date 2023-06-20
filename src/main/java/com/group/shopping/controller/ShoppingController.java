package com.group.shopping.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.group.shopping.domain.ToBuyObject;
import com.group.shopping.service.ShoppingService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class ShoppingController {

	private final ShoppingService shoppingService;

	@GetMapping("/shopping-list")
	public ResponseEntity<ShoppingResponse> findAll() {
		List<ToBuyObject> toBuyObjects = shoppingService.findAll();
		ShoppingResponse shoppingResponse = ShoppingResponse.builder()
                .toBuyObjects(toBuyObjects)
                .build();
        return new ResponseEntity<ShoppingResponse>(shoppingResponse, HttpStatus.OK);
	}

	@PostMapping("/shopping-list")
	public ResponseEntity<HttpStatus> addItem(@RequestParam("name") String itemName, @RequestParam("file") MultipartFile file) {
		shoppingService.pictureSave(file.getOriginalFilename());
		shoppingService.save(itemName);
        return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}

	@DeleteMapping("/shopping-list/{id}")
	public ResponseEntity<HttpStatus> deleteItem(@PathVariable Long id) {
		shoppingService.delete(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

}
