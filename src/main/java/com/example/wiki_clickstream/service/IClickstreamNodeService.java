package com.example.wiki_clickstream.service;

import com.example.wiki_clickstream.entity.ClickstreamNode;
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
public interface IClickstreamNodeService extends IService<ClickstreamNode> {
    Map<String, Object> getDateRange();

    Map<String, Object> getList(String date, Integer pageNum, Integer pageSize, String keyword);

    List<ClickstreamNode> getCenters(String date);

    List<ClickstreamNode> getDetail(String date, Integer center);

}
