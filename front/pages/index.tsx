import type { NextPage } from "next";
import { useSession } from "next-auth/client";
import { useRouter } from "next/dist/client/router";
import { Dashboard } from "../components/layout/Dashboard";

const Home: NextPage = (props) => {
  const [session, loading] = useSession();
  const router = useRouter();
  if (loading) return <div>Loading...</div>;
  if (!session && !loading) {
    router.push("/auth/prijava");
    return <div>Redirecting...</div>;
  }

  return (
    <Dashboard>
      <div>Home</div>
    </Dashboard>
  );
};

export default Home;
