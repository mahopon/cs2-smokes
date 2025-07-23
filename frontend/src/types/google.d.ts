export {};

declare global {
  interface Window {
    google: typeof google;
  }

  namespace google.accounts.id {
    interface CredentialResponse {
      credential: string;
      select_by: string;
    }

    function initialize(options: {
      client_id: string;
      callback: (response: CredentialResponse) => void;
    }): void;

    function renderButton(parent: HTMLElement, options: object): void;

    function prompt(): void;
  }
}
