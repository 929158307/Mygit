import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pinyougou.core.pojo.ad.Content;
import pinyougou.core.serviceinterface.ContentService;

import javax.xml.ws.RequestWrapper;
import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Reference
    private ContentService contentServiceImpl;
    public List<Content> findByCateId(Long categoryid){
        return contentServiceImpl.findByCateId(categoryid);
    }
}
