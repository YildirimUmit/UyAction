package com.web.backend.controller;



import com.web.backend.dto.*;
import com.web.backend.enums.*;
import com.web.backend.exception.enums.*;
import com.web.backend.exception.utils.*;
import com.web.backend.payload.response.*;
import com.web.backend.repository.entity.*;
import com.web.backend.request.*;
import com.web.backend.service.*;
import com.web.backend.service.impl.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import javax.validation.*;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api/1.0/product")
@RequiredArgsConstructor
class ProductController {
    private final IProductRepositoryService productRepositoryService;
    private final ProductCategoryServiceImpl productCategoryService;
    private final ProductUtilServiceImpl productUtilService;


    @Autowired
    ModelMapper modelMapper=null;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create")
    public InternalApiResponse<ProductResponse> createProduct(
                                                             @Valid @RequestBody ProductCreateRequest productCreateRequest,
                                                              @RequestHeader("Content-Language") Language language) {
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), productCreateRequest);
        Product product = productRepositoryService.createProduct(language, productCreateRequest);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][createProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_CREATED))
                        .build())
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)
                .payload(productResponse)
                .build();

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/get/{productId}")
    public InternalApiResponse<ProductResponse> getProduct(
                                                           @PathVariable("productId") Long productId,
                                                           @RequestHeader("Content-Language") Language language) {
        log.debug("[{}][getProduct] -> request productId: {}", this.getClass().getSimpleName(), productId);
        Product product = productRepositoryService.getProduct(language, productId);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][getProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/update/{productId}")
    public InternalApiResponse<ProductResponse> updateProduct(
                                                              @PathVariable("productId") Long productId,
                                                              @RequestBody ProductUpdateRequest productUpdateRequest,
                                                              @RequestHeader("Content-Language") Language language) {
        log.debug("[{}][updateProduct] -> request: {} {}", this.getClass().getSimpleName(), productId, productUpdateRequest);
        Product product = productRepositoryService.updateProduct(language, productId, productUpdateRequest);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][updateProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_UPDATED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    @ApiOperation(value = "This endpoint get all product.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/products")
    public InternalApiResponse<List<ProductResponse>> getProducts(@RequestHeader("Content-Language") Language language) {
        log.debug("[{}][getProducts]", this.getClass().getSimpleName());
        List<Product> products = productRepositoryService.getProducts(language);
        List<ProductResponse> productResponses = convertProductResponseList(products);
        log.debug("[{}][getProducts] -> response: {}", this.getClass().getSimpleName(), productResponses);
        return InternalApiResponse.<List<ProductResponse>>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponses)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/delete/{productId}")
    public InternalApiResponse<ProductResponse> deleteProduct(@RequestHeader("Content-Language") Language language,
                                                              @PathVariable("productId") Long productId) {
        log.debug("[{}][deleteProduct] -> request productId: {}", this.getClass().getSimpleName(), productId);
        Product product = productRepositoryService.deleteProduct(language, productId);

        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][deleteProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_DELETED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    // ---------------------- Product Category -------------------------------

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/productcategory/create")
    public InternalApiResponse<ProductCategory> productCategory(@RequestHeader("Content-Language") Language language,
                                                           @RequestBody ProductCategory productCategory) {
        ProductCategory productCategoryResponse=  productCategoryService.createProductCategory(language,productCategory);
        return InternalApiResponse.<ProductCategory>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_DELETED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productCategoryResponse)
                .build();
    }


    // ---------------------- Product Util-------------------------------

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/productutil/create")
    public InternalApiResponse<ProductUtilDto> createProductUtil(@RequestHeader("Content-Language") Language language,
                                                        @RequestBody ProductUtilDto productUtilDto){

        ProductUtil productUtil=productUtilService.createProductUtil(language,productUtilDto);
        ProductUtilDto productUtilDto1=modelMapper.map(productUtil,ProductUtilDto.class);
        return InternalApiResponse.<ProductUtilDto>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_DELETED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productUtilDto1)
                .build();

    }

//    @ResponseStatus(HttpStatus.OK)
//    @PutMapping(value = "/update/}")
//    public InternalApiResponse<ProductResponse> updateProductUtil(@RequestHeader("Content-Language") Language language,
//                                                                  @RequestBody ProductUtilDto productUtilDto){
//
//
//
//
//    }





    private List<ProductResponse> convertProductResponseList(List<Product> productList) {
        return productList.stream()
                .map(arg -> ProductResponse.builder()
                        .productId(arg.getProductId())
                        .productName(arg.getProductName())
                        .productUtils(arg.getProductUtils())
//                        .productCreatedDate(arg.getCreatedAt().getTime())
//                        .productUpdatedDate(arg.getUpdateAt().getTime())
                        .build())
                .collect(Collectors.toList());
    }

    private ProductResponse convertProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productCreatedDate(product.getCreatedAt().getTime())
                .productUpdatedDate(product.getUpdateAt().getTime())

                .build();
    }

}
