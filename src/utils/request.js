import axios from 'axios'

/**
 * 【指定 axios的 baseURL】
 * @type {*|string}
 */
let apiBaseUrl = '/service';
// 创建 axios 实例
const service = axios.create({
  baseURL: apiBaseUrl, // api base_url
  timeout: 90000 // 请求超时时间
})


export {
  service as axios
}
