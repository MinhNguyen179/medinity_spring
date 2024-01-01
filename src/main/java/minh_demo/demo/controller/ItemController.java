package minh_demo.demo.controller;

import minh_demo.demo.dto.model.ItemDTO;
import minh_demo.demo.dto.response.ItemResponse;
import minh_demo.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class ItemController {
    @Autowired
    ItemService itemService;
    @GetMapping("/listItems")
    public ResponseEntity<ItemResponse> listItem(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(itemService.getAllItem(pageNo, pageSize), HttpStatus.OK);
    }
    @GetMapping("/item/{id}")
    public ResponseEntity<ItemDTO> itemDetail(@PathVariable int id) {
        return ResponseEntity.ok(itemService.getItemById(id));    }
    @PostMapping("item/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ItemDTO> createItem (@RequestBody ItemDTO itemDTO) {
        return new ResponseEntity<>(itemService.createItem(itemDTO), HttpStatus.CREATED);
    }

    @PutMapping("item/{id}/update")
    public ResponseEntity<ItemDTO> updateItem (@RequestBody ItemDTO itemDto, @PathVariable("id") int itemId) {
        ItemDTO response = itemService.updateItem(itemDto, itemId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("item/{id}/delete")
    public ResponseEntity<String> deleteItem (@PathVariable("id") int itemId) {
        itemService.deleteItem(itemId);
        return new ResponseEntity<>("Item delete", HttpStatus.OK);
    }
}
