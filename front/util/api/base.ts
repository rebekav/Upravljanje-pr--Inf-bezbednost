import { Session } from "next-auth";

export const URL = "http://localhost:8080";
export const fetcher = (session: Session, url: string, config?: RequestInit) =>
  fetch(url, {
    headers: {
      Authorization: "Bearer " + session.accessToken,
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    ...config,
  }).then((res) => {
    return res.json();
  });
