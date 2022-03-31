var merge = require('webpack-merge')
var prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  FRONTEND_HOST: '"http://127.0.0.1"',
  FRONTEND_PORT: '"8087"',
  BACKEND_HOST: '"http://127.0.0.1"',
  BACKEND_PORT: '"8074"'
})
