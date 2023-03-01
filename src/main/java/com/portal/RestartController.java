package com.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RestartController {

	@PostMapping("/restart")
    public void restart() {
        PatientPortalServiceApplication.restart();
    } 
}
