package com.web.backend.service;



import com.web.backend.enums.*;
import com.web.backend.repository.entity.*;
import com.web.backend.request.*;

import java.util.List;

public interface IProductRepositoryService {

    Product createProduct(Language language, ProductCreateRequest productCreateRequest);

    Product getProduct(Language language, Long productId);

    List<Product> getProducts(Language language);

    Product updateProduct(Language language, Long productId, ProductUpdateRequest productUpdateRequest);

    Product deleteProduct(Language language, Long productId);

}
