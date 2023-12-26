package minh_demo.demo.service.implementation;

import minh_demo.demo.dto.model.ItemDTO;
import minh_demo.demo.dto.response.ItemResponse;
import minh_demo.demo.model.Item;
import minh_demo.demo.service.ItemService;
import minh_demo.demo.exceptions.StudentNotFoundException;
import minh_demo.demo.model.User;
import minh_demo.demo.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImplement implements ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public StudentServiceImplement(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemDTO createItem(ItemDTO studentDTO) {
        Item item = new Item();
        item.setUsername(studentDTO.getName());
        item.setAge(studentDTO.getAge());

        Item newStudent = itemRepository.save(item);

        ItemDTO studentResponse = new ItemDTO();
        studentResponse.setId((int) newStudent.getId());
        studentResponse.setName(newStudent.getUsername());
        studentResponse.setAge(newStudent.getAge());
        return studentResponse;
    }

    @Override
    public ItemResponse getAllItem(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Item> students = itemRepository.findAll(pageable);
        List<Item> listOfStudent = students.getContent();
        List<ItemDTO> content = listOfStudent.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        ItemResponse studentResponse = new ItemResponse();
        studentResponse.setContent(content);
        studentResponse.setPageNo(students.getNumber());
        studentResponse.setPageSize(students.getSize());
        studentResponse.setTotalElements(students.getTotalElements());
        studentResponse.setTotalPages(students.getTotalPages());
        studentResponse.setLast(students.isLast());

        return studentResponse;
    }

    @Override
    public ItemDTO getItemById(int id) {
        Item student = itemRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student could not be found"));
        return mapToDto(student);
    }

    @Override
    public ItemDTO updateItem(ItemDTO studentDTO, int id) {
        Item student = itemRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Pokemon could not be updated"));

        student.setUsername(studentDTO.getName());
        student.setAge(studentDTO.getAge());

        Item updatedStudent = itemRepository.save(student);
        return mapToDto(updatedStudent);
    }

    @Override
    public void deleteItem(int id) {
        Item student = itemRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Pokemon could not be delete"));
        itemRepository.delete(student);
    }

    private ItemDTO mapToDto(Item student) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId((int) student.getId());
        itemDTO.setName(student.getUsername());
        itemDTO.setAge(student.getAge());
        return itemDTO;
    }

    private Item mapToTeacher(ItemDTO itemDTO) {
        Item item = new Item();
        item.setUsername(itemDTO.getName());
        item.setAge(itemDTO.getAge());
        return item;
    }
}