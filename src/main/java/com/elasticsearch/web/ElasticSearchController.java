package com.elasticsearch.web;

import com.elasticsearch.Model.Es;
import com.elasticsearch.service.QueryService;
import com.elasticsearch.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author quanroon.ysq
 * @version 1.0.0
 * @date 2020/2/29 17:29
 * @content
 */
@RestController
@RequestMapping(value = "${clientPath}/elasticsearch")
public class ElasticSearchController extends BaseController {
    @Autowired
    QueryService queryService;

    @RequestMapping("queryOrder")
    public ResponseMessage queryOrder(){
        Es es = new Es("search_index","search_index");
        List<Map<String, Object>> list = queryService
                .queryListFromES(es, 13,"旗舰店"+13, "2020-02-29", "2020-03-01");

        return getResponseMsg_success(list);
    }
}
