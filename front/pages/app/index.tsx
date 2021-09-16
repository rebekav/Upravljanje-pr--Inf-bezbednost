import { useDisclosure } from "@chakra-ui/hooks";
import type { GetServerSideProps, NextPage } from "next";
import { getSession, useSession } from "next-auth/client";
import { Router, useRouter } from "next/dist/client/router";
import { Dashboard } from "../../components/layout/Dashboard";
import { ChangePass } from "../../components/Modal/ChangePass";
import { changePass } from "../../util/api/korisnik";

interface HomeProps {}
const Home: NextPage<HomeProps> = (props) => {
  const [session, loading] = useSession();
  const router = useRouter();
  const { isOpen, onOpen, onClose } = useDisclosure({ defaultIsOpen: true });
  if (loading) return <div>Loading...</div>;
  if (!session) {
    router.push("/auth/prijava");
    return <div>Redircting</div>;
  }
  if (session?.user?.changePass)
    return (
      <Dashboard>
        <ChangePass
          isOpen={isOpen}
          onClose={onClose}
          onAction={(l, sl) => {
            return changePass(session, {
              oldPassword: sl,
              newPassword: l,
            });
          }}
        ></ChangePass>
      </Dashboard>
    );
  return <Dashboard></Dashboard>;
};

export default Home;
