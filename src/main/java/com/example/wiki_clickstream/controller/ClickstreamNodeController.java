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

    @GetMapping("/date_range/{lang}")
    public ResponseEntity<Result<Map<String, Object>>> getDateRange(@PathVariable String lang) {
        Map<String, Object> dateRangeData = clickstreamNodeService.getDateRange(lang);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS)) // 缓存1小时
                .body(Result.success(dateRangeData));
    }

    @GetMapping("/center/{lang}/{date}")
    public ResponseEntity<Result<List<ClickstreamNode>>> getCenterNodes(@PathVariable String lang, @PathVariable String date) {
        List<ClickstreamNode> clickstreamNodes = clickstreamNodeService.getCenterNodes(lang, date);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS)) // 缓存1小时
                .body(Result.success(clickstreamNodes));
    }

    @GetMapping("/list/{lang}/{date}")
    public ResponseEntity<Result<Object>> getClusterNodes(@PathVariable String lang,
                                                          @PathVariable String date,
                                                          @RequestParam(defaultValue = "1") Integer pageNum,
                                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                                          @RequestParam(defaultValue = "") String keyword) {
        Map<String, Object> clickstreams = clickstreamNodeService.getNodeList(lang, date, pageNum, pageSize, keyword);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS)) // 缓存1小时
                .body(Result.success(clickstreams));
    }

    @GetMapping("/cluster/{lang}/{date}")
    public ResponseEntity<Result<List<ClickstreamNode>>> getClusterNodes(@PathVariable String lang, @PathVariable String date, @RequestParam Integer center) {
        List<ClickstreamNode> clickstreamNodes = clickstreamNodeService.getClusterNodes(lang, date, center);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS)) // 缓存1小时
                .body(Result.success(clickstreamNodes));
    }

    @GetMapping("/similarity/{lang}")
    public ResponseEntity<Result<Map<String, Object>>> getMonthlyClusterSimilarity(@PathVariable String lang, @RequestParam String date1, @RequestParam String date2) {
        Map<String, Object> monthlyClusterSimilarity = clickstreamNodeService.getMonthlyClusterSimilarity(lang, date1, date2);
        return ResponseEntity.ok()
//                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS)) // 缓存1小时
                .body(Result.success(monthlyClusterSimilarity));
    }
}

