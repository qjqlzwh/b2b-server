package com.cow.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.feign.user.CustomerFeignClient;
import com.cow.feign.vo.ProductPriceCustomerItemVo;
import com.cow.po.dto.ProductPriceDTO;
import com.cow.po.enums.CommonState;
import com.cow.po.pojo.ProductPrice;
import com.cow.po.pojo.ProductPriceCustomerItem;
import com.cow.po.pojo.ProductPriceGoodsItem;
import com.cow.resp.R;
import com.cow.service.ProductPriceCustomerItemService;
import com.cow.service.ProductPriceGoodsItemService;
import com.cow.service.ProductPriceService;
import com.cow.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 产品价格 前端控制器
 * </p>
 *
 * @author cow
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/product-price")
public class ProductPriceController {
    
    @Autowired
    private ProductPriceService productPriceService;
    @Autowired
    private ProductPriceGoodsItemService productPriceGoodsItemService;
    @Autowired
    private ProductPriceCustomerItemService productPriceCustomerItemService;
    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerFeignClient customerFeignClient;

    /**
     * 加载列表数据
     */
    @GetMapping("/list")
    public R list(ProductPriceDTO productPriceDTO) {
        Page<ProductPrice> page = productPriceService.pageData(productPriceDTO);
        return R.ok().data(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    public R add(@RequestBody ProductPrice productPrice) {
        productPriceService.add(productPrice);
        return R.ok();
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    public R update(@RequestBody ProductPrice productPrice) {
        productPriceService.update(productPrice);
        return R.ok();
    }

    /**infoCustomerItem
     * 审核
     */
    @PostMapping("/audit")
    public R audit(@RequestBody ProductPrice productPrice) {
        productPrice.setState(CommonState.AUDIT.getState());
        productPriceService.update(productPrice);
        return R.ok();
    }

    /**
     * 取消
     */
    @PostMapping("/cancel")
    public R cancel(Long id) {
        ProductPrice productPrice = productPriceService.getById(id);
        productPriceService.cancel(productPrice);
        return R.ok();
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        Map<String, Object> productPriceMap = productPriceService.detail(id);
        return R.ok().data(productPriceMap);
    }

    /**
     * 失效产品行 <br/>
     * 根据产品明细行id失效
     * @return
     */
    @PostMapping("/invalidProduct")
    public R invalidProduct(Long productItemId) {
        ProductPriceGoodsItem productItem = productPriceGoodsItemService.getById(productItemId);
        Assert.isTrue(CommonState.CANCEL.getState() != productItem.getState(), "该产品已经失效，无需重复操作！");
        productItem.setState(CommonState.CANCEL.getState());
        productItem.setInvalidTime(new Date());
        productPriceGoodsItemService.updateById(productItem);
        return R.ok();
    }

    /**
     * 失效客户行 <br/>
     * 根据产品明细行id失效
     * @return
     */
    @PostMapping("/invalidCustomer")
    public R invalidCustomer(Long customerItemId) {
        ProductPriceCustomerItem customerItem = productPriceCustomerItemService.getById(customerItemId);
        Assert.isTrue(CommonState.CANCEL.getState() != customerItem.getState(), "该客户已经失效，无需重复操作！");
        customerItem.setState(CommonState.CANCEL.getState());
        customerItem.setInvalidTime(new Date());
        productPriceCustomerItemService.updateById(customerItem);
        return R.ok();
    }

    /**
     * 获取价格表信息
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        ProductPrice productPrice = productPriceService.getById(id);
        return R.ok().data(productPrice);
    }

    /**
     * 获取有效的客户明细
     * @param productPriceId 价格表id
     * @param customerId     客户id
     * @return
     */
    @GetMapping(value = "/getValidCustomerItem")
    public R getValidCustomerItem(@RequestParam("productPriceId") Long productPriceId, @RequestParam("customerId") Long customerId) {
        ProductPriceCustomerItem customerItem = productPriceCustomerItemService.getOne(
                Wrappers.<ProductPriceCustomerItem>lambdaQuery()
                        .eq(ProductPriceCustomerItem::getProductPrice, productPriceId)
                        .eq(ProductPriceCustomerItem::getCustomer, customerId)
                        .eq(ProductPriceCustomerItem::getState, CommonState.AUDIT.getState()));
        return R.ok().data(customerItem);
    }

    /**
     * 获取价格表客户明细信息
     * @param customerItemId
     * @return
     */
    @GetMapping("/infoCustomerItem/{customerItemId}")
    public R infoCustomerItem(@PathVariable("customerItemId") Long customerItemId) {
        ProductPriceCustomerItem customerItem = productPriceCustomerItemService.getById(customerItemId);
        return R.ok().data(customerItem);
    }

    /**
     * 获取价格表产品明细信息
     * @param productItemId
     * @return
     */
    @GetMapping("/infoProductItem/{productItemId}")
    public R infoProductItem(@PathVariable("productItemId") Long productItemId) {
        ProductPriceGoodsItem goodsItem = productPriceGoodsItemService.getById(productItemId);
        return R.ok().data(goodsItem);
    }

    /**
     * 增加产品行的已使用数量
     * @param productItemId
     * @param useQuantity 增加的数量
     * @return
     */
    @PostMapping("/addProductItemUseQuantity")
    public R addProductItemUseQuantity(@RequestParam("productPriceId") Long productItemId, @RequestParam("useQuantity") BigDecimal useQuantity) {
        productPriceService.addProductItemUseQuantity(productItemId, useQuantity);
        return R.ok();
    }
}

