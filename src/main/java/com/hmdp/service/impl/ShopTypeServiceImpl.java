package com.hmdp.service.impl;

import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *h
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private IShopTypeService typeService;

    @Override
    public List<ShopType> getTypeList() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:MM");
        String shopTypeKey = "cache:shopType";
        List<ShopType> typeList = null;
        List<String> stringTypeList = stringRedisTemplate.opsForList().range(shopTypeKey, 0, -1);
        if(!stringTypeList.isEmpty()){
            for(String str :stringTypeList){
                String[] shopStr = str.split(",");
                ShopType shopType = new ShopType();
                shopType.setId(Long.parseLong(shopStr[0]));
                shopType.setName(shopStr[1]);
                shopType.setIcon(shopStr[2]);
                shopType.setSort(Integer.parseInt(shopStr[3]));
//                LocalDateTime creatTime = LocalDateTime.parse(shopStr[4], DateTimeFormatter.ofPattern("yyyyMMdd HH:mm"));
//                LocalDateTime updateTime = LocalDateTime.parse(shopStr[5], DateTimeFormatter.ofPattern("yyyyMMdd HH:mm"));
//                shopType.setCreateTime(creatTime);
//                shopType.setUpdateTime(updateTime);
                typeList.add(shopType);
            }

        }else {

            typeList = typeService.query().orderByAsc("sort").list();
            stringRedisTemplate.opsForList().leftPushAll(shopTypeKey, String.valueOf(typeList));

        }


        return typeList;
    }
}
