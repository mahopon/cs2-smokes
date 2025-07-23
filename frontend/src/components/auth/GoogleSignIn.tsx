'use client';
import { useEffect } from 'react';

type Props = {
  onLoginSuccess?: (jwt: string) => void;
};

const GoogleSignInButton: React.FC<Props> = ({ onLoginSuccess }) => {
  useEffect(() => {
    // Load Google script
    const script = document.createElement('script');
    script.src = 'https://accounts.google.com/gsi/client';
    script.async = true;
    script.defer = true;
    script.onload = () => {
      /* global google */
      if (window.google) {
        window.google.accounts.id.initialize({
          client_id: process.env.NEXT_PUBLIC_GOOGLE_CLIENT_ID!,
          callback: async (response: google.accounts.id.CredentialResponse) => {
            const idToken = response.credential;
            // Send to backend
            const res = await fetch('/api/auth/handle-google-login', {
              method: 'POST',
              headers: { 'Content-Type': 'application/json' },
              body: JSON.stringify({ idToken }),
            });

            if (res.ok) {
              const data = await res.json();
              onLoginSuccess?.(data.id);
            } else {
              console.error('Login failed');
            }
          },
        });

        window.google.accounts.id.renderButton(
          document.getElementById('google-signin-button')!,
          { theme: 'outline', size: 'large' }
        );
      }
    };
    document.body.appendChild(script);
  }, []);

  return <div id="google-signin-button"></div>;
};

export default GoogleSignInButton;
