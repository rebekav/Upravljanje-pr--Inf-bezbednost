package com.fax.lekari.service;

public interface EmailService {
    void zakazanPregledEmail(String klinika, String nazivPregleda, String vreme, String lekar, String emailTo);
}
