import { Session } from "next-auth";
import useSWR from "swr";
import { KorisnikReqDTO, KorisnikResDTO } from "../types/korisnik";
import { fetcher, URL } from "./base";

export const getKorisnici = (session: Session) => {
  const { data, error, mutate } = useSWR<[KorisnikResDTO]>(
    URL + `/korisnik`,
    (url) => fetcher(session, url)
  );
  return {
    data,
    isLoading: !data && !error,
    error: error,
    mutate,
  };
};

export const banKorisnik = (session: Session, id: number) => {
  return fetcher(session, URL + `/korisnik/ban/${id}`, { method: "PUT" });
};

export const unBanKorisnik = (session: Session, id: number) => {
  return fetcher(session, URL + `/korisnik/unban/${id}`, { method: "PUT" });
};

export const createAdminKlinike = (session: Session, data: KorisnikReqDTO) => {
  return fetcher(session, URL + `/adminklinike`, {
    method: "POST",
    body: JSON.stringify(data),
  });
};

export const createAdmin = (session: Session, data: KorisnikReqDTO) => {
  return fetcher(session, URL + `/superadmini`, {
    method: "POST",
    body: JSON.stringify(data),
  });
};
