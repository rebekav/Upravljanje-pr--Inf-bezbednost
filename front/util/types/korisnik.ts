export type KorisnikResDTO = {
  id: number;
  email: string;
  roles: Array<string>;
  adresa?: string;
  identifikator: string;
  klinikaDtoRes?: any;
  pass: string;
  prezime: string;
  ime: string;
  telefon?: string;
  validiran: number;
  changePass: boolean;
};
export type KorisnikReqDTO = {
  email: string;
  adresa: string;
  identifikator: string;
  idKlinika: number;
  pass: string;
  prezime: string;
  ime: string;
  telefon: string;
};
