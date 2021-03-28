package com.cow.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.CustomerDTO;
import com.cow.po.pojo.Customer;
import com.cow.mapper.CustomerMapper;
import com.cow.po.pojo.CustomerAddress;
import com.cow.po.pojo.User;
import com.cow.service.CustomerAddressService;
import com.cow.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cow.service.UserService;
import com.cow.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  客户服务实现类
 * </p>
 *
 * @author cow
 * @since 2021-02-01
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Autowired
    private CustomerAddressService customerAddressService;
    @Autowired
    private UserService userService;
    @Resource
    private CustomerMapper customerMapper;

    /**
     * 列表数据
     *
     * @param customerDTO
     * @return
     */
    @Override
    public Page<Map<String, Object>> pageData(CustomerDTO customerDTO) {
//        QueryWrapper<Customer> qw = new QueryWrapper<>();
//        QwUtils.eq(qw, "salesman", customerDTO.getSalesman());
//        QwUtils.eq(qw, "dname", customerDTO.getDname());
//        QwUtils.eq(qw, "dcode", customerDTO.getDcode());
//        QwUtils.eq(qw, "is_enabled", customerDTO.getIsEnabled());
        Page<Map<String, Object>> pageData = baseMapper.pageData(customerDTO.page(), customerDTO);
        return pageData;
    }

    /**
     * 新增<br/>
     *   1、创建客户时，自动创建一个用户（用于登录）
     *
     * @param customer
     */
    @Override
    @Transactional
    public void add(Customer customer) {
        handleSaveOrUpdate(customer);
        customer.setDcode(IdUtils.snfId());

        // 生成登录账号
        addUser(customer);

        baseMapper.insert(customer);
        List<CustomerAddress> customerAddressList = customer.getCustomerAddressList();
        for (CustomerAddress address : customerAddressList) {
            address.setCustomerId(customer.getId());
        }
        customerAddressService.saveBatch(customerAddressList);
    }

    /**
     * 生成客户登录账号
     */
    private void addUser(Customer customer) {
        User user = new User();
        user.setPassword("666666");
        user.setUsername(customer.getDname());
        user.setIsEnabled(true);
        user.setUserType(1);
        userService.add(user);

        customer.setUserId(user.getId());
    }

    /**
     * 更新
     *
     * @param customer
     */
    @Override
    public void update(Customer customer) {
        handleSaveOrUpdate(customer);
        baseMapper.updateById(customer);
        customerAddressService.saveOrUpdateBatch(customer.getCustomerAddressList());
    }

    /**
     * 弹框分页数据
     *
     * @param customerDTO
     * @return
     */
    @Override
    public Page<Map<String, Object>> pageDataPop(CustomerDTO customerDTO) {
        return customerMapper.pageDataPop(customerDTO.page(), customerDTO);
    }

    private void handleSaveOrUpdate(Customer customer) {
        Assert.notEmpty(customer.getCustomerAddressList(), "客户地址不能为空！");
        Assert.hasText(customer.getDname(), "客户名称不能为空！");
        Customer exCustomer = null;
        if (customer.getId() != null) {
            exCustomer = baseMapper.selectOne(
                    Wrappers.<Customer>lambdaQuery().eq(Customer::getDname, customer.getDname()).ne(Customer::getId, customer.getId()));
        } else {
            exCustomer = baseMapper.selectOne(Wrappers.<Customer>lambdaQuery().eq(Customer::getDname, customer.getDname()));
        }
        Assert.isNull(exCustomer, "客户名称已经存在！");
        Assert.notNull(customer.getSalesman(), "业务员不能为空！");
        Assert.notNull(customer.getOrganization(), "机构不能为空！");

        List<CustomerAddress> customerAddressList = customer.getCustomerAddressList();
        for (CustomerAddress address : customerAddressList) {
            Assert.hasText(address.getConsignee(), "收货人不能为空！");
            Assert.hasText(address.getPhone(), "收货人电话不能为空！");
            Assert.hasText(address.getDetailedAddress(), "收货人地址不能为空！");
            address.setCustomerId(customer.getId());
        }
    }
}
