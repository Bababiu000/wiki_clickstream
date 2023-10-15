package com.example.wiki_clickstream.service;

import com.example.wiki_clickstream.entity.Clickstream;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2023-09-23
 */
public interface IClickstreamService extends IService<Clickstream> {
    Map<String, Object> getDateRange();

    Map<String, Object> getList(String date, Integer pageNum, Integer pageSize, String keyword);

    List<Clickstream> getCenters(String date);

    List<Clickstream> getDetail(String date, Integer center);

}
