package com.tony.erp.service;

import com.github.pagehelper.PageHelper;
import com.tony.erp.dao.OrderMapper;
import com.tony.erp.domain.Order;
import com.tony.erp.utils.CurrentUser;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public List<Order> getAllOrders(int pageNum){
        PageHelper.startPage(pageNum,10);
        return orderMapper.find(null);
    }

    public int addOrder(Order order){
        order.setOId(KeyGeneratorUtils.keyUUID());
        order.setOCreateDate(KeyGeneratorUtils.dateGenerator());
        order.setOCreator(CurrentUser.getCurrentUser().getUname());
        order.setOStatus("1");
        return orderMapper.insertSelective(order);
    }

    public int delOrder(String oid){
        return orderMapper.deleteByPrimaryKey(oid);
    }

    public int upOrder(Order order){
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    public Order getByOid(String oid){
        return orderMapper.selectByPrimaryKey(oid);
    }

}
