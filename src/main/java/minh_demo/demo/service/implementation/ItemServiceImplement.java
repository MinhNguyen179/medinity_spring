package minh_demo.demo.service.implementation;

import minh_demo.demo.dto.model.ItemDTO;
import minh_demo.demo.dto.response.ItemResponse;
import minh_demo.demo.model.Item;
import minh_demo.demo.service.ItemService;
import minh_demo.demo.exceptions.ItemNotFoundException;
import minh_demo.demo.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImplement implements ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImplement(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemDTO createItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setUsername(itemDTO.getName());
        item.setAge(itemDTO.getAge());

        Item newItem = itemRepository.save(item);

        ItemDTO studentResponse = new ItemDTO();
        studentResponse.setId((int) newItem.getId());
        studentResponse.setName(newItem.getUsername());
        studentResponse.setAge(newItem.getAge());
        return studentResponse;
    }

    @Override
    public ItemResponse getAllItem(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Item> items = itemRepository.findAll(pageable);
        List<Item> listOfItem = items.getContent();
        List<ItemDTO> content = listOfItem.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setContent(content);
        itemResponse.setPageNo(items.getNumber());
        itemResponse.setPageSize(items.getSize());
        itemResponse.setTotalElements(items.getTotalElements());
        itemResponse.setTotalPages(items.getTotalPages());
        itemResponse.setLast(items.isLast());

        return itemResponse;
    }

    @Override
    public ItemDTO getItemById(int id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Student could not be found"));
        return mapToDto(item);
    }

    @Override
    public ItemDTO updateItem(ItemDTO itemDTO, int id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Pokemon could not be updated"));

        item.setUsername(itemDTO.getName());
        item.setAge(itemDTO.getAge());

        Item updatedStudent = itemRepository.save(item);
        return mapToDto(updatedStudent);
    }

    @Override
    public void deleteItem(int id) {
        Item student = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Pokemon could not be delete"));
        itemRepository.delete(student);
    }

    private ItemDTO mapToDto(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId((int) item.getId());
        itemDTO.setName(item.getUsername());
        itemDTO.setAge(item.getAge());
        return itemDTO;
    }

    private Item mapToTeacher(ItemDTO itemDTO) {
        Item item = new Item();
        item.setUsername(itemDTO.getName());
        item.setAge(itemDTO.getAge());
        return item;
    }
}