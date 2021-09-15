export type PregledResDTO = {
  id: number;
  podaciOPregledu: string;
  popust: number;
  trajanje: number;
  vreme: string;
  nazivUsluge: string;
  cenaUsluge: string;
  lekar: string;
  nazivKlinike: string;
  adresaKlinike: string;
  pacijent: string;
};

export type PregledReqDTO = {
  uslugaId: number;
  medicinskaSestraId: number;
  lekarId: number;
  vreme: string | Date;
  trajanje: number;
  popust: number;
  podaciOPregledu: string;
};

export type PregledLightDTO = {
  id: number;
  usluga: string;
  pacijent: string;
  pacijent_id: number;
  lekar: string;
  sestra: string;
  podaciOPregledu: string;
  recepti: Array<{
    id: number;
    naziv: string;
    napomena: string;
  }>;
};
