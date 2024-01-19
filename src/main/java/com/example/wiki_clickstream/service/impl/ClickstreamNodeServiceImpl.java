package com.example.wiki_clickstream.service.impl;

import com.example.wiki_clickstream.entity.ClickstreamNode;
import com.example.wiki_clickstream.mapper.ClickstreamNodeMapper;
import com.example.wiki_clickstream.service.IClickstreamNodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wiki_clickstream.vo.ClickstreamNodeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-09-23
 */
@Service
public class ClickstreamNodeServiceImpl extends ServiceImpl<ClickstreamNodeMapper, ClickstreamNode> implements IClickstreamNodeService {

    @Resource
    private ClickstreamNodeMapper clickstreamNodeMapper;

    @Override
    @Cacheable(value = "dateRangeCache", key = "'clickstream_node_date_range_' + #lang")
    public Map<String, Object> getDateRange(String lang) {
        Map<String, Object> dateRangeData = new HashMap<>();
        List<String> dateRanges = clickstreamNodeMapper.getDateRanges(lang);

        List<String> years = new ArrayList<>();
        Map<Integer, List<Integer>> months = new HashMap<>();
        int latestYear = 0;
        int latestMonth = 0;

        for (String dateRange : dateRanges) {
            String[] parts = dateRange.split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);

            if (!years.contains(parts[0])) {
                years.add(parts[0]);
            }

            if (!months.containsKey(year)) {
                months.put(year, new ArrayList<>());
            }
            months.get(year).add(month);

            if (year > latestYear || (year == latestYear && month > latestMonth)) {
                latestYear = year;
                latestMonth = month;
            }
        }

        // 对 years 列表进行排序
        Collections.sort(years);
        Collections.reverse(years);

        // 对 months 列表中的每个年份的月份列表进行排序
        for (List<Integer> monthList : months.values()) {
            Collections.sort(monthList);
        }

        dateRangeData.put("years", years);
        dateRangeData.put("months", months);
        dateRangeData.put("latestDate", String.valueOf(latestYear) + '-' + String.valueOf(latestMonth));

        return dateRangeData;
    }

    @Override
    @Cacheable(value = "clickstreamNodeListCache", key = "'clickstream_node_list_' + #lang + '_' + #dateStr + '_' + #pageNum + '_' + #pageSize + '_' + #keyword")
    public Map<String, Object> getNodeList(String lang, String dateStr, Integer pageNum, Integer pageSize, String keyword) {
        LocalDate parsedDate = LocalDate.parse(dateStr.concat("-01"), DateTimeFormatter.ofPattern("yyyy-M-dd"));
        List<ClickstreamNode> clickstreamNodes = clickstreamNodeMapper.getNodeList(lang, parsedDate, (pageNum - 1) * pageSize, pageSize, keyword);

        List<ClickstreamNodeVo> list = new ArrayList<>();
        ClickstreamNodeVo currentCenter = null;

        for (ClickstreamNode clickstreamNode : clickstreamNodes) {
            if (currentCenter == null || !clickstreamNode.getDcDictIdx().equals(currentCenter.getDcDictIdx())) {
                currentCenter = new ClickstreamNodeVo();
                BeanUtils.copyProperties(clickstreamNode, currentCenter);
                currentCenter.setMembers(new ArrayList<>());
                list.add(currentCenter);
            } else {
                currentCenter.getMembers().add(clickstreamNode);
            }
        }

        Long total = clickstreamNodeMapper.getListTotal(lang, parsedDate, keyword);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);

        return result;
    }

    @Cacheable(value = "clickstreamNodeCenterCache", key = "'clickstream_node_center_' + #lang + '_' + #dateStr")
    public List<ClickstreamNode> getCenterNodes(String lang, String dateStr) {
        LocalDate parsedDate = LocalDate.parse(dateStr.concat("-01"), DateTimeFormatter.ofPattern("yyyy-M-dd"));
        return clickstreamNodeMapper.getCenterNodes(lang, parsedDate);
    }

    @Override
    @Cacheable(value = "clickstreamNodeClusterCache", key = "'clickstream_node_cluster_' + #lang + '_' + #dateStr + '_' + #center")
    public List<ClickstreamNode> getClusterNodes(String lang, String dateStr, Integer center) {
        LocalDate parsedDate = LocalDate.parse(dateStr.concat("-01"), DateTimeFormatter.ofPattern("yyyy-M-dd"));
        return clickstreamNodeMapper.getClusterNodes(lang, parsedDate, center);
    }
}
