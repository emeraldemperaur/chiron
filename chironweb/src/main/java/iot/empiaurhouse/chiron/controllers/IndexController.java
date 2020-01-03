package iot.empiaurhouse.chiron.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class IndexController {

    @RequestMapping({"","/", "index", "index.html"})
    public String index(Model indexModel){

        String pattern = "E, dd MMM yyyy zzzz";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        indexModel.addAttribute("date", date);

        return "index";
    }

}
