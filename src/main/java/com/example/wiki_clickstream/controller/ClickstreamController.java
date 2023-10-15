package com.example.wiki_clickstream.controller;


import com.example.wiki_clickstream.entity.Clickstream;
import com.example.wiki_clickstream.service.IClickstreamService;
import com.example.wiki_clickstream.utils.Result;
import com.example.wiki_clickstream.vo.ClickstreamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
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

    @GetMapping("/date-range")
    public Result<Map<String, Object>> getDateRange() {
        Map<String, Object> dateRangeData = clickstreamService.getDateRange();
        return Result.success(dateRangeData);
    }

    @GetMapping("/center/{date}")
    public Result<List<Clickstream>> getCenters(@PathVariable String date) {
        List<Clickstream> clickstreams = clickstreamService.getCenters(date);
        return Result.success(clickstreams);
    }

    @GetMapping("/list/{date}")
    public Result<Map<String, Object>> getList(@PathVariable String date,
                                               @RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @RequestParam(defaultValue = "") String keyword) {
        Map<String, Object> clickstreams = clickstreamService.getList(date, pageNum, pageSize, keyword);
        return Result.success(clickstreams);
    }

    @GetMapping("/detail/{date}")
    public Result<List<Clickstream>> getDetail(@PathVariable String date, @RequestParam Integer center) {
        List<Clickstream> clickstreams = clickstreamService.getDetail(date, center);
        return Result.success(clickstreams);
    }

}

