/** @type {import('next').NextConfig} */
const nextConfig = {
  experimental: {
    appDir: true,
  },
  env: {
    // DOMAIN: 'http://10.111.3.121:8080/ws-miseat',
    DOMAIN: 'http://localhost:8080/ws-miseat',
  },
};

module.exports = nextConfig;
