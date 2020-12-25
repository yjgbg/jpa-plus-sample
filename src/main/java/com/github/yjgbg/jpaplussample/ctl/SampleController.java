package com.github.yjgbg.jpaplussample.ctl;

import com.github.yjgbg.jpa.plus.annotations.ReturnPropsSetNull;
import com.github.yjgbg.jpaplussample.jpa.entity.CriusUser;
import com.github.yjgbg.jpaplussample.service.SampleSpecificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SampleController {
    private final SampleSpecificationService sampleService;

    @GetMapping("criusUser")
    @ReturnPropsSetNull("email")
    public CriusUser hello(Long id, String email) {
        return null;
    }
}
