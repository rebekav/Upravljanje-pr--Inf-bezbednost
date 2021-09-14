package com.fax.lekari.dto;

import com.fax.lekari.model.User;


public class PregledDTOReq {
    private String podaciOPregledu;
    private int popust;
    private int trajanje;
    private String vreme;
    private Integer lekarId;
    private Integer medicinskaSestraId;
    private Integer uslugaId;

    public String getPodaciOPregledu() {
        return podaciOPregledu;
    }

    public void setPodaciOPregledu(String podaciOPregledu) {
        this.podaciOPregledu = podaciOPregledu;
    }

    public int getPopust() {
        return popust;
    }

    public void setPopust(int popust) {
        this.popust = popust;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public Integer getLekarId() {
        return lekarId;
    }

    public void setLekarId(Integer lekarId) {
        this.lekarId = lekarId;
    }

    public Integer getMedicinskaSestraId() {
        return medicinskaSestraId;
    }

    public void setMedicinskaSestraId(Integer medicinskaSestraId) {
        this.medicinskaSestraId = medicinskaSestraId;
    }

    public Integer getUslugaId() {
        return uslugaId;
    }

    public void setUslugaId(Integer uslugaId) {
        this.uslugaId = uslugaId;
    }
}
