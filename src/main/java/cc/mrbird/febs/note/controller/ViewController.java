package cc.mrbird.febs.note.controller;

import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("noteView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "note")
public class ViewController extends BaseController {


}
