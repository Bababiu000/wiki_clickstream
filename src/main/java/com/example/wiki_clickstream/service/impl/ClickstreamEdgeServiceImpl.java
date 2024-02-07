package com.example.wiki_clickstream.service.impl;

import com.example.wiki_clickstream.entity.ClickstreamEdge;
import com.example.wiki_clickstream.mapper.ClickstreamEdgeMapper;
import com.example.wiki_clickstream.service.IClickstreamEdgeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-10-21
 */
@Service
public class ClickstreamEdgeServiceImpl extends ServiceImpl<ClickstreamEdgeMapper, ClickstreamEdge> implements IClickstreamEdgeService {

    @Resource
    private ClickstreamEdgeMapper clickstreamEdgeMapper;

    @Override
    @Cacheable(value = "clickstreamEdgeCenterCache", key = "'clickstream_edge_center_' + #lang + '_' + #dateStr")
    public List<ClickstreamEdge> getCenterEdges(String lang, String dateStr) {
        LocalDate parsedDate = LocalDate.parse(dateStr.concat("-01"), DateTimeFormatter.ofPattern("yyyy-M-dd"));
        return clickstreamEdgeMapper.getCenterEdges(lang, parsedDate);
    }

    @Override
    @Cacheable(value = "clickstreamEdgeClusterCache", key = "'clickstream_edge_cluster_' + #lang + '_' + #dateStr + '_' + #label")
    public List<ClickstreamEdge> getClusterEdges(String lang, String dateStr, Integer label) {
        LocalDate parsedDate = LocalDate.parse(dateStr.concat("-01"), DateTimeFormatter.ofPattern("yyyy-M-dd"));
        return clickstreamEdgeMapper.getClusterEdges(lang, parsedDate, label);
    }
}
