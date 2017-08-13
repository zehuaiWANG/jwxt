package com.jwxt.controller.protal;

import com.jwxt.pojo.Loginfo;
import com.jwxt.service.ILoginfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/log/")
public class LoginfoController {

    @Autowired
    private ILoginfoService iLoginfoService;
    @RequestMapping(value ="getallLoginfo.do",method = RequestMethod.GET)
    @ResponseBody
    public List<Loginfo> getallLoginfo(){
        List<Loginfo> list = iLoginfoService.findLoginfoList();
        return list;
    }
}
