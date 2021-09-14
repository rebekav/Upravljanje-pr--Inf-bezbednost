package com.fax.lekari.service.impl;

import com.fax.lekari.service.EmailService;
import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {


    @Override
    public void zakazanPregledEmail(String klinika, String nazivPregleda, String vreme, String lekar, String emailTo) {
        VelocityContext context = new VelocityContext();
        context.put("klinika", klinika);
        context.put("nazivPregleda", nazivPregleda);
        context.put("vreme", vreme);
        context.put("lekar", lekar);

    }
}
