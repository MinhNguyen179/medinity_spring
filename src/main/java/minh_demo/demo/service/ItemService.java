package minh_demo.demo.service;

import minh_demo.demo.dto.model.ItemDTO;
import minh_demo.demo.dto.response.ItemResponse;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {
    ItemDTO createItem(ItemDTO pokemonDto);
    ItemResponse getAllItem(int pageNo, int pageSize);
    ItemDTO getItemById(int id);
    ItemDTO updateItem(ItemDTO studentDto, int id);
    void deleteItem(int id);
}
