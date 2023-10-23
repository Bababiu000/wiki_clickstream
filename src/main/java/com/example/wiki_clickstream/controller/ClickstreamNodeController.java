package com.example.wiki_clickstream.controller;


import com.example.wiki_clickstream.entity.ClickstreamNode;
import com.example.wiki_clickstream.service.IClickstreamNodeService;
import com.example.wiki_clickstream.utils.Result;
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
@RequestMapping("/clickstream_node")
public class ClickstreamNodeController {
    @Autowired
    private IClickstreamNodeService clickstreamNodeService;

    @GetMapping("/date_range")
    public Result<Map<String, Object>> getDateRange() {
        Map<String, Object> dateRangeData = clickstreamNodeService.getDateRange();
        return Result.success(dateRangeData);
    }

    @GetMapping("/center/{date}")
    public Result<List<ClickstreamNode>> getCenters(@PathVariable String date) {
        List<ClickstreamNode> clickstreamNodes = clickstreamNodeService.getCenters(date);
        return Result.success(clickstreamNodes);
    }

    @GetMapping("/list/{date}")
    public Result<Map<String, Object>> getList(@PathVariable String date,
                                               @RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @RequestParam(defaultValue = "") String keyword) {
        Map<String, Object> clickstreams = clickstreamNodeService.getList(date, pageNum, pageSize, keyword);
        return Result.success(clickstreams);
    }

    @GetMapping("/cluster/{date}")
    public Result<List<ClickstreamNode>> getDetail(@PathVariable String date, @RequestParam Integer center) {
        List<ClickstreamNode> clickstreamNodes = clickstreamNodeService.getDetail(date, center);
        return Result.success(clickstreamNodes);
    }

}

