import { Session } from "next-auth";

export function hasRole(session: Session, role: string) {
  return (
    !!session &&
    !!session.user &&
    !!session.user.roles &&
    session.user.roles.indexOf(role) != -1
  );
}
