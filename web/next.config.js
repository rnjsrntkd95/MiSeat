/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: false,
  env: {
    DOMAIN: 'http://localhost:8080',
  },
};

module.exports = nextConfig;
