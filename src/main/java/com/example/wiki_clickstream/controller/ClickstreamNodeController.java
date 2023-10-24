package com.example.wiki_clickstream.controller;


import com.example.wiki_clickstream.entity.ClickstreamNode;
import com.example.wiki_clickstream.service.IClickstreamNodeService;
import com.example.wiki_clickstream.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    public ResponseEntity<Result<List<ClickstreamNode>>> getCenterNodes(@PathVariable String date) {
        List<ClickstreamNode> clickstreamNodes = clickstreamNodeService.getCenterNodes(date);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS)) // 缓存1小时
                .body(Result.success(clickstreamNodes));
    }

    @GetMapping("/list/{date}")
    public ResponseEntity<Result<Object>> getClusterNodes(@PathVariable String date,
                                                          @RequestParam(defaultValue = "1") Integer pageNum,
                                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                                          @RequestParam(defaultValue = "") String keyword) {
        Map<String, Object> clickstreams = clickstreamNodeService.getNodeList(date, pageNum, pageSize, keyword);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS)) // 缓存1小时
                .body(Result.success(clickstreams));
    }

    @GetMapping("/cluster/{date}")
    public ResponseEntity<Result<List<ClickstreamNode>>> getClusterNodes(@PathVariable String date, @RequestParam Integer center) {
        List<ClickstreamNode> clickstreamNodes = clickstreamNodeService.getClusterNodes(date, center);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS)) // 缓存1小时
                .body(Result.success(clickstreamNodes));
    }

}

