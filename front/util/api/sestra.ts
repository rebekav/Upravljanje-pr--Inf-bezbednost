import { Session } from "next-auth";
import useSWR from "swr";
import { PregledResDTOToEvent } from "../adapters";
import { KorisnikResDTO } from "../types/korisnik";
import { PregledResDTO } from "../types/pregled";
import { ReceptResDTO } from "../types/recept";
import { fetcher, URL } from "./base";

function getSort(sort: string): string {
  switch (sort) {
    case "1":
      return "?orderBy=ime&direction=asc";
    case "2":
      return "?orderBy=ime&direction=desc";
    case "3":
      return "?orderBy=id&direction=asc";
    case "4":
      return "?orderBy=id&direction=desc";
    default:
      return "";
  }
}

export const getPacijenti = (session: Session, sort: string) => {
  const { data, error, mutate } = useSWR<[KorisnikResDTO]>(
    URL + `/sestre/pacijenti${sort != "0" ? getSort(sort) : ""}`,
    (url) => fetcher(session, url)
  );
  return {
    data,
    isLoading: !data && !error,
    error: error,
    mutate,
  };
};

export const getRecepti = (session: Session) => {
  const { data, error, mutate } = useSWR<[ReceptResDTO]>(
    URL + `/sestre/recepti`,
    (url) => fetcher(session, url)
  );
  return {
    data,
    isLoading: !data && !error,
    error: error,
    mutate,
  };
};

export const overiRecept = (session: Session, id: number) => {
  return fetcher(session, URL + `/sestre/recepti/overa/${id}`, {
    method: "PUT",
  });
};

export const getRadniKalendar = (session: Session) => {
  const { data, error, mutate } = useSWR<[PregledResDTO]>(
    URL + `/pregled/radniKalendarZaSestru`,
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
