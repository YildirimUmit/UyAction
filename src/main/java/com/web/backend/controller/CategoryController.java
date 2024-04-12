package com.web.backend.controller;


import com.web.backend.dto.*;
import com.web.backend.enums.*;
import com.web.backend.exception.enums.*;
import com.web.backend.exception.utils.*;
import com.web.backend.payload.response.*;
import com.web.backend.repository.entity.*;
import com.web.backend.request.*;
import com.web.backend.service.impl.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.modelmapper.*;
import org.modelmapper.convention.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/1.0/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepositoryServiceImpl categoryRepositoryService;


    @Autowired
    ModelMapper modelMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create")
    public InternalApiResponse<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto,
                               @RequestHeader("Content-Language") Language language) {
        log.debug("[{}][createCategory] -> request: {}", this.getClass().getSimpleName(), categoryDto);
        Category categoryResponse=categoryRepositoryService.createCategory(language,categoryDto);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        CategoryDto categoryDto1=modelMapper.map(categoryResponse,CategoryDto.class);
        log.debug("[{}][createCategory] -> response: {}", this.getClass().getSimpleName(), categoryResponse);
        return InternalApiResponse.<CategoryDto>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_CREATED))
                        .build())
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)
                .payload(categoryDto1)
                .build();
    }


    @GetMapping(value = "/get/{categoryId}")
    public InternalApiResponse<CategoryDto> getCategory(@PathVariable("categoryId") Long categoryId,
                                                      @RequestHeader("Content-Language") Language language ){
        log.debug("[{}][getCategory] -> requset categoryId : {}",this.getClass().getSimpleName(),categoryId);
        Optional<Category> categoryOptional=categoryRepositoryService.getCategory(language,categoryId) ;
        Category category=categoryOptional.get();
        CategoryDto categoryDto=modelMapper.map(category,CategoryDto.class);

        log.debug("[{}][getCategory] -> response: {}", this.getClass().getSimpleName(), categoryDto);
        return InternalApiResponse.<CategoryDto>builder().httpStatus(HttpStatus.OK).hasError(false).payload(categoryDto).build();
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/update")
    public InternalApiResponse<CategoryDto> updateCategory(
            @RequestBody CategoryDto categoryDto,
            @RequestHeader("Content-Language") Language language) {
        log.debug("[{}][updateCategory] -> request: {} {}", this.getClass().getSimpleName(), categoryDto.getId(), categoryDto);
        categoryDto.setId(categoryDto.getId());
        Optional<Category> category=categoryRepositoryService.updateCategory(language,categoryDto) ;
        CategoryDto categoryDto1=modelMapper.map(category.get(),CategoryDto.class);
        log.debug("[{}][updateCategory] -> response: {}", this.getClass().getSimpleName(), categoryDto);
        return InternalApiResponse.<CategoryDto>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESSFULLY_UPDATED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(categoryDto1)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/delete")
    public InternalApiResponse<CategoryDto> daleteCategory( @RequestBody CategoryDto categoryDto,
                                                            @RequestHeader("Content-Language") Language language){
        log.debug("[{}][daleteCategory] -> request: {} {}", this.getClass().getSimpleName(), categoryDto.getId(), categoryDto);
        categoryDto.setId(categoryDto.getId());
        Optional<Category> category=categoryRepositoryService.deleteCategory(language,categoryDto) ;
        CategoryDto categoryDto1=modelMapper.map(category.get(),CategoryDto.class);
        log.debug("[{}][daleteCategory] -> response: {}", this.getClass().getSimpleName(), categoryDto);
        return InternalApiResponse.<CategoryDto>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESSFULLY_DELETED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(categoryDto1)
                .build();

    }



    }
