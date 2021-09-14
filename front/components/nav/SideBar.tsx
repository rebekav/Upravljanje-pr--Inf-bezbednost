import { VStack } from "@chakra-ui/layout";
import { Box } from "@chakra-ui/react";
import Link from "next/link";

interface SideBarProps {}

export function SideBar(props: SideBarProps) {
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
      <Box>
        <Link href="/app/korisnici">Korisnici</Link>
      </Box>
      <Box>
        <Link href="/app/klinike">Klinike</Link>
      </Box>
      <Box>Pregledi</Box>
      <Box>Izvestaj</Box>
      <Box> Termini</Box>
      <Box>Klinika</Box>
    </VStack>
  );
}
