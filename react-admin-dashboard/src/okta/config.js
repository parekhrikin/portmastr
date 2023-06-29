const CLIENT_ID = process.env.REACT_APP_CLIENT_ID || '0oa9ze95msMAy8Zpx5d7';
const ISSUER = process.env.REACT_APP_ISSUER || 'https://dev-35309972.okta.com/oauth2/default';
// const ISSUER = process.env.REACT_APP_ISSUER || `${window.location.origin}`;
const REDIRECT_URI = `${window.location.origin}/login/callback`;

export default {
  oidc: {
    clientId: CLIENT_ID,
    issuer: ISSUER,
    redirectUri: REDIRECT_URI,
    scopes: ['openid', 'profile', 'email'],
    pkce: true,
    responseType: ['id_token', 'token']
  }
};