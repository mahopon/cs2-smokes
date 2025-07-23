"use client";

import GoogleSignInButton from '@/components/auth//GoogleSignIn';


export default function Home() {
  const handleLogin = (id: string) => {
    console.log('Logged in with JWT:', id);
  };

  return (
    <div className="min-h-screen flex flex-col items-center justify-center">
      <h1 className="text-2xl mb-4">Login</h1>
      <GoogleSignInButton onLoginSuccess={handleLogin} />
    </div>
  );
}