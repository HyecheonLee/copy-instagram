module.exports = {
  reactStrictMode: true,
  async rewrites() {
    return [
      {
        source: '/:path*',
        destination: 'http://localhost:8080/api/v1/:path*',
      },
    ]
  },
}
