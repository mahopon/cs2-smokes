"use client";
import Link from "next/link";
import { signIn, signOut, useSession } from "next-auth/react";


function AuthButton() {
  const { data: session } = useSession();
  return (
    <div className="flex items-center space-x-4">
      {session ? (
        <>
          <span className="font-medium text-gray-300">
            {session?.user?.name}
          </span>
          <button
            onClick={() => signOut()}
            className="px-4 py-2 bg-red-700 text-white rounded hover:bg-red-800 transition duration-150"
          >
            Sign Out
          </button>
        </>
      ) : (
        <>
          <span className="text-gray-400">Not signed in</span>
          <button
            onClick={() => signIn()}
            className="px-4 py-2 bg-blue-700 text-white rounded hover:bg-blue-800 transition duration-150"
          >
            Sign In
          </button>
        </>
      )}
    </div>
  );
}


export default function NavMenu() {
  return (
    <div className="bg-gray-800 shadow-md fixed top-0 inset-x-0 z-10">
      <div className="container mx-auto flex justify-between items-center p-4">
        <Link
          href="/"
          className="text-xl font-semibold text-gray-200 hover:text-gray-100"
        >
          Home
        </Link>
        {/* <div className="flex space-x-4"> */}
        <Link href="/protected" className="text-gray-200 hover:text-gray-100">
          My Account
        </Link>
        <AuthButton />
        {/* </div> */}
      </div>
    </div>
  );
}