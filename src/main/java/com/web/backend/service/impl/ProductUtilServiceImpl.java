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
public class ProductUtilServiceImpl {
    private final  ProductUtilRepository productUtilRepository;

    @Autowired
    ModelMapper modelMapper;

    public ProductUtil createProductUtil(Language language,ProductUtilDto productUtilDto){
        if (Optional.of(productUtilDto.getProductId()).isEmpty()){
            throw new ProductNotFoundException(language, FriendlyMessageCodes.NOT_CREATED_EXCEPTION, "Products Util : product id not found");
        }
        ProductUtil productUtil=modelMapper.map(productUtilDto,ProductUtil.class);
        ProductUtil productUtilRequest=productUtilRepository.save(productUtil);
        return productUtilRequest;
    }

    public ProductUtil updateProductUtil(Language language, ProductUtilDto productUtilDto){
        ProductUtil productUtil=modelMapper.map(productUtilDto,ProductUtil.class);
        return productUtilRepository.save(productUtil);
    }

    public ProductUtil deleteProductUtil(Language language, ProductUtilDto productUtilDto){
        ProductUtil productUtil=modelMapper.map(productUtilDto,ProductUtil.class);
        productUtil.setStatus(true);
        return productUtilRepository.save(productUtil);
    }


}
