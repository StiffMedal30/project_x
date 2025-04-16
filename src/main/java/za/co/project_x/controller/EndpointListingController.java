package za.co.project_x.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.stream.Collectors;

@Controller
public class EndpointListingController {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @GetMapping("/endpoints")
    @ResponseBody
    public String listEndpoints() {
        return requestMappingHandlerMapping.getHandlerMethods().keySet().stream()
                .map(Object::toString)
                .sorted()
                .collect(Collectors.joining("\n"));
    }
}
