import { Session } from "next-auth";
import useSWR from "swr";
import { PregledResDTOToEvent } from "../adapters";
import { KorisnikResDTO } from "../types/korisnik";
import { fetcher, URL } from "./base";

export const getRadniKalendar = (session: Session) => {
  const { data, error, mutate } = useSWR<[PregledResDTO]>(
    URL + `/lekari/pregledi`,
    (url) => fetcher(session, url)
  );
  const out = data?.map((v) => PregledResDTOToEvent(v));
  return {
    data: out,
    isLoading: !data && !error,
    error: error,
    mutate,
  };
};

export const getPacijenti = (session: Session) => {
  const { data, error, mutate } = useSWR<[KorisnikResDTO]>(
    URL + `/lekari/pacijenti`,
    (url) => fetcher(session, url)
  );
  return {
    data,
    isLoading: !data && !error,
    error: error,
    mutate,
  };
};
