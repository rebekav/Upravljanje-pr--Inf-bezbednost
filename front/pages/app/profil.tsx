import { Box, Text } from "@chakra-ui/layout";
import type { NextPage } from "next";
import { useSession } from "next-auth/client";
import { useRouter } from "next/dist/client/router";
import { Dashboard } from "../../components/layout/Dashboard";

const Home: NextPage = (props) => {
  const [session, loading] = useSession();
  if (loading) return <div>Loading...</div>;
  return (
    <Dashboard>
      {session && (
        <Box>
          <Text>Identifikator: {session.user.identifikator}</Text>
          <Text>Ime: {session.user.ime}</Text>
          <Text>Prezime: {session.user.prezime}</Text>
          <Text>Email: {session.user.email}</Text>
          <Text>Uloge: {session.user.roles.join(", ")}</Text>
          {session.user.telefon && (
            <Text>Telefont: {session.user.telefon}</Text>
          )}
          {session.user.adresa && <Text>Adresa: {session.user.adresa}</Text>}
        </Box>
      )}
    </Dashboard>
  );
};

export default Home;
