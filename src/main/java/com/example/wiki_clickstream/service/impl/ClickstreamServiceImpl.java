package com.example.wiki_clickstream.service.impl;

import com.example.wiki_clickstream.entity.Clickstream;
import com.example.wiki_clickstream.mapper.ClickstreamMapper;
import com.example.wiki_clickstream.service.IClickstreamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2023-09-23
 */
@Service
public class ClickstreamServiceImpl extends ServiceImpl<ClickstreamMapper, Clickstream> implements IClickstreamService {

    @Resource
    private ClickstreamMapper clickstreamMapper;

    public List<Clickstream> getDataByDate(String dateStr) {
        LocalDate parsedDate = LocalDate.parse(dateStr);
        return clickstreamMapper.getDataByDate(parsedDate);
    }
}
