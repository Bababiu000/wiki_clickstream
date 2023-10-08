package com.example.wiki_clickstream.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.example.wiki_clickstream.entity.Clickstream;
import com.example.wiki_clickstream.mapper.ClickstreamMapper;
import com.example.wiki_clickstream.service.IClickstreamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wiki_clickstream.vo.ClickstreamVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-09-23
 */
@Service
public class ClickstreamServiceImpl extends ServiceImpl<ClickstreamMapper, Clickstream> implements IClickstreamService {

    @Resource
    private ClickstreamMapper clickstreamMapper;

    @Override
    public Map<String, Object> getDateRange() {

        Map<String, Object> dateRangeData = new HashMap<>();
        List<String> dateRanges = clickstreamMapper.getDateRanges();

        List<String> years = new ArrayList<>();
        Map<Integer, List<Integer>> months = new HashMap<>();
        int maxYear = 0;
        int maxMonth = 0;

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

            if (year > maxYear || (year == maxYear && month > maxMonth)) {
                maxYear = year;
                maxMonth = month;
            }
        }

        dateRangeData.put("years", years);
        dateRangeData.put("months", months);
        dateRangeData.put("maxYear", maxYear);
        dateRangeData.put("maxMonth", maxMonth);

        return dateRangeData;
    }

    @Override
    public Map<String, Object> getList(String dateStr, Integer pageNum, Integer pageSize) {
        LocalDate parsedDate = LocalDate.parse(dateStr.concat("-01"), DateTimeFormatter.ofPattern("yyyy-M-dd"));
        List<Clickstream> clickstreams = clickstreamMapper.getList(parsedDate, (pageNum - 1) * pageSize, pageSize);

        List<ClickstreamVo> list = new ArrayList<>();
        ClickstreamVo currentCenter = null;

        for (Clickstream clickstream : clickstreams) {
            if (currentCenter == null || !clickstream.getDcDictIdx().equals(currentCenter.getDcDictIdx())) {
                currentCenter = new ClickstreamVo();
                BeanUtils.copyProperties(clickstream, currentCenter);
                currentCenter.setMembers(new ArrayList<>());
                list.add(currentCenter);
            } else {
                currentCenter.getMembers().add(clickstream);
            }
        }

        QueryWrapper<Clickstream> wrapper = new QueryWrapper<>();
        wrapper.eq("date", parsedDate).apply("dict_idx = dc_dict_idx");
        Long total = clickstreamMapper.selectCount(wrapper);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);

        return result;
    }

    public List<Clickstream> getCenters(String dateStr) {
        LocalDate parsedDate = LocalDate.parse(dateStr.concat("-01"), DateTimeFormatter.ofPattern("yyyy-M-dd"));
        return clickstreamMapper.getCenters(parsedDate);
    }

    @Override
    public List<Clickstream> getDetail(String dateStr, Integer center) {
        LocalDate parsedDate = LocalDate.parse(dateStr.concat("-01"), DateTimeFormatter.ofPattern("yyyy-M-dd"));
        return clickstreamMapper.getDetail(parsedDate, center);
    }
}
