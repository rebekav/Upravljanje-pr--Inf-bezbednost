import { HStack, VStack } from "@chakra-ui/layout";
import {
  Box,
  Menu,
  MenuItem,
  MenuButton,
  MenuList,
  MenuIcon,
  MenuDivider,
  Button,
} from "@chakra-ui/react";
import { ChevronDownIcon } from "@chakra-ui/icons";
import { getSession, signOut, useSession } from "next-auth/client";
import Link from "next/link";
import { GetServerSideProps } from "next";
import { Session } from "next-auth";
import { useRouter } from "next/dist/client/router";

interface NavBarProps {
  session: Session;
}

export function NavBar(props: NavBarProps) {
  const [session, loading] = useSession();
  const router = useRouter();
  return (
    <HStack
      align="stretch"
      alignItems="center"
      justifyContent="space-between"
      px="4"
      py="2"
      borderBottomWidth="1px"
      borderBottomColor="gray.300"
    >
      <Box fontSize="2xl" color="cyan.500" fontWeight="bold">
        <Link href="/app">KlinickiCentar</Link>
      </Box>
      <Box>
        <HStack>
          <VStack spacing="-0.5" alignItems="baseline">
            <Box>{session?.user?.email}</Box>
            <Box textColor="gray.500" fontSize="xs">
              {session?.user?.roles?.join(", ")}
            </Box>
          </VStack>
          <Menu>
            <MenuButton px="2">
              <ChevronDownIcon fontSize="xl" />
            </MenuButton>
            <MenuList>
              <MenuItem>Podesavanja</MenuItem>
              <MenuDivider />
              <MenuItem
                onClick={() => {
                  signOut();
                  router.push("/auth/prijava");
                }}
              >
                Odjavi se
              </MenuItem>
            </MenuList>
          </Menu>
        </HStack>
      </Box>
    </HStack>
  );
}

export const getServerSideProps: GetServerSideProps = async (ctx) => {
  return { props: { session: await getSession(ctx) } };
};
