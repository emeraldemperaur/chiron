package iot.empiaurhouse.chiron.controllers;

import iot.empiaurhouse.chiron.model.Pharmaceuticals;
import iot.empiaurhouse.chiron.services.PharmaceuticalsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequestMapping("/pharmaceuticals")
@Controller
public class PharmaceuticalController {

    private final PharmaceuticalsService pharmaceuticalsService;
    public static final String Rx_EDITOR_VIEW = "pharmaceuticals/rxeditor";


    public PharmaceuticalController(PharmaceuticalsService pharmaceuticalsService) {
        this.pharmaceuticalsService = pharmaceuticalsService;
    }

    @RequestMapping({"","/", "/index","/index.html"})
    public String listPharmaceuticals(Model pharmaceuticalsModel){

        Set<Pharmaceuticals> allRx = pharmaceuticalsService.findAll();
        int n = allRx.size();
        List<Integer> RxCounter = new ArrayList<Integer>(n);
        for (Pharmaceuticals r : allRx){
            Integer stockCount = r.getInStock();
            RxCounter.add(stockCount);
        }
        int sumCount = RxCounter.stream().mapToInt(Integer::intValue).sum();
        pharmaceuticalsModel.addAttribute("pharmaceuticals", allRx);
        pharmaceuticalsModel.addAttribute("pharmaceutical", new Pharmaceuticals());
        pharmaceuticalsModel.addAttribute("totalStock", sumCount);

        return "pharmaceuticals/index";
    }

    @PostMapping("/create")
    public String addNewPharmaceuticalsRecord(@Valid Pharmaceuticals pharmaceutical, BindingResult bindingResult){
         if(bindingResult.hasErrors()){
             return Rx_EDITOR_VIEW;
         }else {
            Pharmaceuticals stagedRx = pharmaceuticalsService.save(pharmaceutical);
            return "redirect:/pharmaceuticals/info/" + stagedRx.getId();
         }
    }

    @GetMapping({"/inform","/info/{RxId}"})
    public ModelAndView renderPharmaceuticalsInfo(@PathVariable("RxId") Long RxId){
        ModelAndView pharmaceuticalMV = new ModelAndView("pharmaceuticals/rxinformation");
        pharmaceuticalMV.addObject(pharmaceuticalsService.findById(RxId));

        return pharmaceuticalMV;
    }

    @GetMapping("/edit/{Id}")
    public String initPharmaceuticalEditorForm(@PathVariable Long Id, Model pharmaceuticalModel){
        pharmaceuticalModel.addAttribute(pharmaceuticalsService.findById(Id));
        return Rx_EDITOR_VIEW;
    }

    @PostMapping("/edit/{Id}")
    public String submitPharmaceuticalEditorForm(@Valid Pharmaceuticals pharmaceutical, BindingResult result, @PathVariable Long Id){
        if(result.hasErrors()){
            return Rx_EDITOR_VIEW;
        } else {
            pharmaceutical.setId(Id);
            Pharmaceuticals stagedRx = pharmaceuticalsService.save(pharmaceutical);
            return "redirect:/pharmaceuticals/info/" + stagedRx.getId();
        }
    }


    @GetMapping("/delete/{Id}")
    public String deletePharmaceuticalsRecordById(@PathVariable String Id){
        pharmaceuticalsService.deleteById(Long.valueOf(Id));
        return "redirect:/pharmaceuticals";
    }

}
