package nl.project.poll;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated 
@RequestMapping("/api/poll/")
public class PollRestApi {

}
