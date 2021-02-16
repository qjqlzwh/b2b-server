package com.cow.service.impl;

import com.cow.po.pojo.CustomerAddress;
import com.cow.mapper.CustomerAddressMapper;
import com.cow.service.CustomerAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cow
 * @since 2021-02-02
 */
@Service
public class CustomerAddressServiceImpl extends ServiceImpl<CustomerAddressMapper, CustomerAddress> implements CustomerAddressService {

}
