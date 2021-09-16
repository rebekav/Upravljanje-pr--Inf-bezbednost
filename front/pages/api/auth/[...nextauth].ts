import NextAuth from "next-auth";
import Providers from "next-auth/providers";
const URL = "http://localhost:8080";

export default NextAuth({
  // Configure one or more authentication providers
  providers: [
    Providers.Credentials({
      // The name to display on the sign in form (e.g. 'Sign in with...')
      name: "Credentials",
      // The credentials is used to generate a suitable form on the sign in page.
      // You can specify whatever fields you are expecting to be submitted.
      // e.g. domain, username, password, 2FA token, etc.
      credentials: {
        username: { label: "Username", type: "text", placeholder: "jsmith" },
        password: { label: "Password", type: "password" },
      },
      async authorize(credentials) {
        // Add logic here to look up the user from the credentials supplied
        const user = await (
          await fetch(URL + "/login", {
            method: "POST",
            body: JSON.stringify({
              username: credentials.username,
              password: credentials.password,
            }),
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json",
            },
            credentials: "include",
          })
        ).json();
        if (user.poruka) throw new Error(user.poruka);
        if (!user.error) {
          // Any object returned will be saved in `user` property of the JWT
          return {
            token: user.token,
          };
        } else {
          // If you return null or false then the credentials will be rejected
          return null;
          // You can also Reject this callback with an Error or with a URL:
          // throw new Error('error message') // Redirect to error page
          // throw '/path/to/redirect'        // Redirect to a URL
        }
      },
    }),
  ],
  pages: {
    signIn: "/auth/prijava",
  },
  session: {
    // Use JSON Web Tokens for session instead of database sessions.
    // This option can be used with or without a database for users/accounts.
    // Note: `jwt` is automatically set to `true` if no database is specified.
    jwt: true,

    // Seconds - How long until an idle session expires and is no longer valid.
    maxAge: 30 * 24 * 60 * 60, // 30 days

    // Seconds - Throttle how frequently to write to database to extend a session.
    // Use it to limit write operations. Set to 0 to always update the database.
    // Note: This option is ignored if using JSON Web Tokens
    updateAge: 24 * 60 * 60, // 24 hours
  },
  secret: "3Fu9uzhUwU5wbREnYjG6Ju3D8J4YKhqE",
  jwt: {
    signingKey: process.env.JWT_SIGNING_PRIVATE_KEY,
    encryptionKey: process.env.JWT_ENCRYPTION_PRIVATE_KEY,
    encryption: true,
  },
  callbacks: {
    /**
     * @param  {object}  token     Decrypted JSON Web Token
     * @param  {object}  user      User object      (only available on sign in)
     * @param  {object}  account   Provider account (only available on sign in)
     * @param  {object}  profile   Provider profile (only available on sign in)
     * @param  {boolean} isNewUser True if new user (only available on sign in)
     * @return {object}            JSON Web Token that will be saved
     */
    async jwt(token, user, account, profile, isNewUser) {
      // Add access_token to the token right after signin
      if (user?.token) {
        return {
          accessToken: user.token,
        };
      }
      return token;
    },
    async session(session, token) {
      if (!session.accessToken && token?.accessToken) {
        const data = await (
          await fetch(URL + "/me", {
            headers: {
              Authorization: "Bearer " + token.accessToken,
              Accept: "application/json",
            },
          })
        ).json();
        return {
          accessToken: token.accessToken,
          user: data,
        };
      } else {
        return session;
      }
    },
  },
  events: {
    async signIn(message) {
      console.log("signIn", message);
    },
    async signOut(message: any) {
      console.log("signOut", message);
    },
    async createUser(message) {
      console.log("createUser", message);
    },
    async linkAccount(message) {
      console.log("linkAccount", message);
    },
    async session(message: any) {
      console.log("session", message);
    },
    async error(message) {
      console.log("error", message);
    },
  },
});
