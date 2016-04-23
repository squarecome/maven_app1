package com.fanglai.utils.fileupload;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
 
@Controller
@SessionAttributes("status")
public class ProgressController {
 
    @RequestMapping(value = "/upfile/progress", method = RequestMethod.GET)
    @ResponseBody
    public String initCreateInfo(Map<String, Object> model) {
        Progress status = (Progress) model.get("status");
        if(status==null){
            return "{}";
        }
        return status.toString();
    }
}
