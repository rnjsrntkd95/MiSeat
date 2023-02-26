import axios, { AxiosInstance } from 'axios';

const customAxios: AxiosInstance = axios.create({
  baseURL: `${process.env.DOMAIN}`, // 기본 서버 주소 입력
});

customAxios.defaults.headers.common['Authorization'] =
  'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJNaVNlYXQgSXNzdWVyIiwidGVhbUNvZGUiOjc3Nzc3NywiZXhwIjoxNzc1OTU2NTY2LCJ1c2VySWQiOiJra3MxMDIzIiwiaWF0IjoxNjc1OTU2NTY2fQ.NV4llGQS22QFCCQLTpNOrnIXi0xvqTe_d-_x_3nejIo';

export default customAxios;
