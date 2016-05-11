package dudeco;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller 
public class TestController {
	
	@RequestMapping("/")
	public ModelAndView testIndex() {
		return new ModelAndView("testIndex", "model", "my message");
	}
}
