package com.example.wiki_clickstream.controller;


import com.example.wiki_clickstream.entity.Clickstream;
import com.example.wiki_clickstream.service.IClickstreamService;
import com.example.wiki_clickstream.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-09-23
 */
@RestController
@RequestMapping("/clickstream")
public class ClickstreamController {
    @Autowired
    private IClickstreamService clickstreamService;

    @GetMapping("/dateRange")
    public Result<Map<String, Object>> getDateRange() {
        Map<String, Object> dateRangeData = clickstreamService.getDateRange();
        return Result.success(dateRangeData);
    }

    @GetMapping("/dcRoot/{date}")
    public Result<List<Clickstream>> getDCRoot(@PathVariable String date) {
        List<Clickstream> clickstreams = clickstreamService.getDCRoot(date);
        return Result.success(clickstreams);
    }

    @GetMapping("/dcs/{date}")
    public Result<List<Clickstream>> getDCs(@PathVariable String date, @RequestParam Integer dcRoot) {
        List<Clickstream> clickstreams = clickstreamService.getDCs(date, dcRoot);
        return Result.success(clickstreams);
    }

}

