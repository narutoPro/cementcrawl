package cn.wanghaomiao.seimi.controller;

import cn.wanghaomiao.seimi.model.Page;
import cn.wanghaomiao.seimi.repository.PageRepository;
import cn.wanghaomiao.seimi.spring.common.CrawlerCache;
import cn.wanghaomiao.seimi.struct.CrawlerModel;
import cn.wanghaomiao.seimi.struct.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author github.com/zhegexiaohuozi seimimaster@gmail.com
 */
@RestController
@RequestMapping(value = "/seimi")
public class IndexController {
    @Autowired
    PageRepository pageRepository;
    @RequestMapping(value = "/cement/{id}")
    public Page cementPage(@PathVariable Long id){

        return pageRepository.findOne(id);
    }

    @RequestMapping(value = "/info/{cname}")
    public String crawler(@PathVariable String cname) {
        CrawlerModel model = CrawlerCache.getCrawlerModel(cname);
        if (model == null) {
            return "not find " + cname;
        }
        return model.queueInfo();
    }

    @RequestMapping(value = "send_req")
    public String sendRequest(Request request){
        CrawlerCache.consumeRequest(request);
        return "consume suc";
    }
}
