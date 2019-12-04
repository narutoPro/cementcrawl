package cn.chende.seimi.controller;

import cn.chende.seimi.repository.PageRepository;
import cn.chende.seimi.spring.common.CrawlerCache;
import cn.chende.seimi.struct.CrawlerModel;
import cn.chende.seimi.struct.Request;
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
    public String cementPage(@PathVariable Long id){

        return pageRepository.findOne(id).getHtmlText();
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
