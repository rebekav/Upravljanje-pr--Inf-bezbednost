import "next-auth";

declare module "next-auth" {
  /**   * Returned by `useSession`, `getSession` and received as a prop on the `SessionProvider` React Context   */
  interface Session {
    accessToken: string;
    user: {
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
      changePass: boolean;
      validiran: number;
    };
  }
}
