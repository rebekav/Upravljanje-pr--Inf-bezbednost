import { VStack } from "@chakra-ui/layout";
import { Box } from "@chakra-ui/react";
import { GetServerSideProps } from "next";
import { Session } from "next-auth";
import { getSession, useSession } from "next-auth/client";
import Link from "next/link";
import { hasRole } from "../../util/auth";

interface SideBarProps {}

export function SideBar(props: SideBarProps) {
  const [session, loading] = useSession();
  return (
    <VStack
      w="200px"
      alignItems="baseline"
      px="4"
      py="2"
      spacing="2"
      borderRightWidth="1px"
      borderRightColor="gray.300"
      height="full"
      minHeight="calc(100vh - 58px)"
    >
      {!loading && session && hasRole(session, "SUPER_ADMIN") && (
        <>
          <Box>
            <Link href="/app/korisnici">Korisnici</Link>
          </Box>
          <Box>
            <Link href="/app/klinike">Klinike</Link>
          </Box>
        </>
      )}
      {!loading && session && hasRole(session, "MEDICINSKA_SESTRA") && (
        <>
          <Box>
            <Link href="/app/sestra/pacijenti">Pacijenti</Link>
          </Box>
          <Box>
            <Link href="/app/sestra/recepti">Recepti</Link>
          </Box>
        </>
      )}
    </VStack>
  );
}
