import { HStack, VStack } from "@chakra-ui/layout";
import { ReactNode } from "react";
import { NavBar } from "../nav/NavBar";
import { SideBar } from "../nav/SideBar";
import { Box } from "@chakra-ui/react";
import { GetServerSideProps } from "next";
import { getSession } from "next-auth/client";
import { Session } from "next-auth";

interface DashboardProps {
  children?: ReactNode;
}

export function Dashboard(props: DashboardProps) {
  return (
    <VStack align="stretch" spacing="unset" minHeight="100vh">
      <NavBar />
      <HStack alignItems="unset" minHeight="calc(100vh - 58px)" spacing="unset">
        <SideBar />
        <Box px="4" py="2" w="full">
          {props.children}
        </Box>
      </HStack>
    </VStack>
  );
}
