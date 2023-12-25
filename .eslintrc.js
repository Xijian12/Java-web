module.exports = {
  root: true,
  env: {
    node: true
  },
  'extends': [
    'plugin:vue/vue3-essential',
    'eslint:recommended'
  ],
  parserOptions: {
    parser: 'babel-eslint'
  },
  rules: {
    'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'space-before-function-paren': 0,
    "no-unused-vars": 0,
    "quotes": 0,
    "indent": [0, 2],
    "eqeqeq": [0],
    "prefer-const": 0,
    "no-new-object": 0,
    "no-useless-escape": 0,
    "no-undef": 0,
    "standard/no-callback-literal": 0,
    "promise/param-names": 0,
    "no-array-constructor": 0,
    "camelcase": 0,
    "curly": 0
  }
}
