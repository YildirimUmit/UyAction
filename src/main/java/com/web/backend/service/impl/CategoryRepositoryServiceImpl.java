package com.web.backend.service.impl;


import com.web.backend.dto.*;
import com.web.backend.enums.*;
import com.web.backend.exception.enums.*;
import com.web.backend.exception.exceptions.*;
import com.web.backend.repository.*;
import com.web.backend.repository.entity.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;


import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryRepositoryServiceImpl {
    private final CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper1;


    public Category createCategory(Language language, CategoryDto categoryDto){
        log.debug("[{}][createCategory] -> request: {}", this.getClass().getSimpleName(), categoryDto);
        try {
            Category categoryRequest=modelMapper1.map(categoryDto,Category.class);
            Category category=categoryRepository.save(categoryRequest);

            return  category;
        }catch (Exception exception){
            throw new CategoryNotCreatedException(language, FriendlyMessageCodes.PRODUCT_NOT_CREATED_EXCEPTION, "category request: " + categoryDto.toString());
        }
    }

    public Optional<Category> getCategory(Language language, Long  categoryId) {
        log.debug("[{}][getCategory] -> request categoryId: {}", this.getClass().getSimpleName(), categoryId);
         Optional<Category> category = categoryRepository.findById(categoryId);
        if (Objects.isNull(category)) {
            throw new NotFoundException(language, FriendlyMessageCodes.NOT_CREATED_EXCEPTION, "Category not found for Category id: " + categoryId);
        }
        log.debug("[{}][getCategory] -> response: {}", this.getClass().getSimpleName(), category);
        return category;
    }

    public Optional<Category> updateCategory(Language language, CategoryDto  categoryDto ) {
        log.debug("[{}][updateCategory] -> request: {} {}", this.getClass().getSimpleName(),  categoryDto);
        Category categoryRequest=modelMapper1.map(categoryDto,Category.class);
        Category categoryResponse=categoryRepository.save(categoryRequest);
        log.debug("[{}][updateCategory] -> response: {}", this.getClass().getSimpleName(), categoryResponse);
        return getCategory(language,categoryResponse.getId());
    }

}
