module.exports = {
  // https://cli.vuejs.org/config/#devserver-proxy
  devServer: {
    port: 8081,
    proxy: {
      "/shopping-list": {
        target: "http://localhost:8080",
        ws: true,
        changeOrigin: true,
      },
    },
  },
};
